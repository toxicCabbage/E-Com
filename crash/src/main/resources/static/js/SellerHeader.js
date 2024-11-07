var element = document.getElementById("userEle");
var userList = document.getElementById("userList");

function setUserListPosi() {
  userList.style.visibility = "visible";
  //   alert("hellow");
}
function hideUserList() {
  userList.style.visibility = "hidden";
}

userList.addEventListener("mouseover", setUserListPosi);
userList.addEventListener("mouseout", hideUserList);
element.addEventListener("mouseover", setUserListPosi);
element.addEventListener("mouseout", hideUserList);
// alert("ehh");
