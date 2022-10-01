import React from "react";
import "./index.css";
import {MapContainer, TileLayer, ZoomControl, useMap} from "react-leaflet";
import RestroomMarker from "../RestroomMarker";
import {ApiEndpoint} from "../../../app/constants";
import {LatLngExpression} from "leaflet";
import {Restroom} from "../../../app/typings";
import {useDataApi} from "../../../hooks/useDataApi";

export interface IMapProps {
    selectRestroom: (restroomToSelect: Restroom) => void;
}

const Map = ({selectRestroom}: IMapProps): JSX.Element => {
    const defaultPosition: LatLngExpression = [53.21176, 50.18394];
    const defaultZoom: number = 13;
    const {state: {data: restrooms}} = useDataApi<Restroom>(ApiEndpoint.GetAllRestrooms, [], 'GET');

    return (
        <MapContainer
            className="map"
            center={defaultPosition}
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
