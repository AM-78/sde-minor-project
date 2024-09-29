import { useNavigate } from "react-router-dom";
import "../styles/DocumentTile.css";

const DocumentTile = ({ document }) => {
    const navigate = useNavigate();

    const handleClick = () => {
        navigate(`/document/${document.id}`, { state: document });
    };

    return (
        <div className="document-tile" onClick={handleClick}>
            <h3>{document.title}</h3>
            <p>Created: {new Date(document.creationTime).toLocaleString()}</p>
        </div>
    );
};

export default DocumentTile;
