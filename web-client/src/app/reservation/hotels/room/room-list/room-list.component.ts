import {Component, Input, OnInit} from '@angular/core';
import {RoomDto} from "../../../../contracts/RoomDto.model";
import {HotelDto} from "../../../../contracts/HotelDto.model";
import {ReservationService} from "../../../reservation.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {

  @Input()
  public rooms: RoomDto[];

  constructor(private reservationService: ReservationService, private router: Router) {
  }

  ngOnInit() {
  }

  selectRoom(room: RoomDto) {
    this.reservationService.selectRoom(room);

    this.router.navigate(['/book/data']);
    // this.selectedRoom.reservations.push(
    //   {
    //     checkInDate: this.checkInDate,
    //     checkOutDate: this.checkOutDate,
    //     state: null,
    //     id: null,
    //     cost: null
    //   }
    // );
    // this.selected = false;
    // this.reservationService.selectRoom(room);

  }

}
