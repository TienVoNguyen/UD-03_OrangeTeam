import request from '@/utils/request'

export function getUsers(query) {
  return request({
    url: `users`,
    method: 'get',
    params: query
  })
}
export function getUser(id) {
  return request({
    url: `user/${id}`,
    method: 'get'
  })
}

export function insertUser(data) {
  return request({
    url: `user`,
    method: 'post',
    data
  })
}

export function updateUser(id, data) {
  return request({
    url: `user/${id}`,
    method: 'put',
    data
  })
}

export function changeStatus(id, data) {
  return request({
    url: `/user/${id}/change/status`,
    method: 'put',
    data
  })
}

export function getRoles(query) {
  return request({
    url: `roles`,
    method: 'get',
    query
  })
}
