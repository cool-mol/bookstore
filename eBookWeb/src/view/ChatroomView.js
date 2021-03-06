import React from 'react';
import {Layout, Carousel, Button} from 'antd'
import {HeaderInfo} from "../components/HeaderInfo";
import {SideBar} from "../components/SideBar";
import '../css/bookDetail.css'
import {withRouter} from "react-router-dom";
import {history} from '../utils/history';
import {Orders} from "../components/Orders";
import {Cart} from "../components/Cart";
import {TopNavigateBar} from "../components/TopNavigateBar";
import {BookCarousel} from "../components/BookCarousel";
import {BookList} from "../components/BookList";
import {FooterInfo} from "../components/FooterInfo";
import {Chatroom} from "../components/Chatroom";

const {Header, Content, Footer} = Layout;

export class ChatroomView extends React.Component {
    render() {
        return (
            <div className="content">
                <Chatroom />
            </div>
        );
    }
}

export default withRouter(ChatroomView);