
const industryGroupRouter = {
  path: 'group-management',
  component: () => import('@/views/parameter-management/group-management/index'),
  name: 'Group Management',
  meta: { title: 'Quản lý nhóm ngành' },
  children: [
    {
      path: 'css',
      name: 'CSSGroup',
      meta: { title: 'Nhóm ngành' }
    },
    {
      path: 'economic-sbv',
      component: () => import('@/views/parameter-management/group-management/economic-sbv/economic-sbv-list'),
      name: 'Economic SBV',
      meta: { title: 'Ngành kinh tế SBV' }
    },
    {
      path: 'economic-fcc',
      component: () => import('@/views/industry/fcc/index'),
      name: 'FCCGroup',
      meta: { title: 'Ngành kinh tế FCC' }
    }
  ]
}

export default industryGroupRouter
