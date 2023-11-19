<script>
    import RideItem from "./RideItem.svelte";

    export let rides;
</script>

<section id="rides">
    {#each rides as ride}
        <RideItem
            id={ride.id}
            from={ride.from}
            to={ride.to}
            date={ride.date}
            time={ride.time}
            driver={ride.driver}
            email={ride.email}
            description={ride.description}
            isFav={ride.isFavorite}
            on:togglefavorite
        />
    {/each}
</section>

<style>
    section {
        width: 100%;
        display: grid;
        grid-template-columns: 1fr;
        grid-gap: 1rem;
    }

    @media (min-width: 768px) {
        section {
            grid-template-columns: repeat(2, 1fr);
        }
    }
    #rides {
        margin-top: 5rem;
    }
</style>
