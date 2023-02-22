import request from '@/utils/request'

export function getReportFailoverByMonth(data) {
  return request({
    url: 'model/public/BRNToolCoreAPI/BRNToolCoreAPI/api/ReportOrderFailOver/ReportOrderFailOver',
    method: 'post',
    data
  })
}

