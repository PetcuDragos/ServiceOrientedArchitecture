import React from "react";
import { useState, useEffect } from "react";

function Attraction(props) {
    let tokenId = props.tokenId;
    const [attractions, setAttractions] = useState("");
    useEffect(() => {
        let url = "http://localhost:8080/flight/" + props.flightId + "/knownfor";
        fetch(url, { headers: { 'Authorization': tokenId } })
            .then((response) => {
                return response.text();
            })
            .then((result) => {
                    console.log(result);
                    setAttractions(result);
                },
                (error) => {
                    console.log("Error fetching the flights " + error);
                }
            )
    }, [setAttractions]);

    let attractionList = attractions.split(",");
    return (
        <ul>
            {attractionList.map(attraction => (
                <li>{attraction}</li>
            ))}
        </ul>);
}

export default Attraction;