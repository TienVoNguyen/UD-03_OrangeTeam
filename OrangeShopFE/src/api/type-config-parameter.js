import request from '@/utils/request'

export function getAllPage(data) {
  return request({
    url: `type-config-parameter`,
    method: 'post',
    data
  })
}

export function getOneById(id) {
  return request({
    url: `type-config-parameter/${id}`,
    method: 'get'
  })
}

export function createTypeConfig(data) {
  return request({
    url: `type-config-parameter/create`,
    method: 'post',
    data
  })
}

export function updateTypeConfig(data) {
  return request({
    url: `type-config-parameter/${data.id}`,
    method: 'put',
    data
  })
}

export function deleteTypeConfig(id) {
  return request({
    url: `type-config-parameter/${id}`,
    method: 'delete'
  })
}
