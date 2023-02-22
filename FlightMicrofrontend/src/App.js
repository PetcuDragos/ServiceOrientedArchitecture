import React, { useState, useEffect } from "react";
import ListFlights from "./components/ListFlights";
import "./App.css";
import Login from "./components/Login";
import { Buffer } from 'buffer';
import Socket from "./components/Socket";

function App() {
  const [isConnected, setIsConnected] = useState(false);
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [tokenId, setTokenId] = useState("");
  const credentialsHandler = (username, password) => {
    let url = "http://localhost:8080/login";
    fetch(url, { headers: { 'Authorization': 'Basic ' + Buffer.from(username + ':' + password).toString('base64') } })
      .then((response) => {
        return response.text();
      })
      .then(
        (result) => {
          setTokenId('Basic ' + Buffer.from(username + ':' + password).toString('base64'));
          setUsername(username);
          setPassword(password);
          setIsConnected(true);
        },
        (error) => {
          console.log("Error fetching the flights " + error);
        }
      )
  }


  useEffect(() => {
    
  }, []);

  return (
    <div className="background-background">
      {isConnected ? (
        <div className="background-container">
          <div className="title-container">
            <div className="title-content">
              CHECK OUR FLIGHTS!
            </div>
          </div>
          <Socket></Socket>
          <ListFlights username={username} password={password} tokenId={tokenId} />
        </div>
      ) : <Login credentialsHandler={credentialsHandler} />}
    </div>)
};
export default App;