import request from '@/utils/request'

export function getAllOutputCSS(data) {
  return request({
    url: `out-put-scoring-model-from-css`,
    method: 'post',
    data
  })
}

export function getOneById(id) {
  return request({
    url: `out-put-scoring-model-from-css/${id}`,
    method: 'get'
  })
}

export function createOutputCSS(data) {
  return request({
    url: `out-put-scoring-model-from-css/create`,
    method: 'post',
    data
  })
}

export function updateOutputCSS(data) {
  return request({
    url: `out-put-scoring-model-from-css/${data.id}`,
    method: 'put',
    data
  })
}

export function deleteOutputCSS(id) {
  return request({
    url: `out-put-scoring-model-from-css/${id}`,
    method: 'delete'
  })
}
