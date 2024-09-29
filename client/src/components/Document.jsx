import { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Editor from "./Editor";
import ShareModal from "./ShareModal";
import { addUsers, editTitle } from "../service/document";
import "../styles/Document.css";

const Document = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const { state: document } = location;

    const [isModalOpen, setModalOpen] = useState(false);
    const [title, setTitle] = useState(document.title);
    const [isEditing, setIsEditing] = useState(false);

    if (!document) {
        return <p>No document details available</p>;
    }

    const goBack = () => {
        navigate("/dashboard");
    };

    const handleTitleChange = async (e) => {
        setTitle(e.target.value);
        let res = await editTitle(document.id, e.target.value);
        console.log(res);
    };

    const handleTitleClick = () => {
        setIsEditing(true);
    };

    const handleShare = async (sharedUsers) => {
        let userPermMap = {};

        for (let usr of sharedUsers) {
            userPermMap[usr.username] = usr.permission;
        }
        console.log(userPermMap);
        let resp = await addUsers(document.id, userPermMap);
        console.log(resp);
    };

    return (
        <div className="document-page">
            <div className="document-header">
                {/* Editable title */}
                {isEditing ? (
                    <input
                        type="text"
                        value={title}
                        onChange={handleTitleChange}
                        className="editable-title"
                        autoFocus
                    />
                ) : (
                    <h2 className="title" onClick={handleTitleClick}>
                        {title}
                    </h2>
                )}

                <p>
                    <strong>Created:</strong>{" "}
                    {new Date(document.creationTime).toLocaleString()}
                </p>
                <div className="document-buttons">
                    <button onClick={goBack}>Back to Dashboard</button>
                    <button onClick={() => setModalOpen(true)}>
                        Share Document
                    </button>
                </div>
            </div>
            <Editor docId={document.id} />
            <ShareModal
                isOpen={isModalOpen}
                onClose={() => setModalOpen(false)}
                users={[]}
                onShare={handleShare}
            />
        </div>
    );
};

export default Document;
