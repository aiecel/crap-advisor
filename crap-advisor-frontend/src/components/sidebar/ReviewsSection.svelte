<script lang="ts">
    import type {Restroom} from "$lib/model";
    import {getReviews} from "$lib/api";
    import Button from "../Button.svelte";
    import AddReviewDialog from "../dialog/AddReviewDialog.svelte";
    import Review from "./Review.svelte";
    import Loader from "../Loader.svelte";

    export let restroom: Restroom;

    let reviewsPromise = getReviews(restroom.id);
    let isAddReviewDialogVisible = false;

    $: reviewsPromise = getReviews(restroom.id);
</script>

<section>
    <h1>Отзывы</h1>
    {#await reviewsPromise}
        <div class="section-header">
            <Loader />
        </div>
    {:then reviews}
        {#if reviews.length == 0}
            <div class="text">
                <p>Отзывов пока нет</p>
            </div>
        {/if}
        <div class="section-header">
            <Button onClick={() => (isAddReviewDialogVisible = true)}>
                + Добавить отзыв
            </Button>
        </div>
        <AddReviewDialog bind:isVisible={isAddReviewDialogVisible} {restroom} />
        {#each reviews as review}
            <Review {review} />
        {/each}
    {:catch}
        <p>Не удалось загрузить отзывы</p>
    {/await}
</section>

<style>
    h1 {
        font-size: 1rem;
        font-weight: 500;
        background-color: #eeeeee;
        padding: 0.5rem 1rem;
        margin: 0;
    }

    p {
        text-align: center;
    }

    .section-header :global(*) {
        display: block;
        margin: 1rem auto;
    }

    .text {
        text-align: center;
    }
</style>
