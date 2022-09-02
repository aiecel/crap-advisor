import React from "react";
import "./index.css"
import {MapContainer, TileLayer, ZoomControl} from "react-leaflet";
import RestroomMarker from "../RestroomMarker";

const defaultPosition = [53.21176, 50.18394]
const zoom = 13

const restrooms = [
    {
        name: "У Шифра",
        position: [53.5114, 49.23536],
        rating: 4.1
    },
    {
        name: "Сызранский толчок",
        position: [53.15745, 48.46434],
        rating: 2.2
    },
    {
        name: "Самарская уборная",
        position: [53.21176, 50.18394],
        rating: 3.7
    },
    {
        name: "Туалет в Отрадном",
        position: [53.38419, 51.35402],
        rating: 4.3
    },
    {
        name: "У Кудра",
        position: [53.13837, 50.08059],
        rating: 4.6
    },
    {
        name: "Яма в Бузулуке",
        position: [52.80482, 52.25104],
        rating: 1.5
    }
]

const Map = () => {
    return (
        <MapContainer className={"map"} center={defaultPosition} zoom={zoom} zoomControl={false}>
            <TileLayer
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            <ZoomControl position="bottomright"/>
            {
                restrooms.map((item, i) => {
                    return (
                        <RestroomMarker key={i} props={item}/>
                    )
                })
            }
        </MapContainer>
    );
};

export default Map;