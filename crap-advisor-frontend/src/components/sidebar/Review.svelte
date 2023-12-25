<script lang="ts">
    import type {Review} from "$lib/model";
    import {format} from "date-fns";
    import {ru} from "date-fns/locale";
    import Rating from "../Rating.svelte";
    import Image from "../Image.svelte";

    export let review: Review;
</script>

<div class="review-container">
    <p class="created">
        {format(new Date(review.created), "d MMMM yyyy в HH:mm", {
            locale: ru,
        })}
    </p>
    <div class="rating-container">
        <p class="rating">{review.rating}</p>
        <Rating class="rating-stars" rating={review.rating} />
    </div>
    {#if review.images.length > 0}
        <div class="images-container">
            {#each review.images as image}
                <Image
                    src={"http://localhost:9000/" + image.thumbnailPath}
                    alt="Туалет"
                />
            {/each}
        </div>
    {/if}
    {#if review.comment}
        <p>{review.comment}</p>
    {/if}
</div>

<style>
    p {
        margin: 0;
        font-weight: 300;
    }

    .review-container {
        padding: 1rem;
        border-bottom: 1px solid #eeeeee;
    }

    .review-container:hover {
        background-color: #f8f8f8;
    }

    .created {
        color: #aaaaaa;
        font-size: 0.8rem;
    }

    .rating-container {
        display: flex;
    }

    .rating {
        font-weight: 500;
        font-size: 1.5rem;
    }

    .rating-container :global(.rating-stars) {
        margin: auto 0.5rem;
    }

    .images-container {
        margin-block: 0.5rem;
        display: flex;
        gap: 0.5rem;
    }

    .images-container :global(.image) {
        background-color: #eeeeee;
        height: 5rem;
        border-radius: 0.5rem;
        cursor: pointer;
        transition: all 0.1s ease-in-out;
    }

    .images-container :global(.image:hover) {
        transform: scale(1.1);
    }
</style>
