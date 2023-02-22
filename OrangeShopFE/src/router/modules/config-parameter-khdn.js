
const configParameterKHDNRouter = {
  path: 'config-parameter-khdn',
  component: () => import('@/views/parameter-management/config-parameter-khdn/index'),
  name: 'config-parameter-khdn',
  meta: { title: 'Quản trị cấu hình tham số KHDN' },
  children: [
    {
      path: '',
      component: () => import('@/views/parameter-management/config-parameter-khdn/components/list-config'),
      name: 'ListConfig',
      hidden: true
    },
    {
      path: 'add/:id',
      component: () => import('@/views/parameter-management/config-parameter-khdn/components/modify-config'),
      name: 'AddConfig',
      hidden: true
    },
    {
      path: 'edit/:id',
      component: () => import('@/views/parameter-management/config-parameter-khdn/components/modify-config'),
      name: 'EditConfig',
      hidden: true
    }
  ]
}

export default configParameterKHDNRouter
