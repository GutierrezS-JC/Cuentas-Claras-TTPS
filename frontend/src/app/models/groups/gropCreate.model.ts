export class GroupCreate {
    name: string;
    totalBalance: number;
    userOwnerId: number;
    description: string;
    groupCategoryId: number;
    usersIds: any;

    constructor(name: string, totalBalance: number, userOwnerId: number, description: string,
        groupCategoryId: number, usersIds: []) {
        this.name = name;
        this.totalBalance = totalBalance;
        this.userOwnerId = userOwnerId;
        this.description = description;
        this.groupCategoryId = groupCategoryId;
        this.usersIds = usersIds;
    }
}