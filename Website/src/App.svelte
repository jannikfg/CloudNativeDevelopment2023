<script>
    import Header from "./UI/Header.svelte";
    import RideGrid from "./Rides/RideGrid.svelte";
    import TextInput from "./UI/TextInput.svelte";
    import Button from "./UI/Button.svelte";

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
    <form on:submit|preventDefault={addRide}>
        <TextInput
            id="from"
            label="From"
            value={from}
            type="text"
            on:input={(event) => (from = event.target.value)}
        />

        <TextInput
            id="to"
            label="Toooo"
            value={to}
            type="text"
            on:input={(event) => (to = event.target.value)}
        />

        <TextInput
            id="date"
            label="Date"
            value={date}
            type="text"
            on:input={(event) => (date = event.target.value)}
        />

        <TextInput
            id="time"
            label="Time"
            value={time}
            type="text"
            on:input={(event) => (time = event.target.value)}
        />

        <TextInput
            id="driver"
            label="Driver"
            value={driver}
            type="text"
            on:input={(event) => (driver = event.target.value)}
        />

        <TextInput
            id="email"
            label="Email"
            value={email}
            type="text"
            on:input={(event) => (email = event.target.value)}
        />

        <TextInput
            id="description"
            label="Description"
            value={description}
            controlType="textarea"
            rows="3"
            on:input={(event) => (description = event.target.value)}
        />
        <Button type="submit" caption="Save" />
    </form>
    <RideGrid {rides} on:togglefavorite={togglefavorite} />
</main>

<style>
    main {
        margin-top: 5rem;
    }

    form {
        width: 30rem;
        max-width: 90%;
        margin: auto;
    }
</style>
