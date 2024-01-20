export class SpendingCategory {
    public id: number;
    public name: string;
    //public base64Image: string;
    //public spendings: Array<Spending>;

    constructor(
        id: number,
        name: string,
        //base64Image: string,
        //spendings: Spending[],
    ) {
        this.id = id;
        this.name = name;
        //this.base64Image = base64Image;
        //this.spendings = spendings;
    }
}