<template>
    <div class="container">
        <h2>Available Vehicles Today </h2>
        <div class="container mrgnbtm">
          <div class="row">
            <div class="col-md-8">
              <Vehicles :vehicles="vehicles" @getAllVehiclesAvailableForHire="getAllVehiclesAvailableForHire()" />
            </div>
          </div>
        </div>

      <table class="table table-bordered">
            <thead>
            <tr>
                <th>Vehicle Id</th>
                <th>Registration Number</th>
                <th>Make</th>
                <th>Model</th>
                <th>Fuel Type</th>
                <th>Quote Start Date</th>
                <th>Quote End Date</th>
                <th>Calculate Cost of Hiring</th>
            </tr>
            </thead>
            <tbody>
              <tr v-for="item in vehicles" :key="item.id">
                  <td>{{ item.id }} </td>
                  <td>{{ item.registrationNumber }}</td>
                  <td>{{ item.make }}</td>
                  <td>{{ item.model }}</td>
                  <td>{{ item.fuelType }}</td>
                  <td><input type="date" id="startDate" name="startDate" v-model="startDate" value="2021-01-13"></td>
                  <td><input type="date" id="endDate" name="endDate" v-model="endDate" value="2021-01-23"></td>
                  <td><button type="button" @click='callCalculateCost()' class="btn btn-danger">Calculate Cost of Hire</button></td>
              </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import {calculateCost, getAllVehiclesAvailableForHire} from '../services/VehicleHireService'

    export default {
      data() {
        return {
          vehicles: [],
          quote: '',
          id: 0,
          startDate: '',
          endDate: ''
        }
      },methods: {
        getAllVehiclesAvailableForHire() {
          getAllVehiclesAvailableForHire().then(response => {
            console.log("ddddddd" + response)
            this.vehicles = response
          })
        }, callCalculateCost: function (data) {
          console.log('data:::', data)
          const payload = {
            id: this.id,
            startDate: this.startDate,
            endDate: this.endDate
          }
          this.$emit('calculateCost', payload)
          calculateCost(data).then(response => {
            console.log(response);
            this.quote = response
            console.log("emmitting quote" + this.quote)
            this.$emit('updateQuoteFromAvailableVehicles', this.quote)
            // this.getAllVehiclesAvailableForHire();
          });
        }
      },
      mounted () {
        console.log("calling.....")
        this.getAllVehiclesAvailableForHire();
      }
     }
</script>