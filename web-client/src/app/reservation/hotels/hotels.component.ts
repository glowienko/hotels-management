import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HotelsService} from "../../shared/Hotels.service";
import {HotelDto} from "../../contracts/HotelDto.model";
import {RoomDto} from "../../contracts/RoomDto.model";
import {RoomsService} from "../../shared/Rooms.service";
import {ReservationService} from "../reservation.service";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent implements OnInit {
  roomMode: boolean = false;
  selected: boolean = false;

  hotels: HotelDto[];
  selectedHotel: HotelDto;
  rooms: RoomDto[];
  selectedRoom: RoomDto;

  city: string;
  checkInDate: Date;
  checkOutDate: Date;

  constructor(private router: Router,
              private hotelsService: HotelsService,
              private roomService: RoomsService,
              private reservationService: ReservationService) {
  }

  selectRoom() {

  };

  ngOnInit() {
    console.log('hotels are alive');
  }

  loadHotels() {
    this.hotels = HotelDto.getSampleData();
    let ok: boolean = true;
    if (this.city == undefined || this.city == '') {
      ok = false
    }
    if (this.checkInDate == undefined) {
      ok = false;
    }
    if (this.checkOutDate == undefined) {
      ok = false
    }
    /*
    if(ok){
    this.hotelsService.getHotels(city, checkInDate, checkOutDate)
      .subscribe(
        (hotels: HotelDto[]) => {
          this.hotels = hotels;
        }
      );
      }
    */
  }

  onHotelClick(hotel: HotelDto) {
    this.selectedHotel = hotel;
    this.roomMode = true;
    this.reservationService.selectHotel(this.selectedHotel);
    this.rooms = RoomDto.getSampleDate();
    /*
    this.roomService.getRooms(hotel.id).subscribe(
      (value: RoomDto[]) => {
        this.rooms = value;
      });
      */
  }

  onRoomClick(room: RoomDto) {
    this.selectedRoom = room;
    this.selectedRoom.reservations.push(
      {
        checkInDate: this.checkInDate,
        checkOutDate: this.checkOutDate,
        state: null,
        id: null,
        cost: null
      }
    );
    this.selected = false;
    this.reservationService.selectRoom(room);

    this.router.navigate(['/book/data']);
  }

}
