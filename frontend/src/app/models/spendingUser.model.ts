export class SpendingUser {
    public id: number;
    public amount: number;

    constructor(
        id: number,
        amount: number, 
    ) {
        this.id = id;
        this.amount = amount;
    }
}