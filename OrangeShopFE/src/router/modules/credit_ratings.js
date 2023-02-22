
import Layout from '@/layout'

const creditRatingsRouter = {
  path: '/creditRatingsRouter',
  component: Layout,
  redirect: 'noRedirect',
  name: 'CreditRatings',
  meta: {
    title: 'Xếp Hạng Tín Dụng',
    icon: 'el-icon-document'
  },
  children: [
    // {
    //   path: 'business_customers',
    //   component: () => import('@/views/business_customers/business_customers-list'),
    //   name: 'Business Customers',
    //   meta: { title: 'KH Doanh Nghiệp' }
    // },
    {
      path: 'business_customers',
      component: () => import('@/views/creditRatings/business_customers/business_customers-list'),
      name: 'Business Customers',
      meta: { title: 'KH Doanh Nghiệp' },
      children: [
        {
          path: 'customer_identification',
          component: () => import('@/views/creditRatings/business_customers/customer_identification/customer_identification-list'),
          name: 'Customer Identification',
          meta: { title: 'Định danh KH' }
        },
        {
          path: 'add_customer_identification',
          component: () => import('@/views/creditRatings/business_customers/customer_identification/add-customer_identification'),
          name: 'Add Customer Identification',
          hidden: true,
          meta: { title: 'Thêm mới định danh KH' }
        },
        {
          path: 'update_customer_identification/:id',
          component: () => import('@/views/creditRatings/business_customers/customer_identification/update-customer_identification'),
          name: 'Update Customer Identification',
          hidden: true,
          meta: { title: 'Cập nhật định danh KH' }
        },
        {
          path: 'update_change_department/:id',
          component: () => import('@/views/creditRatings/business_customers/change_department/update-change_department'),
          name: 'Update Change Department',
          hidden: true,
          meta: { title: 'Cập nhật thông tin ĐVQLKH' }
        },
        {
          path: 'details_change_department/:id',
          component: () => import('@/views/creditRatings/business_customers/change_department/details-change_department'),
          name: 'Details Change Department',
          hidden: true,
          meta: { title: 'Xem thông tin ĐVQLKH' }
        },
        {
          path: 'profile_initialization',
          component: () => import('@/views/creditRatings/business_customers/profile_initialization/profile_initialization-list'),
          name: 'Profile Initialization',
          meta: { title: 'Khởi Tạo Hồ Sơ' }
        },
        {
          path: 'profile_list',
          component: () => import('@/views/creditRatings/business_customers/profile_list/profile_list-list'),
          name: 'Profile List',
          meta: { title: 'Danh Sách Hồ Sơ' }
        },
        {
          path: 'documents_processed',
          component: () => import('@/views/creditRatings/business_customers/documents_processed/documents_processed-list'),
          name: 'Documents Processed',
          meta: { title: 'Hồ Sơ Cần Xử Lý' }
        },
        {
          path: 'rank_adjustment',
          component: () => import('@/views/creditRatings/business_customers/rank_adjustment/rank_adjustment-list'),
          name: 'Rank Adjustment',
          meta: { title: 'Điều Chỉnh Hạng' }
        },
        {
          path: 'change_department',
          component: () => import('@/views/creditRatings/business_customers/change_department/change_department-list'),
          name: 'Change Department',
          meta: { title: 'Thay Đổi ĐVQLKH' }
        }
      ]
    },
    {
      path: 'ctck_customers',
      component: () => import('@/views/creditRatings/ctck_customers/ctck_customers-list'),
      name: 'CTCK Customers',
      meta: { title: 'CT chứng khoán' },
      children: [
        {
          path: 'customer_identification',
          component: () => import('@/views/creditRatings/ctck_customers/customer_identification/customer_identification-list'),
          name: 'Customer Identification',
          meta: { title: 'Định danh KH' }
        },
        {
          path: 'add_customer_identification',
          component: () => import('@/views/creditRatings/ctck_customers/customer_identification/add-customer_identification'),
          name: 'Add Customer Identification',
          hidden: true,
          meta: { title: 'Thêm mới định danh KH' }
        },
        {
          path: 'update_customer_identification/:id',
          component: () => import('@/views/creditRatings/ctck_customers/customer_identification/update-customer_identification'),
          name: 'Update Customer Identification',
          hidden: true,
          meta: { title: 'Cập nhật định danh KH' }
        },
        {
          path: 'update_change_department/:id',
          component: () => import('@/views/creditRatings/ctck_customers/change_department/update-change_department'),
          name: 'Update Change Department',
          hidden: true,
          meta: { title: 'Cập nhật thông tin ĐVQLKH' }
        },
        {
          path: 'details_change_department/:id',
          component: () => import('@/views/creditRatings/ctck_customers/change_department/details-change_department'),
          name: 'Details Change Department',
          hidden: true,
          meta: { title: 'Xem thông tin ĐVQLKH' }
        },
        {
          path: 'profile_initialization',
          component: () => import('@/views/creditRatings/ctck_customers/profile_initialization/profile_initialization-list'),
          name: 'Profile Initialization',
          meta: { title: 'Khởi Tạo Hồ Sơ' }
        },
        {
          path: 'profile_list',
          component: () => import('@/views/creditRatings/ctck_customers/profile_list/profile_list-list'),
          name: 'Profile List',
          meta: { title: 'Danh Sách Hồ Sơ' }
        },
        {
          path: 'documents_processed',
          component: () => import('@/views/creditRatings/ctck_customers/documents_processed/documents_processed-list'),
          name: 'Documents Processed',
          meta: { title: 'Hồ Sơ Cần Xử Lý' }
        },
        // {
        //   path: 'rank_adjustment',
        //   component: () => import('@/views/creditRatings/ctck_customers/rank_adjustment/rank_adjustment-list'),
        //   name: 'Rank Adjustment',
        //   meta: { title: 'Điều Chỉnh Hạng' }
        // },
        {
          path: 'change_department',
          component: () => import('@/views/creditRatings/ctck_customers/change_department/change_department-list'),
          name: 'Change Department',
          meta: { title: 'Thay Đổi ĐVQLKH' }
        }
      ]
    }
  ]
}

export default creditRatingsRouter
