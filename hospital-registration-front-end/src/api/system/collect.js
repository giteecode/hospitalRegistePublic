import request from '@/utils/request'

// 查询收藏信息列表
export function listCollect(query) {
  return request({
    url: '/system/collect/list',
    method: 'get',
    params: query
  })
}

// 查询收藏信息详细
export function getCollect(id) {
  return request({
    url: '/system/collect/' + id,
    method: 'get'
  })
}

// 新增收藏信息
export function addCollect(data) {
  return request({
    url: '/system/collect',
    method: 'post',
    data: data
  })
}

// 修改收藏信息
export function updateCollect(data) {
  return request({
    url: '/system/collect',
    method: 'put',
    data: data
  })
}

// 删除收藏信息
export function delCollect(id) {
  return request({
    url: '/system/collect/' + id,
    method: 'delete'
  })
}
