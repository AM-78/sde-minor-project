import React, { useState } from 'react';
import { searchUser } from '../service/auth';
import '../styles/ShareModal.css';

const ShareModal = ({ isOpen, onClose, selectedUsers, onShare }) => {
    const [searchTerm, setSearchTerm] = useState('');
    const [foundUsers, setFoundUsers] = useState([]);
    const [selected, setSelected] = useState(selectedUsers || []);

    if (!isOpen) {
        return null;
    }

    const handleSearchChange = async (e) => {
        const term = e.target.value;
        setSearchTerm(term);

        if (term) {
            try {
                const users = await searchUser(term);
                setFoundUsers(users);
            } catch (error) {
                console.error('Failed to fetch users:', error);
            }
        } else {
            setFoundUsers([]);
        }
    };


    const handleAddUser = (user) => {
        if (!selected.find((u) => u.id === user.id)) {
            setSelected([...selected, { ...user, permission: '0' }]);
        }
    };


    const handleRemoveUser = (userId) => {
        setSelected(selected.filter((user) => user.id !== userId));
    };


    const handlePermissionChange = (userId, newPermission) => {
        setSelected(
            selected.map((user) =>
                user.id === userId ? { ...user, permission: newPermission } : user
            )
        );
    };

    const handleSubmit = () => {
        onShare(selected);
        onClose();
    };

    return (
        <div className="modal">
            <div className="modal-content">
                <h2>Share Document</h2>
                
                {/* Search bar for users */}
                <input
                    type="text"
                    placeholder="Search users..."
                    value={searchTerm}
                    onChange={handleSearchChange}
                />

                {/* Filtered users to add */}
                {searchTerm && foundUsers.length > 0 && (
                    <ul className="user-search-results">
                        {foundUsers.map((user) => (
                            <li key={user.id} onClick={() => handleAddUser(user)}>
                                {user.username}
                            </li>
                        ))}
                    </ul>
                )}

                {/* Selected users list */}
                <h3>Shared Users</h3>
                {selected.length > 0 ? (
                    <ul className="selected-users">
                        {selected.map((user) => (
                            <li key={user.id}>
                                {user.username}
                                {/* Permission dropdown */}
                                <select
                                    value={user.permission}
                                    onChange={(e) =>
                                        handlePermissionChange(user.id, e.target.value)
                                    }
                                >
                                    <option value="0">Read</option>
                                    <option value="1">Write</option>
                                </select>
                                <button onClick={() => handleRemoveUser(user.id)}>Remove</button>
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>No users added yet.</p>
                )}

                {/* Share and Close buttons */}
                <div className="modal-actions">
                    <button onClick={onClose}>Cancel</button>
                    <button onClick={handleSubmit}>Share</button>
                </div>
            </div>
        </div>
    );
};

export default ShareModal;