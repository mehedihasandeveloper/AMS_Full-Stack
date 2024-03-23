import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js/auto';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-doughnut',
  templateUrl: './doughnut.component.html',
  styleUrls: ['./doughnut.component.scss']
})
export class DoughnutComponent implements OnInit {
  public assets!: any[];

  constructor(public auth: AuthService) { }
  public chartdou: any;
  ngOnInit(): void {
    this.createDoughnutChart();
  }

  createDoughnutChart() {
    this.auth.getDoughnut().subscribe((r: any) => {
      this.assets = r;
      this.chartdou = new Chart("MyChartDoughnut", {
        type: 'doughnut', //this denotes tha type of chart

        data: {// values on X-Axis
          labels: ['Cash', 'Equipment', 'Supplies', 'Receivable'],
          datasets: [{
            label: 'My First Dataset',
            data: this.assets,
            backgroundColor: [
              '#42A5E3',
              '#FB6885',
              '#24B684',
              '#FFC107'
            ],
            hoverOffset: 4
          }],
        },
        options: {
          aspectRatio: 2.5
        }

      });
    })

  }
}
