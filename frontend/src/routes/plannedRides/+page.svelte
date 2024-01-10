<script lang="ts">
	import Button from '$lib/Button.svelte';
	import EditRide from '$lib/Rides/EditRide.svelte';
	import RideGrid from '$lib/Rides/RideGrid.svelte';
	import { onMount } from 'svelte';
	import * as api from '$lib/api';

	const PUBLIC_RIDESERVICE_URL = 'http://localhost:80/bookings/api/v1/rides';

	let editMode = null;

	let rides = [];

	let email = 'test@test.de';

	onMount(async () => {
		rides = await loadAllCustomRides(email);
	});

	async function loadAllRides() {
		return api.get(PUBLIC_RIDESERVICE_URL, 'all');
	}

	function filterRides(rides, email) {
		console.log('filterRides');
		return rides.filter((ride) => ride.driver === email);
	}

	async function loadAllCustomRides(email) {
		return loadAllRides().then((rides) => filterRides(rides, email));
	}

	interface rideCreationRequest {
		origin: string;
		destination: string;
		date: string;
		time: string;
		driver: string;
		description: string;
		capacity: number;
	}

	async function createNewRide(ride) {
		const requestBody: rideCreationRequest = {
			origin: ride.origin,
			destination: ride.destination,
			date: ride.date,
			time: ride.time,
			driver: ride.driver,
			description: ride.description,
			capacity: 4
		};
		return send({
			method: 'POST',
			base: PUBLIC_RIDESERVICE_URL,
			path: 'create',
			data: requestBody
		});
	}

	async function send({ method, base, path, data }) {
		const opts = { method, headers: {} };

		if (data) {
			opts.headers['Content-Type'] = 'application/json';
			opts.body = JSON.stringify(data);
		}
		console.log('Logging in API.js' + opts.method);

		console.log('Logging in API.js:' + base + '/' + path);

		const res = await fetch(`${base}/${path}`, opts);
		if (res.ok || res.status === 422) {
			const text = await res.text();
			return text ? JSON.parse(text) : {};
		}

		// @ts-ignore
		throw error(res.status);
	}

	async function addRide(event) {
		const newRide = {
			origin: event.detail.origin,
			destination: event.detail.destination,
			date: event.detail.date,
			time: event.detail.time,
			driver: event.detail.driver,
			description: event.detail.description,
			capacity: 4
		};

		await createNewRide(newRide);

		// @ts-ignore
		rides = await loadAllCustomRides(email);
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
