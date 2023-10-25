/**
 * opt  object | string
 * to_url object | string
 * 例:
 * this.$Tips('/pages/test/test'); 跳转不提示
 * this.$Tips({title:'提示'},'/pages/test/test'); 提示并跳转
 * this.$Tips({title:'提示'},{tab:1,url:'/pages/index/index'}); 提示并跳转值table上
 * tab=1 一定时间后跳转至 table上
 * tab=2 一定时间后跳转至非 table上
 * tab=3 一定时间后返回上页面
 * tab=4 关闭所有页面跳转至非table上
 * tab=5 关闭当前页面跳转至table上
 */
export function Tips(opt, to_url) {
	if (typeof opt == 'string') {
		to_url = opt;
		opt = {};
	}
	let title = opt.title || '',
		icon = opt.icon || 'none',
		endtime = opt.endtime || 3000,
		success = opt.success;
	if (title) uni.showToast({
		title: title,
		icon: icon,
		duration: endtime,
		success
	})
	if (to_url != undefined) {
		if (typeof to_url == 'object') {
			let tab = to_url.tab || 1,
				url = to_url.url || '';
			switch (tab) {
				case 1:
					//一定时间后跳转至 table
					setTimeout(function() {
						uni.switchTab({
							url: url
						})
					}, endtime);
					break;
				case 2:
					//跳转至非table页面
					setTimeout(function() {
						uni.navigateTo({
							url: url,
						})
					}, endtime);
					break;
				case 3:
					//返回上页面
					setTimeout(function() {
						// #ifndef H5
						uni.navigateBack({
							delta: parseInt(url),
						})
						// #endif
						// #ifdef H5
						history.back();
						// #endif
					}, endtime);
					break;
				case 4:
					//关闭当前所有页面跳转至非table页面
					setTimeout(function() {
						uni.reLaunch({
							url: url,
						})
					}, endtime);
					break;
				case 5:
					//关闭当前页面跳转至非table页面
					setTimeout(function() {
						uni.redirectTo({
							url: url,
						})
					}, endtime);
					break;
			}

		} else if (typeof to_url == 'function') {
			setTimeout(function() {
				to_url && to_url();
			}, endtime);
		} else {
			//没有提示时跳转不延迟
			setTimeout(function() {
				uni.navigateTo({
					url: to_url,
				})
			}, title ? endtime : 0);
		}
	}
}
/* 
弹出框
 */
export function toast(msg, duration) {
	uni.showToast({
		title: msg,
		icon: 'none',
		duration: duration || 2000,
	});
}
/*
 * 合并数组
 */
export function SplitArray(list, sp) {
	if (typeof list != 'object') return [];
	if (sp === undefined) sp = [];
	for (var i = 0; i < list.length; i++) {
		sp.push(list[i]);
	}
	return sp;
}
/**
 * 构造树型结构数据
 * @param {*} data 数据源
 * @param {*} id id字段 默认 'id'
 * @param {*} parentId 父节点字段 默认 'parentId'
 * @param {*} children 孩子节点字段 默认 'children'
 */
export function handleTree(data, id, parentId, children) {
	let config = {
		id: id || 'id',
		parentId: parentId || 'parentId',
		childrenList: children || 'children'
	};
	var childrenListMap = {};
	var nodeIds = {};
	var tree = [];
	for (let d of data) {
		let parentId = d[config.parentId];
		if (childrenListMap[parentId] == null) {
			childrenListMap[parentId] = [];
		}
		nodeIds[d[config.id]] = d;
		childrenListMap[parentId].push(d);
	}

	for (let d of data) {
		let parentId = d[config.parentId];
		if (nodeIds[parentId] == null) {
			tree.push(d);
		}
	}

	for (let t of tree) {
		adaptToChildrenList(t);
	}

	function adaptToChildrenList(o) {
		if (childrenListMap[o[config.id]] !== null) {
			o[config.childrenList] = childrenListMap[o[config.id]];
		}
		if (o[config.childrenList]) {
			for (let c of o[config.childrenList]) {
				adaptToChildrenList(c);
			}
		}
	}
	return tree;
}
