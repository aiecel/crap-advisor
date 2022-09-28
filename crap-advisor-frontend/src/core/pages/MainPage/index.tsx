import React, {useState} from "react";
import {Map, Sidebar} from "../../components";
import "./index.css";
import {Restroom} from "../../../app/typings";

const MainPage = (): JSX.Element => {
    const [selectedRestroom, setSelectedRestroom] = useState<Restroom | null>(null);

    const selectRestroom = (restroomToSelect: Restroom): void => {
        if (selectedRestroom !== restroomToSelect) {
            setSelectedRestroom(restroomToSelect);
        }
    };

    return (
        <main className="mainContainer">
            <Sidebar selectedRestroom={selectedRestroom}/>
            <Map selectRestroom={selectRestroom}/>
        </main>
    );
};

export default MainPage;
