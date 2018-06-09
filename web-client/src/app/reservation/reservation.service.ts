import {Injectable} from "@angular/core";
import {RoomDto} from "../contracts/RoomDto.model";
import {ClientDto} from "../contracts/ClientDto.model";
import {HotelDto} from "../contracts/HotelDto.model";
import {BehaviorSubject} from "rxjs/BehaviorSubject";
import {Observable} from "rxjs/Observable";

@Injectable()
export class ReservationService {
  private _selectedHotel: BehaviorSubject<HotelDto> = new BehaviorSubject(new HotelDto());
  private _selectedRoom: BehaviorSubject<RoomDto> = new BehaviorSubject(new RoomDto());
  private _clientData: BehaviorSubject<ClientDto> = new BehaviorSubject(new ClientDto());
  private _userReservationInfo: BehaviorSubject<any> = new BehaviorSubject({});

  public readonly selectedHotel: Observable<HotelDto> = this._selectedHotel.asObservable();
  public readonly selectedRoom: Observable<RoomDto> = this._selectedRoom.asObservable();
  public readonly clientData: Observable<ClientDto> = this._clientData.asObservable();
  public readonly userReservationInfo: Observable<any> = this._userReservationInfo.asObservable();

  selectRoom(room: RoomDto) {
    this._selectedRoom.next(room);
  }

  saveClientData(client: ClientDto) {
    this._clientData.next(client);
  }

  selectHotel(hotel: HotelDto) {
    this._selectedHotel.next(hotel);
  }

  setReservationInfo(data: any) {
    this._userReservationInfo.next(data);
  }
}
