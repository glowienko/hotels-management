import {HotelDto} from "../contracts/HotelDto.model";
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable()
export class HotelsService{

  constructor( private httpClient: HttpClient){}

  getHotels(city: string, checkInDate: Date, checkOutDate: Date) {
    return this.httpClient.get<HotelDto[]>('app/path/hotels?city=' + city + '&checkInDate=' + checkInDate + '&checkOutDate=' + checkOutDate,
      {observe: "body"});
  }

  //tmp function for testing
  getSimpleAllHotels() {
    return this.httpClient.get<HotelDto[]>("http://localhost:9095/api/hotels");
  }
}
