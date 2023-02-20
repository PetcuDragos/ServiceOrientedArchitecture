// remote/src/App.js
import React, { useEffect, useState } from "react";

function App(props) {
  const [hotels, setHotels] = useState([]);
  useEffect(() => {
    let url = "http://localhost:8080/flight/" + props.flightId + "/hotels";
    fetch(url)
      .then(res => res.json())
      .then(
        (result) => {
          console.log(result);
          setHotels(result);
        },
        (error) => {
          console.log("Error fetching the hotels " + error);
        }
      )
  }, [setHotels]);
  // console.log(props.flightId);
  return (<div>
     {hotels.map(hotel => {
        return <div>
          {hotel.id},{hotel.name},{hotel.city},{hotel.pricePerNight},{hotel.placesLeft}
        </div>
      })}

  </div>);
};

export default App;