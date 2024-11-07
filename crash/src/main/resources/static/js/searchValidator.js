const form = document.getElementById("searchForm");
const searchBar = document.getElementById("searchBar");

form.addEventListener("submit", (e) => {
  if (searchBar.value === "" || searchBar.value == null) {
    e.preventDefault();
  }
});
