import React from 'react';
import {Map, Sidebar} from "../../components";
import "./index.css"

const MainPage = () => {
    return (
        <div className="mainContainer">
            <Sidebar/>
            <Map/>
        </div>
    );
};

export default MainPage;