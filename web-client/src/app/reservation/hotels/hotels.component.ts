import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HotelsService} from "../../shared/Hotels.service";
import {HotelDto} from "../../contracts/HotelDto.model";
import {RoomDto} from "../../contracts/RoomDto.model";
import {RoomsService} from "../../shared/Rooms.service";
import {ReservationService} from "../reservation.service";
import {validate} from "codelyzer/walkerFactory/walkerFn";

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent implements OnInit {

  showHotels: boolean = false;
  showRooms: boolean = false;
  areSearchFieldsPresent: boolean = true;
  validData = true;

  hotels: HotelDto[];
  rooms: RoomDto[];
  selectedRoom: RoomDto;

  city: string;
  stars = 5;
  maxPrice = 1000;
  checkInDate: Date = new Date();
  checkOutDate: Date = new Date();

  constructor(private router: Router,
              private hotelsService: HotelsService,
              private roomService: RoomsService,
              private reservationService: ReservationService) {
  }

  ngOnInit() {
    console.log('hotels are alive');

    this.hotelsService.getSimpleAllHotels().
      subscribe(
      (hotels: HotelDto[]) => {
        this.showHotels = true;
        this.hotels = hotels;
      }
    )
  }

  loadHotels() {
    this.showRooms = false;
    if (this.areHotelSearchDataValid()) {
      this.areSearchFieldsPresent = true;
      this.hotelsService.getHotelsByUserSelection(this.city, this.stars, this.checkInDate, this.checkOutDate, this.maxPrice)
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
    if(this.areHotelSearchDataValid()) {
      this.saveReservationInfo();
      this.showRooms = true;

      this.reservationService.selectHotel(hotel);

      this.roomService.getRoomsByUserSelection(hotel.id, this.checkInDate, this.checkOutDate, this.maxPrice).subscribe(
        (value: RoomDto[]) => {
          this.rooms = value;
        });
    }
    else {
      this.validData = false;
    }
  }

  selectRoom(room: RoomDto) {
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
    // this.selected = false;
    this.reservationService.selectRoom(room);

    this.router.navigate(['/book/data']);
  }

  private saveReservationInfo() {
    let info = {
      city: this.city,
      hotelStars: this.stars,
      maxPrice: this.maxPrice,
      checkInDate: this.checkInDate,
      checkOutDate: this.checkOutDate
    };
    this.reservationService.setReservationInfo(info);
  }

  setStars(stars: number) {
    this.stars = stars;
  }

}
