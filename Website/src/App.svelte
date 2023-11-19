<script>
    import Header from "./UI/Header.svelte";
    import RideGrid from "./Rides/RideGrid.svelte";
    import TextInput from "./UI/TextInput.svelte";
    import Button from "./UI/Button.svelte";
    import EditRide from "./Rides/EditRide.svelte";

    let id = "";
    let from = "";
    let to = "";
    let date = "";
    let time = "";
    let driver = "";
    let email = "";
    let description = "";

    let rides = [
        {
            id: "r1",
            from: "Ingolstadt",
            to: "Mailing",
            date: "05.11.2023",
            time: "15:00",
            driver: "Jannik Füllgraf",
            email: "jannik@test.de",
            description: "Dies ist eine Beschreibung der Reise",
            isFavorite: false,
        },
        {
            id: "r2",
            from: "Mailing",
            to: "Ingolstadt",
            date: "05.11.2023",
            time: "17:00",
            driver: "Manuel Wittmann",
            email: "manuel@test.de",
            description: "Dies ist eine Beschreibung der Reise",
            isFavorite: false,
        },
    ];

    let editMode = null;

    function addRide() {
        const newRide = {
            id: Math.random().toString,
            from: from,
            to: to,
            date: date,
            time: time,
            driver: driver,
            email: email,
            description: description,
        };

        rides = [...rides, newRide];
    }

    function togglefavorite(event) {
        const id = event.detail;
        const updatedRide = { ...rides.find((m) => m.id === id) };
        updatedRide.isFavorite = !updatedRide.isFavorite;
        const rideIndex = rides.findIndex((m) => m.id === id);
        const updatedRides = [...rides];
        updatedRides[rideIndex] = updatedRide;
        rides = updatedRides;
    }
</script>

<Header />

<main>
    <Button caption="New Ride" on:click={() => (editMode = "add")} />
    {#if editMode === "add"}
        <EditRide />
    {/if}
    <RideGrid {rides} on:togglefavorite={togglefavorite} />
</main>

<style>
    main {
        margin-top: 5rem;
    }
</style>
