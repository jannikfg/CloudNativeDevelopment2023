<script>
	import { onMount } from 'svelte';
	import Chart from 'chart.js/auto';
	import * as api from '$lib/api';
	import { PUBLIC_ANALYTICSSERVICE_URL } from '$env/static/public';

	function loadRidesPerDay() {
		return api.get(PUBLIC_ANALYTICSSERVICE_URL, 'ridesPerDay');
	}

	function loadRidesPerLocation() {
		return api.get(PUBLIC_ANALYTICSSERVICE_URL, 'ridesPerLocation');
	}

	function loadAveragePassengerCount() {
		return api.get(PUBLIC_ANALYTICSSERVICE_URL, 'averagePassengerCount');
	}

	onMount(async () => {
		let ridesPerDayData = await loadRidesPerDay();
		let ridesPerLocationData = await loadRidesPerLocation();
		let averagePassengerCountData = await loadAveragePassengerCount();

		console.log(averagePassengerCountData);

		const datesDay = ridesPerDayData.map((entry) => entry.date);
		const ridesDay = ridesPerDayData.map((entry) => entry.ridesPerDay);

		const locations = ridesPerLocationData.map((entry) => entry.location);
		const ridesPerLocation = ridesPerLocationData.map((entry) => entry.ridesPerLocation);

		const datesAverage = averagePassengerCountData.map((entry) => entry.date);
		const averagePassengerCount = averagePassengerCountData.map(
			(entry) => entry.averagePassengerCount
		);

		console.log(averagePassengerCount);

		// Create a chart
		// @ts-ignore
		const ctx = document.getElementById('myChart').getContext('2d');
		new Chart(ctx, {
			type: 'bar',
			data: {
				labels: datesDay,
				datasets: [
					{
						label: 'Fahrten pro Tag',
						data: ridesDay,
						backgroundColor: '#cf0056',
						borderColor: '#cf0056',
						borderWidth: 1
					}
				]
			},
			options: {
				scales: {
					y: {
						beginAtZero: true
					}
				}
			}
		});

		// @ts-ignore
		const ctx2 = document.getElementById('myChart2').getContext('2d');
		new Chart(ctx2, {
			type: 'bar',
			data: {
				labels: locations,
				datasets: [
					{
						label: 'Fahrten pro Ort',
						data: ridesPerLocation,
						backgroundColor: '#cf0056',
						borderColor: '#cf0056',
						borderWidth: 1
					}
				]
			},
			options: {
				scales: {
					y: {
						beginAtZero: true
					}
				}
			}
		});

		// @ts-ignore
		const ctx3 = document.getElementById('myChart3').getContext('2d');
		new Chart(ctx3, {
			type: 'bar',
			data: {
				labels: datesAverage,
				datasets: [
					{
						label: 'Durchschnittliche Personenauslastung pro Fahrt',
						data: averagePassengerCount,
						backgroundColor: '#cf0056',
						borderColor: '#cf0056',
						borderWidth: 1
					}
				]
			},
			options: {
				scales: {
					y: {
						beginAtZero: true,
						ticks: {
							callback: function (value, index, values) {
								return value.toFixed(2);
							}
						}
					}
				}
			}
		});
	});
</script>

<svelte:head>
	<title>analytics</title>
</svelte:head>

<div class="analytics">
	<h1 class="analytics_title">Analytics</h1>
	<div class="analytics-views">
		<div class="analytics-view">
			<h2 class="analytics-view_title">Fahrten pro Tag</h2>
			<div class="analytics-view_content">
				<div class="analytics-view_content-graph">
					<canvas id="myChart" width="400" height="400"></canvas>
				</div>
			</div>
		</div>
		<div class="analytics-view">
			<h2 class="analytics-view_title">Fahrten pro Ort</h2>
			<div class="analytics-view_content">
				<div class="analytics-view_content-graph">
					<canvas id="myChart2" width="400" height="400"></canvas>
				</div>
			</div>
		</div>
		<div class="analytics-view">
			<h2 class="analytics-view_title">Durchschnittliche Personenauslastung pro Fahrt</h2>
			<div class="analytics-view_content">
				<div class="analytics-view_content-graph">
					<canvas id="myChart3" width="400" height="400"></canvas>
				</div>
			</div>
		</div>
	</div>
</div>

<style>
	.analytics {
		display: flex;
		flex-direction: column;
		align-items: center;
		padding: 20px;
	}

	.analytics_title {
		border: 1px solid #cf0056;
		color: #cf0056;
		padding: 10px 50px 10px 50px;
		font-size: 24px;
		margin-bottom: 20px;
		border-radius: 4px;
	}

	.analytics-views {
		display: flex;
		gap: 20px;
		flex-wrap: wrap;
		justify-content: center;
	}

	.analytics-view {
		flex: 1;
		display: flex;
		flex-direction: column;
		align-items: center;
		padding: 20px;
		border: 1px solid #ddd;
		border-radius: 8px;
		margin: 10px;
	}

	.analytics-view_title {
		color: #333;
		font-size: 18px;
		margin-bottom: 10px;
	}

	.analytics-view_content {
		display: flex;
		flex-direction: column;
		align-items: center;
	}

	.analytics-view_content-graph {
		margin-top: 20px;
	}
</style>
