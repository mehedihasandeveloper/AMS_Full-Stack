import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';
import { HttpService } from 'src/app/services/http.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-equipment-account',
  templateUrl: './equipment-account.component.html',
  styleUrls: ['./equipment-account.component.scss']
})
export class EquipmentAccountComponent implements OnInit {

  constructor(public http: HttpService, private storageService: StorageService, private route: Router) { }

  ngOnInit(): void {
    this.subscription = this.storageService.currentMessage.subscribe(message => this.sharedMessage = message);
    if (this.storageService.isLoggedIn()) {
      this.onSearch();
    } else {
      this.route.navigateByUrl('/sign-in')
    }
    
  }

  cashAccountDetails: any[] = [] 

  selectedOption!: string;
  textInputValue!: string;
  startDate!: string;
  endDate!: string;

  onOptionChange(event: any) {
    this.selectedOption = event.target.value;
    if (this.selectedOption === '1') {
      this.startDate = '';
      this.endDate = '';
    }

    if (this.selectedOption === '2') {
      this.textInputValue = '';
    }
  }

  onSearch() {
    console.log(this.textInputValue);
    console.log(this.startDate);
    console.log(this.endDate);
    this.http.searchEquipment(this.textInputValue, this.startDate, this.endDate).subscribe((r: any) => {
      this.cashAccountDetails = r;
    })
  }

  sharedMessage:string = 'EN';
  subscription!: Subscription;
}

 