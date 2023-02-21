import React, { useEffect, useState } from "react";
import Hotels from "./Hotels";
import "./ListFlights.css";

function ListFlights() {
    const [flights, setFlights] = useState([]);
    const [flightsToggled, setFlightsToggled] = useState([]);
    const [hotelsToggled, setHotelsToggled] = useState([]);

    useEffect(() => {
        let url = "http://localhost:8080/flights";
        fetch(url)
            .then(res => res.json())
            .then(
                (result) => {
                    setFlights(result);
                },
                (error) => {
                    console.log("Error fetching the flights " + error);
                }
            )
    }, [setFlights]);

    const toggleFlight = (id) => {
        if (flightsToggled.includes(id)) {
            setFlightsToggled(flightsToggled.filter(sid => sid !== id))
        } else {
            let newFlightsToggled = [...flightsToggled]
            newFlightsToggled.push(id)
            setFlightsToggled(newFlightsToggled)
        }
    }

    const toggleHotels = (id) => {
        if (hotelsToggled.includes(id)) {
            setHotelsToggled(hotelsToggled.filter(sid => sid !== id))
        } else {
            let newHotelsToggled = [...hotelsToggled]
            newHotelsToggled.push(id)
            setHotelsToggled(newHotelsToggled)
        }
    }

    return (<div className="flight-container">
        {flights.map(flight => {
            return (
                <div className="flight-trip">
                    <div className="flight-title" onClick={() => toggleFlight(flight.id)}>
                        {flight.origin} - {flight.destination}
                    </div>
                    {flightsToggled.includes(flight.id) ? (
                        <div className="flight-content">
                            <div className="flight-duration"><img src="duration.svg" /> {flight.durationInMinutes} minutes.</div>
                            <div className="flight-price"><img src="price.svg" /> {flight.price} euro.</div>
                            <div className="flight-placesLeft"><img src="places-left.svg" />{flight.placesLeft} places left.</div>

                            <div class="flight-resources">
                                <button className="hotels-button" role="button" onClick={() => toggleHotels(flight.id)}> See hotels around </button>
                                {hotelsToggled.includes(flight.id) ? (
                                    (<Hotels flightId={flight.id} />)
                                ) : null}
                            </div >
                        </div >) : null}
                </div >
            )
        })}
    </div >)
}

export default ListFlights;