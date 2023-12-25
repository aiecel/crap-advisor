import { writable } from "svelte/store";
import { getReviews } from "$lib/api";
import type { Review } from "$lib/model";

function createStore() {
    const { subscribe, set } = writable<Array<Review>>([]);

    return {
        subscribe,
        fetchForRestroom: async (restroomId: number) => {
            set([]);
            getReviews(restroomId)
                .then((reviews: Review[]) => set(reviews))
                .catch(() => set([]));
        },
        clear: () => set([]),
    };
}

export const reviews = createStore();
