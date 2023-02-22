import request from '@/utils/request'

export function getAllPage(data) {
  return request({
    url: `matrix-classify`,
    method: 'post',
    data
  })
}

export function getOneById(id) {
  return request({
    url: `matrix-classify/${id}`,
    method: 'get'
  })
}

export function createMatrix(data) {
  return request({
    url: `matrix-classify/create`,
    method: 'post',
    data
  })
}

export function updateMatrix(data) {
  return request({
    url: `matrix-classify/${data.id}`,
    method: 'put',
    data
  })
}

export function deleteMatrix(id) {
  return request({
    url: `matrix-classify/${id}`,
    method: 'delete'
  })
}
