export interface IPaqueteApps{
    id?:number;
    title:string;
    price:number;
    img:string;
    duration:number;
    apps:IApp[];
}
interface IApp{
    id?:number;
    name:string;
}