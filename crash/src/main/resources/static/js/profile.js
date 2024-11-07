var addpicBloack = document.getElementById("wrapALL");
var profilePicture = document.getElementById("profilePic");

var addpicbgBloack = document.getElementById("wrapALLbg");
var BGPic = document.getElementById("BGPic");
// const addProfilePicForm = document.getElementById("addProfilePicForm");
// const form = document.querySelector(".formRegi");
const picForm = document.querySelector(".addProfilePicForm");
const addBGPicForm = document.querySelector(".addBGPicForm");

// alert("script works!");

function addBGPic() {
  addpicbgBloack.style.visibility = "visible";
}
function hideBg() {
  addpicbgBloack.style.visibility = "hidden";
}

function addPic() {
  addpicBloack.style.visibility = "visible";
}
function helo() {
  addpicBloack.style.visibility = "hidden";
}
function addPic() {
  addpicBloack.style.visibility = "visible";
}
function helo() {
  addpicBloack.style.visibility = "hidden";
}

picForm.addEventListener("submit", (e) => {
  if (profilePicture.value == "") {
    e.preventDefault();
  } else {
  }
  //   e.preventDefault();
});

addBGPicForm.addEventListener("submit", (e) => {
  if (BGPic.value == "") {
    e.preventDefault();
  }
});
