import badRestroom from "assets/restroom_2.png";
import commonRestroom from "assets/restroom_4.png";
import goldenRestroom from "assets/restroom_5.png";
import {Location} from "app/typings";

// Api
export const apiUrl = "http://25.38.182.18:8082";

export enum ApiEndpoint {
    GetAllRestrooms = '/restrooms',
    AddRestroom = '/restrooms',
    GetAllReviewsByRestroomId = '/reviews',
    AddReview = '/reviews',
}

// Map
export const defaultLocation: Location = {latitude: 53.21176, longitude: 50.18394}
export const defaultZoom: number = 12

// Restroom Markers
export const defaultRestroomMarkerImage = commonRestroom
export const restroomMarkerImages = [badRestroom, badRestroom, commonRestroom, commonRestroom, goldenRestroom]

// Sidebar
export const defaultRestroomHue: number = 200