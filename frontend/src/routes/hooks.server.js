/** @type {import('@sveltejs/kit').Handle} */
export function handle({ event, resolve }) {
	const jwt = event.cookies.get('jwt');
	// @ts-ignore
	event.locals.user = jwt ? JSON.parse(atob(jwt)) : null;
	console.log("User in hooks server: " + event.locals.user);

	return resolve(event);
}
