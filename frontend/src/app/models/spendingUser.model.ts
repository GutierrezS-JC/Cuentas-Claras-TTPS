import { Spending } from './spending.model';
import { User } from './user.model';

export class SpendingUser {
    public id: number;
    public amount: number;
    //public created_at: Date;
    //public updated_at: Date;

    constructor(
        id: number,
        amount: number, 
        //created_at: Date, 
        //updated_at: Date
    ) {
        this.id = id;
        this.amount = amount;
    }
}