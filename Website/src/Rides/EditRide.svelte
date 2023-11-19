<script>
    import { createEventDispatcher } from "svelte";
    import TextInput from "../UI/TextInput.svelte";
    import Button from "../UI/Button.svelte";

    let id = "";
    let from = "";
    let to = "";
    let date = "";
    let time = "";
    let driver = "";
    let email = "";
    let description = "";

    const dispatch = createEventDispatcher();

    function submitForm() {
        dispatch("save", {
            from: from,
            to: to,
            date: date,
            time: time,
            driver: driver,
            email: email,
            description: description,
        });
    }
</script>

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

<style>
    form {
        width: 30rem;
        max-width: 90%;
        margin: auto;
    }
</style>
