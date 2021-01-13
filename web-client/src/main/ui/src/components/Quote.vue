<template>
    <div class="display-board">
        <h4>Your Quotation for Vehicle Hire</h4>
        <table>
          <tr><td>Cost Of Hiring:</td><td></td></tr>
          <tr><td>Date Start</td><td>{{quote.startDate}}</td></tr>
          <tr><td>Date Start</td><td>{{quote.endDate}}</td></tr>
          <tr><td>Cost Of Hiring:</td><td></td></tr>
          <tr><td>Vehicle:</td><td>{{quote.vehicle}}</td></tr>
        </table>
    </div>
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


