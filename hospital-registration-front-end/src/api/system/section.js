import request from '@/utils/request'

// 查询科室信息列表
export function listSection(query) {
  return request({
    url: '/system/section/list',
    method: 'get',
    params: query
  })
}

// 查询科室信息详细
export function getSection(deptId) {
  return request({
    url: '/system/section/' + deptId,
    method: 'get'
  })
}

// 新增科室信息
export function addSection(data) {
  return request({
    url: '/system/section',
    method: 'post',
    data: data
  })
}

// 修改科室信息
export function updateSection(data) {
  return request({
    url: '/system/section',
    method: 'put',
    data: data
  })
}

// 删除科室信息
export function delSection(deptId) {
  return request({
    url: '/system/section/' + deptId,
    method: 'delete'
  })
}
