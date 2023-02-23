
const scaleClassificationRouter = {
  path: 'scale',
  component: () => import('@/views/parameter-management/scale-classification/index'),
  name: 'ScaleClassification',
  meta: { title: 'Phân loại quy mô KHDN' },
  children: [
    {
      path: 'tpbank-rule',
      name: 'TPBankRule',
      component: () => import('@/views/parameter-management/scale-classification/tpbank-rule/index'),
      meta: { title: 'Quy tắc TPBank' },
      children: [
        {
          path: '',
          component: () => import('@/views/parameter-management/scale-classification/tpbank-rule/components/list-rule'),
          name: 'ListRule',
          hidden: true
        },
        {
          path: 'add',
          component: () => import('@/views/parameter-management/scale-classification/tpbank-rule/components/modify-rule'),
          name: 'AddRule',
          hidden: true
        },
        {
          path: 'edit/:id',
          component: () => import('@/views/parameter-management/scale-classification/tpbank-rule/components/modify-rule'),
          name: 'EditRule',
          hidden: true
        },
        {
          path: ':id',
          component: () => import('@/views/parameter-management/scale-classification/tpbank-rule/components/modify-rule'),
          name: 'ViewRule',
          hidden: true
        }
      ]
    },
    {
      path: 'matrix-classify',
      name: 'MatrixClassify',
      meta: { title: 'Ma trận kết hợp phân loại quy mô' },
      component: () => import('@/views/parameter-management/scale-classification/matrix-classify/index'),
      children: [
        {
          path: '',
          component: () => import('@/views/parameter-management/scale-classification/matrix-classify/components/list-matrix'),
          name: 'ListMatrix',
          hidden: true
        },
        {
          path: 'add',
          component: () => import('@/views/parameter-management/scale-classification/matrix-classify/components/modify-matrix'),
          name: 'AddMatrix',
          hidden: true
        },
        {
          path: 'edit/:id',
          component: () => import('@/views/parameter-management/scale-classification/matrix-classify/components/modify-matrix'),
          name: 'EditMatrix',
          hidden: true
        },
        {
          path: 'detail/:id',
          component: () => import('@/views/parameter-management/scale-classification/matrix-classify/components/modify-matrix'),
          name: 'ViewDetailMatrix',
          hidden: true
        },
        {
          path: 'approve/:id',
          component: () => import('@/views/parameter-management/scale-classification/matrix-classify/components/modify-matrix'),
          name: 'ApproveMatrix',
          hidden: true
        }
      ]
    },
    {
      path: 'economic-fcc',
      name: 'FCCGroup',
      meta: { title: 'Ma trận' }
    }
  ]
}

export default scaleClassificationRouter
