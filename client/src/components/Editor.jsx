import { useEffect, useRef, useState } from "react";
import * as Y from "yjs";
import Quill from "quill";
import { Base64 } from "js-base64";
import { QuillBinding } from "y-quill";
import { readFromDoc, writeToDoc } from "../service/readWrite";
import "quill/dist/quill.snow.css";
import "../styles/Editor.css";


const Editor = ({ docId }) => {
    const [editor, setEditor] = useState(null);
    const ydoc = useRef(new Y.Doc());
    const yText = useRef(ydoc.current.getText("quill"));
    const updateQueueUserTyping = useRef([]);
    const updateQueueNetwork = useRef([]);
    const [version, setVersion] = useState(0);

    const sendUpdatesToBackend = async () => {
        if (updateQueueUserTyping.current.length > 0) {
            // move user data to network quueue
            // empty user queue
            updateQueueNetwork.current = [
                ...updateQueueNetwork.current,
                ...updateQueueUserTyping.current,
            ];
            updateQueueUserTyping.current = [];
        }

        if (updateQueueNetwork.current.length > 0) {
            try {
                // try to send network queue to the server
                // if sent successfully, empty the network queue
                const response = await writeToDoc(
                    docId,
                    updateQueueNetwork.current
                );
                console.log(response);
                updateQueueNetwork.current = [];
            } catch (error) {
                console.error("Error sending updates to backend:", error);
            }
        }
    };

    const fetchUpdatesFromBackend = async () => {
        let resp = await readFromDoc(docId, version);
        resp = resp.map((r) => {
            return JSON.parse(r.content);
        });
        console.log(resp);

        for (let u of resp.flat()) {
            Y.applyUpdate(ydoc.current, Base64.toUint8Array(u));
        }
    };

    useEffect(() => {
        const intervalId = setInterval(() => {
            sendUpdatesToBackend();
            fetchUpdatesFromBackend();
        }, 1000);
        return () => clearInterval(intervalId);
    }, []);

    useEffect(() => {
        const toolbarOptions = [
            ["bold", "italic", "underline", "strike"],
            ["code-block"],
            [{ list: "ordered" }, { list: "bullet" }, { list: "check" }],
            [{ header: [1, 2, false] }],
            [{ font: [] }],
            [{ align: [] }],
        ];

        const quill = new Quill("#editor", {
            theme: "snow",
            modules: {
                toolbar: toolbarOptions,
            },
        });

        const binding = new QuillBinding(yText.current, quill);
        setEditor(quill);

        quill.setContents(yText.current.toJSON());

        ydoc.current.on("update", function (update, origin, doc, tr) {
            // console.log(update, origin);
            if (origin)
                updateQueueUserTyping.current.push(
                    Base64.fromUint8Array(update)
                );
        });

        return () => {
            binding.destroy();
            quill.disable();
        };
    }, []);

    return (
        <div className="editor">
            <div id="editor"></div>
        </div>
    );
};

export default Editor;
