<script>
	// @ts-nocheck

	import Button from '$lib/Button.svelte';
	import Modal from '$lib/Modal.svelte';
	import TextInput from '$lib/TextInput.svelte';
	import { createEventDispatcher } from 'svelte';

	let id = '';
	let from = '';
	let to = '';
	let date = '';
	let time = '';
	let driver = '';
	let email = '';
	let description = '';

	const dispatch = createEventDispatcher();

	function submitForm() {
		dispatch('save', {
			from: from,
			to: to,
			date: date,
			time: time,
			driver: driver,
			email: email,
			description: description
		});
	}

	function cancel() {
		dispatch('cancel');
	}
</script>

<Modal title="Edit Ride Data" on:cancel>
	<form on:submit|preventDefault={submitForm}>
		<TextInput
			id="from"
			label="From"
			value={from}
			type="text"
			on:input={(event) => (from = event.target.value)}
		/>

		<TextInput
			id="to"
			label="To"
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
