import { Injectable } from '@angular/core';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/delay';
import {HttpClient} from "@angular/common/http";
import {ClientDto} from "../contracts/ClientDto.model";


@Injectable()
export class ManagementService {
  constructor(private httpClient: HttpClient) {

  }

  getBestCostClients(){
    return this.httpClient.get<ClientDto[]>('http://localhost:9095/api/clients/best_cost?year=2018&numberOfClients=10');
  }

  getBestTimeClients(){
    return this.httpClient.get<ClientDto[]>('http://localhost:9095/api/clients/best_time?year=2018&numberOfClients=10&dateFrom=2018-01-01 00:00:00&dateTo=2018-12-31 00:00:00');
  }
}
