import { writable } from "svelte/store";

export interface User {
    firstName: string;
    lastName: string;
    email: string;
    birthDate: Date;
}

export const userStore = writable<User>(undefined);


