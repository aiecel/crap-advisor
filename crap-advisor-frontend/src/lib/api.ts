import type {Area, City, Marks, Restroom, Review} from "./model";

const API_HOST = "http://localhost:8082";

export type AddReviewRequest = {
    restroomId: number;
    marks: Marks;
    comment: string | null;
};

export async function getAllRestrooms(): Promise<Array<Restroom>> {
    return await fetch(API_HOST + "/restrooms")
        .then(async response => await response.json())
        .catch(reason => console.error(reason));
}

export async function getBestRestroomsForArea(area: Area): Promise<Array<Restroom>> {
    return await fetch(API_HOST + "/restrooms/best?" + new URLSearchParams({
        fromLatitude: area.fromLocation.latitude.toString(),
        fromLongitude: area.fromLocation.longitude.toString(),
        toLatitude: area.toLocation.latitude.toString(),
        toLongitude: area.toLocation.longitude.toString()
    }))
        .then(async response => await response.json())
        .catch(reason => console.error(reason));
}

export async function getReviews(restroomId: number): Promise<Array<Review>> {
    return await fetch(API_HOST + "/reviews?restroomId=" + restroomId).then(
        async response => await response.json()
    );
}

export async function addReview(formData: FormData): Promise<Review> {
    return await fetch(API_HOST + "/reviews", {
        method: "POST",
        body: formData,
    })
        .then(async response => await response.json())
        .catch(reason => console.error(reason));
}

export async function getAllCities(): Promise<Array<City>> {
    return await fetch(API_HOST + "/cities")
        .then(async response => await response.json())
        .catch(reason => console.error(reason));
}