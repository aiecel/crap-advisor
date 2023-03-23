import React from 'react';
import { MapContainer, TileLayer, ZoomControl, useMap } from 'react-leaflet';

import { RestroomMarker } from 'core/components';

import { defaultLocation, defaultZoom } from 'app/constants';
import { useAppSelector } from 'app/typings/redux';

import { toLatLngExpression } from 'utils';

import 'style/main.css';

import './index.css';

function SetMapToSelectedRestroom(): null {
  const { currentRestroom } = useAppSelector((state) => state.REVIEWS);
  const map = useMap();
  React.useEffect(() => {
    if (currentRestroom) {
      map.setView(toLatLngExpression(currentRestroom.location));
    }
  }, [currentRestroom]);
  return null;
}

const Map = (): JSX.Element => {
  const { result: restrooms } = useAppSelector((state) => state.RESTROOM);
  return (
    <MapContainer
      className="map"
      center={toLatLngExpression(defaultLocation)}
      zoom={defaultZoom}
      zoomControl={false}
    >
      <SetMapToSelectedRestroom />
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      <ZoomControl position="bottomright" />
      {restrooms.map((restroom) => {
        return <RestroomMarker key={restroom.id} restroom={restroom} />;
      })}
    </MapContainer>
  );
};

export default Map;
