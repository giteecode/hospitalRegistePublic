//阿里云图片访问地址
let imgUrl = 'xxx'
//数据请求基地址
let domain = "http://localhost/api"
module.exports = {
	HTTP_REQUEST_URL: domain,
	HTTP_IMG_URL: imgUrl,
	HEADER: {
		'content-type': 'application/json'
	},
	HEADERPARAMS: {
		'content-type': 'application/x-www-form-urlencoded'
	},
	// 回话密钥名称 请勿修改此配置
	TOKENNAME: 'Authorization',
	// 缓存时间 0 永久
	EXPIRE: 0,
};
