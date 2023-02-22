import Layout from '@/layout'

export default {
  path: '/parameter-management',
  component: Layout,
  redirect: 'noRedirect',
  name: 'input-parameter-management',
  meta: {
    title: 'Quản Trị Tham số Nhập liệu',
    icon: 'form'
  },
  children: [
    {
      path: 'code-rules',
      redirect: 'noRedirect',
      meta: {
        title: 'Quy tắc chuyển đổi biến'
      },
      name: 'converted-rule',
      component: () =>
        import(
          '@/views/enterprise-financial-statement/components/nested-route'
        ),
      children: [
        {
          path: 'enterprise-report',
          component: () => import('@/views/enterprise-financial-statement'),
          name: 'enterpriseFinancialStatement',
          meta: { title: 'BCTC chung KHDN' }
        },
        {
          path: 'financial-norm',
          component: () => import('@/views/financial-norm'),
          name: 'financialNorm',
          meta: { title: 'Chỉ tiêu tài chính' }
        },
        {
          path: 'nonfinancial-norm',
          component: () => import('@/views/nonfinancial-norm'),
          name: 'financialNorm',
          meta: { title: 'Chỉ tiêu phi tài chính' }
        }
      ]
    },
    {
      path: 'input-form',
      redirect: 'noRedirect',
      name: 'input-form',
      meta: {
        title: 'Biểu mẫu nhập liệu'
      },
      component: () => import('@/components/NestedRoute/nested-route'),
      children: [
        {
          path: 'financial-report',
          component: () => import('@/views/financial-form'),
          name: 'financial-report',
          meta: { title: 'Báo cáo tài chính' }
        },
        {
          path: 'financial-report/form',
          component: () => import('@/views/financial-form/create-form'),
          name: 'financial-report',
          meta: { title: 'Tạo báo cáo tài chính' },
          hidden: true
        },
        {
          path: 'financial-report-2',
          component: () => import('@/views/enterprise-financial-statement'),
          name: 'financial-report-2',
          meta: { title: 'Thông tin phi tài chính' }
        }
      ]
    }
  ]
}
