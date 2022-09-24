import React from 'react';
import {Marker, Tooltip} from "react-leaflet";
import L from "leaflet";
import restroomImage from "../../../assets/restroomMarker.png";
import "./index.css"

const RestroomMarker = ({restroom}) => {
    const icon = L.divIcon({
        className: "icon",
        html: `<div class="marker-container">` +
            `<img class="marker-icon" src='${restroomImage}' alt='Toilet'/><p class="marker-text outlined">${restroom.rating}</p></div>`
    })

    return (
        <Marker position={[restroom.position.latitude, restroom.position.longitude]} icon={icon}>
            <Tooltip className="marker-tooltip outlined" direction="top" offset={[0, -10]}>{restroom.name}</Tooltip>
        </Marker>
    );
};

export default RestroomMarker;