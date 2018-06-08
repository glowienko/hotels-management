import {Component, OnInit} from '@angular/core';
import {ReservationService} from "../reservation.service";
import {RoomDto} from "../../contracts/RoomDto.model";
import {ClientDto} from "../../contracts/ClientDto.model";
import {HotelDto} from "../../contracts/HotelDto.model";

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit {
  selectedRoom: RoomDto;
  clientData: ClientDto;
  selectedHotel: HotelDto;

  constructor(private reservationService: ReservationService) {
  }

  ngOnInit() {
    this.selectedRoom = this.reservationService.getSelectedRoom();
    this.selectedHotel = this.reservationService.getSelectedHotel();
    this.clientData = this.reservationService.getClientData();

  }


  makeReservation() {
    // this.roomsService.bookRoom(this.selectedRoom);
  }

}
