import request from '@/utils/request'

export function getDepartments(query) {
  return request({
    url: `departments`,
    method: 'get',
    params: query
  })
}

export function getDepartment(id) {
  return request({
    url: `department/${id}`,
    method: 'get'
  })
}

export function insertDepartment(data) {
  return request({
    url: `department`,
    method: 'post',
    data
  })
}

export function updateDepartment(id, data) {
  return request({
    url: `department/${id}`,
    method: 'put',
    data
  })
}

export function deleteDepartment(id, data) {
  return request({
    url: `department/${id}`,
    method: 'delete',
    data
  })
}

export function getDataDepartments(query) {
  return request({
    url: `data/departments`,
    method: 'get',
    params: query
  })
}
