import React from "react";
import RemoteWrapper from "./RemoteWrapper";

function Hotels(props)
{
    return (
    <div>
        <RemoteWrapper flightId={props.flightId} tokenId={props.tokenId}></RemoteWrapper>
    </div>)
}

export default Hotels;