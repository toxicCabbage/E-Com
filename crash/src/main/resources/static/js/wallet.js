var wrapALL = document.getElementById("formwrap");
const forma = document.querySelector("#form");
var money = document.getElementById("moneyInput");
wrapALL;
function shoowFrom() {
  wrapALL.style.visibility = "visible";
}
function hideFrom() {
  wrapALL.style.visibility = "hidden";
}

forma.addEventListener("submit", (e) => {
  if (money.value == "") {
    e.preventDefault();
  } else {
  }
});
