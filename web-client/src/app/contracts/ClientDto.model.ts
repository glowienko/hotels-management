import {ClientFieldDto} from "./ClientFieldDto";

export class ClientDto{
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  conference: boolean;
  clientFields: ClientFieldDto[];

  constructor(){
    this.clientFields = [];
  }
}
