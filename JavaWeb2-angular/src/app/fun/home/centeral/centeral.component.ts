import {Component, OnInit} from '@angular/core';

import * as echarts from 'echarts';

@Component({
    selector: 'app-web-home-centeral',
    templateUrl: './centeral.html',
    styleUrls: ['./centeral.scss']
})

export class CenteralComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
    this.chart1();
  }

  chart1() {
    const lineChart = echarts.init(document.getElementById('lineChart'));
    const lineChartOption = {
      xAxis: {
        type: 'category',
        data: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期日']
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        data: [709, 832, 951, 673, 789, 535, 1320],
        type: 'line'
      }]
    };
    lineChart.setOption(lineChartOption);
  }

}
