import {Component, OnDestroy, OnInit} from '@angular/core';
import {ReservationService} from "../reservation.service";
import {RoomDto} from "../../contracts/RoomDto.model";
import {ClientDto} from "../../contracts/ClientDto.model";
import {HotelDto} from "../../contracts/HotelDto.model";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit, OnDestroy {
  selectedRoom: RoomDto;
  clientData: ClientDto;
  selectedHotel: HotelDto;
  startDate: Date;
  endDate: Date;

  private selectedRoomSub: Subscription;
  private clientDataSub: Subscription;
  private selectedHotelSub: Subscription;
  private startDateSub: Subscription;
  private endDateSub: Subscription;

  constructor(private reservationService: ReservationService) {
  }

  ngOnInit() {
    this.selectedRoomSub = this.reservationService.selectedRoom.subscribe(room => this.selectedRoom = room);
    this.selectedHotelSub = this.reservationService.selectedHotel.subscribe(hotel => this.selectedHotel = hotel);
    this.clientDataSub = this.reservationService.clientData.subscribe(client => this.clientData = client);

    console.log(this.selectedRoom);
  }

  ngOnDestroy() {
    this.selectedRoomSub.unsubscribe();
    this.selectedHotelSub.unsubscribe();
    this.clientDataSub.unsubscribe();
  }


  makeReservation() {
    // this.roomsService.bookRoom(this.selectedRoom);
  }

}
