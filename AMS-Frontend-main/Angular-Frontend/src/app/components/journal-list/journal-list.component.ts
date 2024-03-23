import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';
import { HttpService } from 'src/app/services/http.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-journal-list',
  templateUrl: './journal-list.component.html',
  styleUrls: ['./journal-list.component.scss']
})
export class JournalListComponent implements OnInit {

  constructor(public http: HttpService, private storageService: StorageService, private route: Router) { }

  ngOnInit(): void {
    this.subscription = this.storageService.currentMessage.subscribe(message => this.sharedMessage = message);
    if (this.storageService.isLoggedIn()) {
      this.onSearch();

    } else {
      this.route.navigateByUrl('/sign-in')
    }
  }



  journalList: any[] = []

 

  delete(id: any) {
    this.http.deleteById(id).subscribe((result: any) => {
      alert("Do you want to delete " + id + " ?");
      this.onSearch();
    })

  }

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
    this.http.search(this.textInputValue, this.startDate, this.endDate).subscribe((r: any) => {
      this.journalList = r;
    })
  }
  sharedMessage:string = 'EN';
  subscription!: Subscription;
}

// getAll() {
//   this.http.getAll().subscribe((result: any) => {
//     this.journalList = result;
//   })

// }