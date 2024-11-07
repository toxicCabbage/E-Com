var array;
var xd = [];

var term;

function getTheValues() {
  term = document.getElementById("searchBar").value;
  // alert(term);

  xd.length = 0;
  // alert("this is from autocomplete");
  productUrl = "http://localhost:8080/FlashDeal/search/" + term;
  // alert(productUrl);
  $.ajax({ method: "GET", url: productUrl }).done(function (responseJson) {
    array = responseJson;
    // xd = JSON.parse(responseJson);

    $.each(responseJson, function () {
      // alert(responseJson.join(" "));
    });

    array.forEach(function (item, index) {
      console.log(item, index);
      // test[index] = item[index];
      // console.log(test);
      xd.push(item);
    });

    for (var x of xd) {
      console.log(x);
    }
  });
}

$("#searchBar").autocomplete({
  source: xd,
});

// function logout() {
//   alert("Logout");
//   var LogoutURL = "http://localhost:8080/Home/search/logout";

//   $.ajax({ method: "POST", url: LogoutURL }).done(function () {
//     $.each(function () {
//       console.log("logout Successfully");
//     });
//   });
// }

// function logout() {
//   alert("logout");

//   // var link = /*[[@{/electronic}]] */ 'helo';
//   var link = "/logout";
//   // var Url = "http://localhost:8080/" + hei;
//   // window.location = Url;
//   alert(link);
//   window.location = link;

//   // const xhr = new XMLHttpRequest();

//   // xhr.open("POST", "http://localhost:8080/Home/search/logout", true);
//   // xhr.onprogress = function () {
//   //   console.log("on Progress");
//   // };
//   // xhr.onload = function () {
//   //   if (this.status === 200) {
//   //     console.log(this.responseText);
//   //     console.log("nothing left the show");
//   //   }
//   // };
// }
