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
  showHotels: boolean = false;
  selected: boolean = false;

  hotels: HotelDto[];
  selectedHotel: HotelDto;
  rooms: RoomDto[];

  city: string;
  checkInDate: Date;
  checkOutDate: Date;

  constructor(private router: Router,
              private hotelsService: HotelsService,
              private roomService: RoomsService,
              private reservationService: ReservationService) {
  }


  ngOnInit() {
    console.log('hotels are alive');
  }

  loadHotels() {
    if (this.areHotelSearchDataValid()) {
      this.hotelsService.getSimpleAllHotels()
        .subscribe(
          (hotels: HotelDto[]) => {
            this.showHotels = true;
            this.hotels = hotels;
          }
        );
    }
    else {
      alert("Fill empty fields!");
    }
  }

  private areHotelSearchDataValid() {
    return this.city && this.checkInDate && this.checkOutDate;
  }

  onHotelClick(hotel: HotelDto) {
    this.selectedHotel = hotel;
    this.showHotels = true;
    this.reservationService.selectHotel(this.selectedHotel);
    this.rooms = RoomDto.getSampleDate();
    /*
    this.roomService.getRooms(hotel.id).subscribe(
      (value: RoomDto[]) => {
        this.rooms = value;
      });
      */
  }


}
