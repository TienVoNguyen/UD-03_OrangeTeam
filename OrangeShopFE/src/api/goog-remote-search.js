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
