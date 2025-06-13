import request from '@/utils/request'

// 查询考核计划列表
export function listPlan(query) {
  return request({
    url: '/oa/plan/list',
    method: 'get',
    params: query
  })
}

// 查询考核计划详细
export function getPlan(id) {
  return request({
    url: '/oa/plan/' + id,
    method: 'get'
  })
}

// 新增考核计划
export function addPlan(data) {
  return request({
    url: '/oa/plan',
    method: 'post',
    data: data
  })
}

// 修改考核计划
export function updatePlan(data) {
  return request({
    url: '/oa/plan',
    method: 'put',
    data: data
  })
}

// 删除考核计划
export function delPlan(id) {
  return request({
    url: '/oa/plan/' + id,
    method: 'delete'
  })
}
