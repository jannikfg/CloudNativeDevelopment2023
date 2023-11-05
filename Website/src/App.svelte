<script>
    import Header from "./UI/Header.svelte";
    import RideGrid from "./Rides/RideGrid.svelte";

    const rides = [
        {
            id: "r1",
            from: "Ingolstadt",
            to: "Mailing",
            date: "05.11.2023",
            time: "15:00",
            driver: "Jannik Füllgraf",
            description: "Dies ist eine Beschreibung der Reise",
        },
        {
            id: "r2",
            from: "Mailing",
            to: "Ingolstadt",
            date: "05.11.2023",
            time: "17:00",
            driver: "Manuel Wittmann",
            description: "Dies ist eine Beschreibung der Reise",
        },
    ];
</script>

<Header />

<main>
    <RideGrid {rides} />
</main>

<style>
</style>
