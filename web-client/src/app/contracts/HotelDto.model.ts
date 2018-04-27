import {BuildingsDto} from "./BuildingsDto.model";
import {CategoryDto} from "./CategoryDto.model";

export class HotelDto{

  id: number;
  imgPath: string;
  name: string;
  location: string;
  stars: number;
  category: CategoryDto;
  buildings: BuildingsDto[];

  constructor(){}

  static getSampleData(){
    let hotels = [];

    hotels.push(
      {
        id: 0,
        imgPath: 'http://streamafrica.com/wp-content/uploads/2016/01/hotels-4.jpg',
        name: 'Mariott',
        location: 'Warsaw',
        stars: 4,
        category: {
          name: 'premium'
        },
        buildings: [
          {
            id: 0,
            name: 'first',
            floors: 5
          }
        ]
      },
      {
        id: 0,
        imgPath: 'http://streamafrica.com/wp-content/uploads/2016/01/hotels-4.jpg',
        name: 'Mariott',
        location: 'Warsaw',
        stars: 4,
        category: {
          name: 'premium'
        },
        buildings: [
          {
            id: 0,
            name: 'first',
            floors: 5
          }
        ]
      },
      {
        id: 0,
        imgPath: 'http://streamafrica.com/wp-content/uploads/2016/01/hotels-4.jpg',
        name: 'Mariott',
        location: 'Warsaw',
        stars: 4,
        category: {
          name: 'premium'
        },
        buildings: [
          {
            id: 0,
            name: 'first',
            floors: 5
          }
        ]
      },
      {
        id: 1,
        imgPath: 'http://static.asiawebdirect.com/m/phuket/portals/kosamui-com/shared/teasersL/top10s/top10-chaweng-hotels/teaserMultiLarge/imageHilight/chaweng-best-hotels.jpg',
        name: 'Chaweng',
        location: 'Chaweng',
        stars: 5,
        category: {
          name: 'premium'
        },
        buildings: [
          {
            id: 0,
            name: 'first',
            floors: 1
          }
        ]
      }
    );
    return hotels;
  }

}
