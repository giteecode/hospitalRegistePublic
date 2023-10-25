<template>
	<view class="contentBox">
		<view class="fill"></view>
		<view class="doctorInfoBox">
			<view class="doctorImg">
				<u-avatar :size='142' :src="doctorInfo.sysUser.avatar"></u-avatar>
			</view>
			<view class="doctorInfo">
				<view class="top">
					<view class="doctorName">{{doctorInfo.sysUser.nickName}}</view>
					<view class="doctorPost">{{doctorInfo.pSection.deptName}} | {{doctorInfo.sysUser.sex === '0' ? '男' : '女'}} | {{doctorInfo.sysUser.age}} | {{doctorInfo.sysUser.rank1}}</view>
				</view>
				<view class="center">医生简介:{{doctorInfo.sysUser.remark}}</view>
			</view>
		</view>
		<view class="subscribeInfo">
			<view class="infoItem">
				<view class="label">就诊科室</view>
				<view class="value">{{doctorInfo.pOutpatien.roomName}}
					<view class="icon"></view>
				</view>
			</view>
			<view class="infoItem">
				<view class="label">预约时间</view>
				<!-- <view class="value">
					<view v-if="showDateValue"> {{showDateValue}}</view>
					<view v-else> 请选择</view>
					<u-icon name="arrow-right" :size='40'></u-icon>
				</view> -->
				<view class="value">
					<xp-picker mode="ymdhi" :action-position="actionPosition" :history="history" :year-range="[2020,2030]" @confirm="confirm">
						<view v-if="showDateValue"> {{showDateValue}}</view>
						<view v-else>请选择</view>
					</xp-picker>
					<u-icon name="arrow-right" :size='40'></u-icon>
				</view>
				
			</view>
			<view class="infoItem">
				<view class="label">就诊患者</view>
				<view class="value">{{nickName}}
					<view class="icon"></view>
				</view>
			</view>
			<view class="infoItem">
				<view class="label">挂号费用</view>
				<view class="value">¥{{doctorInfo.sysUser.balance}}
					<view class="icon"></view>
				</view>
			</view>
			<view class="infoItem">
				<view class="label">预约总数量</view>
				<view class="value">{{doctorInfo.sysUser.totalNumber}}
					<view class="icon"></view>
				</view>
			</view>
			<view class="infoItem">
				<view class="label">剩余数量</view>
				<view class="value">{{doctorInfo.sysUser.residualNumber}}
					<view class="icon"></view>
				</view>
			</view>
		</view>
		<view class="warmPrompt">
			<view class="title">温馨提示</view>
			<view class="value">1.预约成功后可在挂号记录查看预约详情</view>
			<!-- <view class="value">2.预约成功后不能随意取消预约订单，否则将会纳入黑名单</view> -->
		</view>
		<view class="payOrder">
			<view class="totalMoney">总金额:¥{{doctorInfo.sysUser.balance}}元</view>
			<view class="payNow" @click="payNowOrder">立即支付</view>
			<view class="payNow" @click="toEvaluateRecord">查看评价</view>
		</view>
		<u-datetime-picker :show="showDate" v-model="dateValue" mode="date" closeOnClickOverlay @confirm="confirmDate"
			@cancel="cancelDate" @change="changeDate" @close="closeDate"></u-datetime-picker>
		<u-datetime-picker :show="showTime" v-model="timeValue" mode="time" closeOnClickOverlay @confirm="confirmTime"
			@cancel="cancelTime" @change="changeTime" @close="closeTime"></u-datetime-picker>
	</view>
</template>

<script>
	import request from '@/utils/request.js'
	export default {
		data() {
			return {
				actionPosition: "bottom",
				history: true,
				//是否展示日期选择框
				showDate: false,
				//日期选择框默认日期
				dateValue: Number(new Date()),
				//预约日期
				showDateValue: '',
				//是否展示时间选择框
				showTime: false,
				//时间选择框默认时间
				timeValue: '8:00',
				//预约时间
				showTimeValue: '',
				//医生预约Id
				doctorId: null,
				//预约医生信息
				doctorInfo: {},
				nickName: ''
			}
		},
		onLoad(options) {
			console.log(options)
			this.nickName = uni.getStorageSync('userInfo').nickName
			if(options.type === '1'){
				if (options.item) {
					let str = decodeURIComponent(options.item)
					console.log(str)
					let obj = JSON.parse(str)
					console.log('obj===>',obj)
					this.doctorId = obj.userId
					this.doctorInfo = obj
				}
			}else{
				if (options.item) {
					let str = decodeURIComponent(options.item)
					console.log(str)
					let obj = JSON.parse(str)
					console.log('obj===>',obj)
					this.doctorInfo.sysUser = obj.sysUser
					this.doctorInfo.pSection = obj.pSection
					this.doctorInfo.pOutpatien = obj.pOutpatien
					this.doctorId = obj.doctorId
					this.doctorInfo.userId = obj.doctorId
				}
			}
			
		},
		methods: {
			confirm(e) {
				this.showDateValue = e.value
			},
			toEvaluateRecord(){
				uni.navigateTo({
					url: '/pages/doctorEvaluateRecord/doctorEvaluateRecord?doctorId=' + this.doctorInfo.userId
				})
			},
			//订单支付
			payNowOrder() {
				if(this.showDateValue === ''){
					uni.showToast({
						title: '请选择预约时间',
						icon:'none'
					})
					return
				}
				let userInfo = uni.getStorageSync('userInfo')
				let obj = Object.assign({
					userId: userInfo.userId,
					registrationTime: this.showDateValue,
					doctorId: this.doctorInfo.sysUser.userId,
					sectionId: this.doctorInfo.pSection.deptId,
					outpatien: this.doctorInfo.pOutpatien.roomId,
					registrationPrice: this.doctorInfo.sysUser.balance,
				})
				console.log('obj==>',obj)
				request.addRegistration(obj).then(res => {
					if(res && res.code === 200){
						request.getUserByUserName(userInfo.userName).then(res1 => {
							if (res1 && res1.code === 200) {
								let userInfo = res1.userInfo
								uni.setStorageSync('userInfo', userInfo)
								uni.navigateTo({
									url:'/pages/orderPayStatus/orderPayStatus?type=success&orderId=00001'
								})
							}
						})
					}
				})
				
			},
			// 关闭日期选择
			closeDate() {
				this.showDate = false
			},
			// 取消日期选择
			cancelDate() {
				this.showDate = false
				this.showDateValue = ''
			},
			// 提交日期选择
			confirmDate(e) {
				this.showDate = false
				this.showDateValue = uni.$u.timeFormat(e.value, 'yyyy-mm-dd')
			},
			// 改变日期选择
			changeDate(e) {
				this.showDateValue = uni.$u.timeFormat(e.value, 'yyyy-mm-dd')
			},
			// 关闭时间选择
			closeTime() {
				this.showTime = false
			},
			// 取消时间选择
			cancelTime() {
				this.showTime = false
				this.showTimeValue = ''
			},
			// 提交时间选择
			confirmTime(e) {
				this.showTimeValue = e.value
				this.showTime = false
			},
			// 改变时间选择
			changeTime(e) {
				this.showTimeValue = e.value
			},
		}
	}
</script>
<style>
	page {
		width: 100%;
		height: 100%;
		background-color: #F8F8F8;
	}
</style>
<style scoped lang="scss">
	.contentBox {
		position: relative;
		width: 100%;
		height: 100%;

		.fill {
			width: 100%;
			height: 30rpx;
		}

		.doctorInfoBox {
			height: 160rpx;
			background: #FFFFFF;
			display: flex;
			align-items: center;
			padding: 0rpx 30rpx;

			.doctorImg {
				width: 142rpx;
				height: 142rpx;
				border: 2px solid #FFFFFF;
				border-radius: 50%;
				margin-right: 30rpx;
			}

			.doctorInfo {
				display: flex;
				flex-direction: column;
				justify-content: space-around;

				.top {
					display: flex;

					.doctorName {
						font-size: 36rpx;
						color: #5D5C5C;
					}

					.doctorPost {
						margin-left: 20rpx;
						margin-top: 15rpx;
						font-size: 24rpx;
						color: #5D5C5C;
					}
				}

				.center {
					color: #979797;
					font-size: 24rpx;
					line-height: 30rpx;
					padding: 15rpx 0;
				}
			}
		}

		.subscribeInfo {
			margin-top: 20rpx;

			.infoItem {
				height: 76rpx;
				display: flex;
				color: #5D5C5C;
				border-bottom: 1px solid #F1F1F1;
				justify-content: space-between;
				align-items: center;
				background: #FFFFFF;
				padding: 0 30rpx;

				.label {
					font-size: 34rpx;
				}

				.value {
					font-size: 32rpx;
					display: flex;
					margin-right: 10rpx;

					.icon {
						width: 40rpx;
					}
				}
			}

			.infoItem:last-child {
				border-bottom: 0;
			}
		}

		.warmPrompt {
			width: 550rpx;
			font-size: 24rpx;
			color: #CFCCCC;
			margin: 42rpx auto 0;

			.value {
				margin-top: 5rpx;
				line-height: 30rpx;
				padding: 0 40rpx 0 16rpx;
			}
		}

		.payOrder {
			position: absolute;
			bottom: 0;
			width: 100%;
			height: 98rpx;
			display: flex;
			align-items: center;
			background-color: #FFFFFF;
			justify-content: space-between;

			.totalMoney {
				color: #5D5C5C;
				font-size: 36rpx;
				margin-left: 32rpx;
				letter-spacing: 2rpx;
			}

			.payNow {
				width: 212rpx;
				height: 98rpx;
				color: #FFFFFF;
				font-size: 36rpx;
				display: flex;
				letter-spacing: 2rpx;
				align-items: center;
				justify-content: center;
				background: linear-gradient(90deg, #518CFD 0%, #62B8F9 100%);
			}
		}
	}
</style>
