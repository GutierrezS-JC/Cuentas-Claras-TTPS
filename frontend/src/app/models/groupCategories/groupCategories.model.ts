export class GroupCategories {
  public id: number;
  public name: string;
  public base64Image: string;

  constructor(id: number, name: string, base64Image: string) {
    this.id = id;
    this.name = name;
    this.base64Image = base64Image;
  }
}