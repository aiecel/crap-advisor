<script lang="ts">
    export let size: number = 1;
    export let color: string = "#ffcc00";
    export let backgroundColor: string = "#eeeeee";
    export let rating: number = 0;
</script>

<svg
    height={size + "rem"}
    width={size * 5 + "rem"}
    viewBox="0 0 269.335 53.867"
    class={$$props.class}
>
    <mask id={"mask-stars-" + rating}>
        <rect x="0" y="0" width="100%" height="100%" fill="white" />
        <rect
            x={rating * 20 + "%"}
            y="0"
            width="100%"
            height="100%"
            fill="black"
        />
    </mask>

    <symbol id="star">
        <polygon
            points="26.934,1.318 35.256,18.182 53.867,20.887 40.4,34.013 43.579,52.549 26.934,43.798 
	10.288,52.549 13.467,34.013 0,20.887 18.611,18.182 "
        />
    </symbol>

    <symbol id="stars">
        {#each Array(5) as _, i}
            <use href="#star" x={i * 53.867} />
        {/each}
    </symbol>

    <use href="#stars" style={"fill:" + backgroundColor + ";"} />
    <use
        href="#stars"
        mask={`url(#mask-stars-${rating})`}
        style={"fill:" + color + ";"}
    />
</svg>

<style>
    svg {
        display: block;
    }
</style>
