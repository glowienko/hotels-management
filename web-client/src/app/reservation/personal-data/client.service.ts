import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ClientDto} from "../../contracts/ClientDto.model";
import {Observable} from "rxjs/Observable";

@Injectable()
export class ClientService {

  constructor(private httpClient: HttpClient) {
  }

  public createOrUpdateClient(client: ClientDto): Observable<any> {
    return this.httpClient.post("http://localhost:9095/api/clients", client);
  }

}
