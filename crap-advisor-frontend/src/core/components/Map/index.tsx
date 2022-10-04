import React from "react";
import {MapContainer, TileLayer, ZoomControl} from "react-leaflet";
import RestroomMarker from "../RestroomMarker";
import {ApiEndpoint, defaultLocation, defaultZoom} from "../../../app/constants";
import {Restroom} from "../../../app/typings";
import {useDataApi} from "../../../hooks/useDataApi";
import {toLatLngExpression} from "../../../utils";
import "./index.css";
import "../../../style/main.css";

export interface IMapProps {
    selectRestroom: (restroomToSelect: Restroom) => void;
}

const Map = ({selectRestroom}: IMapProps): JSX.Element => {
    const {state: {data: restrooms}} = useDataApi<Restroom>(ApiEndpoint.GetAllRestrooms, [], 'GET');

    return (
        <MapContainer
            className="map"
            center={toLatLngExpression(defaultLocation)}
            zoom={defaultZoom}
            zoomControl={false}
        >
            <TileLayer
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            <ZoomControl position="bottomright"/>
            {restrooms.map((restroom) => {
                return (
                    <RestroomMarker
                        key={restroom.id}
                        restroom={restroom}
                        selectRestroom={selectRestroom}
                    />
                );
            })}
        </MapContainer>
    );
};

export default Map;
