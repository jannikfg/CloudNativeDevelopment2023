<script lang="ts">
	import Button from '$lib/Button.svelte';
	import EditRide from '$lib/Rides/EditRide.svelte';
	import RideGrid from '$lib/Rides/RideGrid.svelte';
	import { onMount } from 'svelte';
	import * as api from '$lib/api';
	import { PUBLIC_RIDESERVICE_URL } from '$env/static/public';

	let rides: object[] = [];

	let open: boolean;

	let id = '';
	let from = '';
	let to = '';
	let date = '';
	let time = '';
	let driver = '';
	let email = '';
	let description = '';

	let editMode: 'add' | null = null;

	function addRide(
		event: CustomEvent<{
			from: any;
			to: any;
			date: any;
			time: any;
			driver: any;
			email: any;
			description: any;
			isFavorite: any;
		}>
	) {
		const newRide = {
			id: Math.random().toString(),
			from: event.detail.from,
			to: event.detail.to,
			date: event.detail.date,
			time: event.detail.time,
			driver: event.detail.driver,
			email: event.detail.email,
			description: event.detail.description,
			isFavorite: event.detail.isFavorite
		};
		editMode = null;
	}

	onMount(async () => {
		rides = await loadAllRides();
	});

	async function loadAllRides() {
		return api.get(PUBLIC_RIDESERVICE_URL, 'all');
	}

	/*
	function togglefavorite(event: CustomEvent<string>) {
		const id = event.detail;
		const rideIndex = rides.findIndex((m) => m.id === id);
		rides[rideIndex].isFavorite = !rides[rideIndex].isFavorite;
		rides = rides;
	}
	*/
</script>

<main>
	<div>
		<h3>Eigene Fahrten kannst du unter "Geplante Fahrten" hinzufügen, wenn du eingeloggt bist</h3>
	</div>

	<RideGrid {rides} />
</main>

<style>
	main {
		margin-top: 5rem;
	}

	.ride-controls {
		margin: 1rem;
	}
</style>
