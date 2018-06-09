import {Component, OnDestroy, OnInit} from '@angular/core';
import {ReservationService} from "../reservation.service";
import {RoomDto} from "../../contracts/RoomDto.model";
import {ClientDto} from "../../contracts/ClientDto.model";
import {HotelDto} from "../../contracts/HotelDto.model";
import {Subscription} from "rxjs/Subscription";
import {RoomsService} from "../../shared/Rooms.service";

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit, OnDestroy {
  selectedRoom: RoomDto;
  clientData: ClientDto;
  selectedHotel: HotelDto;
  userReservationInfo: any;

  private selectedRoomSub: Subscription;
  private clientDataSub: Subscription;
  private selectedHotelSub: Subscription;
  private userReservationInfoSub: Subscription;

  constructor(private reservationService: ReservationService, private roomsService: RoomsService) {
  }

  ngOnInit() {
    this.selectedRoomSub = this.reservationService.selectedRoom.subscribe(room => this.selectedRoom = room);
    this.selectedHotelSub = this.reservationService.selectedHotel.subscribe(hotel => this.selectedHotel = hotel);
    this.clientDataSub = this.reservationService.clientData.subscribe(client => this.clientData = client);
    this.userReservationInfoSub = this.reservationService.userReservationInfo.subscribe(info => this.userReservationInfo = info);

    console.log('data from reservation service loaded');
  }

  ngOnDestroy() {
    this.selectedRoomSub.unsubscribe();
    this.selectedHotelSub.unsubscribe();
    this.clientDataSub.unsubscribe();
    this.userReservationInfoSub.unsubscribe();
  }


  makeReservation() {
    this.userReservationInfo.totalReservationCost = 1000; // tmp
    let reservationContext = {
      room: this.selectedRoom,
      client: this.clientData,
      hotel: this.selectedHotel,
      info: this.userReservationInfo
    };

    this.roomsService.bookRoom(reservationContext);
  }

}
