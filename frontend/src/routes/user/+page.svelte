<script>
	import { onMount } from 'svelte';
	import * as api from '$lib/api';
	import { PUBLIC_USERSERVICE_URL } from '$env/static/public';

	let user = null;

	onMount(async () => {
		user = await loadUser();
	});

	function loadUser() {
		return api.get(PUBLIC_USERSERVICE_URL, 'user');
	}
</script>

<h1>HEllo</h1>
