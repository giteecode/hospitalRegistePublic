import request from "@/utils/request.js";

/*
管理员账户登录
*/
export function login(data) {
	return request.post("/login", data, {
		noAuth: true,
	});
}
/*
管理员账户信息
*/
export function getInfo() {
	return request.get("/getInfo");
}