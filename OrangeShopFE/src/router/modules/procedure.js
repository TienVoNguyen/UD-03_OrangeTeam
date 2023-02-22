
import Layout from '@/layout'

const proceduresRouter = {
  path: '/procedures',
  component: Layout,
  redirect: 'noRedirect',
  name: 'Procedure',
  meta: {
    title: 'Quy Trình Nghiệp Vụ',
    icon: 'el-icon-menu'
  },
  children: [
    // {
    //   path: 'enterprise/user/yuii',
    //   component: () => import('@/views/procedure/user/user-list'),
    //   name: 'TinymceDemo'
    // },
    {
      path: '',
      component: () => import('@/views/procedure/approver'),
      name: 'Procedure'
    },
    {
      path: ':id/enterprises',
      component: () => import('@/views/procedure/layout/procedure-layout'),
      name: 'Enterprise',
      meta: { title: 'KH Doanh Nghiệp' },
      children: [
        {
          path: '',
          component: () => import('@/views/procedure/approver/components/procedure-detail')
        },
        {
          path: 'users',
          redirect: '/procedures',
          name: 'Approver',
          meta: { title: 'Người Phê Duyệt' }
        },
        {
          path: '/extend-scores',
          name: 'ExtendScore',
          component: () => import('@/views/procedure/extend-score/index'),
          meta: { title: 'Gia Hạn Chấm Điểm' }
        },
        {
          path: '/score-periods',
          name: 'ScorePeriod',
          component: () => import('@/views/procedure/score-period/index'),
          meta: { title: 'Kỳ Chấm Điểm' }
        },
        {
          path: '/score-periods/add',
          name: 'AddScorePeriod',
          component: () => import('@/views/procedure/score-period/components/add-score-period'),
          hidden: true
        },
        {
          path: '/score-periods/:id',
          name: 'DetailScorePeriod',
          component: () => import('@/views/procedure/score-period/components/detail-score-period'),
          hidden: true
        },
        {
          path: '/score-periods/:id/edit',
          name: 'EditScorePeriod',
          component: () => import('@/views/procedure/score-period/components/edit-score-period'),
          hidden: true
        },
        {
          path: ':id/users',
          component: () => import('@/views/procedure/approver/components/user'),
          hidden: true
        },
        {
          path: '/lending-products',
          component: () => import('@/views/procedure/lending-product/index'),
          name: 'LendingProduct',
          meta: { title: 'Sản Phẩm Vay' }
        },
        {
          path: '/procedure-config',
          component: () => import('@/views/procedure/config/index'),
          name: 'ProcedureConfig',
          meta: { title: 'Cấu Hình Khác' }
        }
      ]
    },
    {
      path: 'tinymce2',
      component: () => import('@/views/components-demo/tinymce'),
      name: 'TinymceDemo',
      meta: { title: 'CT Chứng Khoán' },
      children: [
        {
          path: 'tinymce1',
          component: () => import('@/views/components-demo/tinymce'),
          name: 'TinymceDemo',
          meta: { title: 'Người Phê Duyệt' }
        },
        {
          path: 'tinymce2',
          component: () => import('@/views/components-demo/tinymce'),
          name: 'TinymceDemo',
          meta: { title: 'Gia Hạn Chấm Điểm' }
        },
        {
          path: 'tinymce3',
          component: () => import('@/views/components-demo/tinymce'),
          name: 'TinymceDemo',
          meta: { title: 'Kỳ Chấm Điểm' }
        },
        {
          path: 'tinymce4',
          component: () => import('@/views/components-demo/tinymce'),
          name: 'TinymceDemo',
          meta: { title: 'Sản Phẩm Vay' }
        },
        {
          path: 'tinymce5',
          component: () => import('@/views/components-demo/tinymce'),
          name: 'TinymceDemo',
          meta: { title: 'Cấu Hình Khác' }
        }
      ]
    }
  ]
}

export default proceduresRouter
