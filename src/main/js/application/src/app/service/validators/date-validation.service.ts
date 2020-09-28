import {Injectable} from '@angular/core';
import {FormControl} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class DateValidationService {

  startDateValidation(control: FormControl, endDate: string): { [s: string]: boolean } {
    console.log(control.value);
    console.log(endDate);
    // if (StateList.lastIndexOf(control.value.toString()) != -1) {
    //   return null;
    // }
    return {"select": true}
  }

  // static dateFuture(control: FormControl): { [s: string]: boolean } {
  //   if((Date.parse(control.value)).getMilliseconds()> Date.now()) return {"futureDate": true};
  //   return null;
  // }
}
