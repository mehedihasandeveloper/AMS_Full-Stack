import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';
import { AuthService } from 'src/app/services/auth.service';

import { HttpService } from 'src/app/services/http.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-all-roles',
  templateUrl: './all-roles.component.html',
  styleUrls: ['./all-roles.component.scss']
})
export class AllRolesComponent {
  constructor(public http: HttpService, private storageService: StorageService, private route: Router, private authService: AuthService) { }
  journalList: any[] = []
  ngOnInit(): void {
    this.subscription = this.storageService.currentMessage.subscribe(message => this.sharedMessage = message);
    if (this.storageService.isLoggedIn()) {
      this.getAll();
    } else {
      this.route.navigateByUrl('/sign-in')
    }
  }

  getAll() {
    this.authService.getAllRoles().subscribe((result: any) => {
      this.journalList = result;
    })
  }

  sharedMessage: string = 'EN';
  subscription!: Subscription;
}
