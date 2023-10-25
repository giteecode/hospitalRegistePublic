<template>
	<view class="recordBox">
		<view class="recordItem" v-for="(item,index) in tableData">
			<view class="title">挂号记录</view>
			<view class="contentList">
				<view class="contentItem">
					<view class="label">订单号</view>
					<view class="value">{{item.orderNo}}</view>
				</view>
				<view class="contentItem">
					<view class="label">就诊人</view>
					<view class="value">{{item.sysUser.nickName}}</view>
				</view>
				<view class="contentItem">
					<view class="label">预约时间</view>
					<view class="value">{{item.registrationTime}}</view>
				</view>
				<view class="contentItem">
					<view class="label">科室</view>
					<view class="value">{{item.pSection.deptName}}</view>
				</view>
				<view class="contentItem">
					<view class="label">订单状态</view>
					<view class="value">{{item.status === '0' ? '待就诊' : item.status === '3' ? '已评价' : item.status === '1' ? '已取消' : '已完成'}}</view>
				</view>
			</view>
			<view class="showDetail" @click="registrationDetail(item)">查看详情</view>
		</view>
	</view>
</template>

<script>
	import request from '@/utils/request.js'
	export default {
		data() {
			return {
				searchParameters: {
					pageNum: 1,
					pageSize: 100,
					userId: uni.getStorageSync('userInfo').userId
				},
				tableData: []
				
			}
		},
		onShow() {
			this.tableData = []
			this.getList()
		},
		methods: {
			getList(){
				request.getRegistrationList(this.searchParameters).then(res => {
					if(res && res.code === 200){
						console.log('length===>',res.rows.length)
						this.tableData = res.rows
					}
					
				})
			},
			// 挂号记录详情
			registrationDetail(item){
				uni.navigateTo({
					url:'/pages/registrationDetail/registrationDetail?item=' + JSON.stringify(item)
				})
			},
		},
	}
</script>
<style>
	page{
		width: 100%;
		height: 100%;
		background: #F8F8F8;
	}
</style>
<style scoped lang="scss">
	.recordBox{
		height: 100%;
		padding: 0 40rpx;
		.recordItem{
			margin-top: 20rpx;
			background-color: #FFFFFF;
			.title{
				height: 66rpx;
				padding: 0 20rpx;
				line-height: 66rpx;
				background: #E1E1E1;
				font-size: 32rpx;
				color: #141414;
			}
			.contentList{
				display: flex;
				flex-direction: column;
				font-size: 28rpx;
				color: #6D6D6D;
				.contentItem{
					display: flex;
					align-items: center;
					justify-content: space-between;
					border-bottom: 1px dashed #C1C1C1;
					height: 66rpx;
					padding: 0 20rpx;
					line-height: 66rpx;
				}
			}
			.showDetail{
				color: #548AFB;
				padding: 20rpx 0;
				display: flex;
				align-items: center;
				justify-content: center;
			}
		}
	}
</style>
