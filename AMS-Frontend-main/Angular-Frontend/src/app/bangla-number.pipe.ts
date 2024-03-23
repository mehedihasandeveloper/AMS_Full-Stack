import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'banglaNumber'
})
export class BanglaNumberPipe implements PipeTransform {

  transform(value: number): string {

    const banglaNumbers = ['০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯'];
    return value.toString().split('').map(digit => banglaNumbers[parseInt(digit)]).join('');
  }

}
