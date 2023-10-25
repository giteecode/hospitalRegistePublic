<template>
	<view class="visitorsList">
		<view class="visitorsItem" @click="addVisitors">
			<view class="top">
				<view class="name">{{userInfo.nickName}}</view>
				<view class="sex">{{userInfo.sex === '0' ? '男' : '女'}}</view>
			</view>
			<view class="bottom">
				<view class="left">
					<view class="title">卡号：</view>
					<view class="value">{{userInfo.remark === null ? '暂无' : userInfo.remark}}</view>
				</view>
				<view class="more">···</view>
			</view>
		</view>
<!-- 		<view class="addVisitors" @click="addVisitors">
			新增就诊人
		</view> -->
	</view>
</template>

<script>
	import request from '@/utils/request.js'
	export default {
		data() {
			return {
				userInfo: {}

			}
		},
		onShow() {
			this.userInfo = uni.getStorageSync('userInfo')
		},
		methods: {
			//就诊人详情
			visitorsDetail() {
				uni.navigateTo({
					url: '/pages/CRM_MedicalCard/CRM_MedicalCard'
				})
			},
			//新增就诊人
			addVisitors(){
				uni.navigateTo({
					url:'/pages/addVisitors/addVisitors'
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	.visitorsList {
		display: flex;
		align-items: center;
		justify-content: center;
		flex-direction: column;

		.visitorsItem {
			width: 606rpx;
			height: 98rpx;
			margin-top: 20rpx;
			background: rgba(255, 255, 255, 0.39);
			box-shadow: 0px 6rpx 12rpx rgba(0, 0, 0, 0.16);
			background-image: url('@/static/crad.png');
			background-size: 100% 100%;
			border-radius: 18rpx;
			padding: 40rpx;
			color: #FFFFFF;
			display: flex;
			flex-direction: column;
			justify-content: space-between;

			.top {
				font-size: 40rpx;
				display: flex;

				.sex {
					margin-left: 40rpx;
					align-self: center;
					font-size: 32rpx;
				}
			}

			.bottom {
				margin-top: 10rpx;
				font-weight: 400;
				display: flex;
				justify-content: space-between;

				.left {
					display: flex;
				}

				.more {
					letter-spacing: 4rpx;
					font-size: 32rpx;
				}
			}
		}

		.addVisitors {
			width: 490rpx;
			height: 84rpx;
			margin: 100rpx auto 0;
			background: linear-gradient(90deg, #5088FD 0%, #62B9F9 100%);
			border-radius: 12rpx;
			display: flex;
			align-items: center;
			justify-content: center;
			color: #fff;
			font-size: 40rpx;
			letter-spacing: 4rpx;
		}
	}
</style>
