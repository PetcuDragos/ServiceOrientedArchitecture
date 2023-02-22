import React, { useState } from "react";
import "../App.css";

function Login(props) {

    let credentialsHandler = props.credentialsHandler;
    const [password, setPassword] = useState("");
    const [username, setUsername] = useState("");

    const usernameHandler = (event) => {
        setUsername(event.target.value);
    }
    const passwordHandler = (event) => {
        setPassword(event.target.value);
    }

    const loginHandler = () => {
        credentialsHandler(username, password);
    }

    return (
    <div>
        <div>
            Username: <input type="text" onChange={usernameHandler}></input>
        </div>
        <div>
            Password: <input type="password" onChange={passwordHandler}></input>
        </div>
        <div className="hotels-button" onClick={() => loginHandler()}>Login</div>
    </div>);
}

export default Login;