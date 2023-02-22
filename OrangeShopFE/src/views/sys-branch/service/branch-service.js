import request from '@/utils/request'

export function getBranches(query) {
  return request({
    url: `branches`,
    method: 'get',
    params: query
  })
}

export function getBranch(id) {
  return request({
    url: `branch/${id}`,
    method: 'get'
  })
}

export function insertBranch(data) {
  return request({
    url: `branch`,
    method: 'post',
    data
  })
}

export function updateBranch(id, data) {
  return request({
    url: `branch/${id}`,
    method: 'put',
    data
  })
}

export function deleteBranch(id, data) {
  return request({
    url: `branch/${id}`,
    method: 'delete',
    data
  })
}

export function getTaxonomy(params) {
  return request({
    url: `taxonomy`,
    method: 'get',
    params: params
  })
}

export function getDataBranches(params) {
  return request({
    url: `data/branches`,
    method: 'get',
    params: params
  })
}
