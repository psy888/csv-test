import {Csvfile} from "./csvfile";

export class SpecDevice {
  id: number;
  name: string;
  address: string;
  curState: string;
  file: Csvfile;
  productionDate: number;
}
