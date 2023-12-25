<script lang="ts">
    import { selectedRestroom } from "$lib/store/selectedRestroomStore";
    import HeaderSection from "./HeaderSection.svelte";
    import ReviewsSection from "./ReviewsSection.svelte";
</script>

<div>
    {#if $selectedRestroom}
        <HeaderSection restroom={$selectedRestroom} />
        <ReviewsSection restroom={$selectedRestroom} />
    {:else}
        <h1>Crap Advisor</h1>
        <p>Выберите туалет</p>
    {/if}
</div>

<style>
    div {
        background: white;
        box-shadow: rgba(0, 0, 0, 0.1) 0 0 3rem;
        height: 100vh;
        max-height: 100vh;
        overflow-y: auto;
    }

    div::-webkit-scrollbar {
        background-color: transparent;
        width: 0.4rem;
    }

    div::-webkit-scrollbar-thumb {
        background: #eeeeee;
    }

    div::-webkit-scrollbar-thumb:hover {
        background: #f5f5f5;
    }

    div::-webkit-scrollbar-thumb:active {
        background: #dddddd;
    }

    h1 {
        margin: 1rem 0 0 1rem;
    }

    p {
        margin: 0 1rem;
    }
</style>
