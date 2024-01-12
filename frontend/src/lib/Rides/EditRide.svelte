<script>
	// @ts-nocheck

	import Button from '$lib/Button.svelte';
	import Modal from '$lib/Modal.svelte';
	import TextInput from '$lib/TextInput.svelte';
	import { createEventDispatcher } from 'svelte';

	let id = '';
	let origin = '';
	let destination = '';
	let date = '';
	let time = '';
	let driver = '';
	let email = '';
	let description = '';
	let capacity = 0;

	const dispatch = createEventDispatcher();

	function submitForm() {
		dispatch('save', {
			origin: origin,
			destination: destination,
			date: date,
			time: time,
			driver: driver,
			email: email,
			description: description,
			capacity: capacity
		});
	}

	function cancel() {
		dispatch('cancel');
	}
</script>

<Modal title="Edit Ride Data" on:cancel>
	<form on:submit|preventDefault={submitForm}>
		<TextInput
			id="origin"
			label="Origin"
			value={origin}
			type="text"
			on:input={(event) => (origin = event.target.value)}
		/>

		<TextInput
			id="destination"
			label="Destination"
			value={destination}
			type="text"
			on:input={(event) => (destination = event.target.value)}
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
		<TextInput
			id="capacity"
			label="Capacity"
			value={capacity}
			type="number"
			on:input={(event) => (capacity = event.target.value)}
		/>
	</form>

	<div slot="footer">
		<Button type="button" mode="outline" on:click={cancel}>Cancel</Button>
		<Button type="button" on:click={submitForm}>Save</Button>
	</div>
</Modal>

<style>
	form {
		width: 100%;
	}
</style>
