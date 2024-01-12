<script>
	import { onMount } from 'svelte';
	import * as api from '$lib/api';
	import { PUBLIC_USERSERVICE_URL } from '$env/static/public';
	import { PUBLIC_BOOKINGSERVICE_URL } from '$env/static/public';
	import BookingGrid from '$lib/bookings/BookingGrid.svelte';
	import { user } from '$lib/stores/user-store.ts';

	let userObject;
	let bookings = [];

	onMount(async () => {
		userObject = await loadUser();
		bookings = await loadBookings();
	});

	function loadUser() {
		let path = 'user/' + $user.email;
		return api.get(PUBLIC_USERSERVICE_URL, path);
	}

	function loadBookings() {
		return api.get(PUBLIC_BOOKINGSERVICE_URL, `user/${userObject.email}`);
	}
</script>

<svelte:head>
	<title>Bookings</title>
</svelte:head>

<div class="bookings">
	<h1 class="bookings_title">Bookings</h1>

	<div class="bookings_list">
		<BookingGrid {bookings} />
	</div>
</div>

<style>
	.bookings {
		display: flex;
		flex-direction: column;
		align-items: center;
		padding: 20px;
	}

	.bookings_title {
		border: 1px solid #cf0056;
		color: #cf0056;
		padding: 10px 50px 10px 50px;
		font-size: 24px;
		margin-bottom: 20px;
		border-radius: 4px;
	}
</style>
