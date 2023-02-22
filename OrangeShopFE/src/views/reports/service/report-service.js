import request from '@/utils/request'
import requestExt from '@/utils/requestExt'

export function getReportFailoverByMonth(data) {
  return request({
    url: 'model/public/BRNToolCoreAPI/BRNToolCoreAPI/api/ReportOrderFailOver/ReportOrderFailOver',
    method: 'post',
    data
  })
}

export function getReportFailoverByCamp(data) {
  return request({
    url: 'model/public/BRNToolCoreAPI/BRNToolCoreAPI/api/ReportOrderFailOver/ReportOrderFailOverCampaign',
    method: 'post',
    data
  })
}

export function getReportFailoverDetail(data) {
  return request({
    url: 'model/public/BRNToolCoreAPI/BRNToolCoreAPI/api/ReportOrderFailOver/ReportOrderFailOverDetail',
    method: 'post',
    data
  })
}

export function exportExcelReportFailoverByMonth(data) {
  return requestExt({
    url: 'model/public/BRNToolCoreAPI/BRNToolCoreAPI/api/ReportOrderFailOver/ExportReportOrderFailOver',
    method: 'post',
    data
  })
}

export function exportExcelReportFailoverByCamp(data) {
  return requestExt({
    url: 'model/public/BRNToolCoreAPI/BRNToolCoreAPI/api/ReportOrderFailOver/ExportReportCamPaignOrderFailOver',
    method: 'post',
    data
  })
}

export function exportExcelReportFailoverDetail(data) {
  return requestExt({
    url: 'model/public/BRNToolCoreAPI/BRNToolCoreAPI/api/ReportOrderFailOver/ExportReportOrderFailOverDetail',
    method: 'post',
    data
  })
}

export function getAccount(data) {
  return request({
    url: 'model/public/PortalAPI/PortalAPI/api/sysAccounts/search',
    method: 'post',
    data
  })
}
