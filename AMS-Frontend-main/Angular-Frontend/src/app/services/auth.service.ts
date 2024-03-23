import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Role } from '../model/role';
import { User } from '../model/user';
import { SignIn } from '../model/signIn';

const AUTH_API = 'http://localhost:8080/api/auth/';


const AUTH_API_REG = 'http://localhost:8080/';


const Role_API_REG = 'http://localhost:8080/api/roles';

const User_API_REG = 'http://localhost:8080/api/users';

const Dashboard_API = 'http://localhost:8080/dashboard';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'signin',
      {
        username,
        password,
      },
      httpOptions
    );
  }

  register(user: User): Observable<any> {
    return this.http.post(
      AUTH_API + 'signup', user
    );
  }

  deleteUser(username: any) {
    return this.http.delete(User_API_REG + '/' + username);
  }

  logout(): Observable<any> {
    return this.http.post(AUTH_API + 'signout', {}, httpOptions);
  }

  // Role related api
  roleCreate(role: Role) {
    return this.http.post(Role_API_REG, role);
  }

  getAllRoles() {
    return this.http.get(Role_API_REG, httpOptions);
  }

  getRole(roleName: string){
    return this.http.get('http://localhost:8080/api/roles' + '/' + roleName); 
  }

  // http://localhost:8080/api/roles/

  getAllUser() {
    return this.http.get(User_API_REG, httpOptions);
  }

 

  getCashBalance() {
    return this.http.get(Dashboard_API + '/cash', httpOptions);
  }

  getRevenueBalance() {
    return this.http.get(Dashboard_API + '/revenue', httpOptions);
  }
  getReceivaleBalance() {
    return this.http.get(Dashboard_API + '/receivable', httpOptions);
  }
  getLoanBalance() {
    return this.http.get(Dashboard_API + '/loan', httpOptions);
  }
  getCashSpendingHalfYear() {
    return this.http.get(Dashboard_API + '/getCashSpending', httpOptions);
  }
  getRevenueHalfYear() {
    return this.http.get(Dashboard_API + '/getRevenue', httpOptions);
  }

  getDoughnut() {
    return this.http.get(Dashboard_API + '/doughnut', httpOptions);
  }

 
}
