import {CategoryDto} from "./CategoryDto.model";
import {DiscountDto} from "./DiscountDto.model";

export class BuildingsDto{
  id: number;
  name: string;
  floors: number;
  category: CategoryDto;
  discounts: DiscountDto[];

  constructor(){}
}
