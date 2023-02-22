
import Layout from '@/layout'
import industryGroupRouter from '@/router/modules/industry-group'
import scaleClassificationRouter from '@/router/modules/scale-classification'
import ruleChangeRankRouter from '@/router/modules/rule-change-rank'
import configParameterKHDNRouter from '@/router/modules/config-parameter-khdn'

const parameterManagementRouter = {
  path: '/parameter-management',
  component: Layout,
  redirect: 'noRedirect',
  name: 'ParameterManagement',
  meta: {
    title: 'Quản trị tham số nhập liệu',
    icon: 'el-icon-s-help'
  },
  children: [
    industryGroupRouter,
    scaleClassificationRouter,
    ruleChangeRankRouter,
    configParameterKHDNRouter
  ]
}

export default parameterManagementRouter
