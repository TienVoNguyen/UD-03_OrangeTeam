import request from '@/utils/request-be-app'

export function getListProduct(params) {
  return request({
    url: 'product',
    method: 'get',
    params: params
  })
}

export function getProductDetail(params) {
  return request({
    url: 'product/product-detail',
    method: 'get',
    params: params
  })
}

