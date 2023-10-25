import request from '@/utils/request'

// 查询门诊信息列表
export function listOutpatien(query) {
  return request({
    url: '/system/outpatien/list',
    method: 'get',
    params: query
  })
}

// 查询门诊信息详细
export function getOutpatien(roomId) {
  return request({
    url: '/system/outpatien/' + roomId,
    method: 'get'
  })
}

// 新增门诊信息
export function addOutpatien(data) {
  return request({
    url: '/system/outpatien',
    method: 'post',
    data: data
  })
}

// 修改门诊信息
export function updateOutpatien(data) {
  return request({
    url: '/system/outpatien',
    method: 'put',
    data: data
  })
}

// 删除门诊信息
export function delOutpatien(roomId) {
  return request({
    url: '/system/outpatien/' + roomId,
    method: 'delete'
  })
}
