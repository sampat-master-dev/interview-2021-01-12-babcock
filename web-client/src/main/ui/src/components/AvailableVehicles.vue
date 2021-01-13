<template>

    <div class="container">
        <h2>Available Vehicles Today </h2>
        <div id="quote" style="display:none">
          <div class="row">
            <div class="col-md-8">
              <div class="display-board">
                <h4>Your Quotation for Vehicle Hire</h4>
                <table width="400" class="table table-bordered">
                  <tr><td>Cost Of Hiring:</td><td>{{quote.hiringCost}} GBP</td></tr>
                  <tr><td>Date Start</td><td>{{quote.startDate}}</td></tr>
                  <tr><td>Date Start</td><td>{{quote.endDate}}</td></tr>
                  <tr><td>Vehicle:</td><td>{{quote.vehicle}}</td></tr>
                </table>
              </div>
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
        displayQuoteDiv() {
          var x = document.getElementById("quote");
          if (x.style.display === "none") {
            x.style.display = "block";
          } else {
            x.style.display = "none";
          }
        },getAllVehiclesAvailableForHire() {
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
            this.displayQuoteDiv()
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