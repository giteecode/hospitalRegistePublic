<template>
	<view class="registrationDetailBox">
		<view class="registrationDetail">
			<view class="title">挂号详情</view>
			<view class="contentList">
				<view class="contentItem">
					<view class="label">订单号</view>
					<view class="value">{{orderDetail.orderNo}}</view>
				</view>
				<view class="contentItem">
					<view class="label">就诊人</view>
					<view class="value">{{orderDetail.sysUser.nickName}}</view>
				</view>
				<view class="contentItem">
					<view class="label">预约时间</view>
					<view class="value">{{orderDetail.registrationTime}}</view>
				</view>
				<!-- <view class="contentItem">
					<view class="label"> 就诊时间 </view>
					<view class="value">2022-04-02 08:00</view>
				</view> -->
				<view class="contentItem">
					<view class="label">订单状态</view>
					<view class="value">{{orderDetail.status === '0' ? '待就诊': orderDetail.status === '3' ? '已评价' : orderDetail.status === '1' ? '已取消' : '已完成'}}</view>
				</view>
				<view class="contentItem">
					<view class="label">医生</view>
					<view class="value">{{orderDetail.doctorName}}</view>
				</view>
				<view class="contentItem">
					<view class="label">科室</view>
					<view class="value">{{orderDetail.pSection.deptName}}</view>
				</view>
				<view class="contentItem">
					<view class="label">诊室</view>
					<view class="value">{{orderDetail.pOutpatien.roomName}}</view>
				</view>
				<view class="contentItem">
					<view class="label">下单时间</view>
					<view class="value">{{orderDetail.createTime}}</view>
				</view>
			</view>
		</view>
		<view class="cancelReservation" v-if="orderDetail.status === '0'" @tap="cancel()">取消预约</view>
		<view class="cancelReservation" v-if="orderDetail.status === '2'" @tap="evaluate()">评价</view>
		
	</view>
</template>

<script>
	import request from '@/utils/request.js'
	export default {
		data() {
			return {
				orderDetail:{}

			}
		},
		onLoad(e) {
			console.log('e===>',e)
			this.orderDetail = JSON.parse(e.item)
			
		},
		methods: {
			evaluate(){
				console.log('orderDetail===>',this.orderDetail)
				uni.navigateTo({
					url: '/pages/evaluateAdd/evaluateAdd?doctorId=' + this.orderDetail.doctorId + '&id=' + this.orderDetail.id
				})
			},
			cancel(){
				uni.showModal({
					title: '提示',
					content: '确认取消吗？',
					cancelText: '取消',
					confirmText: '确认',
					success: res => {
						if (res.confirm) {
							let userInfo = uni.getStorageSync('userInfo')
							let obj = Object.assign({
								id: this.orderDetail.id,
								status: '1',
							})
							request.editRegistration(obj).then(res => {
								if(res && res.code === 200){
									request.getUserByUserName(userInfo.userName).then(res1 => {
										if (res1 && res1.code === 200) {
											let userInfo = res1.userInfo
											uni.setStorageSync('userInfo', userInfo)
											uni.navigateBack()
										}
									})
								}
							})
						}
					},
				})

			}

		}
	}
</script>
<style>
	page {
		width: 100%;
		height: 100%;
		background: #F8F8F8;
	}
</style>
<style scoped lang="scss">
	.registrationDetailBox {
		height: 100%;
		padding: 0 40rpx;

		.registrationDetail {
			margin-top: 20rpx;
			background-color: #FFFFFF;

			.title {
				height: 66rpx;
				padding: 0 20rpx;
				line-height: 66rpx;
				background: #E1E1E1;
				font-size: 32rpx;
				color: #141414;
			}

			.contentList {
				display: flex;
				flex-direction: column;
				font-size: 28rpx;
				color: #6D6D6D;

				.contentItem {
					display: flex;
					align-items: center;
					justify-content: space-between;
					border-bottom: 1px dashed #C1C1C1;
					height: 66rpx;
					padding: 0 20rpx;
					line-height: 66rpx;
				}
				.contentItem:last-child{
					border: 0;
				}
			}
		}
		.cancelReservation {
			width: 600rpx;
			height: 90rpx;
			background: linear-gradient(90deg, #4D80FD 0%, #68CAF6 100%);
			border-radius: 16rpx;
			color: #FFFFFF;
			display: flex;
			margin:72rpx auto 0;
			align-items: center;
			justify-content: center;
		}
	}
</style>
