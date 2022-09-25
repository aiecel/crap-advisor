export type TLocation = {
  latitude: number;
  longitude: number;
};

export interface Restroom {
  id: number;
  created: Date;
  location: TLocation;
  name: string;
  rating: number | null;
}

export type ReviewMarks = {
  cleanness: number;
  comfort: number;
  primaryFixtures: number;
  secondaryFixtures: number;
}

export interface IReview {
  id: number;
  comment: string | null;
  created: Date;
  rating: number;
  restroomId: number;
  reviewMarks: ReviewMarks;
}
