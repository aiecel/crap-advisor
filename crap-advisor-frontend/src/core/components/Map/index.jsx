import React, {useEffect, useState} from "react";
import "./index.css"
import {MapContainer, TileLayer, ZoomControl} from "react-leaflet";
import RestroomMarker from "../RestroomMarker";

const Map = () => {
    const apiUrl = "http://localhost:8082"
    const defaultPosition = [53.21176, 50.18394]
    const defaultZoom = 13
    const [restrooms, setRestrooms] = useState([])

    useEffect(() => {
        fetch(apiUrl + "/restroom/all")
        .then((response) => response.json())
        .then((data) => {
            setRestrooms(data)
            console.log(`Received ${data.length} restrooms`)
        })
    }, [])

    return (
        <MapContainer className="map" center={defaultPosition} zoom={defaultZoom} zoomControl={false}>
            <TileLayer
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            <ZoomControl position="bottomright"/>
            {
                restrooms.map((restroom, i) => {
                    return (
                        <RestroomMarker key={i} restroom={restroom}/>
                    )
                })
            }
        </MapContainer>
    );
};

export default Map;