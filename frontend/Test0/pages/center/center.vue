<template>
	<view class="center">
		<view class="logo" @click="goLogin" :hover-class="!login ? 'logo-hover' : ''">
			<image class="logo-img" :src="login ? uerInfo.avatarUrl :avatarUrl"></image>
			<view class="logo-title">
				<text v-model="data" class="uer-name">Hi，{{login ? data_name : '您未登录'}}</text>
				<text class="go-login navigat-arrow" v-if="!login">&#xe65e;</text>
			</view>
		</view>
		
		<view class="center-list">
			<view class="center-list-item border-bottom">
				<image class="center-img hour-img" :src="hoururl"></image>
				<text class="list-text">志愿时长</text>
				<text class="list-text">0h</text>
			
			</view>
			<view class="center-list-item">
				<image class="center-img times-img" :src="timesurl"></image>
				<text class="list-text">出队次数</text>
				<text class="list-text">{{team_num}}次</text>				
			</view>
		</view>
		<view class="center-list">
			<!--<view class="center-list-item border-bottom">
				<text class="list-icon">&#xe60b;</text>
				<text class="list-text">消息免打扰</text>
			
			</view>
			-->
			<view class="center-list-item">
				<image class="center-img status-img" :src="statusurl"></image>
				<text class="list-text">状态</text>
				<view>
					<radio-group>
				 <radio class="list-text myradio" value="r1" :checked='flagwork=="true"' disabled="true"/>工作</radio>
				 <radio class="list-text myradio" value="r2" :checked='flagnowork=="true"' disabled="true"/>休息</radio>
					</radio-group>
				</view>
			</view>
		</view>
		<view class="center-list">
			
			<view class="center-list-item border-bottom">
				<image class="center-img set-img" :src="seturl"></image>
				<text class="list-text">帐号管理</text>
			</view>
			<view class="center-list-item">
				<image class="center-img ques-img" :src="questionurl"></image>
				<view class="list-text" @click="goHelp">帮助</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				flagwork: 'true',
				flagnowork:'false',
				token:'',
				data_name:'小蓝',
				team_num:"5",
				time:"0",
				login:true,
				
				hoururl:'../../static/time.png',
				timesurl:'../../static/rank.png',
				statusurl:'../../static/attention.png',
				seturl:'../../static/settings.png',
				questionurl:'../../static/question.png',
				
				zavatarUrl:"../../static/logo.png",
				uerInfo:{
					name:"",
					avatarUrl:"../../static/logo.png"
				}
			}
		},
		onLoad() {
			
		},
		onShow() {
			var that = this;
			uni.getStorage({
			    key: 'token',
			    success: function (res) {
						
			        	that.token=String(res.data);
						
						//console.log(this.token)
						uni.request({
							url: 'http://139.224.208.92:8080/app/selectByToken',
							
							data:{
									token:that.token
							},
							success: (res) => {
							console.log(res.data);
							
							console.log(res.data.data.name);
							that.data_name=res.data.data.name;
							
							if(res.data.data.status>0){
							//工作
							
							that.flagwork='true';
							that.flagnowork='false';
							//console.log(111)
							}
							else{
								
							that.flagwork='false';
							that.flagnowork='true';	
							}
							}
							
						})
						uni.request({
									url: 'http://139.224.208.92:8080/app/getTimes',
													
									data:{
											token:that.token
										},
									success: (res) => {
									console.log(res.data);
											that.team_num = res.data.times;
									}
								})
						
			    }
			});
		},
		
		methods: {
			goLogin() {
				if(!this.login){
					uni.navigateTo({
						url:"../login/login"
					})
				}
			},
			goHelp(){
				console.log(11111);
				uni.navigateTo({
					url:"../help/help"
				})
			}
		}
	}
</script>

<style>
</style>
