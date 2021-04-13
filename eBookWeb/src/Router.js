import React from 'react';
import {Router, Route, Switch, Redirect} from 'react-router-dom';
import PrivateRoute from './PrivateRoute'
import LoginView from './view/LoginView'
import {history} from "./utils/history";
import BookView from "./view/BookView";
import SignUpView from "./view/SignUpView";
import ChatroomView from "./view/ChatroomView";
import eBookStore from "./view/eBookStore";
import AdminStore from "./view/AdminStore";
import PrivateAdminRoute from "./PrivateAdminRoute";

class BasicRoute extends React.Component {

    constructor(props) {
        super(props);

        history.listen((location, action) => {
            // clear alert on location change
            console.log(location, action);
        });
    }

    render() {
        return (
            <Router history={history}>
                <Switch>
                    <PrivateRoute exact path="/" component={eBookStore}/>
                    <Route exact path="/login" component={LoginView}/>
                    <Route exact path="/signUp" component={SignUpView}/>
                    <PrivateAdminRoute exact path="/admin" component={AdminStore}/>
                    <PrivateRoute exact path="/bookDetails" component={BookView}/>
                    <Route exact path="/chatRoom" component={ChatroomView}/>
                    <Redirect from="/*" to="/"/>
                </Switch>
            </Router>
        )
    }
}

export default BasicRoute;
