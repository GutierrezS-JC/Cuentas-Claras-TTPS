import { Group } from './group.model'
import { SpendingUser } from './spendingUser.model';

export class Spending {
    public name: string;
    public description: string;
    public totalAmount: number;
    public createdAt: Date;
    public endingDate?: Date;
    public proofOfPayment?: string;
    public recurrence: string;
    public division: string;
    public owner: number;
    public spendingCategory: number;
    public group: Group;
    public users: Array<SpendingUser>;
    
    constructor(
        name: string,
        description: string,
        totalAmount: number,
        createdAt: Date,
        endingDate: Date,
        proofOfPayment: string,
        recurrence: string,
        division: string,
        owner: number,
        spendingCategory: number,
        group: Group,
        users: SpendingUser[],
    ) {
        this.name = name;
        this.description = description;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.endingDate = endingDate;
        this.proofOfPayment = proofOfPayment;
        this.recurrence = recurrence;
        this.division = division;
        this.owner = owner;
        this.spendingCategory = spendingCategory;
        this.group = group;
        this.users = users;
    }

}