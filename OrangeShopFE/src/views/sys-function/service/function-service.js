import request from '@/utils/request'

export function getFunctions(query) {
  return request({
    url: `functions`,
    method: 'get',
    params: query
  })
}
export function getFunction(id) {
  return request({
    url: `functions/${id}`,
    method: 'get'
  })
}

export function insertFunction(data) {
  return request({
    url: `function`,
    method: 'post',
    data
  })
}

export function updateFunction(id, data) {
  return request({
    url: `function/${id}`,
    method: 'put',
    data
  })
}

export function deleteFunctionAction(id) {
  return request({
    url: `function/action/${id}`,
    method: 'delete'
  })
}
export function deleteFunction(id) {
  return request({
    url: `function/${id}`,
    method: 'delete'
  })
}
