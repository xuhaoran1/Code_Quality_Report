<template>
	<view class="help">
		
		<view class="steps">
			<text class="help-titles centext">1.个人页面介绍</text>
			<image class="sphoto cenphoto" :src="cenurl"></image>
		</view>
		
		<view class="steps">
			<text class="help-titles">2.领取任务流程</text>
			<text class="texts stepstext1">
				当任务位置处于你10公里内时，你将从首页的任务清单看到该任务。
				点击领取任务，任务状态信息变为已接受。
			</text>
			<image class="sphoto step1" :src="step1url"></image>
		</view>
		
		<view class="steps">
			<text class="texts stepstext2">
				领取任务后，你可以从任务栏中查看你领取任务的具体信息。
			</text>
			<image class="sphoto step2" :src="step2url"></image>
		</view>
		
		<view class="steps">
			<text class="texts stepstext3">
				若要查看或上传当前任务的最新信息，可点击最新信息栏切换到最新信息页面。
			</text>
			<image class="sphoto step3" :src="step3url"></image>
		</view>
		
		<view class="steps">
			<text class="texts stepstext4">
				若发现走失老人，可点击人脸识别栏，将疑似老人和目标老人进行人脸识别。
			</text>
			<image class="sphoto step4" :src="step4url"></image>
		</view>
		
		<view class="steps">
			<text class="texts stepstext5">
				搜索过程中，可点击任务地图栏目规划线路。最后，祝愿敬爱的你们能够找到牵挂的老人。
			</text>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				cenurl: '../../static/cenphoto.png',
				step1url: '../../static/step1.png',
				step2url: '../../static/step2.png',
				step3url: '../../static/step3.png',
				step4url: '../../static/step4.png',
				}
			},
		
		onLoad() {
			
		},
		onShow() {
			
		},
		
		methods: {
			
		}
	}
</script>

<style>
	
</style>
