import React from 'react';
import "./index.css"
import logo from "../../../assets/logo.png"

const Sidebar = () => {
    return (
        <div className={"sidebar"}>
            <img className={"logo"} src={logo} alt={"Crap Advisor"}/>
            <p>Тут будет что-нибудь...</p>
        </div>
    );
};

export default Sidebar;