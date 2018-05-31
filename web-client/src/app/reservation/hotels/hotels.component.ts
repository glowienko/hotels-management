import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HotelsService} from "../../shared/Hotels.service";
import {HotelDto} from "../../contracts/HotelDto.model";
import {RoomDto} from "../../contracts/RoomDto.model";
import {RoomsService} from "../../shared/Rooms.service";
import {ReservationService} from "../reservation.service";

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent implements OnInit {

  showHotels: boolean = false;
  showRooms: boolean = false;
  areSearchFieldsPresent: boolean = true;

  hotels: HotelDto[];
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
  }

  loadHotels() {
    this.showRooms = false;
    if (this.areHotelSearchDataValid()) {
      this.areSearchFieldsPresent = true;
      this.hotelsService.getSimpleAllHotels()
        .subscribe(
          (hotels: HotelDto[]) => {
            this.showHotels = true;
            this.hotels = hotels;
          }
        );
    }
    else {
      this.areSearchFieldsPresent = false;
    }
  }

  private areHotelSearchDataValid() {
    return this.city && this.checkInDate && this.checkOutDate;
  }

  selectHotel(hotel: HotelDto) {
    this.rooms = RoomDto.getSampleDate();
    this.showRooms = true;

    this.reservationService.selectHotel(hotel);
    /*
    this.roomService.getRooms(hotel.id).subscribe(
      (value: RoomDto[]) => {
        this.rooms = value;
      });
      */
  }


}
