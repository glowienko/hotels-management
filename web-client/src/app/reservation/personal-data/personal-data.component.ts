import {Component, Injectable, Input, OnInit} from '@angular/core';
import {ClientDto} from "../../contracts/ClientDto.model";
import {ReservationService} from "../reservation.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['./personal-data.component.css']
})
@Injectable()
export class PersonalDataComponent implements OnInit {
    client: ClientDto;

  constructor(private reservationService: ReservationService,
              private router: Router) { }

  ngOnInit() {
    console.log('client is alive');
    this.client = new ClientDto();
  }

  submitForm(){
    console.log(this.client);
    this.reservationService.dataProvided(this.client);
    this.router.navigate(['/book/summary']);

  }

}
