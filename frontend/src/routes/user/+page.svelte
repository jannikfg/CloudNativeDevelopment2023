<script>
	import { onMount } from 'svelte';
	import * as api from '$lib/api';
	import { PUBLIC_USERSERVICE_URL } from '$env/static/public';
	import { PUBLIC_IMAGESERVICE_URL } from '$env/static/public';
	import { enhance } from '$app/forms';
	import { user } from '$lib/stores/user-store.ts';

	let userObject = null;

	onMount(async () => {
		userObject = await loadUser();
		const imageId = userObject.email;
		$user.firstName = userObject.firstName;
		await loadImage(imageId);
	});

	function loadUser() {
		// TODO: Get user from session
		let path = 'user/' + $user.email;
		return api.get(PUBLIC_USERSERVICE_URL, path);
	}

	let image = '';

	async function loadImage(imageId) {
		console.log('Loading image');
		const res = await fetch(`${PUBLIC_IMAGESERVICE_URL}/${imageId}`, {
			method: 'GET',
			headers: {
				'Content-Type': 'text/text'
			}
		});

		const imageBase64 = await res.text();
		image = 'data:image;base64,' + imageBase64;
	}
</script>

<svelte:head>
	<title>Profile</title>
</svelte:head>

<div class="profile">
	<img class="profile_avatar" src={image} alt="user" />
	<form
		method="post"
		use:enhance={() => {
			return async ({ result }) => {
				loadImage(userObject.email);
				const uploadButton = document.querySelector('.profile_upload_button');
				if (uploadButton) {
					uploadButton.value = '';
				}
			};
		}}
		action="{PUBLIC_IMAGESERVICE_URL}/{userObject?.email}"
		enctype="multipart/form-data"
		class="profile_form"
	>
		<input class="profile_upload_button" type="file" name="file" />
		<button class="profile_submit_button" type="submit">Upload</button>
	</form>

	<table class="profile_table">
		<tr>
			<td class="profile_table_label">First Name:</td>
			<td class="profile_table_value">{userObject?.firstName}</td>
		</tr>
		<tr>
			<td class="profile_table_label">Last Name:</td>
			<td class="profile_table_value">{userObject?.lastName}</td>
		</tr>
		<tr>
			<td class="profile_table_label">E-Mail:</td>
			<td class="profile_table_value">{userObject?.email}</td>
		</tr>
		<tr>
			<td class="profile_table_label">Birthdate:</td>
			<td class="profile_table_value">{userObject?.birthDate}</td>
		</tr>
	</table>
</div>

<style>
	.profile {
		display: flex;
		flex-direction: column;
		align-items: center;
		margin-top: 2rem;
	}

	.profile_avatar {
		width: 300px;
		height: 300px;
		border-radius: 50%;
		object-fit: cover;
		margin-bottom: 1rem;
	}

	.profile_form {
		display: flex;
		flex-direction: column;
		align-items: center;
		margin-top: 3rem;
	}

	.profile_table {
		margin-top: 5rem;
		border-collapse: collapse;
		width: 50%;
	}

	.profile_table td {
		padding: 0.5rem;
		border-bottom: 1px solid #ccc;
	}

	.profile_table_label {
		font-weight: bold;
	}

	.profile_table_value {
		padding-left: 1rem;
	}

	.profile_submit_button,
	.profile_upload_button {
		padding: 0.5rem 1rem;
		background-color: #cf0056;
		color: white;
		border: 1px solid #cf0056;
		border-radius: 4px;
		cursor: pointer;
		transition: background-color 0.3s ease;
	}

	.profile_submit_button:hover,
	.profile_upload_button:hover {
		background-color: #ffffff;
		color: #cf0056;
		border-color: #cf0056;
	}

	.profile_submit_button {
		margin-top: 1rem;
	}
</style>
