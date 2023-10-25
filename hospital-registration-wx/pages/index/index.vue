<template>
	<view class="content">
		<u-swiper class="swiper" :height='300' :list="swiperList" keyName="image" :autoplay="true" circular :radius='0'>
		</u-swiper>
		<view class="attendanceUserInfo" v-if="!hasLogin">
			<view class="top" v-if="!showPatient">
				<view class="left">
					<view class="name">
						<view class="label">
							{{userInfo.nickName}}
						</view>
					</view>
					<view class="cardNumber">卡号：{{userInfo.remark}}</view>
					<view class="balance">
						<view class="label"> 余额：</view>
						<view class="money">¥{{userInfo.balance}}</view>
					</view>
				</view>
			</view>
			<view class="addVisitorsBox" v-else>
				<view class="addVisitors" @click="addVisitors">添加就诊人</view>
			</view>
			<view class="bottom">
				<view class="bottomItem" @click="registrationRecord">
					<img class='image' src="../../static/registrationRecord.png">
					<view>挂号记录</view>
				</view>
				<view class="bottomItem" @click="myVisitors">
					<img class='image' src="../../static/addAttendanceUser.png">
					<view>就诊人信息</view>
				</view>
			</view>
		</view>
		<view class="outpatientService">
			<view class="label">门诊服务</view>
			<view class="itemList">
				<u-grid :border="false" col="4">
					<u-grid-item v-for="(baseListItem,baseListIndex) in outpatientServiceList" :key="baseListIndex"
						@click="navigatorPages(baseListItem)">
						<img class="iconImage" :src="baseListItem.url"
							style="	width: 100rpx;height: 100rpx;display: flex;justify-content: center;align-items: center;">
						<text class="grid-text">{{baseListItem.title}}</text>
					</u-grid-item>
				</u-grid>
			</view>
		</view>
		<u-picker :show="showPatientList" :columns="patientList" keyName="name" @confirm='confirmPatient'></u-picker>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				hasLogin: false,
				//当前就诊人信息
				nowPatient: {},
				//是否展示就诊人
				showPatient: false,
				//是否展示就诊人列表
				showPatientList: false,
				//首页宣传图轮播
				swiperList: [{
					image: 'https://img1.baidu.com/it/u=2476236744,1928561942&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=333',
				}, {
					image: 'https://img2.baidu.com/it/u=3297421174,4214062928&fm=253&fmt=auto&app=138&f=JPEG?w=543&h=352',
				}, {
					image: 'https://img0.baidu.com/it/u=3087330938,1064568462&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500',
				}],
				//就诊人信息列表
				patientList: [],
				///门诊服务菜单
				outpatientServiceList: [{
					url: '../../static/appointmentRegister.png',
					title: '预约挂号'
				}, {
					url: '../../static/outpatientPay.png',
					title: '就诊充值'
				}, {
					url: '../../static/rechargeRecord.png',
					title: '充值记录'
				},
				{
					url: '../../static/prescription.png',
					title: '医生排班'
				},
				],
				userInfo: {}
			}
		},
		onShow() {
			if(uni.getStorageSync('userInfo')){
				this.hasLogin = false
				this.userInfo = uni.getStorageSync('userInfo')
			}else{
				this.hasLogin = true
			}
		},
		onLoad() {

		},
		methods: {
			// 确认当前就诊人
			confirmPatient(e) {
				this.nowPatient = e.value[0]
				this.showPatientList = false
			},
			//更新当前就诊人
			changePatient() {
				if (this.patientList.length == 0) {
					return uni.showToast({
						title: '没有更多的就诊人'
					})
				} else {
					this.showPatientList = true
				}
			},
			//我的就诊人
			myVisitors() {
				uni.navigateTo({
					url: '/pages/myVisitors/myVisitors'
				})
			},
			//添加就诊人
			addVisitors() {
				uni.navigateTo({
					url: '/pages/addVisitors/addVisitors'
				})
			},
			//挂号记录
			registrationRecord() {
				uni.navigateTo({
					url: '/pages/registrationRecord/registrationRecord'
				})
			},
			//预约挂号
			navigatorPages(item) {
				if(uni.getStorageSync('userInfo')){
					if (item.title == '预约挂号') {
						uni.navigateTo({
							url: '/pages/chooseClinic/chooseClinic'
						})
					} else if (item.title == '就诊充值') {
						// uni.showToast({
						// 	title: '功能开发中!',
						// 	icon: 'none'
						// })
						// return
						uni.navigateTo({
							url: '/pages/cardPay/cardPay'
						})
					} else if (item.title == '充值记录') {
						// uni.showToast({
						// 	title: '功能开发中!',
						// 	icon: 'none'
						// })
						// return
						uni.navigateTo({
							url: '/pages/payRecord/payRecord'
						})
					}else if(item.title == '医生排班'){
						uni.navigateTo({
							url: '/pages/schedulingRecord/schedulingRecord'
						})
					}
				}else{
					uni.showToast({
						title: '请登录!',
						icon: 'none'
					})
				}
				
			}
		}
	}
</script>
<style>
	page {
		width: 100%;
		height: 100%;
		background-color: #F7F7F7;
	}
</style>
<style scoped lang="scss">
	.content {
		padding: 20rpx;
		background-color: #F7F7F7;

		.attendanceUserInfo {
			margin-top: 20rpx;
			background-color: #FFFFFF;
			border-bottom: 10rpx solid #78C0EB;

			.guide {
				height: 35rpx;
				display: flex;
				align-items: center;
				justify-content: flex-end;
				font-size: 24rpx;
				color: #FFFFFF;
				padding: 0 20rpx;
				background: linear-gradient(74deg, #78C0EB, #79C0EA);
			}

			.addVisitorsBox {
				height: 140rpx;
				display: flex;
				align-items: center;
				justify-content: center;

				.addVisitors {
					height: 70rpx;
					width: 350rpx;
					font-size: 24rpx;
					display: flex;
					align-items: center;
					justify-content: center;
					color: #FFFFFF;
					letter-spacing: 4rpx;
					background: linear-gradient(90deg, #5088FD 0%, #62B9F9 100%);
					border-radius: 6rpx;
				}
			}

			.top {
				display: flex;
				justify-content: space-between;
				padding: 10rpx 60rpx;
				border-bottom: 1px dashed #D3D3D3;

				.left {
					display: flex;
					flex-direction: column;
					justify-content: space-between;
					color: #706F6F;

					.name {
						display: flex;
						font-size: 40rpx;
						letter-spacing: 4rpx;
						font-weight: bold;

						.label {
							color: #454545;
							margin-right: 10rpx;
						}
					}

					.balance {
						display: flex;
						font-size: 33rpx;

						.money {
							color: #4095D2;
							letter-spacing: 4rpx;
						}
					}
				}

				.right {
					display: flex;
					flex-direction: column;
					align-items: center;
					justify-content: center;
					font-size: 20rpx;
					letter-spacing: 2rpx;

					.label {
						margin-top: 10rpx;
					}

					.QrCodeImg {
						width: 84rpx;
						height: 84rpx;
					}
				}
			}

			.bottom {
				display: flex;
				justify-content: space-between;
				padding: 20rpx 0;

				.bottomItem {
					display: flex;
					flex: 1;
					justify-content: center;
					align-items: center;
					font-size: 25rpx;
					color: #656565;

					.image {
						width: 35rpx;
						height: 35rpx;
						margin-right: 30rpx;
					}
				}

				.bottomItem:first-child {
					border-right: 1px solid #78C0EB;
				}
			}
		}

		.outpatientService,
		.patientService,
		.reportSearch {
			margin-top: 20rpx;

			.itemList {
				margin-top: 20rpx;
				background-color: #FFFFFF;
				padding: 0rpx 20rpx 30rpx;

				/deep/.u-grid-item {
					margin-top: 30rpx;
				}

				.grid-text {
					font-size: 28rpx;
					color: #909399;

				}
			}
		}
	}
</style>
