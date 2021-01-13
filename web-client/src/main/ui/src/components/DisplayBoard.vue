<template>
    <div class="display-board">
        <h4>Number of Available Vehicles Today</h4>
        <div class="btn">
            <button @click='getAllVehiclesAvailableForHire()' type="button" class="btn btn-warning">Get all Vehicles Available Today</button>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'DisplayBoard',
        props: ['numberOfVehicles'],
        methods: {
          getAllVehiclesAvailableForHire() {
                this.$emit('getAllVehiclesAvailableForHire')
            }
        }
    }
</script>