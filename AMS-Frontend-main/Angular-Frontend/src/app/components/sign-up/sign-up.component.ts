import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';
import { AuthService } from 'src/app/services/auth.service';
import { HttpService } from 'src/app/services/http.service';
import { StorageService } from 'src/app/services/storage.service';


@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  ngOnInit(): void {
    this.subscription = this.storageService.currentMessage.subscribe(message => this.sharedMessage = message);
    if (this.storageService.isLoggedIn()) {

    } else {
      this.route.navigateByUrl('/sign-in')
    }
  }
  constructor(private route: Router, public http: HttpService, public auth: AuthService, private storageService: StorageService) { }

  signUpForm: FormGroup = new FormGroup({
    username: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    userFirstName: new FormControl(''),
    userLastName: new FormControl('')
  })

  onSubmit() {
    console.log(this.signUpForm.value);
    this.auth.register(this.signUpForm.value).subscribe((result: any) => {
      console.log(this.signUpForm.value);
      this.route.navigateByUrl('/userList');
    })
  }

  sharedMessage: string = 'EN';
  subscription!: Subscription;
}

