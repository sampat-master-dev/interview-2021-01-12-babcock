export async function getAllVehiclesAvailableForHire() {

  const response = await fetch('/vehiclehire/availableToday');
  return await response.json();
}

export async function calculateCost(data) {
  console.log("inside calculateCost function..." + data)

  var url = `vehiclehire/1/calculatecost/?startDate=2021-01-10&endDate=2021-01-20`
  const response = await fetch(url);
  return await response.json();
}