import Vue from 'vue'
import App from './App'
import Store from './store/index.js'
import Cache from '@/utils/cache'
import configs from '@/config/app.js'
import {Tips} from '@/utils/utils.js'
import uView from '@/uni_modules/uview-ui'
import request from '@/utils/request.js'  //引入异步请求函数
Vue.prototype.request = request.request //挂载到全局
Vue.use(uView)
uni.$u.config.unit = 'rpx'
Vue.prototype.$config = configs;
Vue.prototype.$Cache = Cache;
Vue.prototype.$store = Store;
Vue.prototype.$Tips = Tips
Vue.config.productionTip = false
App.mpType = 'app'
const app = new Vue({
	...App,
	Store,
	Cache
})
app.$mount()
