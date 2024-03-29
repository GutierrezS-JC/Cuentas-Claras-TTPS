import { User } from './user.model';
import { Spending } from './spending.model';

export class Group {
    public groupId: number;
    public name: string;
    public totalBalance: number;
    public owner: User;
    public members: Array<User>;
    public groupCategory: any;
    public spendings: Array<Spending>;
    //public payments: Array<any>;

    constructor(
        groupId: number,
        name: string,
        totalBalance: number,
        owner: User,
        members: User[],
        groupCategory: any,
        spendings: Spending[],
      //  payments: any[],
    ) {
        this.groupId = groupId;
        this.name = name;
        this.totalBalance = totalBalance;
        this.owner = owner;
        this.members = members;
        this.groupCategory = groupCategory;
        this.spendings = spendings;
 //       this.payments = payments;
    }
}