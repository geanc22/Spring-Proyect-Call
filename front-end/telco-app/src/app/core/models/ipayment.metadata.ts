export class IPayment{
    id?:number;
    idProducto!:number;
    typeProduct!:string;
	amount!:number;
    client!:Client
    terms!:boolean;
}

class Client{
    name!:string;
    lastName1!:string;
    lastName2!:string;
    email!:string;
    telf!:string;
    card!:Card;
}

class Card{
    number!:string;
    expMonth!:string;
    expYear!:string;
    cvv!:string;
}