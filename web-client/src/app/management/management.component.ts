import { Component, OnInit } from '@angular/core';
import { ManagementService } from './management-service';
import {Observable} from "rxjs/Observable";
import {ClientDto} from "../contracts/ClientDto.model";

@Component({
  selector: 'app-management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.css'],
  providers: [ManagementService]
})
export class ManagementComponent implements OnInit {

  costClients: Observable<ClientDto[]>;
  timeClients: Observable<ClientDto[]>;

  constructor(private atService: ManagementService) { }

  ngOnInit() {
    this.costClients = this.atService.getBestCostClients();
    this.timeClients = this.atService.getBestTimeClients();
  }

}
