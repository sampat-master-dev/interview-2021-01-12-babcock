<template>

</template>

<component @eventname="updateparent"></component>

<script>

export default {
  name: 'Quote',
  data() {
    return {
      quote: ''
    }
  },
  methods: {
    updateQuoteFromAvailableVehicles(quoteFromAvailableVehicles) {
      console.log("received quote" + this.quote)

      this.quote = quoteFromAvailableVehicles
    }
  },
  mounted () {
    this.getAllVehiclesAvailableForHire();
  }
}
</script>


