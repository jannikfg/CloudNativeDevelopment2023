import { fail, redirect } from '@sveltejs/kit';
import * as api from '$lib/api.js';

const PUBLIC_USERSERVICE_URL = 'http://localhost:80/users/api/v1/users';
/** @type {import('./$types').PageServerLoad} */
export async function load({ parent }) {
	const { user } = await parent();
	if (user) throw redirect(307, '/');
}

/** @type {import('./$types').Actions} */
export const actions = {
	default: async ({ cookies, request }) => {
		const data = await request.formData();

		console.log("Anfrage eingegangen")

		const user = {
			firstName: "Jannik",
			lastName: "Wiese",
			email: data.get('email'),
			password: data.get('password'),
			birthDate: "2000-01-01",
		};

		const requestBody = user;
		const body = await api.post(PUBLIC_USERSERVICE_URL, 'create', { requestBody });

		if (body.errors) {
			return fail(401, body);
		}
		console.log(body);
		const value = btoa(JSON.stringify(body));
		cookies.set('jwt', value, { path: '/' });

		throw redirect(307, '/');

	}
};
