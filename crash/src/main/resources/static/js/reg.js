var fname = document.getElementById("firstName");
var lastname = document.getElementById("secondName");
var username = document.getElementById("userName");
var mobileno = document.getElementById("mobileNumber");
var email = document.getElementById("emailAddress");
var gender = document.getElementById("Gender");
var landmark = document.getElementById("landMark");
var area = document.getElementById("Area");
var district = document.getElementById("District");
var state = document.getElementById("State");
var county = document.getElementById("County");
var password = document.getElementById("passWord");
var ConPassword = document.getElementById("conformPassword");
var Role = document.getElementById("Role");
const form = document.querySelector(".formRegi");

// error massages
var ferror = document.getElementById("ferror");
var lerror = document.getElementById("lerror");
var uerror = document.getElementById("uerror");
var merror = document.getElementById("merror");
var eerror = document.getElementById("eerror");
var gerror = document.getElementById("gerror");
var laerror = document.getElementById("laerror");
var aerror = document.getElementById("aerror");
var derror = document.getElementById("derror");
var serror = document.getElementById("serror");
var cerror = document.getElementById("cerror");
var perror = document.getElementById("perror");
var conerror = document.getElementById("conerror");
var RoleErrro = document.getElementById("Roleerror");

var phoneNumberValidator = /^\d{10}$/;
var validationCount = 0;

form.addEventListener("submit", (e) => {
  if (fname.value == "" || fname.value == null) {
    fname.style.border = "3px solid red";
    ferror.innerText = "invalid value";
  } else {
    fname.style.border = "3px solid green";
    validationCount++;
  }

  if (lastname.value == "" || lastname.value == null) {
    lastname.style.border = "3px solid red";
    lerror.innerText = "invalid value";
  } else {
    lastname.style.border = "3px solid green";
    validationCount++;
  }

  if (username.value == "" || username.value == null) {
    username.style.border = "3px solid red";
    uerror.innerText = "invalid value";
  } else {
    username.style.border = "3px solid green";
    validationCount++;
  }

  if (Role.value == "Select" || Role.value == null) {
    Role.style.border = "3px solid red";
    RoleErrro.innerText = "invalid value";
  } else {
    Role.style.border = "3px solid green";
    validationCount++;
  }

  if (mobileno.value == "" || mobileno.value == null) {
    mobileno.style.border = "3px solid red";
    merror.innerText = "invalid value";
  } else {
    phone = mobileno.value;
    if (phoneNumberValidator.test(phone)) {
      mobileno.style.border = "3px solid green";
      validationCount++;
    } else {
      mobileno.style.border = "3px solid red";
    }
  }

  if (email.value == "" || email.value == null) {
    email.style.border = "3px solid red";
    eerror.innerText = "invalid value";
  } else {
    email.style.border = "3px solid green";
    validationCount++;
  }

  if (gender.value == "Select" || gender.value == null) {
    gender.style.border = "3px solid red";
    gerror.innerText = "invalid value";
  } else {
    gender.style.border = "3px solid green";
    validationCount++;
  }

  if (landmark.value == "" || landmark.value == null) {
    landmark.style.border = "3px solid red";
    laerror.innerText = "invalid value";
  } else {
    landmark.style.border = "3px solid green";
    validationCount++;
  }

  if (area.value == "" || area.value == null) {
    area.style.border = "3px solid red";
    aerror.innerText = "invalid value";
  } else {
    area.style.border = "3px solid green";
    validationCount++;
  }

  if (district.value == "" || district.value == null) {
    district.style.border = "3px solid red";
    derror.innerText = "invalid value";
  } else {
    district.style.border = "3px solid green";
    validationCount++;
  }

  if (state.value == "" || state.value == null) {
    state.style.border = "3px solid red";
    serror.innerText = "invalid value";
  } else {
    state.style.border = "3px solid green";
    validationCount++;
  }
  alert("hello");

  if (county.value == "" || county.value == null) {
    county.style.border = "3px solid red";
    cerror.innerText = "invalid value";
  } else {
    county.style.border = "3px solid green";
    validationCount++;
  }
  if (password.value == "" || password.value == null) {
    password.style.border = "3px solid red";
    perror.innerText = "invalid value";
  } else {
    password.style.border = "3px solid green";
    validationCount++;
  }

  if (ConPassword.value == "" || ConPassword.value == null) {
    ConPassword.style.border = "3px solid red";
    conerror.innerText = "invalid value";
  } else {
    ConPassword.style.border = "3px solid green";
    validationCount++;
  }

  if (validationCount < 14) {
    e.preventDefault();
  }

  //  e.preventDefault()
});

function clear() {}
