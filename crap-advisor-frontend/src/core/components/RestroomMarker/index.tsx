import React, {useEffect, useMemo, useState} from "react";
import {Marker, Tooltip, useMap} from "react-leaflet";
import L from "leaflet";
import {Restroom} from "../../../app/typings";
import {IMapProps} from "../Map";
import {LatLngExpression} from 'leaflet'
import {toLatLngExpression} from "../../../utils";
import {defaultRestroomMarkerImage, restroomMarkerImages} from "../../../app/constants";
import "./index.css";
import "../../../style/main.css";

interface IRestroomMakerProps extends Pick<IMapProps, 'selectRestroom'> {
    restroom: Restroom
}

const RestroomMarker = ({restroom, selectRestroom}: IRestroomMakerProps): JSX.Element => {
    const [image, setImage] = useState(defaultRestroomMarkerImage)
    const [rating, setRating] = useState<number | null>(null)

    const icon = useMemo(() => L.divIcon({
        className: "icon",
        html:
            `<div class="marker-container">` +
            `<img class="marker-icon" src='${image}' alt='Toilet'/>` +
            `<p class="marker-text outlined">${rating ?? ""}</p>` +
            `</div>`,
    }), [image, rating])

    useEffect(() => {
        setRating(restroom.rating)
        setImage(restroom.rating ?
            restroomMarkerImages[Math.round(restroom.rating) - 1] : defaultRestroomMarkerImage)
    }, [restroom.rating])

    const map = useMap()
    const handleMarkerClick = (): void => {
        selectRestroom(restroom);
        map.setView(toLatLngExpression(restroom.location), map.getZoom())
    }

    return (
        <Marker
            position={toLatLngExpression(restroom.location) as LatLngExpression}
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
