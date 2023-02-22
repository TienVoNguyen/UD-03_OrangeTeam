import request from '@/utils/request'

export function getAllOutputCodeModel(data) {
  return request({
    url: `out-put-scoring-model-from-code-model`,
    method: 'post',
    data
  })
}

export function getOneById(id) {
  return request({
    url: `out-put-scoring-model-from-code-model/${id}`,
    method: 'get'
  })
}

export function createOutputCodeModel(data) {
  return request({
    url: `out-put-scoring-model-from-code-model/create`,
    method: 'post',
    data
  })
}

export function updateOutputCodeModel(data) {
  return request({
    url: `out-put-scoring-model-from-code-model/${data.id}`,
    method: 'put',
    data
  })
}

export function deleteCodeModel(id) {
  return request({
    url: `out-put-scoring-model-from-code-model/${id}`,
    method: 'delete'
  })
}

export function importCodeModel(data) {
  return request({
    url: `out-put-scoring-model-from-code-model/import`,
    method: 'post',
    data
  })
}

export function forceImportCodeModel(data) {
  return request({
    url: `out-put-scoring-model-from-code-model/force-import`,
    method: 'post',
    data
  })
}
