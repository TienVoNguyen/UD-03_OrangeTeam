export default {
  methods: {
    hasPermission(componentName, action) {
      // debugger
      return this.$store.getters.actions[componentName?.toLowerCase()].indexOf(action) !== -1
    }
  }
}

