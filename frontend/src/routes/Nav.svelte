<script>
	import { page } from '$app/stores';
	import { user } from '$lib/stores/user-store.ts';
</script>

<nav class="navbar navbar-light">
	<div class="container">
		<a class="navbar-brand" href="/">RedeRede Auto</a>
		<ul class="nav navbar-nav pull-xs-right">
			<li class="nav-item">
				<a class="nav-link" class:active={$page.url.pathname === '/'} href="/">Home</a>
			</li>

			{#if $user.verified}
				<li class="nav-item">
					<a href="/bookings" class="nav-link" class:active={$page.url.pathname === '/bookings'}>
						<i class="ion-compose" />&nbsp;Buchungen
					</a>
				</li>

				<li class="nav-item">
					<a
						href="/plannedRides"
						class="nav-link"
						class:active={$page.url.pathname === '/plannedRides'}
					>
						<i class="ion-gear-a" />&nbsp;Geplante Fahrten
					</a>
				</li>

				<li class="nav-item">
					<a href="/user" class="nav-link">{$user.firstName}</a>
				</li>
			{:else if $user.verified === false}
				<li class="nav-item">
					<a href="/login" class="nav-link" class:active={$page.url.pathname === '/login'}>
						Sign in
					</a>
				</li>

				<li class="nav-item">
					<a href="/register" class="nav-link" class:active={$page.url.pathname === '/register'}>
						Sign up
					</a>
				</li>
			{/if}
		</ul>
	</div>
</nav>

<style>
	body {
		font-family: Arial, sans-serif;
		margin: 0;
		padding: 0;
		background-color: #ffffff; /* Hintergrundfarbe der Seite */
	}

	.container {
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 10px;
	}

	.navbar-brand {
		font-weight: bold;
		color: #ffffff; /* Weiße Schriftfarbe für die Brand */
		text-decoration: none; /* Entferne die Unterstreichung */
	}

	.navbar-light {
		background-color: #cf0056; /* Hintergrundfarbe für die Navbar */
	}

	.navbar-nav {
		list-style: none;
		display: flex;
		justify-content: flex-end;
		align-items: center;
		padding: 0;
	}

	.nav-item {
		margin-left: 15px;
	}

	.nav-link {
		text-decoration: none;
		color: #ffffff; /* Weiße Schriftfarbe für die Links */
	}

	/* aktiver Link-Stil */
	.nav-link.active {
		font-weight: bold;
	}

	/* Hover-Stil für Links */
	.nav-link:hover {
		color: #ffffff; /* Weiße Schriftfarbe für die Hover-Links */
	}
</style>
