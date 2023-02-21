import React from "react";
import ListFlights from "./components/ListFlights";
import "./App.css";

function App() {
  return (
    <div className="background-container">
      <div className="title-container">
        <div className="title-content">
          CHECK OUR FLIGHTS!
        </div>
      </div>
      <ListFlights></ListFlights>
    </div>)
}
export default App;