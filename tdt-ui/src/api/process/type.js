import request from '@/utils/request'

// 查询审批类型列表
export function listType(query) {
  return request({
    url: '/process/type/list',
    method: 'get',
    params: query
  })
}

// 查询审批类型详细
export function getType(id) {
  return request({
    url: '/process/type/' + id,
    method: 'get'
  })
}

// 新增审批类型
export function addType(data) {
  return request({
    url: '/process/type',
    method: 'post',
    data: data
  })
}

// 修改审批类型
export function updateType(data) {
  return request({
    url: '/process/type',
    method: 'put',
    data: data
  })
}

// 删除审批类型
export function delType(id) {
  return request({
    url: '/process/type/' + id,
    method: 'delete'
  })
}
