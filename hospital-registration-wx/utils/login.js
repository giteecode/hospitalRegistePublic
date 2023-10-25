import store from "../store";
import Cache from '@/utils/cache';
import {
	Debounce
} from '@/utils/validate.js'
import {
	UID,
	USER_INFO,
	LOGIN_STATUS,
	EXPIRES_TIME
} from '../config/cache';
export const toLogin = Debounce(_toLogin, 800)
export function _toLogin(push, pathLogin) {
	uni.navigateTo({
		url: '/pages/login/login'
	})
	store.commit("LOGOUT");
}
//校验登陆状态
export function checkLogin() {
	let token = Cache.get(LOGIN_STATUS);
	let expiresTime = Cache.get(EXPIRES_TIME);
	let newTime = Math.round(new Date() / 1000);
	if (expiresTime < newTime || !token) {
		Cache.clear(LOGIN_STATUS);
		Cache.clear(EXPIRES_TIME);
		Cache.clear(USER_INFO);
		return false;
	} else {
		store.commit('UPDATE_LOGIN', token);
		let userInfo = Cache.get(USER_INFO, true);
		if (userInfo) {
			store.commit('UPDATE_USERINFO', userInfo);
		}
		return true;
	}
}
