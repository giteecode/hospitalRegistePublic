import request from '@/utils/request'

// 查询预约挂号记录列表
export function listRegistration(query) {
  return request({
    url: '/system/registration/list',
    method: 'get',
    params: query
  })
}

export function getStatistics(query) {
  return request({
    url: '/system/registration/getStatistics',
    method: 'get',
    params: query
  })
}

export function getStatisticsWeekVO(query) {
  return request({
    url: '/system/registration/getStatisticsWeekVO',
    method: 'get',
    params: query
  })
}


export function getStatisticsDayVO(query) {
  return request({
    url: '/system/registration/getStatisticsDayVO',
    method: 'get',
    params: query
  })
}

// 查询预约挂号记录详细
export function getRegistration(id) {
  return request({
    url: '/system/registration/' + id,
    method: 'get'
  })
}

// 新增预约挂号记录
export function addRegistration(data) {
  return request({
    url: '/system/registration',
    method: 'post',
    data: data
  })
}

// 修改预约挂号记录
export function updateRegistration(data) {
  return request({
    url: '/system/registration',
    method: 'put',
    data: data
  })
}

// 删除预约挂号记录
export function delRegistration(id) {
  return request({
    url: '/system/registration/' + id,
    method: 'delete'
  })
}
