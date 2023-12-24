export class MySpending{
    nameSpendingId: string;
    description: string;
    idSpending: number;
    amount: number;
    createdAt: Date;

    constructor(nameSpending: string, description: string, idSpending: number, amountSpending: number, dateSpending: Date){
        this.nameSpendingId = nameSpending;
        this.description = description;
        this.idSpending = idSpending;
        this.amount = amountSpending;
        this.createdAt = dateSpending;
    }
}