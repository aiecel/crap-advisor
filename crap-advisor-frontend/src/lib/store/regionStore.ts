import type {Region} from "$lib/model";
import {writable} from "svelte/store";

function createStore() {
    const { subscribe, set, update } = writable<Array<Region>>([]);

    return {
        subscribe,
        add: (region: Region) => {
            update(regions => {
                regions.push(region);
                return regions;
            });
        },
        clear: () => set([]),
    };
}

export const regions = createStore();
