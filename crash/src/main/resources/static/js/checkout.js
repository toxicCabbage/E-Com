var payMethod = document.getElementById("payMethod");
const submitFrom = document.getElementById("submitFrom");
var hidden = document.getElementById("hidden");

submitFrom.addEventListener("submit", (e) => {
  // alert("SUMIR");
  hidden.value = payMethod.value;
  // alert("payMethod " + payMethod.value);
  // alert("final value " + hidden.value);
});
