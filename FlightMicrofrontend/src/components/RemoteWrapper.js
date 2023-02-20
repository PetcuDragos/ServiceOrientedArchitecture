import React from "react";
import ErrorBoundary from "../ErrorBoundary";
const RemoteApp = React.lazy(() => import("Remote/App"));

function RemoteWrapper(props) {

    return (
        <div
            style={{
                border: "1px solid red",
                background: "white",
            }}
        >
            <ErrorBoundary>
                <RemoteApp flightId={props.flightId}/>
            </ErrorBoundary>
        </div>
    );
}

export default RemoteWrapper;