export type Restroom = {
    id: number;
    name: string;
    created: Date;
    location: Location;
    rating: number | null;
};

export type Review = {
    id: number;
    restroomId: number;
    created: Date;
    comment: string | null;
    rating: number;
    marks: Marks;
    images: Array<Image>
};

export type Marks = {
    primaryFixtures: number | null;
    secondaryFixtures: number | null;
    cleanness: number | null;
    comfort: number | null;
};

export type Image = {
    originalPath: string,
    thumbnailPath: string
};

export type City = {
    id: number;
    name: string;
    created: Date;
    location: Location;
};

export type Location = {
    latitude: number;
    longitude: number;
};

export type Region = {
    fromLocation: Location,
    toLocation: Location
}
