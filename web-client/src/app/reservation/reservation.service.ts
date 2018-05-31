import {EventEmitter, Injectable} from "@angular/core";
import {RoomDto} from "../contracts/RoomDto.model";
import {ClientDto} from "../contracts/ClientDto.model";
import {HotelDto} from "../contracts/HotelDto.model";

@Injectable()
export class ReservationService {

  onRoomSelected = new EventEmitter<RoomDto>();
  clientDataProvided = new EventEmitter<ClientDto>();
  onHotelSelected = new EventEmitter<HotelDto>();
  private selectedHotel: HotelDto;
  private selectedRoom: RoomDto;
  private clientData: ClientDto;

  selectRoom(room: RoomDto) {
    this.selectedRoom = room;
    this.onRoomSelected.emit(this.selectedRoom);
  }

  dataProvided(client: ClientDto) {
    this.clientData = client;
    this.clientDataProvided.emit(this.clientData);
  }

  selectHotel(hotel: HotelDto) {
    this.selectedHotel = hotel;
    this.onHotelSelected.emit(this.selectedHotel);
  }

  getSelectedRoom() {
    return this.selectedRoom;
  }

  getClientData() {
    return this.clientData;
  }

  getSelectedHotel() {
    return this.selectedHotel;
  }
}
