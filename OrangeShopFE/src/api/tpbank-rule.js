import request from '@/utils/request'

export function getPageTPBankRule(data) {
  return request({
    url: `tpbank-rules`,
    method: 'get',
    params: data
  })
}

export function getTPBankRule(id, data) {
  return request({
    url: `tpbank-rules/${id}`,
    method: 'get',
    params: data
  })
}

export function createTPBankRule(data) {
  return request({
    url: `tpbank-rules`,
    method: 'post',
    data
  })
}

export function updateTPBankRule(id, data) {
  return request({
    url: `tpbank-rules/${id}`,
    method: 'put',
    data
  })
}

export function deleteTPBankRule(id, data) {
  return request({
    url: `tpbank-rules/${id}`,
    method: 'delete',
    data
  })
}

export function updateTPBankRuleStatus(data) {
  return request({
    url: `tpbank-rules/updatestatus`,
    method: 'put',
    data
  })
}
