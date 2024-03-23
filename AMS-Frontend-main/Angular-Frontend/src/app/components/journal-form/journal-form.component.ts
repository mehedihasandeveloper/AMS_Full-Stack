import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { Transaction } from 'src/app/model/Transaction';

import { HttpService } from 'src/app/services/http.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-journal-form',
  templateUrl: './journal-form.component.html',
  styleUrls: ['./journal-form.component.scss']
})
export class JournalFormComponent implements OnInit {

  constructor(private route: Router, public http: HttpService, private storageService: StorageService) { }

  ngOnInit(): void {
    this.subscription = this.storageService.currentMessage.subscribe(message => this.sharedMessage = message);
    if (this.storageService.isLoggedIn()) {
    
      this.username = this.storageService.getUser().userName;
    } else {
      this.route.navigateByUrl('/sign-in')
    }
  }
  journalForm: FormGroup = new FormGroup({
    userName: new FormControl(this.storageService.getUser().userName),
    entryDate: new FormControl(''),
    amount: new FormControl(),
    debitAccount: new FormControl(''),
    creditAccount: new FormControl(''),
    description: new FormControl('')
  })

  journal!: Transaction;
  username!: string;

  onSubmit() {

    this.http.addData(this.journalForm.value).subscribe((result: any) => {
      this.journal = result;
      console.log(this.journalForm.value);
      this.uploadFiles(this.journal.id);
      this.route.navigateByUrl('/journalList')
    })

  }

  selectedFiles?: FileList;



  selectFiles(event: any): void {

    this.selectedFiles = event.target.files;
  }
  upload(idx: number, file: File, id: any): void {


    if (file) {
      this.http.upload(file, id).subscribe({
        next: (event: any) => {

        },
        error: (err: any) => {


        }
      });
    }
  }

  uploadFiles(id: any): void {

    if (this.selectedFiles) {
      for (let i = 0; i < this.selectedFiles.length; i++) {
        this.upload(i, this.selectedFiles[i], id);
      }
    }
  }

  sharedMessage: string = 'EN';
  subscription!: Subscription;
}
