import React, { useEffect, useMemo, useState } from 'react';
import { Marker, Tooltip, useMap } from 'react-leaflet';
import { useNavigate } from 'react-router-dom';

import L from 'leaflet';
import { LatLngExpression } from 'leaflet';

import {
  defaultRestroomHue,
  defaultRestroomMarkerImage,
  restroomMarkerImages,
} from 'app/constants';
import { Restroom } from 'app/typings';
import { useAppDispatch, useAppSelector } from 'app/typings/redux';

import { start } from 'store/reviews/slice';

import { toLatLngExpression } from 'utils';

import 'style/main.css';

import './index.css';

interface IRestroomMakerProps {
  restroom: Restroom;
}

const RestroomMarker = ({ restroom }: IRestroomMakerProps): JSX.Element => {
  const [image, setImage] = useState(defaultRestroomMarkerImage);
  const [rating, setRating] = useState<number | null>(null);
  const { currentRestroom } = useAppSelector((state) => state.REVIEWS);
  const navigate = useNavigate();
  const map = useMap();
  const dispatch = useAppDispatch();
  const changeSidebarColors = React.useCallback(() => {
    const hue = currentRestroom?.rating
      ? (currentRestroom?.rating - 1) * 20
      : defaultRestroomHue;
    document.documentElement.style.setProperty('--restroom-hue', `${hue}`);
  }, [currentRestroom]);

  React.useEffect(() => {
    changeSidebarColors();
  }, [currentRestroom]);

  const icon = useMemo(
    () =>
      L.divIcon({
        className: 'icon',
        html:
          `<div class="marker-container">` +
          `<img class="marker-icon" src='${image}' alt='Toilet'/>` +
          `<p class="marker-text outlined">${rating ?? ''}</p>` +
          `</div>`,
      }),
    [image, rating]
  );

  useEffect(() => {
    setRating(restroom.rating);
    setImage(
      restroom.rating
        ? restroomMarkerImages[Math.round(restroom.rating) - 1]
        : defaultRestroomMarkerImage
    );
  }, [restroom.rating]);
  const handleMarkerClick = (): void => {
    if (currentRestroom?.id !== restroom.id) {
      dispatch(start(String(restroom.id)));
    }
    navigate(`/restroom/${restroom.id}`);
    map.setView(toLatLngExpression(restroom.location), map.getZoom());
  };

  return (
    <Marker
      position={toLatLngExpression(restroom.location)}
      icon={icon}
      eventHandlers={{ click: handleMarkerClick }}
    >
      <Tooltip className="marker-tooltip outlined" direction="top" offset={[0, -10]}>
        {restroom.name}
      </Tooltip>
    </Marker>
  );
};

export default RestroomMarker;
