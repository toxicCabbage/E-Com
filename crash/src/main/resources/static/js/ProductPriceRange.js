var minValue = document.getElementById("MinPrice");
var maxValue = document.getElementById("MaxPrice");
var currentPage = document.getElementById("currentPage");
var sortDir = document.getElementById("sortDir");
var sortField = document.getElementById("sortField");

maxValue.onchange = changeListnerMax;
minValue.onchange = changeListnerMin;

function changeListnerMin() {
  // alert("min Value : " + minValue.value);
  redirect();
}

function changeListnerMax() {
  // alert("Max Value : " + maxValue.value + "min Value : " + minValue.value);
  redirect();
}

// http://localhost:8080/FlashDeal/electronic/1?sortField=productName&sortDir=null

function redirect() {
  // var link = /*[[@{/electronic}]] */ 'helo';
  var link =
    "http://localhost:8080/FlashDeal/FillterProducts/" +
    currentPage.value +
    "?sortField=" +
    sortField.value +
    "&sortDir=" +
    sortDir.value +
    "&minValue=" +
    minValue.value +
    "&maxValue=" +
    maxValue.value;

  // var Url = "http://localhost:8080/" + hei;
  // window.location = Url;
  // alert(link);
  window.location = link;
}
