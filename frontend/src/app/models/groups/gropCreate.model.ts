export interface GroupCreate {
  name: string;
  totalBalance: number;
  userOwnerId: number;
  description: string;
  groupCategoryId: number;
  usersIds: any;
}