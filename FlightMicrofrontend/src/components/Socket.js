import React, { useEffect, useState } from 'react';
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

const url = "http://localhost:15674/ws";
let _stompClient = null;

const Socket = () => {
  //const [message, newMessage] = useState();

  const client = new Client({
    brokerURL: 'ws://localhost:15674/ws/topic/notification',
    connectHeaders: {
      login: 'guest',
      passcode: 'guest',
    },
    debug: function (str) {
      console.log(str);
    },
    reconnectDelay: 10000,
    heartbeatIncoming: 30000,
    heartbeatOutgoing: 30000,
  });
  
  // Fallback code
  if (typeof WebSocket !== 'function') {
    // For SockJS you need to set a factory that creates a new SockJS instance
    // to be used for each (re)connect
    client.webSocketFactory = function () {
      // Note that the URL is different from the WebSocket URL
      return new SockJS('http://localhost:15674/ws');
    };
  }
  
  client.onConnect = function (frame) {
    console.log("connect");
    // Do something, all subscribes must be done is this callback
    // This is needed because this will be executed after a (re)connect
  };
  
  client.onStompError = function (frame) {
    // Will be invoked in case of error encountered at Broker
    // Bad login/passcode typically will cause an error
    // Complaint brokers will set `message` header with a brief message. Body may contain details.
    // Compliant brokers will terminate the connection after any error
    console.log('Broker reported error: ' + frame.headers['message']);
    console.log('Additional details: ' + frame.body);
  };
  
  useEffect(() => {
  client.activate();
  },[]);
  
};

export default Socket;

    







//     const [message, setMessage] = useState('You server message here.');

//     let onConnected = () => {
//         console.log("Connected!!")
//     }

//     let onMessageReceived = (msg) => {
//         setMessage(msg.message);
//     }

//     return (
//         <div className='notification-container'>
//             <SockJsClient
//                 url={SOCKET_URL}
//                 topics={['/topic/message']}
//                 onConnect={onConnected}
//                 onDisconnect={console.log("Disconnected!")}
//                 onMessage={msg => onMessageReceived(msg)}
//                 debug={false}
//             />
//             <div>{message}</div>
//         </div>
//     );
// }

// export default Socket;