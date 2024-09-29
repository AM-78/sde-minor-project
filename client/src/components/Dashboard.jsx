import { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import DocumentTile from "./DocumentTile";
import { createDocument, myDocuments, sharedDocuments} from "../service/document.js";
import "../styles/Dashboard.css";


const Dashboard = () => {
    const location = useLocation();
    const { username } = location.state || {};
    const navigate = useNavigate();
    const [refresh, setRefresh] = useState(0)
    const [documents, setDocuments] = useState([]);
    const [sharedDocs, setsharedDocs] = useState([]);

    async function fetchDocs() {
        try {
            let docs1 = await myDocuments();
            setDocuments(docs1)

            let docs2 = await sharedDocuments();
            console.log(docs2)
            setsharedDocs(docs2)
        } catch (e) {
            console.log(e)
        }
    }

    useEffect(() => {
        fetchDocs()
    }, [refresh]);

    const handleCreateDocument = async () => {
        let doc = await createDocument();
        console.log(doc)
        setRefresh(r => r+1)
    };

    const handleLogout = () => {
        localStorage.removeItem("token");
        navigate("/");
    };

    return (
        <div className="dashboard-container">
            <div className="header">
                <h2>{username ? `Hello, ${username}!` : "Hello!"}</h2>
                <div>
                    <button onClick={handleLogout} className="logout-btn">
                        Logout
                    </button>
                </div>
            </div>
            <button onClick={handleCreateDocument} className="create-btn">
                Create a Document
            </button>
            <h3>Your Documents</h3>
            <div className="document-grid">
                { documents.length==0 && <h3>No Documents Available</h3> }
                {documents.map((doc) => (
                    <DocumentTile key={doc.id} document={doc} />
                ))}
            </div>
            <div style={{marginTop: "30px"}}></div>
            <h3>Documents shared with you</h3>
            <div className="document-grid">
                { sharedDocs.length==0 && <h3>No Documents Available</h3> }
                {sharedDocs.map((doc) => (
                    <DocumentTile key={doc.id} document={doc} />
                ))}
            </div>
        </div>
    );
};

export default Dashboard;
