import { Injectable, signal } from "@angular/core";
import { User } from "../../models/user/user.model";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    currentUserSignal = signal<User | undefined | null>(undefined)
}