import { Group } from './group.model'
import { User } from './user.model'
import { RecurrentEnum } from './recurrentEnum.model';
import { DivisionEnum } from './divisionEnum.model';
import { SpendingCategory } from './spendingCategory.model';

export class Spending {
    public name: string;
    public description: string;
    public totalAmount: number;
    public createdAt: Date;
    public endingDate: Date;
    public proofOfPayment: string;
    public recurrence: RecurrentEnum;
    public division: DivisionEnum;
    public owner: User;
    public spendingCategory: SpendingCategory;
    public group?: Group;
    public users: Array<User>;
    
    constructor(
        name: string,
        description: string,
        totalAmount: number,
        createdAt: Date,
        endingDate: Date,
        proofOfPayment: string,
        recurrence: RecurrentEnum,
        division: DivisionEnum,
        owner: User,
        spendingCategory: SpendingCategory,
        group: Group,
        users: User[],
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