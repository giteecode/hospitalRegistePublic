/**
*@des 封装的异步请求处理函数
*@author pipilin pipilin@qq.com
*@date 2022/05/26
*/
let baseUrl = 'http://localhost:9314';
async function request(mehtod, params, type, showLoading) {
	//创建一个名为request请求的方法函数
	if (!type) {
		type = 'GET';
	}
	let url = baseUrl + mehtod
	return new Promise((resolve, reject) => {
		if (showLoading) {
			uni.showLoading({
				title: '加载中'
			});
		}

		uni.request({
			"method": type,
			"url": url,
			"header": {
				'Authorization': 'Bearer ' + uni.getStorageSync('token'),
				'client-type': 'MA', //客户端类型小程序
			},
			"data": params,
			success(res) {
				if (res.statusCode == 200) {
					if (res.data.code === 401) {
						uni.hideTabBarRedDot({
							index: 1
						})
						let pages = getCurrentPages(); // 获取页面栈
						var currentPage = pages[pages.length - 1].route; // 当前页面
						console.log('currentPage===>',currentPage)
						uni.setStorageSync('currentPage',currentPage)
						uni.showModal({
							title: '提示',
							content: '登录状态已过期',
							cancelText: '取消',
							confirmText: '重新登录',
							success: res => {
								if (res.confirm) {
									uni.reLaunch({
										url: '/pages/my/my'
									})
								}else{
									uni.reLaunch({
										url: '/pages/my/my'
									})
								}
								try {
									uni.removeStorageSync('token')
									uni.removeStorageSync('userInfo')
								} catch (e) {
									// error
									console.log(e)
								}
							},
						})
						reject("登录状态已过期，请您重新登录!");
					} else if (res.data.code === 200) {
						let data = res.data
						if (data.hasOwnProperty("token")) {
							let token = data.token
							uni.setStorageSync("token", data.token)
							uni.setStorageSync("userInfo", data.userInfo)
						}
						resolve(res.data);
					} else if (res.statusCode == 404) {
						uni.showModal({
							title: '提示',
							content: '接口请求出错，请检查手机网络',
							success(res) {}
						});
						reject();
					}else {
						console.log("request",res);
						uni.showModal({
							title: '提示',
							content: '错误：' + res.data.msg,
							success(res) {}
						});
						reject();
					}
				}
			},
			fail(error) {
				console.log(error);
				uni.showModal({
					title: '提示',
					content: '接口请求出错：' + error.errMsg,
					success(res) {}
				});
				reject(error);
			},
			complete(res) {
				uni.hideLoading();
			}
		});
	}).catch(e => {});
}
export default {
	request,
	login: data => {
		// 登录
		return request('/login', data, 'Post', true);
	},
	register: data => {
		// 注册
		return request('/register', data, 'Post', true);
	},
	
	logout: data => {
		// 退出登录
		return request('/logout', data, 'Post', true);
	},
	resetPwdMini: data => {
		// 修改密码
		return request('/system/user/resetPwdMini', data, 'Put', true);
	},
	editUserInfo: data => {
		// 修改用户
		return request('/mini/user/edit', data, 'Put', true);
	},
	getUserByUserName: userName => {
		// 通过用户名查询用户
		return request('/mini/user/getUserByUserName/' + userName, 'Get', true);
	},
	getOutpatienList: data => {
		// 查询门诊信息列表
		return request('/mini/outpatien/list', data, 'GET', true);
	},
	getSectionList: data => {
		// 查询科室信息列表
		return request('/mini/section/list', data, 'GET', true);
	},
	getUserList: data => {
		// 获取用户列表
		return request('/mini/user/list', data, 'GET', true);
	},
	getPostDetail: id => {
		// 获取帖子信息详细信息
		return request('/post/mini/'+ id, 'GET', true);
	},
	getRegistrationList: data => {
		// 查询预约挂号记录列表
		return request('/system/registration/mini/list', data, 'GET', true);
	},
	addRegistration: data => {
		// 新增预约挂号记录
		return request('/system/registration/mini', data, 'POST', true);
	},
	editRegistration: data => {
		// 修改预约挂号记录
		return request('/system/registration/mini', data, 'PUT', true);
	},
	getSchedulingList: data => {
		// 查询医生排班信息列表
		return request('/system/scheduling/mini/list', data, 'GET', true);
	},
	addCollect: data => {
		// 新增/取消收藏信息
		return request('/system/collect/mini', data, 'POST', true);
	},
	getCollect: data => {
		// 查询收藏信息列表
		return request('/system/collect/mini/list', data, 'GET', true);
	},
	deleteCollect: id => {
		// 删除收藏信息
		return request('/system/collect/mini/remove/'+ id, 'GET', true);
	},
	addPay: data => {
		// 新增充值记录信息
		return request('/system/pay/mini', data, 'POST', true);
	},
	getPayList: data => {
		// 查询充值记录信息
		return request('/system/pay/mini/list', data, 'GET', true);
	},
	addEvaluate: data => {
		// 新增评价信息
		return request('/system/evaluate/mini', data, 'POST', true);
	},
	getEvaluateList: data => {
		// 查询充值记录信息
		return request('/system/evaluate/mini/list', data, 'GET', true);
	},
};
