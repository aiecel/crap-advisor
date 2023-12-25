<script lang="ts">
    export let onClick: () => void = () => {};
</script>

<button on:click={onClick}>
    <slot />
</button>

<style>
    button {
        cursor: pointer;
        background-color: #eeeeee;
        height: 2.5rem;
        padding-inline: 1rem;
        font-size: 1rem;
        font-family: inherit;
        font-weight: 300;
        border: none;
        border-radius: 0.5rem;
    }

    button:hover {
        background-color: #f5f5f5;
    }
</style>
