<template>
  <div class="app-container">
    <ValidationObserver ref="observer">
      <general-info />
      <list-customer />
    </ValidationObserver>
  </div>
</template>

<script>
import { ValidationObserver } from 'vee-validate'
import ListCustomer from '@/views/procedure/score-period/components/customer/list-customer'
import GeneralInfo from '@/views/procedure/score-period/components/general-info'

export default {
  name: 'AddScorePeriod',
  components: { GeneralInfo, ListCustomer, ValidationObserver }
}
</script>

<style scoped>

</style>
