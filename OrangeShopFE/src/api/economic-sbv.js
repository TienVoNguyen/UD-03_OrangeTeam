import request from '@/utils/request'

export function convertCur(src, des) {
  src.id = des.id
  src.codeSBV = des.codeSBV
  src.nameSBV = des.codeSBV
  src.status = des.status
  src.requestChange = des.requestChange

  return src
}

export function convertRes(src, des) {
  des.id = src.id
  des.code_sbv = src.codeSBV
  des.name_sbv = src.nameSBV
  des.status = src.status
  des.request_change = src.requestChange

  return src
}
export function getAllPage(data) {
  return request({
    url: `sbv-economics`,
    method: 'post',
    data
  })
}

export function getOneById(id) {
  return request({
    url: `sbv-economics/${id}`,
    method: 'get'
  })
}

export function createSBV(data) {
  return request({
    url: `sbv-economics/create`,
    method: 'post',
    data
  })
}

export function updateSBV(data) {
  return request({
    url: `sbv-economics/${data.id}`,
    method: 'put',
    data
  })
}
export function approveSBV(data) {
  return request({
    url: `sbv-economics/approve/${data.id}`,
    method: 'put',
    data
  })
}

export function importSBV(data) {
  return request({
    url: `sbv-economics/import`,
    method: 'post',
    data
  })
}

export function deleteSBV(id) {
  return request({
    url: `sbv-economics/${id}`,
    method: 'delete'
  })
}
