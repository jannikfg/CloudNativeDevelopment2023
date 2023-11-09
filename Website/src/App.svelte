<script>
    import Header from "./UI/Header.svelte";
    import RideGrid from "./Rides/RideGrid.svelte";
    import TextInput from "./UI/TextInput.svelte";
    import Button from "./UI/Button.svelte";

    let from = "";
    let to = "";
    let date = "";
    let time = "";
    let driver = "";
    let description = "";

    let rides = [
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

    function addRide() {
        const newRide = {
            id: Math.random().toString,
            from: from,
            to: to,
            date: date,
            time: time,
            driver: driver,
            description: description,
        };

        rides = [...rides, newRide];
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
            id="description"
            label="Description"
            value={description}
            controlType="textarea"
            rows="3"
            on:input={(event) => (description = event.target.value)}
        />
        <Button type="submit" caption="Save" />
    </form>
    <RideGrid {rides} />
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
