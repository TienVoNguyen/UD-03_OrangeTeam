import request from '@/utils/request'

export function getPageEconomicFCC(data) {
  return request({
    url: `fcc-economics`,
    method: 'get',
    params: data
  })
}

export function createEconomicFCC(data) {
  return request({
    url: `fcc-economics`,
    method: 'post',
    data
  })
}

export function updateEconomicFCC(id, data) {
  return request({
    url: `fcc-economics/${id}`,
    method: 'put',
    data
  })
}

export function deleteEconomicFCC(id, data) {
  return request({
    url: `fcc-economics/${id}`,
    method: 'delete',
    params: data
  })
}

export function applyEconomicFCC(data) {
  return request({
    url: `fcc-economics/apply`,
    method: 'put',
    data
  })
}

export function importEconomicFCC(data) {
  return request({
    url: `fcc-economics/import`,
    method: 'post',
    data
  })
}

export function rejectEconomicFCC(id, data) {
  return request({
    url: `fcc-economics/${id}/reject`,
    method: 'put',
    data
  })
}

export function changeStatusEconomicFCC(id, data) {
  return request({
    url: `fcc-economics/${id}/change-status`,
    method: 'put',
    data
  })
}
export function getAllEconomicSBV(data) {
  return request({
    url: `sbv-economics/getAll`,
    method: 'get',
    params: data
  })
}
export function getAllEconomicCSS(data) {
  return request({
    url: `fcc-economics/getAllEconomicCSS`,
    method: 'get',
    params: data
  })
}
