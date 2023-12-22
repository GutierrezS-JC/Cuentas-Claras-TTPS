export class Groups {
  public listGroups: any[];
  public listOwnedGroups: any[];

  constructor(listGroups: [], listOwnedGroups: []) {
    this.listGroups = listGroups;
    this.listOwnedGroups = listOwnedGroups;
  }
}