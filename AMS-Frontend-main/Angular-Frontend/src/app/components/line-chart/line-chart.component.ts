import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import Chart from 'chart.js/auto';
import { Subscription } from 'rxjs/internal/Subscription';
import { AuthService } from 'src/app/services/auth.service';
import { StorageService } from 'src/app/services/storage.service';
@Component({
  selector: 'app-line-chart',
  templateUrl: './line-chart.component.html',
  styleUrls: ['./line-chart.component.scss']
})
export class LineChartComponent implements OnInit {
  constructor(public auth: AuthService, private storageService: StorageService) { }
  ngOnInit(): void {
    this.subscription = this.storageService.currentMessage.subscribe(message => this.sharedMessage = message);
    this.createLineChart();
  }

  searchForm: FormGroup = new FormGroup({
    // startMonth: new FormControl(''),
    endMonth: new FormControl('')
  })

  public revenue!: any[];
  public chartW: any;
  public labels: any[] = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'];

  createLineChart() {
    if (this.searchForm.value.endMonth == '1') {
      this.labels = ['Jan', 'Feb', 'Mar', 'Apr']
      this.chartW.destroy();
      this.auth.getRevenueHalfYear().subscribe((r: any) => {
        this.revenue = r;

        this.chartW = new Chart("MyChartLine", {
          type: 'line',
          data: {
            labels: this.labels,
            datasets: [{
              label: 'Revenue',
              data: this.revenue,// End data 
              backgroundColor: '#4BC0C0',
              fill: true,
              tension: 0.1
            }],
            // End datasets 
          }// 
        })
      })
    }else if (this.searchForm.value.endMonth == '2') {
      this.labels = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun']
      this.chartW.destroy();
      this.auth.getRevenueHalfYear().subscribe((r: any) => {
        this.revenue = r;

        this.chartW = new Chart("MyChartLine", {
          type: 'line',
          data: {
            labels: this.labels,
            datasets: [{
              label: 'Revenue',
              data: this.revenue,// End data 
              backgroundColor: '#4BC0C0',
              fill: true,
              tension: 0.1
            }],
            // End datasets 
          }// 
        })
      })
    }else if (this.searchForm.value.endMonth == '3') {
      this.labels = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
      this.chartW.destroy();
      this.auth.getRevenueHalfYear().subscribe((r: any) => {
        this.revenue = r;

        this.chartW = new Chart("MyChartLine", {
          type: 'line',
          data: {
            labels: this.labels,
            datasets: [{
              label: 'Revenue',
              data: this.revenue,// End data 
              backgroundColor: '#4BC0C0',
              fill: true,
              tension: 0.1
            }],
            // End datasets 
          }// 
        })
      })
    }else {
      this.auth.getRevenueHalfYear().subscribe((r: any) => {
        this.revenue = r;

        this.chartW = new Chart("MyChartLine", {
          type: 'line',
          data: {
            labels: this.labels,
            datasets: [{
              label: 'Revenue',
              data: this.revenue,// End data 
              backgroundColor: '#4BC0C0',
              fill: true,
              tension: 0.1
            }],
            // End datasets 
          }// 
        })
      })
    }
  }
  sharedMessage:string = 'EN';
  subscription!: Subscription;
}