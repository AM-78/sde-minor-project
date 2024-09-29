import { axiosInstance, getToken } from "./axiosSetup";

export const loginUser = async (username, password) => {
    try {
        const response = await axiosInstance.post("/auth/login", {
            username,
            password,
        });
        console.log(response.data);
        return response.data;
    } catch (error) {
        console.log(error);
        throw new Error(error.response?.data?.message || "Login failed!");
    }
};

export const registerUser = async (username, password) => {
    try {
        const response = await axiosInstance.post("/auth/register", {
            username,
            password,
        });
        console.log(response.data);
        return response.data;
    } catch (error) {
        console.log(error);
        throw new Error(
            error.response?.data?.message || "Registration failed!"
        );
    }
};

export const searchUser = async (username) => {
    const token = getToken();
    if (!token) {
        throw new Error("No token found. Please log in.");
    } else {
        console.log(token);
    }
    try {
        const response = await axiosInstance.get("/auth/search-by-username", {
            headers: {
                Authorization: `Bearer ${token}`,
            },
            params: {
                username,
            },
        });
        console.log(response.data);
        return response.data;
    } catch (error) {
        console.log(error);
        throw new Error(error.response?.data?.message || "Search failed!");
    }
};

