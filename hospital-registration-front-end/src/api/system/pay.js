import request from '@/utils/request'

// 查询充值记录信息列表
export function listPay(query) {
  return request({
    url: '/system/pay/list',
    method: 'get',
    params: query
  })
}

// 查询充值记录信息详细
export function getPay(id) {
  return request({
    url: '/system/pay/' + id,
    method: 'get'
  })
}

// 新增充值记录信息
export function addPay(data) {
  return request({
    url: '/system/pay',
    method: 'post',
    data: data
  })
}

// 修改充值记录信息
export function updatePay(data) {
  return request({
    url: '/system/pay',
    method: 'put',
    data: data
  })
}

// 删除充值记录信息
export function delPay(id) {
  return request({
    url: '/system/pay/' + id,
    method: 'delete'
  })
}
