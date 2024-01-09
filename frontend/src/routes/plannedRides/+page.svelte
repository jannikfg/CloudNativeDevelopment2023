<script>
	import Button from '$lib/Button.svelte';
	import EditRide from '$lib/Rides/EditRide.svelte';
	import RideGrid from '$lib/Rides/RideGrid.svelte';
	import { onMount } from 'svelte';
	import * as api from '$lib/api';
	import { PUBLIC_RIDESERVICE_URL } from '$env/static/public';

	let editMode = null;

	let rides = [];

	let email = '';

	onMount(async () => {
		rides = await loadAllCustomRides(email);
	});

	async function loadAllRides() {
		return api.get(PUBLIC_RIDESERVICE_URL, 'all');
	}

	function filterRides(rides, email) {
		return rides.filter((ride) => ride.email === email);
	}

	async function loadAllCustomRides(email) {
		return loadAllRides().then((rides) => filterRides(rides, email));
	}

	async function createNewRide(ride) {
		const requestBody = ride;

		return api.post(PUBLIC_RIDESERVICE_URL, 'create', requestBody);
	}

	async function addRide(event) {
		const newRide = {
			origin: event.detail.to,
			destination: event.detail.destination,
			date: event.detail.date,
			time: event.detail.time,
			driver: event.detail.driver,
			description: event.detail.description
		};

		console.log(newRide);

		await createNewRide(newRide);

		// @ts-ignore
		rides = loadAllCustomRides(email);
		editMode = null;
	}
</script>

<main>
	<div class="ride-controls">
		<Button on:click={() => (editMode = 'add')}>New Ride</Button>
	</div>
	{#if editMode === 'add'}
		<EditRide on:save={addRide} />
	{/if}

	<RideGrid {rides} />
	<!--<RideGrid {rides} on:togglefavorite={togglefavorite} /> -->
</main>

<style>
	main {
		margin-top: 5rem;
	}

	.ride-controls {
		margin: 1rem;
	}
</style>
