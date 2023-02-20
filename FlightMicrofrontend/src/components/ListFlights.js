import React, { useEffect, useState } from "react";
import BookFlight from "./BookFlight";
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
                        <div class="flight-content">
                            <div class="flight-duration">{flight.durationInMinutes}</div>
                            <div class="flight-price">{flight.price}</div>
                            <div class="flight-placesLeft">{flight.placesLeft}</div>
                            <BookFlight/>

                            <div class="flight-resources">
                                {/* <div onClick="getHotelsForFlight(flight.id)">Get hotels for destination flight</div> */}
                                {/* <div * ngFor= "let hotel of getHotelsForFlightId(flight.id)" >
                {{ hotel.id }},{{ hotel.name }},{{ hotel.city }},{{ hotel.pricePerNight }},{{ hotel.placesLeft }}
                </div> */}
                            <div onClick={()=>toggleHotels(flight.id)}> See hotels around </div>
                                <Hotels flightId={flight.id}/>
                            </div >
                        </div >) : null}
                </div >
            )
        })}
    </div >)
}

export default ListFlights;