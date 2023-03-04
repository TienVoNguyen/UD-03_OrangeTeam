import request from '@/utils/request-be-app'

export function login(data) {
  return request({
    url: 'authenticate',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: 'user/info',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/vue-element-admin/user/logout',
    method: 'post'
  })
}
