import {Injectable} from '@angular/core';
import {FormControl} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class DeviceValidationServiceService {

  booleanValidation(control: FormControl): { [s: string]: boolean } {
    if (control.value.toString().toLocaleLowerCase() == 'true' || control.value.toString().toLocaleLowerCase() == 'false') {
      return null
    }
    return {"not boolean": true};
  }

  stateValidation(control: FormControl): { [s: string]: boolean } {
    const StateList = ['РАБОЧИЙ', 'В РЕМОНТЕ', 'НА УТИЛИЗАЦИИ'];
    if (StateList.lastIndexOf(control.value.toString()) != -1) {
      return null;
    }
    return {"wrong state typed": true}
  }

}
