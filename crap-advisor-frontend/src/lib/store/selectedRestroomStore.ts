import {derived, writable} from "svelte/store";
import {restrooms} from "./restroomsStore";

const selectedRestroomIdStore = writable<number | null>(null);

const selectedRestroomStore = derived(
    [selectedRestroomIdStore, restrooms],
    ([selectedRestroomId, restrooms]) => {
        if (!selectedRestroomId) {
            return null;
        }
        return restrooms.find(restroom => restroom.id == selectedRestroomId);
    },
    null
);

function createStore() {
    return {
        subscribe: selectedRestroomStore.subscribe,
        select: (restroomIdToSelect: number | null) => {
            selectedRestroomIdStore.update(currentSelectedRestroomId => {
                if (currentSelectedRestroomId == restroomIdToSelect) {
                    return currentSelectedRestroomId;
                }
                if (restroomIdToSelect) {
                    //reviews.fetchForRestroom(restroomIdToSelect);
                } else {
                    //reviews.clear()
                }
                return restroomIdToSelect;
            });
        },
    };
}

export const selectedRestroom = createStore();
