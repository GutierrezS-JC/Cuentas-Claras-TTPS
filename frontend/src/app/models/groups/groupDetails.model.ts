export class GroupDetails {
    groupId: number;
    name: string;
    totalBalance: number;
    groupCategory: any;
    description: string;
    owner: any;
    members: any[];
    invitations: any[];

    constructor(groupId: number, name: string, totalBalance: number, groupCategory: any, description: string, owner: any,
        members: [], invitations: any[]) {
        this.groupId = groupId;
        this.name = name;
        this.totalBalance = totalBalance;
        this.groupCategory = groupCategory;
        this.description = description;
        this.owner = owner;
        this.members = members;
        this.invitations = invitations;
    }
}