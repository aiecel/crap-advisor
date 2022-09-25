import React, {useState} from 'react';
import {Map, Sidebar} from "../../components";
import "./index.css"

const MainPage = () => {
    const [selectedRestroom, setSelectedRestroom] = useState(null)

    const selectRestroom = (restroomToSelect) => {
        if (selectedRestroom !== restroomToSelect) {
            setSelectedRestroom(restroomToSelect)
        }
    }

    return (
        <div className="mainContainer">
            <Sidebar selectedRestroom={selectedRestroom}/>
            <Map selectRestroom={selectRestroom}/>
        </div>
    );
};

export default MainPage;