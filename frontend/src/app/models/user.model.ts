import { Group } from './group.model';
import { Spending } from './spending.model';

export class User {
    public id: number;
    public email: string;
    public amount: number;
    //public username: string;
    //public password: string; //hasheado?
    public name: string;
    //public lastName: string;
    //public profilepicBase64: string;
    //public ownedGroups: Array<Group>;
    //public myCreatedSpendings: Array<Spending>;
    //public ownedPayments: Array<Payment>;
    //public payments: Array<Payment>;
    //public contacts: Array<UserContact>;
    //public groups: Array<Group>;
    //public spendings: Array<Spending>;

    constructor (
        id: number,
        email: string,
        amount: number,
        //username: string,
        //password: string,
        name: string,
        //lastName: string,
        //profilepicBase64: string,
        //ownedGroups: Group[],
        //myCreatedSpendings: Spending[],
        //ownedPayments: Payment[],
        //payments: Payment[],
        //contacts: UserContact[],
        //groups: Group[],
        //spendings: Spending[],
    ) {
        this.id = id;
        this.email = email;
        this.amount = amount;
        //this.username = username;
        //this.password = password;
        this.name = name;
        //this.lastName = lastName;
        //this.profilepicBase64 = profilepicBase64;
        //this.ownedGroups = ownedGroups;
        //this.myCreatedSpendings = myCreatedSpendings;
        //this.ownedPayments = ownedPayments;
        //this.payments = payments;
        //this.contacts = contacts;
        //this.groups = groups;
        //this.spendings = spendings;
    }

}