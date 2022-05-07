<template>
	<view class="mckou-weather-content">
		<view class="more-day-report">
			<view class="top-module">
				<view class="title1">多天预报</view>
				<picker @change="bindPickerChange" :value="weatherTypeIndex" :range="weatherTypeArray">
					<view class="change">
						<label for="">{{ weatherTypeArray[weatherTypeIndex] }}</label>
						<view class="down-arrow"></view>
						<!-- <image src="../../static/weather/xiala.svg" mode=""></image> -->
					</view>
				</picker>
			</view>
			<view class="body-module">
				<view class="body-weather-line" v-show="weatherTypeIndex != 1">
					<canvas :style="{ width: cWidth + 'px', height: '100px' }" canvas-id="weather-canvas"></canvas>
				</view>
				<view v-if="weatherTypeIndex == 1" class="zhuzhuangtu">
					<view class="zhuzhuangtu-item" v-for="(item, index) in pillarArray" :key="index">
						<view class="item-bg">
							<view class="item" :style="{ top: item.top + 'px', height: item.height + 'px' }"></view>
						</view>
					</view>
				</view>
				<view class="body-item" v-for="(item, index) in weatherData" :key="index">
					<view class="day-font">{{ item.dayWeek }}</view>
					<view class="day-time">{{ item.dayLabel }}</view>
					<view class="weather-font">{{ item.weatherLabel }}</view>
					<view class="weather-image">
						<image :src="item.weatherIcon" mode=""></image>
					</view>
					<view class="max-weather">{{ item.max }}</view>
					<view class="weather-line"></view>
					<view class="min-weather">{{ item.min }}</view>
					<view class="feng-font">{{ item.wind}}</view>
					<view class="day-state">
						<label>{{ item.air }}</label>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				weatherTypeArray: ['折线', '柱状'],
				weatherTypeIndex: 0,
				cWidth: 345,
				pillarArray: []
			}
		},
		props: {
			weatherData: {
				type: Array
			}
		},
		methods: {
			init() {
				var that = this;
				uni.getSystemInfo({
					success: function(res) {
						that.cWidth = Math.round(res.windowWidth * 0.92);
					}
				});
				that.doLineOrPiller();
			},
			doLineOrPiller() {
				if (this.weatherTypeIndex == 1) {
					this.initWeatherPiller();
				} else {
					this.initWeatherPointLine();
				}
			},
			//处理折线图
			initWeatherPointLine() {
				var maxArry = this.weatherData.map((item) => {
					return item.max;
				});
				var minArry = this.weatherData.map((item) => {
					return item.min;
				});
				var max = [];
				var min = [];
				var maxWeather = Math.max(...maxArry);
				var minWeather = Math.min(...minArry);
				var diff = maxWeather - minWeather;
				for (var i = 0; i < maxArry.length; i++) {
					var height = Math.round(((maxArry[i] - minArry[i]) / diff) * 80);

					max[i] = 50 - Math.round((80 / diff) * (maxWeather - maxArry[i]) + 10);
					min[i] = max[i] - height;
				}

				var section7width = Math.round(this.cWidth / 7);
				var cvs = uni.createCanvasContext('weather-canvas',this);
				cvs.setStrokeStyle('#FB7821');
				cvs.setLineWidth(1);
				cvs.beginPath();
				for (var i = 0; i < max.length; i++) {
					var wendu = max[i];
					var x = Math.round(section7width / 2) + Math.round(section7width * i);
					var y = 50 - wendu;
					cvs.arc(x, y, 5, 0, 2 * Math.PI);
					cvs.setFillStyle('#FB7821');
					cvs.fill();
					cvs.moveTo(x, y);
					if (i < max.length - 1) {
						var nextX = x + section7width;
						var nextY = 50 - max[i + 1];
						cvs.lineTo(nextX, nextY);
					}
					cvs.stroke();
					cvs.beginPath();
				}

				cvs.setStrokeStyle('#1B9DFF');
				cvs.setLineWidth(1);
				for (var i = 0; i < min.length; i++) {
					var wendu = min[i];
					var x = Math.round(section7width / 2) + Math.round(section7width * i);
					var y = 50 - wendu;
					cvs.arc(x, y, 5, 0, 2 * Math.PI);
					cvs.setFillStyle('#1B9DFF');
					cvs.fill();
					cvs.moveTo(x, y);
					if (i < min.length - 1) {
						var nextX = x + section7width;
						var nextY = 50 - min[i + 1];
						cvs.lineTo(nextX, nextY);
					}
					cvs.stroke();
					cvs.beginPath();
				}
				cvs.draw();

			},
			//处理柱状图
			initWeatherPiller() {
				var maxArry = this.weatherData.map(item => {
					return item.max;
				});
				var minArry = this.weatherData.map(item => {
					return item.min;
				});
				var pillarArray = [];

				var max = Math.max(...maxArry);
				var min = Math.min(...minArry);
				var diff = max - min;
				for (var i = 0; i < maxArry.length; i++) {
					var height = Math.round(((maxArry[i] - minArry[i]) / diff) * 80);
					pillarArray.push({
						top: Math.round((80 / diff) * (max - maxArry[i])) + 10,
						height: height
					});
				}
				this.pillarArray = pillarArray;
			},
			bindPickerChange(e) {
				var index = e.target.value;
				if (index == 1) {
					this.initWeatherPiller();
				} else {
					this.initWeatherPointLine();
				}
				this.weatherTypeIndex = index;
			},
		}
	}
</script>

<style>
	
</style>
