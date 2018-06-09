import {HttpClient} from "@angular/common/http";
import {RoomDto} from "../contracts/RoomDto.model";
import {RoomReservationDto} from "../contracts/RoomReservationDto.model";
import {Injectable} from "@angular/core";
import {HotelDto} from "../contracts/HotelDto.model";

@Injectable()
export class RoomsService{

  constructor(private httpClient: HttpClient){

  }

  getRooms(id: number){
    return this.httpClient.get<RoomDto[]>('app/path/hotel/'+id+'/rooms');
  }

  getRoomsByUserSelection(hotelId: number, startDate: Date, endDate: Date, price: number){
    return this.httpClient.get<RoomDto[]>('http://localhost:9095/api/hotels/' + hotelId + '/rooms?&startDate=' + startDate + '&endDate=' + endDate + '&price=' + price);

  }

  bookRoom(reservedRoom: RoomDto){
    this.httpClient.put('app/path/book/room', reservedRoom);
    console.log('room with id:'+ reservedRoom.id + ' was reserved');
  }
}
