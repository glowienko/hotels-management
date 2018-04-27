import {Component, OnInit} from '@angular/core';
import {ReservationService} from "../reservation.service";
import {RoomDto} from "../../contracts/RoomDto.model";
import {ClientDto} from "../../contracts/ClientDto.model";
import {Subscription} from "rxjs/Subscription";
import {RoomsService} from "../../shared/Rooms.service";
import {RoomReservationDto} from "../../contracts/RoomReservationDto.model";
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
  // roomSubscription: Subscription;
  // clientSubscription: Subscription;
  // hotelSubscription: Subscription;


  constructor(private reservationService: ReservationService,
              private roomsService: RoomsService) {
  }

  ngOnInit() {
    console.log('Summary is alive');
    this.selectedRoom = this.reservationService.getSelectedRoom();
    this.selectedHotel = this.reservationService.getSelectedHotel();
    this.clientData = this.reservationService.getClientData();

    if(this.selectedHotel == undefined) {
      this.selectedHotel = new HotelDto();
      this.selectedHotel.buildings = [];
    }

    if(this.selectedRoom == undefined) {
      this.selectedRoom = new RoomDto();
      this.selectedRoom.reservations = [];
      this.selectedRoom.prices = [];
    }

    if(this.clientData == undefined)
      this.clientData = new ClientDto();

    this.reservationService.onRoomSelected
      .subscribe(
        (room: RoomDto) => {
          this.selectedRoom = room;
        }
      );

    this.reservationService.clientDataProvided
      .subscribe(
        (client: ClientDto) =>{
          this.clientData = client;
        }
      );

    this.reservationService.onHotelSelected
      .subscribe(
        (hotel: HotelDto) => {
          this.selectedHotel = hotel;
        }
      )


  }


  makeReservation(){
    // this.roomsService.bookRoom(this.selectedRoom);
  }

}
