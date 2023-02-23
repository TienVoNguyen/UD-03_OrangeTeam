export default {
  'Menu1-1': () => import('@/views/nested/menu1/menu1-1'),
  'Menu1-2-1': () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
  'Menu1-2-2': () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
  'Menu1-3': () => import('@/views/nested/menu1/menu1-3'),
  'Menu2': () => import('@/views/nested/menu2/index'),
  'dashbroad': () => import('@/views/dashboard'),
  'user': () => import('@/views/sys-user'),
  'group': () => import('@/views/sys-group'),
  'function': () => import('@/views/sys-function'),
  'branch': () => import('@/views/sys-branch'),
  'department': () => import('@/views/sys-department'),
  'position': () => import('@/views/sys-position'),
  'fcc': () => import('@/views/industry/fcc/index'),
  'sbv': () => import('@/views/parameter-management/group-management/economic-sbv/economic-sbv-list'),
  'rule-change-rank': () => import('@/views/parameter-management/rule-change-rank/components/list-rule'),
  'config-parameter-khdn': () => import('@/views/parameter-management/config-parameter-khdn/components/list-config'),

  // 'bibo-test' => là mã màn hình => từ mã màn hình này có thể lấy ra được path url
  'bibo-test': () => import('@/views/reports/component/report-failover-by-month'),
  'reportfailoverbymonth': () => import('@/views/reports/component/report-failover-by-month'),
  'reportfailoverbycampaign': () => import('@/views/reports/component/report-failover-by-campaign'),
  'reportfailoverdetail': () => import('@/views/reports/component/report-failover-detail'),
  'createfailover': () => import('@/views/failover/component/create-failover')
}
