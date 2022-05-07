<template>
		<view class="task">
			
			<scroll-view scroll-x class="bg-white nav text-center fixed" :style="[{top:CustomBar + 'px'}]">
				<view class="cu-item" :class="index==TabCur?'text-blue cur':''" v-for="(item,index) in tabNav" :key="index" @tap="tabSelect"
				 :data-id="index">
					{{tabNav[index]}}
				</view>
			</scroll-view>
			
			
			<block v-if="TabCur==0">
				<view class="target-info">
					<image class="target-img" :src="imgurl"></image>
					<view class="infos">
						<p id="name">姓名：{{target.name}}</p>
						<p id="age">年龄：{{target.age}}</p>
						<p id="location">走失地点：{{target.location}}</p>
						<p id="description">家属描述：{{target.description}}</p>
					</view>
				</view>
				<view class="team-info">
					<image class="friend-img" :src="friendurl"></image>
					<view class="i">
						我的信息
						<p id="my-name">姓名：{{my.name}}</p>
						<p id="my-tell">电话：{{my.tell}}</p>
						<p id="my-loc">位置：{{my_latitude}}{{my_longitude}}</p>
					</view>
					<view class="you">
						队友信息
						<p id="your-name">姓名：{{your.name}}</p>
						<p id="your-tell">电话：{{your.tell}}</p>
						<p id="your-loc">位置：{{your.loc}}</p>
					</view>
				</view>
				<view class="other-info">
					<button class="hospital" @click="findH()">医院</button>
					<button class="police" @click="findP()">警局</button>
					<button class="save" @click="findS()">天气</button>
				</view>
			</block>
			
			<block v-if="TabCur==1">
			<view class="new3">
				<view class="img-title3">
					<image class="center-img edit-img" :src="editurl"></image>
					<div id="title">{{ msg }}</div>
				</view>
				<view class="newest-info">
					<view class="task-row3" v-for="(item3,key3) in list3" :key="key3">
						<p id="task-info3">{{item3.info3}}</p>
					</view>
				</view>
			</view>
				<view class="upload">
					<textarea id="texting" v-model="message" placeholder="输入线索(限140字)"></textarea>
					<button class="info-up" @click="infoUp()">上传</button>
				</view>
			</block>
				
			<block v-if="TabCur==2">
					<view class="uni-list">
						
						<view class="uni-list-upload">
							<view class="img-title">
								<image class="center-img warn-img" :src="warnurl"></image>
								<view class="uni-list-title">请上传正面照</view>
							</view>
							<view class="uni-list-cell">
								<view class="uni-list-cell-img">
										<view class="uni-uploader__files">
											<block v-for="(image,index) in image_new" :key="index">
												<view class="uni-uploader__file">
													<image class="uni-uploader__img" :src="image" :data-src="image" @tap="previewImage"></image>
												</view>
											</block>
											<view class="uni-uploader__input-box">
												<view class="uni-uploader__input" @tap="chooseImage"></view>
											</view>
										</view>
								</view>
								<view class="uni-list-cell-buttons">
									<view class="uni-list-cell-upload">
										<button class="uni-label" @tap="uploadImg">上传</button>
									</view>
									<view class="uni-list-cell-rechoose">
										<button class="uni-label" @tap="delImage">重选</button>
									</view>
								</view>
							</view>
						</view>
						
						<view class="similarity">
							<view class="result">
								<view class="img-title">
									<image class="center-img peoplefill-img" :src="peoplefillurl"></image>
									<text id="title4">相似度结果</text>
								</view>
								<block v-for="(image,index) in imageList" :key="index">
							<image id="image" @tap="previewImage" :src="image" data-src="image" mode="widthFix"></image>
								</block>
							</view>
							<view class="data">相似度达到:{{result}}{{temp_str}}</view>
						</view>
						
						<view class="success">
							<button class="call" @click="call()" :disabled='isphone=="true"'>致电家属</button>
							<button class="done" @click="taskDone()" :disabled='isfinish=="true"' >{{finish_text}}</button>
						</view>
					</view>
			</block>
			
			<block v-if="TabCur==3">
				<view class="map">
					<view class="mapbox">
						
							<!-- 在map标签绑定经纬度和标记点数组，以及连线数组 -->
							<map id="maps" :latitude="my_latitude" :longitude="my_longitude" :markers="markers" :polyline="polylines" :circles="circles" scale="18"></map>
							
					</view>
				</view>
			</block>
		</view>
</template>

<script>
	let mapSearch = weex.requireModule('mapSearch');
	import { transform, WGS84, GCJ02 } from 'gcoord';
	import gcoord from 'gcoord';
	var util = require('../../common/util.js');
	var formatLocation = util.formatLocation;
	export default {
		
		data() {
			
			return {
				title: 'chooseLocation',
				hasLocation: false,
				location: {},
				locationAddress: '',
				colors:['#FF0000','#6495ED','#8B0000','#000000','#F08080','#A0522D','#CD853F','#FF8C00','#FFD700','#808000','#008000','#32CD32','#008080','#2F4F4F','#00FFFF','#00BFFF','#4682B4','#000080','#0000FF','#800080'],
				circles:[],
				markers:[],//标记点数组
				polylines:[],//连续数组
				my_longitude:'',
				my_latitude:'',
				refresh:false,
				finish_text:'任务完成',
				temp_str:'',
				isfinish:"true",
				isphone:"true",
				result:'',
				token:'',
				image_new:[],
				imageList:[],
				phone_num:"15166529595",
				CustomBar: this.CustomBar,
				TabCur: 0,
				tabNav: ['基础信息', '最新信息', '人脸识别','任务地图'],
				
				imgurl:'',
				friendurl: '../../static/friend.png',
				warnurl: '../../static/warn.png',
				peoplefillurl: '../../static/people_fill.png',
				
				target: {name:'',
					age:'',
					location:'',
					description:'',
				},
				
				my: {
					name:'',
					tell:'',
					loc:'',
					},
				your: {
					name:'',
					tell:'',
					loc:'',
					},
				
				msg: '',
				editurl: '../../static/edit.png',
				
				list3: [
					{info3:'这是第1条队员反馈信息这是第1条队员反馈信息这是第1条队员反馈信息这是第1条队员反馈信息'},
					{info3:'这是第1条家属反馈信息'},
					{info3:'这是第2条队员反馈信息'},
					{info3:'这是第2条家属反馈信息'},
					{info3:'这是第3条队员反馈信息'},
					{info3:'这是第3条家属反馈信息'},
					{info3:'这是第4条队员反馈信息'},
					{info3:'这是第4条家属反馈信息'},
					{info3:'这是第5条队员反馈信息'},
					{info3:'这是第5条家属反馈信息'},
					{info3:'这是第6条队员反馈信息'},
					{info3:'这是第6条家属反馈信息'}
				],
				
				message:'',
				
				
				
				count: 1,   //设置能上传的最大数量
			}
		},
		
		onLoad() {
			
		},
		onReady() {
			
		},
		onShow() {
			//setInterval(,2000)
			var that = this;
			uni.getStorage({
						    key: 'token',
						    success: function (res) {
						        that.token=String(res.data);
								uni.request({
									url: 'http://139.224.208.92:8080/app/getAcTaskid',
									header:{
										'content-type':'application/x-www-form-urlencoded'
									},
									method:'GET',
									data: {
									    'token': String(that.token)
									},
									success: function (res) {
										console.log(res.data)
										//window.InitSetInterval=setInterval(,2000)
										if(res.data.msg=="taskId已经返回"){
											//console.log(res.data);
											//console.log(111);
											if(that.refresh==true)
											{
												uni.request({
												url: 'http://139.224.208.92:8080/app/jobIsOk',
												header:{
													'content-type':'application/x-www-form-urlencoded'
												},
												method:'GET',
												data: {
												    'token': String(that.token)
												},
												success: function (res) {
													//console.log(res.data)
													if(res.data.msg=='任务状态等待管理员确认')
													{
														
													}else if(res.data.msg=='任务已结束')
													{
														uni.showToast({
														 	    title: '任务完成',
														 	    duration: 2000
														 	});
														that.finish_text='任务完成';
														that.imageList=[];
														that.result='';
														that.isphone='true';
														that.temp_str='';
														that.isfinish='true';
														that.refresh=false;
													}else if(res.data.msg=='任务仍然未结束')
													{
														uni.showToast({
														 	    title: '任务仍未完成',
														 	    duration: 2000
														 	});
														that.finish_text='任务完成';
														that.imageList=[];
														that.result='';
														that.isphone='true';
														that.temp_str='';
														that.isfinish='true';
														that.refresh=false;
													}
												}
											})
											}
											uni.request({
												url: 'http://139.224.208.92:8080/app/getReJob',
												header:{
													'content-type':'application/x-www-form-urlencoded'
												},
												method:'GET',
												data: {
												    'token': String(that.token)
												},
												success: function (res) {
													console.log(res.data)
													that.imgurl= res.data.image_url;
													that.target.name = res.data.target_name;
													that.target.age = res.data.target_age;
													that.target.location = res.data.lost_position;
													that.target.location = "经度"+String(res.data.lost_position).split(',')[0];
													that.target.location += "纬度"+String(res.data.lost_position).split(',')[1]
													that.getlocal();
												
													that.target.description = "长相为"+res.data.target_appearance;
													that.target.description += "穿着为"+res.data.target_cloth;
													that.target.phone_num=res.data.family_contact;
													
												}
											})
											uni.request({
												url: 'http://139.224.208.92:8080/app/selectByToken',
												
												data:{
														'token':String(that.token)
												},
												success: (res) => {
													console.log(res.data)
													that.my.name = res.data.data.name;
													that.my.tell = res.data.data.telephone;
													that.my.loc = '121.111';
												}
											})
											uni.getLocation({
											    type: 'wgs84',
											    success: function (res) {
											        that.my_longitude = '经度:' + res.longitude;
											        that.my_latitude = '纬度:' + res.latitude;
											    }
											});
											uni.request({
												url: 'http://139.224.208.92:8080/app/getTm',
												
												data:{
														'token':String(that.token)
												},
												success: (res) => {
													console.log(res.data)
													if(res.data.isok=='true'){
															that.your.name = res.data.name;
															that.your.tell = res.data.telephone;
															that.your.loc = '';
													}
													else{
														that.your.name = "您当前无队友";
														that.your.tell = "您当前无队友";
														that.your.loc = "您当前无队友";
													}
													
												}
											})
											uni.request({
												url: 'http://139.224.208.92:8080/app/getNewClue',
												
												data:{
														'token':String(that.token)
												},
												success: (res) => {
													//console.log(res.data)
													if(res.data.isok=='true'){
															that.list3=[];
															if(res.data.vdata.length==0 &&res.data.vdata.length==0)
															{
															
																	
																
															}
															else{
															for (var i in res.data.vdata){
																
																
																that.list3.push({info3:'队员第'+String(Number(i)+1)+'条反馈:'+res.data.vdata[i].content});
															}
															for (var i in res.data.fdata){
																that.list3.push({info3:'家属第'+String(Number(i)+1)+'条反馈:'+res.data.fdata[i].content});
																
															}
															
															}
															
													}
												
												}
											})
											
										}else if(res.data.msg=="您当前没有任务可以查询，先去接收任务"){
											uni.showModal({
											    title: '提示',
											    content: '您没有接受任务',
											    success: function (res) {
											        if (res.confirm) {
											            uni.switchTab({
											            	url:"../new/new"
											            })
											        } else if (res.cancel) {
											            uni.switchTab({
											            	url:"../new/new"
											            })
											        }
											    }
											});
										}
										
										
									}
								})
								
								},
								
			}
			);
		},
		/*mounted() {//滑动页面
		window.addEventListener("scroll", this.handleScroll, true);
		},*/
		
		methods: {
			
			//更新所有人的经纬度了,
			getlocal: function() {
				let _this = this;
				//显示当前位置
				var map1 = uni.createMapContext('maps', _this).$getAppMap();
				//map1.showUserLocation(true);
				//map1.showUserLocation(true);
				//获取当前定位
				uni.getLocation({
					type: 'wgs84',
					success: function(res) {
						
						_this.my_longitude = res.longitude;
						_this.my_latitude = res.latitude;
						uni.request({
							url: 'http://139.224.208.92:8080/app/getOtherXy',
							header:{
								'content-type':'application/x-www-form-urlencoded'
							},
							data: {
							    'token': String(_this.token),
							},
							success: function (res) {
								console.log(res.data)
								_this.markers=[];
								_this.markers.push({
									title: '我',
									width:'20',
									height:'20',
									latitude: _this.my_latitude,
									longitude: _this.my_longitude,
									iconPath: '../../static/location_my.png'
								})
								console.log(res.data)
								//console.log(111111)
								for (var i in res.data.data){
									var temp_longitude=String(res.data.data[i]).split(',')[0];//经度
									var temp_latitude=String(res.data.data[i]).split(',')[1]//纬度
									//console.log(res.data)
									//console.log(temp_latitude)
									//console.log(temp_longitude)
									_this.markers.push({
										title: '队友'+i,
										width:'20',
										height:'20',
										latitude: Number(temp_latitude),
										longitude: Number(temp_longitude),
										iconPath: '../../static/location.png'
									})
								}
								console.log(_this.markers)
							}
						})
						uni.request({
							url: 'http://139.224.208.92:8080/app/getReJob',
							header:{
								'content-type':'application/x-www-form-urlencoded'
							},
							method:'GET',
							data: {
							    'token': String(_this.token)
							},
							success: function (res) {
								console.log(res.data)
								//String(res.data.lost_position).split(',')[0]
								//String(res.data.lost_position).split(',')[1]
								console.log(res.data.lost_position)
								_this.circles=[]
								_this.circles.push({
									latitude: Number(String(res.data.lost_position).split(',')[1]),
									longitude: Number(String(res.data.lost_position).split(',')[0]),
									color:"#0016ca",//描边的颜色
									fillColor:"#99999999",//
									radius:10000,//半径
									strokeWidth:2//描边的宽度
								})
								
								
							}
						})
						uni.request({
							url: 'http://139.224.208.92:8080/app/getOtherHisXy',
							header:{
								'content-type':'application/x-www-form-urlencoded'
							},
							method:'GET',
							data: {
							    'token': String(_this.token)
							},
							success: function (res) {
								console.log(res.data)
								
								// var Length = 0;
								// for (var item in res.data.data) {
								//            Length++;
								// }
								_this.polylines = []
								for (var item in res.data.data) {
										console.log(res.data.data[item])
										var temp_whole = []
										for (var n in res.data.data[item]){
											//res.data.data[item][n].split(',')[0]
											temp_whole.push(
												{latitude: Number(res.data.data[item][n].split(',')[1]),
												longitude: Number(res.data.data[item][n].split(',')[0])}
											)
										}
									  
									  _this.polylines.push({
										  points:temp_whole,
									　　　　color:_this.colors[item],//线的颜色
									　　　　width:20,//线的宽度
									　　　　
									  })
									  }
									  console.log(_this.polylines)
									  
								// _this.circles=[]
								// _this.circles.push({
								// 	latitude: Number(String(res.data.lost_position).split(',')[1]),
								// 	longitude: Number(String(res.data.lost_position).split(',')[0]),
								// 	color:"#0016ca",//描边的颜色
								// 	fillColor:"#99999999",//
								// 	radius:10000,//半径
								// 	strokeWidth:2//描边的宽度
								// })
								
								
							}
						})
						
					
						
						
					}
				})
			},
			update_line(){//更新line的数据
				
			},
			
			tabSelect(e) {//页面选择
				
				this.TabCur = e.currentTarget.dataset.id;
				this.scrollLeft = (e.currentTarget.dataset.id - 1) * 60
				
			},
			
			
			
			findH() {//查看医院
				
					uni.chooseLocation({
						keyword:"医院",
						success: (res) => {
							this.hasLocation = true,
								this.location = formatLocation(res.longitude, res.latitude),
								this.locationAddress = res.address
						}
					})
				
			},
			findP() {//查看警局
				
					uni.chooseLocation({
						keyword:"警局",
						success: (res) => {
							this.hasLocation = true,
								this.location = formatLocation(res.longitude, res.latitude),
								this.locationAddress = res.address
						}
					})
				
			},
			
			findS() {//查看天气
				console.log("天气")
				 uni.navigateTo({
				 	url:"../weather/weather"
				 })
			},
			
			infoUp() {//上传线索
			var that = this;
				uni.request({
					url: 'http://139.224.208.92:8080/app/setClue',
					header:{
						'content-type':'application/x-www-form-urlencoded'
					},
					method:'POST', 
					data:{
							'token':String(that.token),
							'clue':that.message
					},
					success: (res) => {
						//console.log(res.data)
						if(res.data.isok=='true'){
							uni.showToast({
								title:"上传成功"
							})
							uni.request({
								url: 'http://139.224.208.92:8080/app/getNewClue',
								
								data:{
										'token':String(that.token)
								},
								success: (res) => {
									//console.log(res.data)
									if(res.data.isok=='true'){
											that.list3=[];
											if(res.data.vdata.length==0 &&res.data.vdata.length==0)
											{
											
													// var jsonObj = {info3:"当前无信息"};
													// that.list3.push(jsonObj);
												
											}
											else{
											for (var i in res.data.vdata){
												//that.list3.push({info3:'家属反馈:'+res.data.fdata[i]});
												
												that.list3.push({info3:'队员第'+String(Number(i)+1)+'条反馈:'+res.data.vdata[i].content});
											}
											for (var i in res.data.fdata){
												that.list3.push({info3:'家属第'+String(Number(i)+1)+'条反馈:'+res.data.fdata[i].content});
												//that.list3.push({info3:'队员反馈:'+res.data.vdata[i]});
											}
											
											}
											
									}
								
								}
							})
						}
						
					}
				})
			},
			/*pathRecom() {//推荐路径
				
			},*/
			
			/* ---------------上传图片--------------- */
			chooseImage: async function() {
					const _self = this
					if (_self.imageList.length === _self.count) {
						let isContinue = await _self.isFullImg();
						//console.log("是否继续?", isContinue);
						if (!isContinue) {
							return;
						}
					}
					/* 选中图片 */
					uni.chooseImage({
						sourceType: ['camera', 'album'],
						sizeType: ['original', 'compressed'],
						count: _self.count,
						success: (res) => {
						this.imageList.push(res.tempFilePaths[0]);
						}
					})
				},
				/* 图片数量满了弹出窗口询问是否清空 */
				isFullImg: function() {
					return new Promise((res) => {
						uni.showModal({
							content: "已经有"+this.count+"张图片了,是否清空现有图片？",
							success: (e) => {
								if (e.confirm) {
									this.imageList = [];
								} else {
									
								}
							},
							fail: () => {
								res(false)
							}
						})
					})
				},
				/* 预览图片 */
				previewImage: function(e) {
					var current = e.target.dataset.src
					uni.previewImage({
						current: current,
						urls: this.imageList
					})
				},
				
				//上传图片
				uploadImg:function(){
									var that = this;
									const tempFilePaths = this.imageList[0]; //获取到本地图片地址
									/* 上传图片 */
									//console.log(that.token);
									const uploadTask = uni.uploadFile({
									    url:'http://139.224.208.92:8080/app/imageCompare',     // 后端api接口
									    filePath: tempFilePaths, // uni.chooseImage函数调用后获取的本地文件路劲
									    name:'file',     //后端通过'file'获取上传的文件对象
									    formData: {
									           'token':  that.token
									          },
									    header:{
											"Content-Type": "multipart/form-data"
										},
									    success:(res) => {
									        //console.log(res);
											var temp = JSON.parse(res.data);
											that.result = temp.score;
											if(parseFloat(temp.score)>0.8){
												that.temp_str=',相似度超过80%,请致电家属';
												that.isphone = 'false';
												
											}
											else{
												that.temp_str=',相似度未超过80%,请仔细辨认';
											}
										
											
									    }
									});
								},
					
				/* 删除图片 */
				delImage(e) {
					this.imageList=[];
					this.result='';
					this.isphone='true';
					this.temp_str='';
				},
				
				call() {//致电家属
					uni.makePhoneCall({
						phoneNumber: String(this.phone_num),
						success: () => {
							//console.log("成功拨打电话")
							this.isfinish = 'false';
						}
					})
					//打完电话开始
				},
				taskDone() {//任务完成
					//
					var that = this; 
					uni.request({
						url: 'http://139.224.208.92:8080/app/commitJob',
						
						data:{
								'token':String(that.token)
						},
						success: (res) => {
							console.log(res.data)
							if(res.data.msg=='任务状态已变更为待确认'){
									uni.showToast({
									    title: '任务待确认完成',
									    duration: 2000
									});
									that.finish_text='待确认';
									
									that.refresh = true;
									// that.imageList=[];
									// that.result='';
									 that.isphone='true';
									// that.temp_str='';
									 that.isfinish='true';
							}else if(res.data.msg=="当前任务已经完成，可以前往确认"){
								//that.finish_text='待确认';
								//that.isfinish='false';
								//that.refresh = true;
								uni.request({
									url: 'http://139.224.208.92:8080/app/jobIsOk',
									header:{
										'content-type':'application/x-www-form-urlencoded'
									},
									method:'GET',
									data: {
									    'token': String(that.token)
									},
									success: function (res) {
										//console.log(res.data)
										if(res.data.msg=='任务状态等待管理员确认')
										{
											
										}else if(res.data.msg=='任务已结束')
										{
											uni.showToast({
											 	    title: '任务完成',
											 	    duration: 2000
											 	});
											that.finish_text='任务完成';
											that.imageList=[];
											that.result='';
											that.isphone='true';
											that.temp_str='';
											that.isfinish='true';
										}else if(res.data.msg=='任务仍然未结束')
										{
											uni.showToast({
											 	    title: '任务仍未完成',
											 	    duration: 2000
											 	});
											that.finish_text='任务完成';
											that.imageList=[];
											that.result='';
											that.isphone='true';
											that.temp_str='';
											that.isfinish='true';
										}
									}
								})
								
							}
							// else if(res.data.msg=="任务仍然未结束"){
							// 	uni.showToast({
							// 	    title: '任务仍未完成',
							// 	    duration: 2000
							// 	});
							// 	that.finish_text='任务完成';
							// 	that.imageList=[];
							// 	that.result='';
							// 	that.isphone='true';
							// 	that.temp_str='';
							// 	that.isfinish='true';
							// }
						
						}
					})
				},
		}	
	}
	
</script>

<style lang="scss">
	.main {
		padding-left: 15rpx;
		padding-right: 15rpx;
	}

	.lawyer-list-bg {
		background-color: #eef5fe;
	
	}
	.content {
		position: absolute;
		width: 100%;
		height: 100%;
	
		#maps {
			width: 100%;
			height: 100%;
		}
	}
</style>