<script>
	import Badge from '$lib/Badge.svelte';
	import Button from '$lib/Button.svelte';
	import { createEventDispatcher } from 'svelte';
	import { page } from '$app/stores';
	import { user } from '$lib/stores/user-store.ts';
	import * as api from '$lib/api';
	import { PUBLIC_BOOKINGSERVICE_URL } from '$env/static/public';
	import { goto } from '$app/navigation';

	export let id;
	export let origin;
	export let destination;
	export let date;
	export let time;
	export let driver;
	export let email;
	export let description;
	export let isFav;

	const dispatch = createEventDispatcher();

	async function bookRide() {
		let requestBody = {
			user: $user.email,
			date: '2023-10-01',
			rideId: id
		};

    try {
      const res = await send({ method: 'POST', base: PUBLIC_BOOKINGSERVICE_URL, path: 'create', data: requestBody });
      alert('Ride booked!');
    } catch (e) {
      console.log(e);
      alert('Ride could not be booked!');
    }

	}
	async function send({ method, base, path, data }) {
		const opts = { method, headers: {} };

		if (data) {
			opts.headers['Content-Type'] = 'application/json';
			opts.body = JSON.stringify(data);
		}
		console.log('Logging in API.js' + opts.body);

		console.log('Logging in API.js:' + base + '/' + path);

		const res = await fetch(`${base}/${path}`, opts);
		if (res.ok || res.status === 422) {
			const text = await res.text();
			return text ? JSON.parse(text) : {};
		}

		// @ts-ignore
		throw error(res.status);
	}
</script>

<article>
	<header>
		<h1>
			{origin} - {destination} / {date} / {time}
			{#if isFav}
				<Badge>Favorite</Badge>
			{/if}
		</h1>
	</header>
	<div class="content">
		<p>{driver}</p>
		<p>{description}</p>
	</div>
	<footer>
		{#if $page.url.pathname !== '/plannedRides'}
			<Button href="mailto:{email}">Contact</Button>
			<Button type="button" on:click={bookRide}>Book Ride</Button>
		{/if}
	</footer>
</article>

<style>
	article {
		box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
		border-radius: 5px;
		background: white;
		margin: 1rem;
	}

	header,
	.content,
	footer {
		padding: 1rem;
	}

	.image {
		width: 100%;
		height: 14rem;
	}

	.image img {
		width: 100%;
		height: 100%;
		object-fit: cover;
	}

	h1 {
		font-size: 1.25rem;
		margin: 0.5rem 0;
		font-family: 'Roboto Slab', sans-serif;
	}

	h1.is-favorite {
		background: #01a129;
		color: white;
		padding: 0 0.5rem;
		border-radius: 5px;
	}

	h2 {
		font-size: 1rem;
		color: #808080;
		margin: 0.5rem 0;
	}

	p {
		font-size: 1.25rem;
		margin: 0;
	}

	div {
		text-align: right;
	}

	.content {
		height: 4rem;
	}
</style>
