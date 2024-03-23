import { Component, OnInit } from '@angular/core';
import { StorageService } from './services/storage.service';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';
import { Subscription } from 'rxjs/internal/Subscription';
import { EventBusService } from './services/event-bus.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(private storageService: StorageService, private route: Router, private auth: AuthService, private eventBusService: EventBusService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();
    this.subscription = this.storageService.currentMessage.subscribe(message => this.sharedMessage = message);
    if (this.isLoggedIn) {

      const user = this.storageService.getUser();
      console.log("User---", user)
      this.roles = user.roles;


      this.showAdminInterface = this.roles.some(item => item.roleName === 'ADMIN')
      console.log('ROLE_ADMIN---', this.roles.some(item => item.roleName === 'ADMIN'))


      this.showAccountantInterface = this.roles.some(item => item.roleName === 'ROLE_MODERATOR')
      console.log('ROLE_MODERATOR---', this.roles.some(item => item.roleName === 'ROLE_MODERATOR'))

      this.showInvestorInterface = this.roles.some(item => item.roleName === 'User');

      this.username = user.username;
    }

    // this.eventBusSub = this.eventBusService.on('logout', () => {
    //   this.logOut();
    // });
  }

  private roles: any[] = [{ roleName: '', roleDescription: '' }];

  isLoggedIn = false;
  showAdminInterface = false;
  showAccountantInterface = false;
  showInvestorInterface = false;
  username?: string;
  eventBusSub?: Subscription;


  logOut() {
    this.route.navigateByUrl('sign-in')
    this.storageService.clean();
    window.location.reload();


  }

  changeLanguage(value: any) {
    console.log(value);
    this.newMessage(value);
    if(value ==="BN"){
      this.english = false;
    }else{
      this.english = true;
    }
    
  }


  english = true;
  
  
  
  title = 'AMS-frontend';


  sharedMessage:string = 'EN';
  subscription!: Subscription;

  newMessage(val:any) {
    this.storageService.changeMessage(val)
  }

}
