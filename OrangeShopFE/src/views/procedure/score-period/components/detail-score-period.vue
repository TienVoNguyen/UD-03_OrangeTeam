<template>
  <div class="app-container">
    <ValidationObserver ref="observer">
      <general-info :score-period="scorePeriod" :disabled="true" />
      <list-customer :enabled="false" />
    </ValidationObserver>
  </div>
</template>

<script>
import { ValidationObserver } from 'vee-validate'
import ListCustomer from '@/views/procedure/score-period/components/customer/list-customer'
import GeneralInfo from '@/views/procedure/score-period/components/general-info'

export default {
  name: 'DetailScorePeriod',
  components: { GeneralInfo, ListCustomer, ValidationObserver },
  data() {
    return {
      scorePeriod: {
        name: '',
        startDate: '',
        endDate: '',
        status: null
      }
    }
  },
  created() {
    this.scorePeriod = {
      ...this.$route.params.scorePeriod
    }
  }
}
</script>

<style scoped>

</style>
