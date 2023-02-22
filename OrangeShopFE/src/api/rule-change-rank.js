import request from '@/utils/request'

export function getAllPage(data) {
  return request({
    url: `rule-change-rank`,
    method: 'post',
    data
  })
}

export function getOneById(id) {
  return request({
    url: `rule-change-rank/${id}`,
    method: 'get'
  })
}

export function createRule(data) {
  return request({
    url: `rule-change-rank/create`,
    method: 'post',
    data
  })
}

export function updateRule(data) {
  return request({
    url: `rule-change-rank/${data.id}`,
    method: 'put',
    data
  })
}

export function deleteRule(id) {
  return request({
    url: `rule-change-rank/${id}`,
    method: 'delete'
  })
}
export function importRule(data) {
  return request({
    url: `rule-change-rank/import`,
    method: 'post',
    data
  })
}
