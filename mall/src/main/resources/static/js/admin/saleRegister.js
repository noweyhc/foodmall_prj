
let date = new Date();
let fDate = new Date();
fDate.setMonth(fDate.getMonth() + 1);

document.getElementById('startDate').value = date.toISOString().slice(0, 10);
document.getElementById('startDate').min = date.toISOString().slice(0, 10);
document.getElementById('endDate').value = fDate.toISOString().slice(0, 10);
document.getElementById('endDate').min= date.toISOString().slice(0, 10);

document.getElementById('startTime').value = date.toString().slice(16, 21);

