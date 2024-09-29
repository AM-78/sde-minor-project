import { useEffect, useState } from "react";
import { loginUser, registerUser } from "../service/auth";
import { useLocation, useNavigate } from "react-router-dom";
import "../styles/LandingPage.css";

const LandingPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [isRegistering, setIsRegistering] = useState(false);
    const navigate = useNavigate();
    const { state } = useLocation();

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (token) {
            navigate("/dashboard");
        }
    }, [navigate]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            if (isRegistering) {
                let data = await registerUser(username, password);
                if (data && data.username)
                    navigate("/", {
                        state: {
                            message:
                                "Registered Successfully! You can login now.",
                        },
                    });
                else
                    navigate("/", {
                        state: { message: "Some issue occured..." },
                    });
            } else {
                let token;
                token = await loginUser(username, password);
                localStorage.setItem("token", token);
                navigate("/dashboard", { state: { username: username } });
            }
        } catch (error) {
            console.error(error.message);
        }
    };

    return (
        <div className="landing-page">
            <h1>{isRegistering ? "Register" : "Login"}</h1>
            <div>{state && state.message}</div>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Username:</label>
                    <input
                        type="username"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <div className="form-buttons">
                    <button type="submit">
                        {isRegistering ? "Register" : "Login"}
                    </button>
                    <button
                        type="button"
                        onClick={() => setIsRegistering(!isRegistering)}
                    >
                        Switch to {isRegistering ? "Login" : "Register"}
                    </button>
                </div>
            </form>
        </div>
    );
};

export default LandingPage;
