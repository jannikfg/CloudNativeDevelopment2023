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
