var PicOne = document.getElementById("Pic1");
var PicTwo = document.getElementById("Pic2");
var PicThree = document.getElementById("Pic3");

var Name = document.getElementById("changeName");
var Price = document.getElementById("changePrice");
var Category = document.getElementById("changeCategory");
var Description = document.getElementById("changeDescription");

function Pic1() {
  PicOne.style.visibility = "visible";
}

function Pic2() {
  PicTwo.style.visibility = "visible";
}

function Pic3() {
  PicThree.style.visibility = "visible";
}

function Hide() {
  // alert("called");
  PicOne.style.visibility = "hidden";
  PicTwo.style.visibility = "hidden";
  PicThree.style.visibility = "hidden";
  Name.style.visibility = "hidden";
  Price.style.visibility = "hidden";
  Category.style.visibility = "hidden";
  Description.style.visibility = "hidden";
}

function changeName() {
  // alert("change Name :");
  Name.style.visibility = "visible";
}

function changeprice() {
  Price.style.visibility = "visible";
}

function changeCategory() {
  Category.style.visibility = "visible";
}

function changeDecription() {
  Description.style.visibility = "visible";
}
