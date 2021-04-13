import React from 'react';
import './App.css';
import BasicRoute from "./Router";
import SockJsClient from 'react-stomp';
import {ChatroomView} from "./view/ChatroomView";

class App extends React.Component {


    render() {
        return (
            <div>
                <BasicRoute/>
            </div>
        );
    }
}


export default App;
