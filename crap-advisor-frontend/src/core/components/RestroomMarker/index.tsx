import React from "react";
import { Marker, Tooltip } from "react-leaflet";
import L from "leaflet";
import restroomImage from "../../../assets/restroomMarker.png";
import "./index.css";
import {Restroom} from "../../../app/typings";
import {IMapProps} from "../Map";
import { LeafletEvent, LatLngExpression} from 'leaflet'

interface IRestroomMakerProps extends Pick<IMapProps, 'selectRestroom'>{
  restroom: Restroom;
}

const RestroomMarker = ({ restroom, selectRestroom }: IRestroomMakerProps) => {
  const icon = L.divIcon({
    className: "icon",
    html:
      `<div class="marker-container">` +
      `<img class="marker-icon" src='${restroomImage}' alt='Toilet'/>` +
      `<p class="marker-text outlined">${restroom.rating ?? ""}</p>` +
      `</div>`,
  });

  const handleMarkerClick = (event: LeafletEvent): void => selectRestroom(restroom);

  return (
    <Marker
      position={[restroom.location.latitude, restroom.location.longitude] as LatLngExpression}
      icon={icon}
      eventHandlers={{ click: handleMarkerClick }}
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
