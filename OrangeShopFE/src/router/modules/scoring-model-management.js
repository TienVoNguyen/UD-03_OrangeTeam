
import Layout from '@/layout'

const scoringModelManagement = {
  path: '/scoring-model-management',
  name: 'ScoringModelManagement',
  component: Layout,
  meta: {
    title: 'Quản trị mô hình chấm điểm',
    icon: 'el-icon-s-management'
  },
  children: [
    {
      path: 'single-scoring-model-khdn',
      name: 'ScoringScoringModelKHDN',
      meta: {
        title: 'Mô hình chấm điểm đơn KHDN'
      },
      component: () =>
        import(
          '@/views/scoring-model-management/single-scoring-model-khdn/index'
        ),
      _children: [
        {
          path: '',
          component: () => import('@/views/scoring-model-management/single-scoring-model-khdn/components/list-scoring-model'),
          name: 'ListSingleScoringModel',
          hidden: true
        },
        {
          path: 'add',
          component: () => import('@/views/scoring-model-management/single-scoring-model-khdn/components/modify-scoring-model'),
          name: 'AddSingleScoringModel',
          hidden: true
        },
        {
          path: 'edit/:id',
          component: () => import('@/views/scoring-model-management/single-scoring-model-khdn/components/modify-scoring-model'),
          name: 'EditSingleScoringModel',
          hidden: true
        },
        {
          path: 'detail/:id',
          component: () => import('@/views/scoring-model-management/single-scoring-model-khdn/components/modify-scoring-model'),
          name: 'ViewDetailSingleScoringModel',
          hidden: true
        },
        {
          path: 'approve/:id',
          component: () => import('@/views/scoring-model-management/single-scoring-model-khdn/components/modify-scoring-model'),
          name: 'ApproveSingleScoringModel',
          hidden: true
        }
      ],
      get children() {
        return this._children
      },
      set children(value) {
        this._children = value
      }
    },
    {
      path: 'combine-scoring-model-khdn',
      meta: {
        title: 'Mô hình chấm điểm kết hợp KHDN'
      },
      name: 'combine-scoring-model-khdn',
      component: () =>
        import(
          '@/views/enterprise-financial-statement/components/nested-route'
        )
    },
    {
      path: 'establish-master-scale-khdn',
      redirect: 'noRedirect',
      meta: {
        title: 'Thiết lập Master scale KHDN'
      },
      name: 'establish-master-scale-khdn',
      component: () =>
        import(
          '@/views/enterprise-financial-statement/components/nested-route'
        )
    }
  ]
}

export default scoringModelManagement
