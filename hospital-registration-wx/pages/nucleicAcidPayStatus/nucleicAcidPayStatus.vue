<template>
	<view class="payOrderTypeBox">
		<view class="iconbox">
			<img class='img' src="../../static/paySuccess.png">
			<view class="iconTitle">操作成功</view>
		</view>
		<view class="returnBox">
			<view class="returnIndex" @click="returnIndex">返回首页</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//订单号
				orderId: '',
				//支付状态
				orderType: 'success'
			}
		},
		onLoad(options) {
			if (options) {
				this.orderId = options.orderId
				this.orderType = options.orderType
			}
		},
		methods: {
			//返回首页
			returnIndex() {
				uni.reLaunch({
					url: '/pages/index/index'
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	.payOrderTypeBox {
		padding: 0 22rpx;

		.iconbox {
			width: 100%;
			height: 100%;
			margin: 120rpx auto 0;
			text-align: center;

			.img {
				width: 142rpx;
				height: 142rpx;
			}

			.iconTitle {
				font-size: 36rpx;
				font-weight: bold;
				line-height: 50rpx;
				color: #528DFD;
				letter-spacing: 2rpx;
				margin-top: 20rpx;
			}
		}

		.contentBox {
			display: flex;
			width: 100%;
			flex-direction: column;
			margin-top: 30rpx;
			color: #5D5C5C;

			.contentItem {
				display: flex;
				height: 80rpx;
				align-items: center;
				border-bottom: 1px solid #F1F1F1;
				justify-content: space-between;
			}

			.contentItem:last-child {
				border: 0;
			}
		}

		.returnBox {
			margin-top: 200rpx;
			display: flex;
			justify-content: center;

			.returnIndex {
				width: 490rpx;
				height: 84rpx;
				color: #FFFFFF;
				line-height: 84rpx;
				font-size: 40rpx;
				text-align: center;
				background: linear-gradient(90deg, #5088FD 0%, #62B9F9 100%);
				border-radius: 12rpx;
			}

		}
	}
</style>
