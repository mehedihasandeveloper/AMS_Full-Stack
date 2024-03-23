import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import Chart from 'chart.js/auto';
import { Subscription } from 'rxjs/internal/Subscription';
import { AuthService } from 'src/app/services/auth.service';
import { StorageService } from 'src/app/services/storage.service';
@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.scss']
})
export class BarChartComponent implements OnInit {
  constructor(private auth: AuthService, private storageService: StorageService) { }
  public chart: any;
  public revenue!: any[];
  public cash!: string[];
  public labels: any[] = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'];

  ngOnInit(): void {
    this.subscription = this.storageService.currentMessage.subscribe(message => this.sharedMessage = message);
    this.createBarChart();
  }

  searchForm: FormGroup = new FormGroup({
    // startMonth: new FormControl(''),
    endMonth: new FormControl('')
  })


  createBarChart() {

    if (this.searchForm.value.endMonth == '1') {
      this.labels = ['Jan', 'Feb', 'Mar']
      this.chart.destroy();
      this.auth.getCashSpendingHalfYear().subscribe((result: any) => {
        this.cash = result;
        this.auth.getRevenueHalfYear().subscribe((r: any) => {
          this.revenue = r;
          this.chart = new Chart("MyChart", {
            type: 'bar',

            data: {
              labels: this.labels,
              datasets: [
                {
                  label: "Cash Spending",
                  data: this.cash,
                  backgroundColor: '#42A5E3'
                },
                {
                  label: "Revenue",
                  data: this.revenue,
                  backgroundColor: '#FB6885'
                }
              ]
            },
            options: {
              aspectRatio: 2.5
            }

          });
        })

      })

    }else if (this.searchForm.value.endMonth == '2') {
      this.chart.destroy();
      this.labels = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun']
      this.auth.getCashSpendingHalfYear().subscribe((result: any) => {
        this.cash = result;
        this.auth.getRevenueHalfYear().subscribe((r: any) => {
          this.revenue = r;
          this.chart = new Chart("MyChart", {
            type: 'bar',

            data: {// values on X-Axis
              labels: this.labels,
              // ,['Jan', 'Feb', 'Mar', 'Apr',
              // 'May', 'Jun', 'Jul']
              datasets: [
                {
                  label: "Cash Spending",
                  data: this.cash,
                  backgroundColor: '#42A5E3'
                },
                {
                  label: "Revenue",
                  data: this.revenue,
                  backgroundColor: '#FB6885'
                }
              ]
            },
            options: {
              aspectRatio: 2.5
            }

          });
        })

      })
    }else if (this.searchForm.value.endMonth == '3') {
      this.chart.destroy();
      this.labels = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun','Jul','Aug', 'Sep', 'Oct','Nov', 'Dec' ]
      this.auth.getCashSpendingHalfYear().subscribe((result: any) => {
        this.cash = result;
        this.auth.getRevenueHalfYear().subscribe((r: any) => {
          this.revenue = r;
          this.chart = new Chart("MyChart", {
            type: 'bar',

            data: {// values on X-Axis
              labels: this.labels,
              // ,['Jan', 'Feb', 'Mar', 'Apr',
              // 'May', 'Jun', 'Jul']
              datasets: [
                {
                  label: "Cash Spending",
                  data: this.cash,
                  backgroundColor: '#42A5E3'
                },
                {
                  label: "Revenue",
                  data: this.revenue,
                  backgroundColor: '#FB6885'
                }
              ]
            },
            options: {
              aspectRatio: 2.5
            }

          });
        })

      })
    }
    else {
      
      this.auth.getCashSpendingHalfYear().subscribe((result: any) => {
        this.cash = result;
        this.auth.getRevenueHalfYear().subscribe((r: any) => {
          this.revenue = r;
          this.chart = new Chart("MyChart", {
            type: 'bar',

            data: {
              labels: this.labels,
              
              datasets: [
                {
                  label: "Cash Spending",
                  data: this.cash,
                  backgroundColor: '#42A5E3'
                },
                {
                  label: "Revenue",
                  data: this.revenue,
                  backgroundColor: '#FB6885'
                }
              ]
            },
            options: {
              aspectRatio: 2.5
            }

          });
        })

      })

    }
  }

  sharedMessage:string = 'EN';
  subscription!: Subscription;

}
