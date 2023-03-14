import request from '@/utils/request-be-app'

export function getListCart() {
  return request({
    url: 'cart',
    method: 'get'
  })
}

export function addCart(data) {
  return request({
    url: 'cart/add',
    method: 'post',
    data: data
  })
}

export function updateQuantity(params) {
  return request({
    url: 'cart/update',
    method: 'post',
    params: params
  })
}

export function removeItem(id) {
  return request({
    url: 'cart/' + id,
    method: 'delete'
  })
}

