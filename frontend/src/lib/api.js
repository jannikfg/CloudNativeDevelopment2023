import { error } from '@sveltejs/kit';

async function send({ method, base, path, data }) {
    const opts = { method, headers: {} };

    if (data) {
        opts.headers['Content-Type'] = 'application/json';
        opts.headers['mode'] = 'no-cors';
        opts.body = JSON.stringify(data.requestBody);
    }
    console.log("Logging in API.js" + opts.body);

    const res = await fetch(`${base}/${path}`, opts);
    if (res.ok || res.status === 422) {
        const text = await res.text();
        return text ? JSON.parse(text) : {};
    }

    // @ts-ignore
    throw error(res.status);
}

export function get(base, path) {
    // @ts-ignore
    return send({ method: 'GET', base, path });
}

export function del(base, path) {
    // @ts-ignore
    return send({ method: 'DELETE', base, path });
}

export function post(base, path, data,) {
    return send({ method: 'POST', base, path, data });
}

export function put(base, path, data) {
    return send({ method: 'PUT', base, path, data });
}