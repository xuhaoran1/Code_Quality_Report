
<template>
	<view class="content">
	    <mckou-weather :weatherData="weatherData" ref="mckouWeather"></mckou-weather>
	</view>
</template>

<script>
	import mckouWeather from '../../components/mckou-weather/mckouWeather';
	export default {
		data() {
			return {
				 weatherData: []
				 							
			}
		},
		onLoad() {
			
		    var that = this;
		    that.$nextTick(() => {
		        that.$refs.mckouWeather.init();
		    });
			uni.getLocation({
				type:'wgs84',
				geocode:true,//必写项
				success:(res)=>{
					console.log(res.address.city)
						  var city = res.address.city;
						  uni.request({
						  	url:'http://wthrcdn.etouch.cn/weather_mini?city=' + city,
						  	data:{},
						  	success:(res)=>{
						  		console.log(res)
								//weatherData
								console.log(res.data.data)
								that.weatherData=[]
								// for (var i=0;i<7;i++){
									
								// }
								var a = res.data.data.yesterday.date.split('星')
								//a[1][0] = '周'
								a[1] = "周"+a[1][1]
								var ys_arr_max = res.data.data.yesterday.high.match(/\d+(.\d+)?/g)
								var ys_arr_min = res.data.data.yesterday.low.match(/\d+(.\d+)?/g)
								//console.log(arr_max)
								that.weatherData.push({
									dayWeek: a[1],
									dayLabel: a[0],
									weatherLabel: res.data.data.yesterday.type,
									weatherIcon: "http://file.powerecs.com/fileserver/fileserver/icon1.png",
									max: Number(ys_arr_max[0]),
									min: Number(ys_arr_min[0]),
									wind: res.data.data.yesterday.fx,
									air: "良"
								})
								for (var n in res.data.data.forecast){
									var b = res.data.data.forecast[n].date.split('星')
									b[1] = "周"+b[1][1]
									var arr_max = res.data.data.forecast[n].high.match(/\d+(.\d+)?/g)
									var arr_min = res.data.data.forecast[n].low.match(/\d+(.\d+)?/g)
									that.weatherData.push({
										dayWeek: b[1],
										dayLabel: b[0],
										weatherLabel: res.data.data.forecast[n].type,
										weatherIcon: "http://file.powerecs.com/fileserver/fileserver/icon1.png",
										max: Number(arr_max[0]),
										min: Number(arr_min[0]),
										wind: res.data.data.forecast[n].fengxiang,
										air: "良"
									})
								}
								that.weatherData.push(that.weatherData[0])
								//console.log(that.weatherData)
								
						  	}
						 })
					
				}
			})
		},
		onShow() {
			var that = this;
			that.$nextTick(() => {
			    that.$refs.mckouWeather.init();
			});
			uni.getLocation({
				type:'wgs84',
				geocode:true,//必写项
				success:(res)=>{
					console.log(res.address.city)
						  var city = res.address.city;
						  uni.request({
						  	url:'http://wthrcdn.etouch.cn/weather_mini?city=' + city,
						  	data:{},
						  	success:(res)=>{
						  		console.log(res)
								//weatherData
								console.log(res.data.data)
								that.weatherData=[]
								// for (var i=0;i<7;i++){
									
								// }
								var a = res.data.data.yesterday.date.split('星')
								//a[1][0] = '周'
								a[1] = "周"+a[1][1]
								var ys_arr_max = res.data.data.yesterday.high.match(/\d+(.\d+)?/g)
								var ys_arr_min = res.data.data.yesterday.low.match(/\d+(.\d+)?/g)
								//console.log(arr_max)
								that.weatherData.push({
									dayWeek: a[1],
									dayLabel: a[0],
									weatherLabel: res.data.data.yesterday.type,
									weatherIcon: "http://file.powerecs.com/fileserver/fileserver/icon1.png",
									max: Number(ys_arr_max[0]),
									min: Number(ys_arr_min[0]),
									wind: res.data.data.yesterday.fx,
									air: "良"
								})
								for (var n in res.data.data.forecast){
									var b = res.data.data.forecast[n].date.split('星')
									b[1] = "周"+b[1][1]
									var arr_max = res.data.data.forecast[n].high.match(/\d+(.\d+)?/g)
									var arr_min = res.data.data.forecast[n].low.match(/\d+(.\d+)?/g)
									that.weatherData.push({
										dayWeek: b[1],
										dayLabel: b[0],
										weatherLabel: res.data.data.forecast[n].type,
										weatherIcon: "http://file.powerecs.com/fileserver/fileserver/icon1.png",
										max: Number(arr_max[0]),
										min: Number(arr_min[0]),
										wind: res.data.data.forecast[n].fengxiang,
										air: "良"
									})
								}
								that.weatherData.push(that.weatherData[0])
								//console.log(that.weatherData)
								
						  	}
						 })
					
				}
			})
		},
		components: {
					mckouWeather
				},

		methods: {
			
		}
	}
</script>

<style>
</style>
