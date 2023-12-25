<script lang="ts">
    import {format} from "date-fns";
    import {ru} from "date-fns/locale";
    import Rating from "../Rating.svelte";
    import type {Restroom} from "$lib/model";

    export let restroom: Restroom;

    $: hue = restroom?.rating ? (restroom.rating - 1) * 30 : 220;
</script>

<section style:--hue={hue}>
    <div class="content">
        <div>
            <h1 class="name">{restroom.name}</h1>
            <p class="created">
                с {format(new Date(restroom.created), "d MMMM yyyy", {
                    locale: ru,
                })}
            </p>
        </div>
        {#if restroom.rating}
            <div>
                <p class="rating">{restroom.rating}</p>
                <Rating
                    class="rating-stars"
                    backgroundColor="#00000090"
                    rating={restroom.rating}
                />
            </div>
        {/if}
    </div>
</section>

<style>
    section {
        position: relative;
        height: 20vh;
        /* background-color: hsl(var(--hue), 100%, 40%); */
        background-image: url("http://localhost:9000/test/fmrcmP1OEzI%20(1).jpg");
        background-size: cover;
        background-position: 0 -120px;
    }

    p,
    h1 {
        margin: 0;
        font-weight: 100;
        color: white;
        text-shadow: 2px 2px 5px rgba(0, 0, 0, 50%);
    }

    .content {
        display: flex;
        justify-content: space-between;
        align-items: end;
        box-sizing: border-box;
        width: 100%;
        position: absolute;
        bottom: 0;
        padding: 1rem;
        background: linear-gradient(transparent, rgba(0, 0, 0, 0.4));
    }

    .rating {
        text-align: right;
        font-size: 1.6rem;
        font-weight: 600;
    }

    .content :global(.rating-stars) {
        filter: drop-shadow(2px 2px 5px rgba(0, 0, 0, 50%));
    }

    .name {
        font-size: 1.6rem;
        font-weight: 600;
    }

    .created {
        font-size: 0.9rem;
    }
</style>
