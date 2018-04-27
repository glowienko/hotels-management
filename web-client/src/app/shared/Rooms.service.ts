import {HttpClient} from "@angular/common/http";
import {RoomDto} from "../contracts/RoomDto.model";
import {RoomReservationDto} from "../contracts/RoomReservationDto.model";
import {Injectable} from "@angular/core";

@Injectable()
export class RoomsService{

  constructor(private httpClient: HttpClient){

  }

  getRooms(id: number){
    return this.httpClient.get<RoomDto[]>('app/path/hotel/'+id+'/rooms');
  }

  bookRoom(reservedRoom: RoomDto){
    this.httpClient.put('app/path/book/room', reservedRoom);
    console.log('room with id:'+ reservedRoom.id + ' was reserved');
  }
}
