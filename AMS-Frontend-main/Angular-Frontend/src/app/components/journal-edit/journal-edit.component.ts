import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';
import { Transaction } from 'src/app/model/Transaction';

import { HttpService } from 'src/app/services/http.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-journal-edit',
  templateUrl: './journal-edit.component.html',
  styleUrls: ['./journal-edit.component.scss']
})
export class JournalEditComponent implements OnInit{
  
  constructor(private router: Router, public http: HttpService, private route: ActivatedRoute, private storageService: StorageService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.getByID(this.id);
    this.subscription = this.storageService.currentMessage.subscribe(message => this.sharedMessage = message);
    this.username = this.storageService.getUser().userName;
  }
  id!: any;
  journalList!: Transaction;

  journalForm: FormGroup = new FormGroup({
    id: new FormControl(),
    userName: new FormControl(this.storageService.getUser().userName),
    entryDate: new FormControl(''),
    amount: new FormControl(),
    debitAccount: new FormControl(''),
    creditAccount: new FormControl(''),
    description: new FormControl(''),
    createdAt: new FormControl(''),
    updatedAt: new FormControl(''),
    createdBy: new FormControl(''),
    updatedBy: new FormControl('')
  
  })

  getByID(id: any) {
    this.http.getByID(id).subscribe((result: any) => {
      this.journalList = result;
      this.journalForm.setValue(this.journalList)
    })
  }

  onSubmit(){
    
    this.http.updateData(this.journalForm.value).subscribe((result:any)=>{
      this.journalForm = result;
      console.log(this.journalForm.value);
      this.router.navigateByUrl('/journalList')
    })
    
  }
  username!: string;
  sharedMessage: string = 'EN';
  subscription!: Subscription;
}
