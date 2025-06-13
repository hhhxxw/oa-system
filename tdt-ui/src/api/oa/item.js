import request from '@/utils/request'

// 查询人力考核指标集列表
export function listItem(query) {
  return request({
    url: '/oa/item/list',
    method: 'get',
    params: query
  })
}

// 查询人力考核指标集详细
export function getItem(id) {
  return request({
    url: '/oa/item/' + id,
    method: 'get'
  })
}

// 新增人力考核指标集
export function addItem(data) {
  return request({
    url: '/oa/item',
    method: 'post',
    data: data
  })
}

// 修改人力考核指标集
export function updateItem(data) {
  return request({
    url: '/oa/item',
    method: 'put',
    data: data
  })
}

// 删除人力考核指标集
export function delItem(id) {
  return request({
    url: '/oa/item/' + id,
    method: 'delete'
  })
}
