import { axiosInstance, getToken } from "./axiosSetup";

export const writeToDoc = async (docId, updates) => {
    const token = getToken();
    if (!token) {
        throw new Error('No token found. Please log in.');
    }
    else {
        console.log(token)
    }
    console.log(updates)

    try {
        const response = await axiosInstance.post(
            `/writer/write/${docId}`,
            updates,
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            }
        );
        return response.data;
    } catch (error) {
        console.error('Error writing to document:', error);
        throw error;
    }
};

export const readFromDoc = async (docId, version) => {
    const token = getToken();
    if (!token) {
        throw new Error('No token found. Please log in.');
    }
    else {
        console.log(token)
    }

    try {
        const response = await axiosInstance.get(
            '/writer/read',
            {
                params: {
                    docId, version
                },
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            }
        );
        return response.data;
    } catch (error) {
        console.error('Error writing to document:', error);
        throw error;
    }
};
