import React from "react";
import RemoteWrapper from "./RemoteWrapper";

function Hotels(props)
{
    return (
    <div>
        {/* cartof */}
        <RemoteWrapper flightId={props.flightId}></RemoteWrapper>
    </div>)
}

export default Hotels;