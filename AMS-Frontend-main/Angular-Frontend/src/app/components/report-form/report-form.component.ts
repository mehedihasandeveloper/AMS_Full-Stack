import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';
import { Report } from 'src/app/model/report';
import { HttpService } from 'src/app/services/http.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-report-form',
  templateUrl: './report-form.component.html',
  styleUrls: ['./report-form.component.scss']
})
export class ReportFormComponent implements OnInit {
  journalForm: any;


  constructor(public http: HttpService, private storageService: StorageService, private route: Router) { }

  ngOnInit(): void {
    this.subscription = this.storageService.currentMessage.subscribe(message => this.sharedMessage = message);
    if (this.storageService.isLoggedIn()) {
    
    } else {
      this.route.navigateByUrl('/sign-in')
    }
    this.journalForm = new FormGroup({
      startDate: new FormControl(),
      endDate: new FormControl(),
      reportType: new FormControl('')
    });
  }

  journalList: any[] = []
  oesList: any[] = []
  tbList: any[] = []
  bsList: any[] = []



  visibleForm = false;



  onSubmit() {
    // Retrieve start and end date values from the form
    const startDate = this.journalForm.get('startDate').value;
    const endDate = this.journalForm.get('endDate').value;
    const reportType = this.journalForm.get('reportType').value;

    // Prepare data to send to the API service
    const requestData = {
      startDate: startDate,
      endDate: endDate,
      reportType: reportType
    };

    // Call your API service method to fetch data
    this.http.getReport(requestData.startDate, requestData.endDate).subscribe((result: any) => {
      this.journalList = result;
      // Show the form
      this.visibleForm = true;
    });

    this.http.getOESReport(requestData.startDate, requestData.endDate).subscribe((result: any) => {
      console.log(result);
      this.oesList = result;

    });

    this.http.getTBReport(requestData.startDate, requestData.endDate).subscribe((result: any) => {
      console.log(result);
      this.tbList = result;
      console.log(this.tbList);
    });

    this.http.getBSReport(requestData.startDate, requestData.endDate).subscribe((result: any) => {
      console.log(result);
      this.bsList = result;


      console.log(this.bsList);
    });

  }
  calculateNetIncomeTotal(): number {
    let total = 0;
    for (const journal of this.journalList) {
      total += journal.netIncome;
    }
    return total;
  }


  calculateTotalOE(): number {
    let total = 0;
    for (const journal of this.oesList) {
      total += journal.balance;
    }
    return total;
  }


  calculateTotalDebit(): number {
    let total = 0;
    for (const journal of this.tbList) {
      total += journal.debitBalance;
    }
    return total;
  }
  calculateTotalCredit(): number {
    let total = 0;
    for (const journal of this.tbList) {
      total += journal.creditBalance;
    }
    return total;
  }

  calculateTotalAsset(): number {
    let total = 0;
    for (const journal of this.bsList) {
      total += journal.balance;
    }
    return total;
  }

  calculateTotalLE(): number {
    let total = 0;
    for (const journal of this.bsList) {
      total += journal.liabilityBalance;
    }
    return total;
  }

  sharedMessage:string = 'EN';
  subscription!: Subscription;


}




//   this.http.getAssetsReport(requestData.startDate, requestData.endDate).subscribe((result: any) => {
//     console.log(result);
//     this.journalList = result;
//     console.log(this.journalList);
//     // Show the form
//     this.visibleAssetForm = true;
//   });
// calculateTotalAssets(): number {
//   let total = 0;
//   for (const journal of this.journalList) {
//     total += journal.totalAsset;
//   }
//   return total;
// }