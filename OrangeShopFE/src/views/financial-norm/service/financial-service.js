import request from '@/utils/request'

export function fetchFinancialVariable(params) {
  return request({
    url: '/variables',
    method: 'get',
    params: params
  })
}

export function fetchVariableException(params) {
  return request({
    url: '/variables/exception',
    method: 'get',
    params: params
  })
}

export function exportFinancialVariable(body) {
  return request({
    url: '/variables/export',
    method: 'post',
    data: body,
    responseType: 'blob'
  })
}

export function exportVariableException(body) {
  return request({
    url: '/variables/exception/export',
    method: 'post',
    data: body,
    responseType: 'blob'
  })
}

