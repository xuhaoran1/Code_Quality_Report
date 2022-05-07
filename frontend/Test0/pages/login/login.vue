<template>
	<form class='loginView' @submit="login">
		<view class="input-view">
			<view class="label-view">
				<text class="label">账号 </text>
			</view>
			<input class="input" type="text" placeholder="请输入用户名" name="nameValue" />
		</view>
		<view class="input-view">
			<view class="label-view">
				<text class="label">密码</text>
			</view>
			<input class="input" type="password" placeholder="请输入密码" name="passwordValue" />
		</view>
		<view class="button-view">
			<button type="default" class="login" hover-class="hover" formType="submit">登录</button>
			<!--
			<button type="default" class="register" hover-class="hover" @click="register">免费注册</button>
			-->
		</view>
	</form>
</template>
<script>
	export default {
		data() {
			return {};
		},
		methods: {
			login(e) {
				uni.request({
				    url: 'http://139.224.208.92:8080/app/login', //仅为示例，并非真实接口地址。
					header:{
						'content-type':'application/x-www-form-urlencoded'
					},
					method:'POST',
					data: {
				        'userName': String(e.detail.value.nameValue),
						'pwd':String(e.detail.value.passwordValue)
				    },
					
				    success: (res) => {
						
				        console.log(res.data);
						if(res.data.isok==true){
							console.log(res.data.token)
							uni.setStorage({
								key:'token',
								data:res.data.token
							});
							uni.switchTab({
							  url: '../center/center'  
							});		
						}
						else{
							uni.showToast({
								 title: '账号或密码错误',
								 duration: 2000
							})
						}
						
						
						
				    }
				});	
			},
			register() {
				console.log("前往注册页面")
			}
		}
	}
</script>

<style>
	
</style>
