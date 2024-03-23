import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';
import { Role } from 'src/app/model/role';
import { AuthService } from 'src/app/services/auth.service';
import { HttpService } from 'src/app/services/http.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-role-edit',
  templateUrl: './role-edit.component.html',
  styleUrls: ['./role-edit.component.scss']
})
export class RoleEditComponent implements OnInit{

  constructor(private route: Router, public http: HttpService, public auth: AuthService, private storageService: StorageService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.subscription = this.storageService.currentMessage.subscribe(message => this.sharedMessage = message);
    this.roleName = this.activatedRoute.snapshot.params['roleName'];
     this.getByID(this.roleName);
  }
  
  roleForm: FormGroup = new FormGroup({
    roleName: new FormControl(''),
    roleDescription: new FormControl(''),
    active: new FormControl()
  })


  roleName!: any;
  journalList!:any[];
  role!: Role;


  onSubmit() {
    
    this.auth.roleCreate(this.roleForm.value).subscribe((result: any) => {
      console.log(this.roleForm.value); 
      this.role = result;
      this.route.navigateByUrl('/roleList')
    })
  }

  getByID(roleName: any) {
    this.auth.getRole(roleName).subscribe((result: any) => {
      this.journalList = result;
      this.roleForm.setValue(this.journalList)
    })
  }

  sharedMessage: string = 'EN';
  subscription!: Subscription;
  
}
