import request from '@/utils/request'

// 查询医生排班信息列表
export function listScheduling(query) {
  return request({
    url: '/system/scheduling/list',
    method: 'get',
    params: query
  })
}

// 查询医生排班信息详细
export function getScheduling(id) {
  return request({
    url: '/system/scheduling/' + id,
    method: 'get'
  })
}

// 新增医生排班信息
export function addScheduling(data) {
  return request({
    url: '/system/scheduling',
    method: 'post',
    data: data
  })
}

// 修改医生排班信息
export function updateScheduling(data) {
  return request({
    url: '/system/scheduling',
    method: 'put',
    data: data
  })
}

// 删除医生排班信息
export function delScheduling(id) {
  return request({
    url: '/system/scheduling/' + id,
    method: 'delete'
  })
}
