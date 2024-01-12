<script>
	import { enhance } from '$app/forms';
	import { user } from '$lib/stores/user-store.ts';
	import { goto } from '$app/navigation';
	import { PUBLIC_USERSERVICE_URL } from '$env/static/public';

	let email = '';
	let password = '';
	let firstName = '';
	let lastName = '';
	let birthDate = '';

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

	async function sendRegistration() {
		const requestBody = {
			firstName: firstName,
			lastName: lastName,
			email: email,
			password: password,
			birthDate: birthDate
		};
		console.log(requestBody);
		return send({
			method: 'POST',
			base: PUBLIC_USERSERVICE_URL,
			path: 'create',
			data: requestBody
		});
	}

	async function registerUser() {
		let response = await sendRegistration();
		console.log(response);
		if (response.email === email) {
			console.log($user.verified);
			$user.verified = true;
			$user.email = email;
			$user.firstName = firstName;
			goto('/plannedRides');
		} else {
			alert('Wrong email or password');
		}
	}
</script>

<svelte:head>
	<title>Sign up • Conduit</title>
</svelte:head>

<div class="auth-page">
	<div class="container page">
		<div class="row">
			<div class="col-md-6 offset-md-3 col-xs-12">
				<h1 class="text-xs-center">Sign up</h1>
				<p class="text-xs-center">
					<a href="/login">Have an account?</a>
				</p>

				<form on:submit|preventDefault={registerUser}>
					<fieldset class="form-group">
						<input
							class="form-control form-control-lg"
							name="firstName"
							type="text"
							required
							bind:value={firstName}
							placeholder="Your Firstname"
						/>
					</fieldset>
					<fieldset class="form-group">
						<input
							class="form-control form-control-lg"
							name="lastName"
							type="text"
							bind:value={lastName}
							required
							placeholder="Your Lastname"
						/>
					</fieldset>
					<fieldset class="form-group">
						<input
							class="form-control form-control-lg"
							name="email"
							type="email"
							bind:value={email}
							required
							placeholder="Email"
						/>
					</fieldset>
					<fieldset class="form-group">
						<input
							class="form-control form-control-lg"
							name="password"
							type="password"
							required
							bind:value={password}
							placeholder="Password"
						/>
					</fieldset>
					<fieldset class="form-group">
						<input
							class="form-control form-control-lg"
							name="birthDate"
							type="text"
							required
							bind:value={birthDate}
							placeholder="Birthdate"
						/>
					</fieldset>
					<button class="btn btn-lg btn-primary pull-xs-right">Sign up</button>
				</form>
			</div>
		</div>
	</div>
</div>

<style>
	body {
		font-family: Arial, sans-serif;
		margin: 0;
		padding: 0;
		background-color: #ffffff; /* Hintergrundfarbe der Seite */
	}

	.container {
		display: flex;
		justify-content: center; /* Alle Inhalte in der Mitte ausrichten */
		align-items: center;
	}

	.auth-page {
		text-align: center; /* Zentrierung des Inhalts */
	}

	h1 {
		font-weight: bold;
		color: #cf0056; /* Überschrift-Farbe */
		font-size: 2em;
	}

	a {
		color: #cf0056; /* Link-Farbe */
		text-decoration: none; /* Entferne Unterstreichung */
	}

	.form-control {
		margin-bottom: 20px;
		font-size: 1.1em;
	}

	.btn-primary {
		background-color: #cf0056; /* Button-Hintergrundfarbe */
		border-color: #cf0056; /* Button-Rahmenfarbe */
		font-size: 1.1em;
	}

	.btn-primary:hover {
		background-color: #ffffff; /* Button-Hintergrundfarbe beim Hover */
		color: #cf0056; /* Textfarbe des Buttons beim Hover */
	}
</style>
