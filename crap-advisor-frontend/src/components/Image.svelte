<script lang="ts">
    import {onMount} from "svelte";
    import Loader from "./Loader.svelte";

    export let src: string;
    export let alt: string;

    let image: HTMLImageElement;
    let stage: "loading" | "ok" | "error" = "loading";

    onMount(() => {
        image.src = src;
        image.onload = () => {
            stage = "ok";
            image.style.cssText = "display: block;";
        };
        image.onerror = () => (stage = "error");
    });
</script>

<img class="image ok" bind:this={image} {alt} style="display: none;" />

{#if stage == "loading"}
    <div class="image loading">
        <Loader color="lightgray" />
    </div>
{:else if stage == "error"}
    <div class="image error">
        <p>{alt}</p>
    </div>
{/if}

<style>
    .image {
        display: flex;
        text-align: center;
    }

    .image.loading,
    .image.error {
        aspect-ratio: 1;
    }

    .image.loading :global(div) {
        margin: auto;
    }

    p {
        color: gray;
        font-size: 0.8rem;
        margin: auto;
    }
</style>
