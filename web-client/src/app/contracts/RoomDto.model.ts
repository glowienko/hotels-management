import {DiscountDto} from "./DiscountDto.model";
import {PriceDto} from "./PriceDto.model";
import {RoomReservationDto} from "./RoomReservationDto.model";

export class RoomDto {
  id: number;
  capacity: number;
  number: number;
  floor: number;
  premium: boolean;
  imgPath: string;

  reservations: RoomReservationDto[];
  prices: PriceDto[];
  discounts: DiscountDto[];


  constructor() {
  }

  static getSampleDate() {
    let rooms: RoomDto[];
    rooms = [];

    rooms.push(
      {
        id: 1,
        capacity: 4,
        number: 123,
        floor: 4,
        premium: false,
        imgPath: 'https://www.corinthia.com/application/files/5214/7582/3494/business-room_02.jpg',
        reservations: [],
        discounts: [],
        prices: [{
          id: 0,
          startDate: new Date(),
          endDate: new Date(),
          value: 1234
        }]
      },
      {
        id: 2,
        capacity: 5,
        number: 5643,
        floor: 2,
        premium: true,
        imgPath: 'https://www.corinthia.com/application/files/5214/7582/3494/business-room_02.jpg',
        reservations: [],
        discounts: [],
        prices: [{
          id: 0,
          startDate: new Date(),
          endDate: new Date(),
          value: 5234
        }]
      }
    );

    return rooms;
  }
}
