import {Csvfile} from "./csvfile";

export class NetDevice {
  id: number;
  name: string;
  address: string;
  curState: string;
  file: Csvfile;
  ipAddress: string;
  procLoad: number;
  isOn: boolean;
}
