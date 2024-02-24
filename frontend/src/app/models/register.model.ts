export class UserRegister {
    public email: string;
    public username: string;
    public name: string;
    public lastName: string;
    public profilepicBase64: string;
    public password: string;
    public id:number;

    constructor (email:string, password:string, username:string, name:string, lastName:string, profilepicBase64:string, id:number){
        this.email= email;
        this.password= password;
        this.username= username;
        this.name= name;
        this.lastName= lastName;
        this.profilepicBase64= profilepicBase64;
        this.id= id;
    }
}