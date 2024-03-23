import { Component, OnInit } from '@angular/core';
import { StorageService } from 'src/app/services/storage.service';
import { AuthService } from 'src/app/services/auth.service';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {

  constructor(private authService: AuthService, private storageService: StorageService, public route: Router) { }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.username = this.storageService.getUser().userName;
      this.roles = this.storageService.getUser().roles;
      this.route.navigateByUrl("/home")
    }
  }

  signIn: FormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  })

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: any[] = [];
  username: string = ''


  form: any = {
    username: null,
    password: null
  };

  onSubmit(): void {
    const { username, password } = this.form;
    // console.log("Role---- -- -- ", this.form)

    this.authService.login(username, password).subscribe({
      next: data => {
        console.log("Role---- -- -- ", data)
        this.reloadPage();
        this.storageService.saveUser(data);
        this.isLoggedIn = true;
        this.roles = this.storageService.getUser().roles;
        const user = this.storageService.getUser();
        // console.log("user-----",user)
        this.username = this.storageService.getUser().userName;
        console.log("user name-----", this.username)
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    });
    
  }




  reloadPage(): void {
    window.location.reload();
  }
}
