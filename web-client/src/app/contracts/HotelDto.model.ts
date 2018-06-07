import {BuildingsDto} from "./BuildingsDto.model";
import {CategoryDto} from "./CategoryDto.model";

export class HotelDto {

  id: number;
  imgPath: string;
  name: string;
  location: string;
  stars: number;
  category: CategoryDto;
  buildings: BuildingsDto[];
}
