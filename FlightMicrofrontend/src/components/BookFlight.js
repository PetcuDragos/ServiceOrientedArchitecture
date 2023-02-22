import React from "react";


function BookFlight(props) {
    let tokenId = props.tokenId;
    const book = (flightId) => {
        let url = "http://localhost:8080/flight/" + flightId;
        fetch(url, { headers: { 'Authorization': tokenId } })
            .then(res => res.json())
            .then(
                (result) => {
                    console.log("received success");
                },
                (error) => {
                    console.log("Error booking flights " + error);
                }
            )
    }

    return <div class="flight-resources">
        <div className="hotels-button" role="button" onClick={() => book(props.flightId)}> Book flight </div>
    </div >
}

export default BookFlight;