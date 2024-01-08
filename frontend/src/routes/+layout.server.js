/** @type {import('./$types').LayoutServerLoad} */
export function load({ locals }) {
	return {
		// @ts-ignore
		user: locals.user && {
			email: locals.user.email,
		}
	};
}
