<script>
	import { enhance } from '$app/forms';

	export let form;
</script>

<svelte:head>
	<title>Sign in • Conduit</title>
</svelte:head>

<div class="auth-page">
	<div class="container page">
		<div class="row">
			<div class="col-md-6 offset-md-3 col-xs-12">
				<h1 class="text-xs-center">Sign In</h1>
				<p class="text-xs-center">
					<a href="/register">Need an account?</a>
				</p>
				<form use:enhance method="POST">
					<fieldset class="form-group">
						<input
							class="form-control form-control-lg"
							name="email"
							type="email"
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
							placeholder="Password"
						/>
					</fieldset>
					<button class="btn btn-lg btn-primary pull-xs-right" type="submit">Sign in</button>
				</form>
			</div>
		</div>
	</div>
</div>

<style>
	/* allgemeine Stile */
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
