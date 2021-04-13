import React from 'react';
import {Layout, Typography, Menu, Breadcrumb,List, Button, Icon, Row, Col, message, Input} from 'antd';
import {getCart, addCartToOrder} from "../services/CartService";
import {CartItem} from "./CartItem";
import {history} from '../utils/history';
import {GiftOutlined} from '@ant-design/icons';
import MenuItem from "antd/es/menu/MenuItem";
import Fetch from "json-fetch";
import SockJsClient from "react-stomp";

const { TextArea } = Input;
const { Title } = Typography;
const { SubMenu } = Menu;
const { Header, Footer, Sider, Content } = Layout;
const user = JSON.parse(sessionStorage.getItem("user"));

export class Chatroom extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            clientConnected: false,
            messages: [],
            messageView: "",
            words: '',
            user: [],
            userView: "当前在线："
        };
        this.handleChange = this.handleChange.bind(this);
    }

    onConnect = () => {
        try {
            this.clientRef.sendMessage("/app/all", JSON.stringify({user: "系统消息", message: "欢迎 " + user.username + " 加入直播间！"}));
        } catch(e) {
        }
        this.setState({ clientConnected: true })
    };

    onDisconnect = () => {
        this.setState({ clientConnected: false })
    };

    useritem = (user) => {
        let s = "";
        for(let i = 0;i < user.length;i ++){
            s = s + user[i] + "\n";
        }
        return s;
    };

    remove = (userall, user) => {
        let i = userall.indexOf(user)
        userall.splice(i, 1)
        return userall
    }

    onMessageReceive = (msg, topic) => {
        console.log(msg)
        console.log(msg)
        // if(msg.user === "系统消息") {
        //     if((msg.message.split(' '))[0] === "欢迎"){
        //         this.setState(prevState => ({
        //             user: [...prevState.user, (msg.message.split(' '))[1]]
        //         }));
        //         this.setState(prevState => ({
        //             userView: "当前在线：\n" + this.useritem(this.state.user) + "\n"
        //         }));
        //     }
        //
        //     if((msg.message.split(' '))[0] === "用户"){
        //         console.log(this.state.user)
        //         console.log((msg.message.split(' '))[1])
        //         this.setState(prevState => ({
        //             user: this.remove(prevState.user, (msg.message.split(' '))[1])
        //         }));
        //         console.log(this.state.user)
        //         this.setState(prevState => ({
        //             userView: "当前在线：\n" + this.useritem(this.state.user) + "\n"
        //         }));
        //     }
        // }
        this.setState(prevState => ({
            userView: "当前在线：\n" + msg.userItems
        }));
        this.setState(prevState => ({
            messages: [...prevState.messages, msg]
        }));
        this.setState(prevState => ({
            messageView: prevState.messageView + msg.user + ": " + msg.message + "\n"
        }));
        // console.log(this.state.messages)
    };

    listener = (e) => {
        try {
            this.clientRef.sendMessage("/app/all", JSON.stringify({user: "系统消息", message: "用户 " + user.username + " 离开直播间！"}));
        } catch(e) {
        }
        this.setState({ clientConnected: false })
    }

    componentWillMount() {
        window.addEventListener('beforeunload', this.listener)
    }

    componentWillUnmount() {
        window.removeEventListener('beforeunload', this.listener)
        try {
            this.clientRef.sendMessage("/app/all", JSON.stringify({user: "系统消息", message: "用户 " + user.username + " 离开直播间！"}));
        } catch(e) {
        }
        this.setState({ clientConnected: false })
    }

    handleChange = (event)=> {
        this.setState({words: event.target.value});
        console.log(this.state.words)
    }

    sendMessage = (msg, selfMsg) => {
        try {
            this.clientRef.sendMessage("/app/all", selfMsg);
            console.log(123213)
            return true;
        } catch(e) {
            console.log(123)
            return false;
        }
    };

    render() {
        return (
            <div>
                <SockJsClient url='http://localhost:8080/chatroom' topics={['/topic/all']}
                              onMessage={this.onMessageReceive}
                              ref={(client) => { this.clientRef = client }}
                              onConnect={ this.onConnect }
                              onDisconnect={ this.onDisconnect }
                              debug={ false }/>
                <Layout>
                    <Header>好难</Header>
                    <Layout>
                        <Row>

                            <Col span={6} pull={1}>
                                <br />
                                <br />
                                <TextArea rows={10}
                                          value={this.state.userView}
                                />
                            </Col>
                            <Col span={18}>
                                <Title level={3}>
                                    小小聊天室
                                </Title>
                                <TextArea rows={10} value={this.state.messageView}/>
                                <br />
                                <br />
                                <TextArea rows={2}
                                          value={this.state.words}
                                          onChange={this.handleChange} allowClear />

                            </Col>
                        </Row>
                    </Layout>
                    <Footer>
                        <Col span={18}>请保持社区聊天规范！</Col>
                        <Button onClick={()=>{this.sendMessage('', JSON.stringify({user: user.username, message: this.state.words}))}}>发送消息</Button>
                    </Footer>
                </Layout>
            </div>
        )
    }
}
