import request from '@/utils/request'

export function fetchFinancialVariable(params) {
  return request({
    url: '/variables',
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
