import {Component, Injectable, OnInit} from '@angular/core';
import {ClientDto} from "../../contracts/ClientDto.model";
import {ReservationService} from "../reservation.service";
import {Router} from "@angular/router";
import {ClientService} from "./client.service";

@Component({
  selector: 'app-personal-data',
  templateUrl: './personal-data.component.html',
  styleUrls: ['./personal-data.component.css']
})
@Injectable()
export class PersonalDataComponent implements OnInit {
  client: ClientDto;
  pesel: string;
  address: string;

  constructor(private reservationService: ReservationService,
              private clientService: ClientService,
              private router: Router) {
  }

  ngOnInit() {
    console.log('client is alive');
    this.client = new ClientDto();
  }

  submitForm() {
    this.addPersonalDataToClient();

    this.clientService.createOrUpdateClient(this.client).subscribe(
      savedClient => {
        this.client = savedClient;
        this.reservationService.saveClientData(this.client);
        this.router.navigate(['/book/summary']);
      }
    );

  }

  private addPersonalDataToClient() {
    this.client.clientFields.push(
      {
        type: 'PESEL',
        value: this.pesel
      });

    this.client.clientFields.push(
      {
        type: 'ADDRESS',
        value: this.address
      });
  }
}
