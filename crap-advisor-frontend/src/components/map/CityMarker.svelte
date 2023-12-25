<script lang="ts">
    import {onDestroy, onMount} from "svelte";
    import type {City} from "$lib/model";
    import type {YMap, YMapMarker} from "@yandex/ymaps3-types";

    export let city: City;
    export let map: YMap;

    let marker: YMapMarker;
    let markerElement: HTMLElement;

    onMount(() => {
        marker = new ymaps3.YMapMarker(
            {
                coordinates: [
                    city.location.longitude,
                    city.location.latitude,
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
<div bind:this={markerElement}>
    <p>{city.name ?? ""}</p>
</div>

<style>
    @keyframes slideInFromLeft {
        0% {
            transform: scale(0);
        }
        100% {
            transform: scale(1);
        }
    }

    div {
        text-align: center;
        cursor: pointer;
        position: relative;
        top: -1.4rem;
        left: -50%;
        transition: all 0.1s ease-in-out;
        animation: 0.2s ease 0s 1 slideInFromLeft;
    }

    div:hover {
        transform: scale(1.1);
    }

    p {
        font-size: 1rem;
        font-weight: 600;
        margin: 0;
        text-shadow: -1px -1px 0 white, 0 -1px 0 white, 1px -1px 0 white,
            1px 0 0 white, 1px 1px 0 white, 0 1px 0 white, -1px 1px 0 white,
            -1px 0 0 white;
    }
</style>
