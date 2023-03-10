import request from '@/utils/request-no-url'

export function searchAddress(queryString) {
  return request({
    url: `https://rsapi.goong.io/Place/AutoComplete`,
    method: 'get',
    params: {
      input: queryString,
      limit: 5,
      more_compound: true,
      api_key: 'I0m6nfyan0h6Asfd0V7YGycRKedCWxihrGXYk4fX'
    }
  })
}
export function getCities() {
  return request({
    url: 'https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province',
    method: 'get',
    headers: {
      'Content-Type': 'application/json',
      'token': '845cb13c-b64c-11ed-9a6e-422f22df4aa9'
    }
  })
}

export function getDistricts(province_id) {
  return request({
    url: 'https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/district',
    method: 'get',
    headers: {
      'Content-Type': 'application/json',
      'token': '845cb13c-b64c-11ed-9a6e-422f22df4aa9'
    },
    params: {
      'province_id': province_id
    }
  })
}

export function getVillages(district_id) {
  return request({
    url: 'https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/ward',
    method: 'get',
    headers: {
      'Content-Type': 'application/json',
      'token': '845cb13c-b64c-11ed-9a6e-422f22df4aa9'
    },
    params: {
      'district_id': district_id
    }
  })
}
