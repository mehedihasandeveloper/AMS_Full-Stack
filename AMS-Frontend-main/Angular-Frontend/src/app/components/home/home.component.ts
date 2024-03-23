import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';
import { AuthService } from 'src/app/services/auth.service';
import { DecimalPipe } from '@angular/common';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
  
})
export class HomeComponent implements OnInit {
  constructor(private storageService: StorageService, private route: Router, private authService: AuthService) { }
  ngOnInit(): void {
    this.subscription = this.storageService.currentMessage.subscribe(message => this.sharedMessage = message);



    if (this.storageService.isLoggedIn()) {
      this.authService.getCashBalance().subscribe((result: any) => {
        this.cashBalance = result;
      });
      this.authService.getRevenueBalance().subscribe((result: any) => {
        this.revenueBalance = result;
      });
      this.authService.getReceivaleBalance().subscribe((result: any) => {
        this.receivableBalance = result;
      });
      this.authService.getLoanBalance().subscribe((result: any) => {
        this.loanBalance = result;
      });
      console.log(this.cashBalance);

    } else {
      this.route.navigateByUrl('/sign-in')
    }
  }

  cashBalance: any;
  revenueBalance: any;
  receivableBalance: any;
  loanBalance: any;

  changeLanguagetoEnglish() {
    this.english = true;
  }

  english = true;


  sharedMessage:string = 'EN';
  subscription!: Subscription;

  newMessage(val:any) {
    this.storageService.changeMessage(val)
  }

  
}
