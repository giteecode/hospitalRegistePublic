<template>
  <view>
	  <view v-if="hasLogin">
		  <button class="btn-logout" @tap="wxLogin()">立即登录</button>
	  </view>
	  <view v-else>
		 <view class="person-head">
		   <cmd-avatar :src="userInfo.avatar" size="lg" :make="{'background-color': '#fff'}"></cmd-avatar>
		   <view class="person-head-box">
		     <view class="person-head-nickname">{{userInfo.nickName}}</view>
		     <!-- <view class="person-head-username">ID：slimmer9501</view> -->
		   </view>
		 </view>
		 <view class="person-list">
		   <cmd-cell-item title="我的收藏" slot-left arrow @click="collect()">
		     <cmd-icon type="bullet-list" size="24" color="#368dff"></cmd-icon>
		   </cmd-cell-item>
		   <cmd-cell-item title="我的评价" slot-left arrow @click="evaluate()">
		     <cmd-icon type="bullet-list" size="24" color="#368dff"></cmd-icon>
		   </cmd-cell-item>
		   <!-- <cmd-cell-item title="个人信息" slot-left arrow>
		     <cmd-icon type="message" size="24" color="#368dff"></cmd-icon>
		   </cmd-cell-item> -->
		   <!-- <cmd-cell-item title="检查版本" addon="v1.0" slot-left arrow>
		     <cmd-icon type="alert-circle" size="24" color="#368dff"></cmd-icon>
		   </cmd-cell-item> -->
		 </view>
		 <button class="btn-logout" @tap="logout()">退出登录</button> 
	  </view>

  </view>
</template>

<script>
  import cmdAvatar from "@/components/cmd-avatar/cmd-avatar.vue"
  import cmdIcon from "@/components/cmd-icon/cmd-icon.vue"
  import cmdCellItem from "@/components/cmd-cell-item/cmd-cell-item.vue"
  import request from '@/utils/request.js'
  export default {
    components: {
      cmdAvatar,
      cmdCellItem,
      cmdIcon
    },
    data() {
      return {
		  hasLogin: false,
		  loginParams: {
			code: '',
			avatarUrl: '',
			sex: '',
		  	username: '',
		  	loginType: 'MA1',
		  },
		  userInfo:{}
	  };
    },
	onShow() {
		if(uni.getStorageSync('userInfo')){
			this.hasLogin = false
			this.userInfo = uni.getStorageSync('userInfo')
		}else{
			this.hasLogin = true
		}
	},
    methods: {
		collect(){
			uni.navigateTo({
				url: '/pages/collectRecord/collectRecord'
			})
		},
		evaluate(){
			uni.navigateTo({
				url: '/pages/evaluateRecord/evaluateRecord'
			})
		},
		logout(){
			uni.showModal({
				content: '确定要退出登录么',
				success: e => {
					if (e.confirm) {
						request.logout().then(res => {
							if(res && res.code === 200){
								uni.reLaunch({
									url: '/pages/index/index'
								})
								uni.removeStorageSync('token')
								uni.removeStorageSync('userInfo')
							}
						})
					}
				}
			});
		},
		  wxLogin() {
		    uni.getUserProfile({
		      desc: "登录",
		      success: (res) => {
		        console.log(res);
		        //res.userInfo
				this.loginParams.username = res.userInfo.nickName
				this.loginParams.sex = res.userInfo.gender
				this.loginParams.avatarUrl = res.userInfo.avatarUrl
		        uni.login({
		          success: (row) => {
		            console.log(row);
					this.loginParams.code = row.code
		            request.login(this.loginParams).then(res => {
		            	if(res && res.code === 200){
		            		uni.switchTab({
		            			url: '../index/index',
		            		})
		            	}
		            })
		          },
		        });
		      },
		    });
		  },
      /**
       * 打开用户信息页
       */
      fnInfoWin() {
        uni.navigateTo({
          url: '/pages/user/info/info'
        })
      }
    }
  }
</script>

<style>
	.btn-logout {
	  margin-top: 100upx;
	  width: 80%;
	  border-radius: 50upx;
	  font-size: 16px;
	  color: #fff;
	  background: linear-gradient(to right, #365fff, #36bbff);
	}
  .person-head {
    display: flex;
    flex-direction: row;
    align-items: center;
    height: 150px;
    padding-left: 20px;
    background: linear-gradient(to right, #365fff, #36bbff);
  }

  .person-head-box {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    margin-left: 10px;
  }

  .person-head-nickname {
    font-size: 18px;
    font-weight: 500;
    color: #fff;
  }

  .person-head-username {
    font-size: 14px;
    font-weight: 500;
    color: #fff;
  }

  .person-list {
    line-height: 0;
  }
</style>
