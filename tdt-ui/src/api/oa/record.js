import request from '@/utils/request'

// 查询人力资源考核详情列表
export function listRecord(query) {
  return request({
    url: '/oa/record/list',
    method: 'get',
    params: query
  })
}

// 查询人力资源考核详情详细
export function getRecord(id) {
  return request({
    url: '/oa/record/' + id,
    method: 'get'
  })
}

// 新增人力资源考核详情
export function addRecord(data) {
  return request({
    url: '/oa/record',
    method: 'post',
    data: data
  })
}

// 修改人力资源考核详情
export function updateRecord(data) {
  return request({
    url: '/oa/record',
    method: 'put',
    data: data
  })
}

// 删除人力资源考核详情
export function delRecord(id) {
  return request({
    url: '/oa/record/' + id,
    method: 'delete'
  })
}
