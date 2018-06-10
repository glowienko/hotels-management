import { Component, OnInit } from '@angular/core';
import { ManagementService } from './management-service';
import {ClientDto} from "../contracts/ClientDto.model";
import {Subject} from "rxjs";
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-management',
  templateUrl: './management.component.html',
  styleUrls: ['./management.component.css'],
  providers: [ManagementService]
})
export class ManagementComponent implements OnInit {

  costClientsList: ClientDto[];
  timeClientsList: ClientDto[];
  dtOptions: any = {};
  dtTrigger1: Subject<any> = new Subject<any>();
  dtTrigger2: Subject<any> = new Subject<any>();

  constructor(private atService: ManagementService) { }

  ngOnInit() {

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10,
      responsive: true
    };

    this.atService.getBestTimeClients()
      .subscribe(
        (response: ClientDto[]) => {
          this.timeClientsList = response;
          this.dtTrigger2.next();
        }
      )

    this.atService.getBestCostClients()
      .subscribe(
        (response: ClientDto[]) => {
          this.costClientsList = response;
          this.dtTrigger1.next();
        }
      )

  }

}
