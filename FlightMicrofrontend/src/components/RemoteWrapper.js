import React, { useEffect, useState } from "react";
import LoadingSpinner from "./LoadingSpinner";
const RemoteApp = React.lazy(() => import("Remote/App"));
function RemoteWrapper(props) {
    const [wait, setWait] = useState(true);

    useEffect(() => {
        const timer = setTimeout(() => {
            setWait(false);
        }, 1000);
        return () => clearTimeout(timer);

    }, [setWait]);
    return (
        <div>
            {wait ? <LoadingSpinner /> : <RemoteApp flightId={props.flightId} />}
        </div>
    );
}

export default RemoteWrapper;