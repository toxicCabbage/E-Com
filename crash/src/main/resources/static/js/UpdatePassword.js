var newPassword = document.getElementById("newPassword");
var repeatPassword = document.getElementById("repeatPassword");
var currentPassword = document.getElementById("currentPassword");
const updatePasswordForm = document.querySelector(".updatePasswordForm");

var upForm = document.getElementById("upform");

addBGPicForm.addEventListener("submit", (e) => {
  if (BGPic.value == "") {
    e.preventDefault();
  }
});

updatePasswordForm.addEventListener("submit", (e) => {
  if (
    newPassword.value == null ||
    newPassword.value == "" ||
    repeatPassword.value == null ||
    repeatPassword.value == "" ||
    currentPassword.value == "" ||
    currentPassword.value == null
  ) {
    newPassword.style.border = "3px solid red";
    repeatPassword.style.border = "3px solid red";
    currentPassword.style.border = "3px solid red";
    e.preventDefault();
  } else {
    if (repeatPassword.value == repeatPassword.value) {
      newPassword.style.border = "3px solid green";
      repeatPassword.style.border = "3px solid green";
    }
  }
});

function PasswrodBloack() {
  upForm.style.visibility = "visible";
}

function hidePasswordBloack() {
  upForm.style.visibility = "hidden";
  addpicBloack.style.visibility = "hidden";
}
