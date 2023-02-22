import request from '@/utils/request'

export function getAllPage(data) {
  return request({
    url: `config-parameter-property`,
    method: 'post',
    data
  })
}

export function getOneConfigPropertyById(id) {
  return request({
    url: `config-parameter-property/${id}`,
    method: 'get'
  })
}

export function createConfigParamProperty(data) {
  return request({
    url: `config-parameter-property/create`,
    method: 'post',
    data
  })
}

export function updateConfigParamProperty(data) {
  return request({
    url: `config-parameter-property/${data.id}`,
    method: 'put',
    data
  })
}

export function deleteConfigParamProperty(id) {
  return request({
    url: `config-parameter-property/${id}`,
    method: 'delete'
  })
}

export function importConfigParamProperty(data) {
  return request({
    url: `config-parameter-property/import`,
    method: 'post',
    data
  })
}

export function forceImportConfigParamProperty(data) {
  return request({
    url: `config-parameter-property/force-import`,
    method: 'post',
    data
  })
}
