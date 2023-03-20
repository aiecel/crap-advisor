import { Location } from 'app/typings';

import {
  restroom_2 as badRestroom,
  restroom_4 as commonRestroom,
  restroom_5 as goldenRestroom,
} from 'assets';

// Api
export const apiUrl = process.env.REACT_APP_ENV_API_ENDPOINT || 'http://25.38.182.18:8082';

export enum ApiEndpoint {
  GetAllRestrooms = '/restrooms',
  AddRestroom = '/restrooms',
  GetAllReviewsByRestroomId = '/reviews',
  AddReview = '/reviews',
}

// Map
export const defaultLocation: Location = {
  latitude: 53.21176,
  longitude: 50.18394,
};
export const defaultZoom: number = 12;

// Restroom Markers
export const defaultRestroomMarkerImage = commonRestroom;
export const restroomMarkerImages = [
  badRestroom,
  badRestroom,
  commonRestroom,
  commonRestroom,
  goldenRestroom,
];

// Sidebar
export const defaultRestroomHue: number = 200;
