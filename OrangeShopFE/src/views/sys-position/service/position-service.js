import request from '@/utils/request'

export function getPositions(query) {
  return request({
    url: `positions`,
    method: 'get',
    params: query
  })
}

export function getPosition(id) {
  return request({
    url: `position/${id}`,
    method: 'get'
  })
}

export function insertPosition(data) {
  return request({
    url: `position`,
    method: 'post',
    data
  })
}

export function updatePosition(id, data) {
  return request({
    url: `position/${id}`,
    method: 'put',
    data
  })
}

export function deletePosition(id, data) {
  return request({
    url: `position/${id}`,
    method: 'delete',
    data
  })
}
export function getDataPositions(query) {
  return request({
    url: `data/positions`,
    method: 'get',
    params: query
  })
}

