import { User } from './user.model';
import { Spending } from './spending.model';

export class Group {

    public name: string;
    //public totalBalance: number;
    //public owner: User;
    //public users: Array<User>;
    //public groupCategory: GroupCategory;
    //public spendings: Array<Spending>;
    //public payments: Array<Payment>;

    constructor(
        name: string,
        //totalBalance: number,
        //owner: User,
        //users: User[],
        //groupCategory: GroupCategory,
        //spendings: Spending[],
        //payments: Payment[],
    ) {
        this.name = name;
        //this.totalBalance = totalBalance;
        //this.owner = owner;
        //this.users = users;
        //this.groupCategory = groupCategory;
        //this.spendings = spendings;
        //this.payments = payments;
    }
}