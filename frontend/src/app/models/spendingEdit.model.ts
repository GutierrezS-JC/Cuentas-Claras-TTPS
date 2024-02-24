export class SpendingEdit {
    public id: number;
    public name?: string;
    public description?: string;
    public totalAmount?: number;
    public endingDate?: Date;
    public proofOfPayment?: string;
    public recurrence?: string;
    public division?: string;
    public spendingCategoryId?: number;

    constructor(
        id: number,
        name?: string,
        description?: string,
        totalAmount?: number,
        endingDate?: Date,
        proofOfPayment?: string,
        recurrence?: string,
        division?: string,
        spendingCategoryId?: number
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.totalAmount = totalAmount;
        this.endingDate = endingDate;
        this.proofOfPayment = proofOfPayment;
        this.recurrence = recurrence;
        this.division = division;
        this.spendingCategoryId = spendingCategoryId;
    }
}