import {
	getUserInfo
} from "@/api/user.js";
import Cache from '@/utils/cache';
import {
	UID,
	USER_INFO,
	LOGIN_STATUS,
} from '@/config/cache';

const state = {
	token: Cache.get(LOGIN_STATUS) || '',
	userInfo: Cache.get(USER_INFO) ? JSON.parse(Cache.get(USER_INFO)) : null,
	uid: Cache.get(UID) || null,
};

const mutations = {
	LOGIN(state, opt) {
		state.token = opt.token;
		Cache.set(LOGIN_STATUS, opt.token);
	},
	SETUID(state, val) {
		state.uid = val;
		Cache.set(UID, val);
	},
	UPDATE_LOGIN(state, token) {
		state.token = token;
	},
	LOGOUT(state) {
		state.token = undefined;
		state.uid = undefined
		Cache.clear(LOGIN_STATUS);
		Cache.clear(UID);
		Cache.clear(USER_INFO);
	},
	UPDATE_USERINFO(state, userInfo) {
		state.userInfo = userInfo;
		Cache.set(USER_INFO, userInfo);
	}
};

const actions = {
	USERINFO({
		state,
		commit
	}, force) {
		return new Promise(reslove => {
			getUserInfo().then(res => {
				commit("UPDATE_USERINFO", res.user);
			});
		}).catch(() => {

		})
	}
};

export default {
	state,
	mutations,
	actions
};
