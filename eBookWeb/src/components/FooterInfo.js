import React from 'react'
import {NavLink} from "react-router-dom";
import config from "config";
import {getBooks, getCount} from "../services/bookService";


export class FooterInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            count : 0
        };
    }

    componentWillMount() {
        const callback = (data) => {
            console.log(data);
            this.setState({count : data});
        };
        getCount({},callback)
    }

    render() {
        return (
            <div style={{
                marginTop:"30px",
                width:"100%",
                marginBottom:"40px",
            }}>
                <img alt="ad" src={require("../assets/footer.png")} style={{width:"100%",margin:"auto"}}/>
                <div style={{paddingTop:"15px",width:"320px",margin:"auto",display:"flex",justifyContent:"space-around"}}>
                    <NavLink to="/">公司简介</NavLink>
                    <text>|</text>
                    <NavLink to="/">诚聘英才</NavLink>
                    <text>|</text>
                    <NavLink to="/">网站联盟</NavLink>
                    <text>|</text>
                    <text>当前访问量：{this.state.count}</text>
                </div>
                <div style={{paddingTop:"15px",margin:"auto",display:"flex",justifyContent:"center"}}>
                    <text>Copyright © 2019 - 2020 叮当电子书城. All Rights Reserved</text>
                </div>
            </div>
        );
    }

}
