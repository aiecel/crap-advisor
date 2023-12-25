<script lang="ts">
    import {addReview} from "$lib/api";
    import type {Restroom} from "$lib/model";
    import {reviews} from "$lib/store/reviewsStore";
    import Button from "../Button.svelte";

    export let restroom: Restroom;
    export let isVisible: Boolean = false;

    let dialogElement: HTMLDialogElement;
    let formElement: HTMLFormElement;

    $: isVisible, showOrHideDialog();

    function showOrHideDialog() {
        if (isVisible) {
            dialogElement?.showModal();
        } else {
            dialogElement?.close();
        }
    }

    function submit() {
        addReview(new FormData(formElement)).then(() => {
            isVisible = false;
            reviews.fetchForRestroom(restroom.id);
        });
    }
</script>

<!-- svelte-ignore a11y-click-events-have-key-events -->
<!-- svelte-ignore a11y-no-noninteractive-element-interactions -->
<dialog bind:this={dialogElement} on:close={() => (isVisible = false)}>
    <form bind:this={formElement}>
        <h2>Отзыв на</h2>
        <h1>{restroom.name}</h1>

        <input type="hidden" name="restroomId" value={restroom.id}>

        <label for="primaryFixtures">Первичная сантехника</label>
        <input type="range" min="1" max="5" name="primaryFixtures" />

        <label for="secondaryFixtures">Вторичная сантехника</label>
        <input type="range" min="1" max="5" name="secondaryFixtures" />

        <label for="cleanness">Чистота</label>
        <input type="range" min="1" max="5" name="cleanness" />

        <label for="comfort">Комфорт</label>
        <input type="range" min="1" max="5" name="comfort" />

        <label for="comment">Отзыв</label>
        <textarea name="comment" />

        <label for="images">Фото</label>
        <input
            type="file"
            name="images"
            accept="image/png, image/gif, image/jpeg"
            multiple
        />

        <Button onClick={submit}>Оставить отзыв</Button>
        <Button onClick={() => (isVisible = false)}>Назад</Button>
    </form>
</dialog>

<style>
    dialog {
        width: 500px;
        border: none;
        border-radius: 10px;
        box-shadow: rgba(0, 0, 0, 0.1) 0 0 30px;
        padding: 30px;
    }

    h2 {
        margin: 0;
        font-weight: 300;
        font-size: 1em;
        color: #aaaaaa;
    }

    h1 {
        margin: 0;
        margin-bottom: 30px;
    }

    input {
        display: block;
        margin-bottom: 10px;
    }

    textarea {
        box-sizing: border-box;
        font-family: inherit;
        font-size: 1em;
        border: none;
        border-radius: 10px;
        background-color: #eeeeee;
        padding: 10px;
        min-height: 2em;
        max-height: 15em;
        width: 100%;
        resize: vertical;
    }
</style>
