<template>
		<view class="home">
			
			<scroll-view scroll-x class="bg-white nav text-center fixed" :style="[{top:CustomBar + 'px'}]">
				<view class="cu-item" :class="index==TabCur?'text-blue cur':''" v-for="(item,index) in tabNav" :key="index" @tap="tabSelect"
				 :data-id="index">
					{{tabNav[index]}}
				</view>
			</scroll-view>
				
			<block v-if="TabCur==0">
				<!--幻灯片-->
				<swiper class="card-swiper" :class="dotStyle?'square-dot':'round-dot'" :indicator-dots="true" :circular="true"
						 :autoplay="true" interval="5000" duration="500" @change="cardSwiper" indicator-color="#7b8083"
						 indicator-active-color="#cbdce7">
					<swiper-item v-for="(item,index) in swiperList" :key="index" :class="cardCur==index?'cur':''">
						<view class="swiper-item">
							<image :src="item.url" mode="aspectFill" v-if="item.type=='image'"></image>
							<video :src="item.url" autoplay loop muted :show-play-btn="false" :controls="false" objectFit="cover" v-if="item.type=='video'"></video>
						</view>
					</swiper-item>
				</swiper>
				<!--任务清单-->
				<view class="new1">
					<view class="img-title">
						<image class="center-img search-img" :src="searchurl"></image>
						<div id="title1">{{ msg1 }}</div>
					</view>
					<!--任务信息列表-->
					<view class="task-list">
						<view class="task-row1" v-for="(item1,key1) of list1" :key="key1">
							<!--
							<p id="task-info1">{{item1.info1}}</p>
							<button :disabled="flag" class="task-ac" @click="acceptTask(item1)">{{item1.accept_text}}</button>
							-->
							<view class="task-info1">
								<p id="appearance">外貌：{{item1.appearance}}</p>
								<p id="clothes">衣着：{{item1.clothes}}</p>
								<p id="loslocation">走失地点：{{item1.loslocation}}</p>
							</view>
							<button :disabled="flag" class="task-ac" @click="acceptTask(item1)">{{item1.accept_text}}</button>
						</view>
					</view>
				</view>
			</block>
			
			<block v-if="TabCur==1">
				<!--走失信息-->
				<view class="new2">
					<view class="img-title">
						<image class="center-img list-img" :src="listurl"></image>
						<div id="title2">{{ msg2 }}</div>
					</view>
					<view class="lost-info">
						<!--垂直导航-->
						<view class="VerticalBox">
							<scroll-view class="VerticalNav nav" scroll-y scroll-with-animation :scroll-top="verticalNavTop" style="height: 80vh">
								<view class="cu-item" :class="index==tabCur?'text-myblue cur':''" v-for="(item,index) in list" :key="index" @tap="TabSelect"
								 :data-id="index">
									{{item.name}}
								</view>
							</scroll-view>
							<scroll-view class="VerticalMain" scroll-y scroll-with-animation style="height: 80vh"
							 :scroll-into-view="'main-'+mainCur" @scroll="VerticalMain">
								<view class="padding-top padding-lr" v-for="(item,index) in list" :key="index" :id="'main-'+index">
									
									<view class="cu-list menu-avatar">
										<view class="cu-item">
											<view class="task-info2">
												<view class="info2-face">{{item.losface}}</view>
												<view class="info2-cloth">{{item.loscloth}}</view>
												<view class="info2-place">{{item.losplace}}</view>
											</view>
										</view>
									</view>
								</view>
							</scroll-view>
						</view>
						
					</view>
				</view>
			</block>
			
		</view>
</template>

<script>
	
	export default {
		
		data() {
			return {
				my_longitude:'',
				my_latitude:'',
				token:'',
				acc_task_id:'',
				//导航栏
				CustomBar: this.CustomBar,
				TabCur: 0,
				tabNav: ['紧急任务', '走失汇总'],
				//垂直导航
				list: [],
				tabCur: 0,
				mainCur: 0,
				verticalNavTop: 0,
				load: true,
				//幻灯片
				cardCur: 0,
				swiperList: [{
					id: 0,
					type: 'image',
					url: '../../static/view1.jpg'
				}, {
					id: 1,
					type: 'image',
					url: '../../static/view2.jpg',
				}, {
					id: 2,
					type: 'image',
					url: '../../static/view3.jpg'
				}, {
					id: 3,
					type: 'image',
					url: '../../static/view4.jpg'
				}, {
					id: 4,
					type: 'image',
					url: '../../static/view5.jpg'
				}, {
					id: 5,
					type: 'image',
					url: '../../static/view6.jpg'
				}],
				dotStyle: false,
				
				//刷新
				flag:false,
				
				//小图标
				searchurl: '../../static/search.png',
				listurl: '../../static/list.png',
				//任务信息
				msg1: '任务清单',
				msg2: '走失信息',
				list1: [
					{
						accept_text:"接受",
						
						appearance:'',
						clothes:'',
						loslocation:''
						},
					{
						accept_text:"接受",
						
						appearance:'',
						clothes:'',
						loslocation:''},
					{
						accept_text:"接受",
						
						appearance:'',
						clothes:'',
						loslocation:''},
					{
						accept_text:"接受",
						
						appearance:'',
						clothes:'',
						loslocation:''},
					{
						accept_text:"接受",
						
						info1:'这是第5条任务信息'},
					{
						accept_text:"接受",
						
						info1:'这是第6条任务信息'},
					{
						accept_text:"接受",
						
						info1:'这是第7条任务信息'},
					{
						accept_text:"接受",
						
						info1:'这是第8条任务信息'},
					{
						accept_text:"接受",
						
						info1:'这是第9条任务信息'},
					{
						accept_text:"接受",
						
						info1:'这是第10条任务信息'},
					{
						accept_text:"接受",
						
						info1:'这是第11条任务信息'},
					{
						accept_text:"接受",
						
						info1:'这是第12条任务信息'},
				]
			}
		},
		
		onLoad() {
			// let list = [{}];
			
			// this.list = list;
			// this.listCur = list[0];
		},
		onShow() {
		var that = this;
		uni.getStorage({
					    key: 'token',
					    success: function (res) {
					        that.token=String(res.data);
							console.log(that.token);
							uni.getLocation({
								type: 'wgs84',
								success: function(res) {
									
									that.my_longitude = res.longitude;
									that.my_latitude = res.latitude;
									
									
					uni.request({
					    url: 'http://139.224.208.92:8080/app/getJob', //仅为示例，并非真实接口地址。
						header:{
							'content-type':'application/x-www-form-urlencoded'
						},
						method:'POST',
						data: {
					        'token': String(that.token),
							'longitude':String(that.my_longitude),
							'latitude':String(that.my_latitude)
					    },
						
					    success: function (res) {							
					        //console.log(res.data);
							if(res.data.isok==true){
								
								console.log(res.data.data);
								that.list1=[];
								var n = 0;
								
								let list = [{}];
								
								that.list = list;
								that.listCur = list[0];
								for(var people in res.data.data){
									if(res.data.data[people].level==1){
										
										
										
										var jsonObj = {taskid:res.data.data[people].task_id,accept_text:"接受",clothes:res.data.data[people].target_cloth,appearance:res.data.data[people].target_appearance,loslocation:res.data.data[people].lost_position};
										that.list1.push(jsonObj);
										console.log(that.list1);
									}else{
										
										
										if(n==0){
											that.list[0]={
												name:res.data.data[people].target_name,
												losface:"外貌为"+res.data.data[people].target_appearance,
												loscloth:'衣着为'+res.data.data[people].target_cloth,
												losplace:'走失于'+res.data.data[people].lost_position+'附近'
											}
										}else{
											that.list.push( {
												name:res.data.data[people].target_name,
												losface:"外貌为"+res.data.data[people].target_appearance,
												loscloth:'衣着为'+res.data.data[people].target_cloth,
												losplace:'走失于'+res.data.data[people].lost_position+'附近'
											})
											
										}
										
										
										//console.log(that.list);
										n++;
									}
								}
								console.log(that.list)
								
									
							}
							else{
								that.list1=[];
								//that.list=[];
								uni.showToast({
									 title: '无任务信息',
									 duration: 2000
								})
							}
							},
							}
							)
							//后面的部分考虑到更改
							uni.request({
								url: 'http://139.224.208.92:8080/app/getAcTaskid', //仅为示例，并非真实接口地址。
								header:{
									'content-type':'application/x-www-form-urlencoded'
								},
								data: {
								    'token': String(that.token),
									
								},
								success: function (res) {
									//console.log(res.data);
									//这里是返回已经接受任务
									if(res.data.isok==true){
										that.acc_task_id = res.data.taskid;
										for(var people in that.list1){
											//console.log(people);
											if(that.list1[people].taskid==that.acc_task_id){
												that.list1[people].accept_text="已接受";
												that.flag = true;
											}
										}
										
										
									}
									//这里是未接受任务,未接受任务都不变
									
								}
							});
								
					    }
					});
					
					}
					});	
		},
		
		methods: {
			tabSelect(e) {//页面选择
				this.TabCur = e.currentTarget.dataset.id;
				this.scrollLeft = (e.currentTarget.dataset.id - 1) * 60
			},
			
			cardSwiper(e) {//幻灯片
			    this.cardCur = e.detail.current
			},
			
			acceptTask(e) {//接受任务
				if(this.flag==false){
					var that =  this;
					uni.showModal({
					    title: '提示',
					    content: '接受后无法取消',
					    success: function (res) {
					        if (res.confirm) {
					            
					            e.accept_text = "已接受";
					            console.log(e.taskid);
					            // uni.request({
								console.log(that.token);
					            uni.request({
					            	url: 'http://139.224.208.92:8080/app/getTask',
					            	header:{
					            		'content-type':'application/x-www-form-urlencoded'
					            	},
					            	method:'POST',
					            	data: {
					            	    'token': String(that.token),
					            		'task_id':String(e.taskid)
					            	},
					            	success: function (res) {
					            		console.log(res.data)
					            		
					            	}
					            })
					        } else if (res.cancel) {
					            
					        }
					    }
					});
						
					// });
				}
				else{
					uni.showToast({
						title:"您已接受任务"
					})
				}
				
				this.flag=true
			},
			
			TabSelect(e) {//垂直导航栏
				this.tabCur = e.currentTarget.dataset.id;
				this.mainCur = e.currentTarget.dataset.id;
				this.verticalNavTop = (e.currentTarget.dataset.id - 1) * 50
			},
			VerticalMain(e) {
				let that = this;
				let tabHeight = 0;
				if (this.load) {
					for (let i = 0; i < this.list.length; i++) {
						let view = uni.createSelectorQuery().select("#main-" + this.list[i].id);
						view.fields({
							size: true
						}, data => {
							this.list[i].top = tabHeight;
							tabHeight = tabHeight + data.height;
							this.list[i].bottom = tabHeight;
						}).exec();
					}
					this.load = false
				}
				let scrollTop = e.detail.scrollTop + 10;
				for (let i = 0; i < this.list.length; i++) {
					if (scrollTop > this.list[i].top && scrollTop < this.list[i].bottom) {
						this.verticalNavTop = (this.list[i].id - 1) * 50
						this.tabCur = this.list[i].id
						//console.log(scrollTop)
						return false
					}
				}
			}
		}
		
	}
	
</script>

<style>
	
</style>