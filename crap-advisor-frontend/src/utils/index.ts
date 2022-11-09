import { LatLngExpression } from 'leaflet';
import { isNil } from 'lodash';

import { Location, Primitive } from 'app/typings';

export const params2form = (payload: Record<string, Primitive | Primitive[]>): string =>
  Object.keys(payload)
    .filter((key) => !isNil(payload[key]))
    .map((key) => {
      if (Array.isArray(payload[key])) {
        return (payload[key] as Primitive[])
          .filter((item) => !isNil(item))
          .map((item) => `${encodeURIComponent(key)}=${encodeURIComponent(item)}`)
          .join('&');
      }
      return `${encodeURIComponent(key)}=${encodeURIComponent(
        payload[key] as Primitive
      )}`;
    })
    .join('&');

export const toLatLngExpression = (location: Location): LatLngExpression => [
  location.latitude,
  location.longitude,
];

export const getRestroomIdFromURL = () => {
  const urlParam = window.location.pathname;
  const result = urlParam.replace('/restroom/', '');
  return result === '/' ? undefined : result;
};

export function sleep(sec: number) {
  return new Promise((resolve) => setTimeout(resolve, sec * 1000));
}
