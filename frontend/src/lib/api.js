import { error } from '@sveltejs/kit';

const baseUsers = 'https://api.realworld.io/api';
const baseRides = 'http://bookingservice/api/v1/rides'
const baseBookings = 'http://bookingservice/api/v1/bookings'
const baseAnalytics = 'http://analyticsservice/api/v1/analytics'

async function send({ method, base, path, data }) {
    const opts = { method, headers: {} };

    if (data) {
        opts.headers['Content-Type'] = 'application/json';
        opts.body = JSON.stringify(data);
    }

    const res = await fetch(`${base}/${path}`, opts);
    if (res.ok || res.status === 422) {
        const text = await res.text();
        return text ? JSON.parse(text) : {};
    }

    // @ts-ignore
    throw error(res.status);
}

export function get(base, path) {
    return send({ method: 'GET', base, path });
}

export function del(path, token) {
    return send({ method: 'DELETE', path, token });
}

export function post(path, data, token) {
    return send({ method: 'POST', path, data, token });
}

export function put(path, data, token) {
    return send({ method: 'PUT', path, data, token });
}