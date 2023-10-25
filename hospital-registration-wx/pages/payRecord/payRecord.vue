<template>
	<view class="payRecordBox">
		<view class="payRecord" v-for="(item,index) in payRecordList" :key='index'>
			<view class="title">充值记录</view>
			<view class="contentList">
				<view class="contentItem">
					<view class="label">订单号</view>
					<view class="value">{{item.orderNo}}</view>
				</view>
				<view class="contentItem">
					<view class="label">充值人</view>
					<view class="value">{{item.sysUser.nickName}}</view>
				</view>
				<view class="contentItem">
					<view class="label">充值金额</view>
					<view class="value">¥{{item.price}}</view>
				</view>
				<view class="contentItem">
					<view class="label">充值时间</view>
					<view class="value">{{item.createTime}}</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import request from '@/utils/request.js'
	export default {
		data() {
			return {
				//充值记录列表
				payRecordList: [],
				searchParameters: {
					pageNum: 1,
					pageSize: 100,
					userId: uni.getStorageSync('userInfo').userId,
				},
			}
		},
		onLoad(options) {
			this.getPayList()
		},
		methods: {
			
			getPayList(){
				request.getPayList(this.searchParameters).then(res => {
					if(res && res.code === 200){
						console.log('length===>',res.rows.length)
						this.payRecordList = res.rows
					}
					
				})
			},

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
	.payRecordBox {
		height: 100%;
		padding: 0rpx 30rpx;

		.payRecord {
			margin-top: 20rpx;
			margin-bottom: 20rpx;
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

				.contentItem:last-child {
					border: 0;
				}
			}
		}
	}
</style>
