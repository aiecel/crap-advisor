import React from 'react';
import {Marker, Tooltip} from "react-leaflet";
import L from "leaflet";
import restroomImage from "../../../assets/restroom.png";
import "./index.css"

const RestroomMarker = ({props}) => {
    const icon = L.divIcon({
        className: "icon",
        html: `<div class="marker-container">` +
            `<img class="marker-icon" src='${restroomImage}' alt='Toilet'/><p class="marker-text">${props.rating}</p></div>`
    })

    return (
        <Marker position={props.position} icon={icon}>
            <Tooltip className="marker-tooltip" direction="top" offset={[0, -10]}>{props.name}</Tooltip>
        </Marker>
    );
};

export default RestroomMarker;