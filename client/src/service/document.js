import { axiosInstance, getToken } from "./axiosSetup";

export const createDocument = async () => {
    const token = getToken();
    if (!token) {
        throw new Error("No token found. Please log in.");
    } else {
        console.log(token);
    }

    try {
        const response = await axiosInstance.post(
            "/document/create-document",
            {},
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            }
        );
        return response.data;
    } catch (error) {
        console.error("Error creating document:", error);
        throw error;
    }
};

export const myDocuments = async () => {
    const token = getToken();
    if (!token) {
        throw new Error("No token found. Please log in.");
    } else {
        console.log(token);
    }

    try {
        const response = await axiosInstance.get("/document/my-documents", {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });
        return response.data;
    } catch (error) {
        console.error("Error in fetching documents:", error);
        throw error;
    }
};

export const sharedDocuments = async () => {
    const token = getToken();
    if (!token) {
        throw new Error("No token found. Please log in.");
    } else {
        console.log(token);
    }

    try {
        const response = await axiosInstance.get("/document/shared-documents", {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });
        return response.data;
    } catch (error) {
        console.error("Error in fetching documents:", error);
        throw error;
    }
};

export const editTitle = async (docId, title) => {
    const token = getToken();
    if (!token) {
        throw new Error("No token found. Please log in.");
    } else {
        console.log(token);
    }

    try {
        const response = await axiosInstance.put(
            "/document/edit-title",
            {
                docId,
                title,
            },
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            }
        );
        return response.data;
    } catch (error) {
        console.error("Error editing title:", error);
        throw error;
    }
};

export const addUsers = async (docId, userPermMap) => {
    const token = getToken();
    if (!token) {
        throw new Error("No token found. Please log in.");
    } else {
        console.log(token);
    }

    try {
        const response = await axiosInstance.post(
            `/document/add-users/${docId}`,
            userPermMap,
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            }
        );
        return response.data;
    } catch (error) {
        console.error("Error sharing to users:", error);
        throw error;
    }
};
