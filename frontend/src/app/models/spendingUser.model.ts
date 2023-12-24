export class SpendingUser {
    public userId: number;
    public amount: number;

    constructor(
        userId: number,
        amount: number, 
    ) {
        this.userId = userId;
        this.amount = amount;
    }
}