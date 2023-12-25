<script lang="ts">
    export let color: string = "#eeeeee";
</script>

<div
    style="border: 5px solid {color}; border-bottom-color: transparent; border-radius: 50%;"
/>

<style>
    div {
        margin: 1rem;
        width: 2rem;
        height: 2rem;
        display: block;
        box-sizing: border-box;
        animation: rotation 1s linear infinite;
    }

    @keyframes rotation {
        0% {
            transform: rotate(0deg);
        }
        100% {
            transform: rotate(360deg);
        }
    }
</style>
