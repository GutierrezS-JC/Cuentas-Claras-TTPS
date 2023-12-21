import { User } from './user.model';

export class UserContact {
    public user: User;
    public contact: User;
    public contactSince: Date;

    constructor
    (
        user: User, 
        contact: User, 
        contactSince: Date
    ) {
        this.user = user;
        this.contact = contact;
        this.contactSince = contactSince;
    }
}