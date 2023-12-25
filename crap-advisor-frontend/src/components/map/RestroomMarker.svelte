<script lang="ts">
    import {onDestroy, onMount} from "svelte";
    import type {Restroom} from "$lib/model";
    import type {YMap, YMapMarker} from "@yandex/ymaps3-types";
    import {goto} from "$app/navigation";
    import {selectedRestroom} from "$lib/store/selectedRestroomStore";

    export let restroom: Restroom;
    export let map: YMap;

    let marker: YMapMarker;
    let markerElement: HTMLElement;

    $: selected = $selectedRestroom == restroom;

    onMount(() => {
        marker = new ymaps3.YMapMarker(
            {
                coordinates: [
                    restroom.location.longitude,
                    restroom.location.latitude,
                ],
            },
            markerElement
        );

        map.addChild(marker);
    });

    onDestroy(() => {
        if (marker) {
            map.removeChild(marker);
        }
    });
</script>

<!-- svelte-ignore a11y-no-static-element-interactions -->
<!-- svelte-ignore a11y-click-events-have-key-events -->
<div bind:this={markerElement} on:click={() => goto(`${restroom.id}`)}>
    {#if selected}
        <p>{restroom.name ?? ""}</p>
    {/if}
    <img src="restroom.png" alt={restroom.name} />
    <p>{restroom.rating ?? ""}</p>
</div>

<style>
    @keyframes pop {
        0% {
            transform: scale(0);
        }
        100% {
            transform: scale(1);
        }
    }

    div {
        width: 3rem;
        text-align: center;
        cursor: pointer;
        position: relative;
        top: -50px;
        left: -50%;
        transition: all 0.1s ease-in-out;
        animation: 0.1s ease 0s 1 pop;
    }

    div:hover {
        transform: scale(1.1);
    }

    img {
        width: 40px;
        height: 40px;
    }

    p {
        font-size: 12px;
        font-weight: 600;
        margin: 0;
        text-shadow: -1px -1px 0 white, 0 -1px 0 white, 1px -1px 0 white,
            1px 0 0 white, 1px 1px 0 white, 0 1px 0 white, -1px 1px 0 white,
            -1px 0 0 white;
    }
</style>
