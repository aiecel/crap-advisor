import React, {useState} from "react";
import {Map, Sidebar} from "../../components";
import "./index.css";
import "../../../style/main.css";
import {Restroom} from "../../../app/typings";
import {defaultRestroomHue} from "../../../app/constants";

const MainPage = (): JSX.Element => {
    const [selectedRestroom, setSelectedRestroom] = useState<Restroom | null>(null);

    const selectRestroom = (restroomToSelect: Restroom): void => {
        if (selectedRestroom !== restroomToSelect) {
            setSelectedRestroom(restroomToSelect);
            const hue = restroomToSelect.rating ? (restroomToSelect.rating - 1) * 20 : defaultRestroomHue
            document.documentElement.style.setProperty('--restroom-hue', `${hue}`);
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
