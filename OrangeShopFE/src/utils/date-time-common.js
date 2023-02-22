export default {
  data() {
    return {

    }
  },
  methods: {
    convertDateToServer(value, currentFormat) {
      if (value === '' || value === undefined || value == null) {
        return ''
      }
      return this.$moment(value, currentFormat).tz(this.$moment.tz.guess(true)).utc().format()
    },
    convertDateToLocal(value, anotherFormat) {
      if (value === '' || value === undefined || value == null) {
        return ''
      }
      return this.$moment.utc(value).parseZone(this.$moment.tz.guess(true)).local().format(anotherFormat)
    },
    convertDateToLocalUTC(value, anotherFormat) {
      if (value === '' || value === undefined || value == null) {
        return ''
      }
      return this.$moment.utc(value).format(anotherFormat)
    },
    convertDateToAnotherFormat(value, currentFormat, anotherFormat) {
      if (value === '' || value === undefined) {
        return ''
      }
      return this.$moment(value, currentFormat).format(anotherFormat)
    },
    ConvertTimeRanges(objTimeRange) {
      if (objTimeRange != null && objTimeRange.toString().length > 0) {
        const splitActionTime = objTimeRange.split(' to ')
        this.TimeRangesModel.from = this.convertDateToAnotherFormat(splitActionTime[0] + ' 00:00:00', 'DD-MM-YYYY HH:mm:ss', 'YYYY-MM-DD[T]HH:mm:ss[Z]')
        this.TimeRangesModel.to = this.convertDateToAnotherFormat(splitActionTime[1] + ' 23:59:59', 'DD-MM-YYYY HH:mm:ss', 'YYYY-MM-DD[T]HH:mm:ss[Z]')
        return this.TimeRangesModel
      }
      this.TimeRangesModel.to = null
      this.TimeRangesModel.from = null
      return this.TimeRangesModel
    },
    ConvertTimeFrom(objDateTime) {
      return this.convertDateToAnotherFormat(objDateTime + ' 00:00:00', 'DD-MM-YYYY HH:mm:ss', 'YYYY-MM-DD[T]HH:mm:ss[Z]')
    },
    ConvertTimeTo(objDateTime) {
      return this.convertDateToAnotherFormat(objDateTime + ' 23:59:59', 'DD-MM-YYYY HH:mm:ss', 'YYYY-MM-DD[T]HH:mm:ss[Z]')
    },
    ConvertTimeFromToDateJava(objDateTime) {
      return objDateTime + ' 00:00:00'
    },
    ConvertTimeToToDateJava(objDateTime) {
      return objDateTime + ' 23:59:59'
    }
  }
}

