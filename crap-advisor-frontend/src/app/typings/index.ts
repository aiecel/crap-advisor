import { Method } from 'axios';

export type Location = {
  latitude: number;
  longitude: number;
};

export interface Restroom {
  id: number;
  created: string;
  location: Location;
  name: string;
  rating: number | null;
}

export type Marks = {
  cleanness: number;
  comfort: number;
  primaryFixtures: number;
  secondaryFixtures: number;
};

export interface IReview {
  id: number;
  comment: string | null;
  created: Date;
  rating: number;
  restroomId: number;
  marks: Marks;
}

export type Primitive = string | boolean | number;

export type RequestType = Extract<Method, 'GET' | 'POST' | 'DELETE' | 'PUT'>;

export type $needToFix = any;
