<script lang="ts">
    import Sidebar from "../../components/sidebar/Sidebar.svelte";
    import Map from "../../components/map/Map.svelte";

    import {page} from "$app/stores";
    import {selectedRestroom} from "$lib/store/selectedRestroomStore";

    $: $page.params, selectedRestroom.select(parseInt($page.params.restroomId));
</script>

<div class="main">
    <div class="sidebar-container">
        <Sidebar />
    </div>
    <div class="map-container">
        <Map />
    </div>
</div>

<style>
    @import url("https://fonts.googleapis.com/css2?family=Geologica:wght@300;400;500;600;700&display=swap");

    :global(body) {
        font-family: "Geologica", Helvetica, sans-serif;
        margin: 0;
    }

    .main {
        display: grid;
        width: 100vw;
        height: 100vh;
        max-width: 100vw;
        max-height: 100vh;
    }

    @media (min-width: 60em) {
        .main {
            grid-template-columns: 32em calc(100vw - 32em);
        }
    }

    @media (max-width: 60em) {
        .map-container {
            position: absolute;
            width: 100vw;
            height: 80vh;
        }

        .sidebar-container {
            position: absolute;
            top: 80vh;
            width: 100vw;
            height: 100vh;
        }
    }

    .sidebar-container {
        z-index: 54;
    }
</style>
