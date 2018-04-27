import { Component, OnInit } from '@angular/core';
import {ReservationService} from "./reservation.service";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css'],
  providers: [ReservationService]
})
export class ReservationComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
