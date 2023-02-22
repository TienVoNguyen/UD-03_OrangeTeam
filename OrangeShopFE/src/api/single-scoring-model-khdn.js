import request from '@/utils/request'

export function getAllPage(data) {
  return request({
    url: `single-scoring-model-khdn`,
    method: 'post',
    data
  })
}

export function getOneById(id) {
  return request({
    url: `single-scoring-model-khdn/${id}`,
    method: 'get'
  })
}

export function createSingleScoringModelKHDN(data) {
  return request({
    url: `single-scoring-model-khdn/create`,
    method: 'post',
    data
  })
}

export function updateSingleScoringModelKHDN(data) {
  return request({
    url: `single-scoring-model-khdn/${data.id}`,
    method: 'put',
    data
  })
}

export function deleteSingleScoringModelKHDN(id) {
  return request({
    url: `single-scoring-model-khdn/${id}`,
    method: 'delete'
  })
}

export function uploadFileSingleScoringModelKHDN(data) {
  return request({
    url: `single-scoring-model-khdn/upload-file`,
    method: 'post',
    headers: { 'Content-Type': 'multipart/form-data' },
    data
  })
}

export function getListDataField(data) {
  return request({
    url: `single-scoring-model-khdn/getListDataField`,
    method: 'post',
    data
  })
}

export function getListFinancialVariable(data) {
  return request({
    url: `single-scoring-model-khdn/getListFinancialVariable`,
    method: 'post',
    data
  })
}

export function getListIndustryGroup(data) {
  return request({
    url: `single-scoring-model-khdn/getListIndustryGroup`,
    method: 'post',
    data
  })
}

export function getInputModel(data) {
  return request({
    url: `single-scoring-model-khdn/getInputModel`,
    method: 'post',
    data
  })
}
