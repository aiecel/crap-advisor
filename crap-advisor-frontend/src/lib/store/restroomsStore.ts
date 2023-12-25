import { readable } from "svelte/store";
import { getAllRestrooms } from "$lib/api";
import type { Restroom } from "$lib/model";

function createStore() {
    const { subscribe } = readable<Array<Restroom>>([], set => {
        getAllRestrooms().then((restrooms: Restroom[]) => set(restrooms));
    });

    return {
        subscribe,
    };
}

export const restrooms = createStore();
