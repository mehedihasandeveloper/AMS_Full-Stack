import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';
import { AuthService } from 'src/app/services/auth.service';

import { HttpService } from 'src/app/services/http.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-all-users',
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.scss']
})
export class AllUsersComponent {
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
    this.authService.getAllUser().subscribe((result: any) => {
      this.journalList = result;
    })
  }

  delete(username: any) {
    this.authService.deleteUser(username).subscribe((r: any) => {
      alert("Do you want to delete " + username + " ?");
      this.getAll();
    })
  }
  sharedMessage: string = 'EN';
  subscription!: Subscription;
}
