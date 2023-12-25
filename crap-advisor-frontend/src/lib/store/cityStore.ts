import {readable} from "svelte/store";
import {getAllCities} from "$lib/api";
import type {City} from "$lib/model";

function createStore() {
    const { subscribe } = readable<Array<City>>([], set => {
        getAllCities().then((cities: City[]) => set(cities));
    });

    return {
        subscribe,
    };
}

export const cities = createStore();
