import { HttpClient, HttpEvent, HttpHeaders, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { Transaction } from '../model/Transaction';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
 
  
  constructor(private httpClient: HttpClient) { }

  private url = "http://localhost:8080/j";

  httpOptions = {
    headers: new HttpHeaders({
      'content-type': 'application/json',
    }),
  };

  addData(journal: Transaction) {
    return this.httpClient.post(this.url, journal);
  }

  getAll() {
    return this.httpClient.get(this.url);
  }

  deleteById(id: any) {
    return this.httpClient.delete(this.url + "/" + id);
  }

  getByID(id: any) {
    return this.httpClient.get(this.url + "/" + id);
  }

  updateData(journal: Transaction) {
    return this.httpClient.put(this.url + "/" + journal.id, journal);
  }

  upload(file: File, id: any): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('image', file);
    const req = new HttpRequest('POST', `${this.url}/fileSystem/`+id, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.httpClient.request(req);
  }

  // getFiles(): Observable<any> {
  //   return this.httpClient.get(`${this.url}/fileSystem`);
  // }

  getAllCash() {
    return this.httpClient.get(this.url + "/cash");
  }

  searchCash(id?:any, startDate?: any, endDate?: any){
    let params = new HttpParams();
    if (id) params = params.set('id', id);
    if (startDate) params = params.set('startDate', startDate);
    if (endDate) params = params.set('endDate', endDate);
    return this.httpClient.get(this.url + "/searchCash", { params });
    
  }
  getAllReceivable() {
    return this.httpClient.get(this.url + "/receivable");
  }
  searchReceivable(id?:any, startDate?: any, endDate?: any){
    let params = new HttpParams();
    if (id) params = params.set('id', id);
    if (startDate) params = params.set('startDate', startDate);
    if (endDate) params = params.set('endDate', endDate);
    return this.httpClient.get(this.url + "/searchReceivable", { params });
    
  }
  getAllEquipment() {
    return this.httpClient.get(this.url + "/equipment");
  }

  searchEquipment(id?:any, startDate?: any, endDate?: any){
    let params = new HttpParams();
    if (id) params = params.set('id', id);
    if (startDate) params = params.set('startDate', startDate);
    if (endDate) params = params.set('endDate', endDate);
    return this.httpClient.get(this.url + "/searchEquipment", { params });
    
  }


  getAllSupplies() {
    return this.httpClient.get(this.url + "/supplies");
  }
  searchSupplies(id?:any, startDate?: any, endDate?: any){
    let params = new HttpParams();
    if (id) params = params.set('id', id);
    if (startDate) params = params.set('startDate', startDate);
    if (endDate) params = params.set('endDate', endDate);
    return this.httpClient.get(this.url + "/searchSupplies", { params });
  }
  getAllExpense() {
    return this.httpClient.get(this.url + "/expense");
  }
  searchExpense(id?:any, startDate?: any, endDate?: any){
    let params = new HttpParams();
    if (id) params = params.set('id', id);
    if (startDate) params = params.set('startDate', startDate);
    if (endDate) params = params.set('endDate', endDate);
    return this.httpClient.get(this.url + "/searchExpense", { params });
  }

  getAllDrawings() {
    return this.httpClient.get(this.url + "/drawings");
  }
  searchDrawings(id?:any, startDate?: any, endDate?: any){
    let params = new HttpParams();
    if (id) params = params.set('id', id);
    if (startDate) params = params.set('startDate', startDate);
    if (endDate) params = params.set('endDate', endDate);
    return this.httpClient.get(this.url + "/searchDrawings", { params });
  }
  getAllPayable() {
    return this.httpClient.get(this.url + "/payable");
  }
  searchPayable(id?:any, startDate?: any, endDate?: any){
    let params = new HttpParams();
    if (id) params = params.set('id', id);
    if (startDate) params = params.set('startDate', startDate);
    if (endDate) params = params.set('endDate', endDate);
    return this.httpClient.get(this.url + "/searchPayable", { params });
  }
  getAllRevenue() {
    return this.httpClient.get(this.url + "/revenue");
  }
  searchRevenue(id?:any, startDate?: any, endDate?: any){
    let params = new HttpParams();
    if (id) params = params.set('id', id);
    if (startDate) params = params.set('startDate', startDate);
    if (endDate) params = params.set('endDate', endDate);
    return this.httpClient.get(this.url + "/searchRevenue", { params });
  }

  getAllCapital() {
    return this.httpClient.get(this.url + "/capital");
  } 
  searchCapital(id?:any, startDate?: any, endDate?: any){
    let params = new HttpParams();
    if (id) params = params.set('id', id);
    if (startDate) params = params.set('startDate', startDate);
    if (endDate) params = params.set('endDate', endDate);
    return this.httpClient.get(this.url + "/searchCapital", { params });
  }
  
  getReport(startDate: any, endDate: any) {
    const params = new HttpParams()
      .set('startDate', startDate)
      .set('endDate', endDate);
    // return this.httpClient.get(this.url + "/report"+ startDate + endDate);
    return this.httpClient.get<any>(this.url + "/report", { params: params });
  }

  getAssetsReport(startDate: any, endDate: any) {
    const params = new HttpParams()
      .set('startDate', startDate)
      .set('endDate', endDate);
    // return this.httpClient.get(this.url + "/report"+ startDate + endDate);
    return this.httpClient.get<any>(this.url + "/report/assetStatement", { params: params });
  }

  getOESReport(startDate: any, endDate: any) {
    const params = new HttpParams()
      .set('startDate', startDate)
      .set('endDate', endDate);
    // return this.httpClient.get(this.url + "/report"+ startDate + endDate);
    return this.httpClient.get<any>(this.url + "/report/OEStatement", { params: params });
  }
  getTBReport(startDate: any, endDate: any) {
    const params = new HttpParams()
      .set('startDate', startDate)
      .set('endDate', endDate);
    // return this.httpClient.get(this.url + "/report"+ startDate + endDate);
    return this.httpClient.get<any>(this.url + "/report/TBStatement", { params: params });
  }

  getBSReport(startDate: any, endDate: any) {
    const params = new HttpParams()
      .set('startDate', startDate)
      .set('endDate', endDate);
    // return this.httpClient.get(this.url + "/report"+ startDate + endDate);
    return this.httpClient.get<any>(this.url + "/report/BSStatement", { params: params });
  }
 
  search(id?:any, startDate?: any, endDate?: any){
    let params = new HttpParams();
    if (id) params = params.set('id', id);
    if (startDate) params = params.set('startDate', startDate);
    if (endDate) params = params.set('endDate', endDate);
    return this.httpClient.get(this.url + "/search", { params });
    
  }
// return this.http.get`${this.apiUrl}/search`, { params });
  
}
