import React from "react";
import {Marker, Tooltip, useMap} from "react-leaflet";
import L from "leaflet";
import "./index.css";
import {Restroom} from "../../../app/typings";
import {IMapProps} from "../Map";
import {LeafletEvent, LatLngExpression} from 'leaflet'

import restroom1 from "../../../assets/restroom_2.png";
import restroom2 from "../../../assets/restroom_2.png";
import restroom3 from "../../../assets/restroom_4.png";
import restroom4 from "../../../assets/restroom_4.png";
import restroom5 from "../../../assets/restroom_5.png";

interface IRestroomMakerProps extends Pick<IMapProps, 'selectRestroom'> {
    restroom: Restroom
}

const RestroomMarker = ({restroom, selectRestroom}: IRestroomMakerProps) => {

    const iconImages = [restroom1, restroom2, restroom3, restroom4, restroom5]
    let currentImage = restroom4

    if (restroom.rating) {
        currentImage = iconImages[Math.round(restroom.rating) - 1]
    }

    const icon = L.divIcon({
        className: "icon",
        html:
            `<div class="marker-container">` +
            `<img class="marker-icon" src='${currentImage}' alt='Toilet'/>` +
            `<p class="marker-text outlined">${restroom.rating ?? ""}</p>` +
            `</div>`,
    });

    const map = useMap()
    const handleMarkerClick = (event: LeafletEvent): void => {
        selectRestroom(restroom);
        map.setView([restroom.location.latitude, restroom.location.longitude], map.getZoom())
    }

    return (
        <Marker
            position={[restroom.location.latitude, restroom.location.longitude] as LatLngExpression}
            icon={icon}
            eventHandlers={{click: handleMarkerClick}}
        >
            <Tooltip
                className="marker-tooltip outlined"
                direction="top"
                offset={[0, -10]}
            >
                {restroom.name}
            </Tooltip>
        </Marker>
    );
};

export default RestroomMarker;
