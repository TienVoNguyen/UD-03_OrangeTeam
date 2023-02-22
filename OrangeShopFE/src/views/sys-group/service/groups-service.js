import request from '@/utils/request'

export function getGroups(query) {
  return request({
    url: `groups`,
    method: 'get',
    params: query
  })
}

export function getDataGroups(query) {
  return request({
    url: `data/groups`,
    method: 'get',
    params: query
  })
}

export function getGroup(id) {
  return request({
    url: `group/${id}`,
    method: 'get'
  })
}

export function insertGroup(data) {
  return request({
    url: `group`,
    method: 'post',
    data
  })
}

export function updateGroup(id, data) {
  return request({
    url: `group/${id}`,
    method: 'put',
    data
  })
}

export function changeStatus(id, data) {
  return request({
    url: `/group/${id}/change/status`,
    method: 'put',
    data
  })
}

export function getFunctionsGroup(id, query) {
  return request({
    url: `/group-function/${id}`,
    method: 'get',
    params: query
  })
}
