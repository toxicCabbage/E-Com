var Img1 = document.getElementById("MainImgHolder1");
var Img2 = document.getElementById("MainImgHolder2");
var Img3 = document.getElementById("MainImgHolder3");

function img1() {
  Img1.style.visibility = "visible";
  Img2.style.visibility = "hidden";
  Img3.style.visibility = "hidden";
}
function img2() {
  Img1.style.visibility = "hidden";
  Img3.style.visibility = "hidden";
  Img2.style.visibility = "visible";
}
function img3() {
  Img1.style.visibility = "hidden";
  Img2.style.visibility = "hidden";
  Img3.style.visibility = "visible";
}
