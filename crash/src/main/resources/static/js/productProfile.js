/*<![CDATA[*/

// var username = /*[[${session.user.name}]]*/ 'Sebastian';

// alert("hello");

function redirect(hei) {
  // var link = /*[[@{/electronic}]] */ 'helo';
  var link = "Home/productProfile/" + hei;
  // var Url = "http://localhost:8080/" + hei;
  // window.location = Url;
  alert(link);
  window.location = link;
}

/*]]>*/
