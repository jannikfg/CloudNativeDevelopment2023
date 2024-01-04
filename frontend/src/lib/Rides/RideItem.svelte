<script>
	import Badge from '$lib/Badge.svelte';
	import Button from '$lib/Button.svelte';
	import { createEventDispatcher } from 'svelte';

	export let id;
	export let from;
	export let to;
	export let date;
	export let time;
	export let driver;
	export let email;
	export let description;
	export let isFav;

	const dispatch = createEventDispatcher();
</script>

<article>
	<header>
		<h1>
			{from} - {to} / {date} / {time}
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
		<Button href="mailto:{email}">Contact</Button>
		<Button
			mode="outline"
			color={isFav ? null : 'success'}
			type="button"
			on:click={() => dispatch('togglefavorite', id)}
		>
			{isFav ? 'Unfavorite' : 'Favorite'}
		</Button>
		<Button type="button">Show Details</Button>
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
