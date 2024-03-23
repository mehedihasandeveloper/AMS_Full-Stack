import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';
import { Role } from 'src/app/model/role';
import { AuthService } from 'src/app/services/auth.service';
import { HttpService } from 'src/app/services/http.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-create-role',
  templateUrl: './create-role.component.html',
  styleUrls: ['./create-role.component.scss']
})
export class CreateRoleComponent implements OnInit{

  constructor(private route: Router, public http: HttpService, public auth: AuthService, private storageService: StorageService) { }

  roleForm: FormGroup = new FormGroup({
    roleName: new FormControl(''),
    roleDescription: new FormControl(''),
    active: new FormControl()
  })

  role!: Role;
  onSubmit() {
    this.auth.roleCreate(this.roleForm.value).subscribe((result: any) => {
      this.role = result;
      this.route.navigateByUrl('/roleList')
    })

  }

  
  ngOnInit(): void {
    this.subscription = this.storageService.currentMessage.subscribe(message => this.sharedMessage = message);
    if (this.storageService.isLoggedIn()) {

    } else {
      this.route.navigateByUrl('/sign-in')
    }
  }

  
  sharedMessage: string = 'EN';
  subscription!: Subscription;

}
