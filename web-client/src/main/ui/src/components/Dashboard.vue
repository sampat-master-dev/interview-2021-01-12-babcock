<template>
  <div class="hello">
    <Header />
    <div class="container mrgnbtm">
          <div class="row">
            <div class="col-md-8">
                <Quote />
            </div>
          </div>
    </div>
    <div class="container mrgnbtm">
          <div class="row">
            <div class="col-md-8">
<!--                <CreateUser @createUser="callCalculateCost($event)" />-->
                <Vehicles :vehicles="vehicles" @getAllVehiclesAvailableForHire="getAllVehiclesAvailableForHire()" />
            </div>
          </div>
    </div>
  </div>
</template>

<script>
import Header from './Header.vue'
// import DisplayBoard from './DisplayBoard.vue'
// import Vehicles from './AvailableVehicles.vue'
import { getAllVehiclesAvailableForHire, calculateCost } from '../services/VehicleHireService'
import Quote from "@/components/Quote";
import Vehicles from "@/components/AvailableVehicles";

export default {
  name: 'Dashboard',
  components: {
    Header,
    Vehicles,
    Quote
    // CreateUser,
    // DisplayBoard,
    // Vehicles
  },
  data() {
      return {
        vehicles: [],
      }
  },
  methods: {
    getAllVehiclesAvailableForHire() {
      getAllVehiclesAvailableForHire().then(response => {
        console.log("ddddddd" + response)
        this.vehicles = response
      })
    },
    callCalculateCost(data) {
      console.log('data:::', data)
      data.id = this.numberOfUsers + 1
      calculateCost(data).then(response => {
        console.log(response);

        this.getAllVehiclesAvailableForHire();
      });
    }
  },
  mounted () {
    this.getAllVehiclesAvailableForHire();
  }
}
</script>