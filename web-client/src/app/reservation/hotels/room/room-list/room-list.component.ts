import {Component, Input, OnInit} from '@angular/core';
import {HotelDto} from "../../../../contracts/HotelDto.model";
import {RoomDto} from "../../../../contracts/RoomDto.model";

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {


  @Input()
  public rooms: RoomDto[];

  public selectedRoom: RoomDto;

  constructor() {
  }

  ngOnInit() {
  }

  onRoomClick(room: RoomDto) {
    this.selectedRoom = room;
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

    // this.router.navigate(['/book/data']);
  }

  selectRoom() {
  };

}
