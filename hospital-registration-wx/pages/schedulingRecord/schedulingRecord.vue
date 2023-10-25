<template>
	<view class="recordBox">
		<view class="recordItem" v-for="(item,index) in tableData">
			<view class="title">排班信息</view>
			<view class="contentList">
				<view class="contentItem">
					<view class="label">姓名</view>
					<view class="value">{{item.sysUser.nickName}}</view>
				</view>
				<view class="contentItem">
					<view class="label">电话</view>
					<view class="value">{{item.sysUser.phonenumber}}</view>
				</view>
				<view class="contentItem">
					<view class="label">开始时间</view>
					<view class="value">{{item.schedulingStartTime}}</view>
				</view>
				<view class="contentItem">
					<view class="label">结束时间</view>
					<view class="value">{{item.schedulingEndTime}}</view>
				</view>
				<view class="contentItem">
					<view class="label">科室</view>
					<view class="value">{{item.pSection.deptName}}</view>
				</view>
				<view class="contentItem">
					<view class="label">门诊</view>
					<view class="value">{{item.pOutpatien.roomName}}</view>
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
				searchParameters: {
					pageNum: 1,
					pageSize: 100,
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
				request.getSchedulingList(this.searchParameters).then(res => {
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
