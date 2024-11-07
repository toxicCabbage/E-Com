// alert("work");
let slideIndex = 1;
let pos =1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
    pos = n;
  showSlides(slideIndex = n);
}

function showSlides(n) {
  let i;
  let slides = document.getElementsByClassName("mySlides");
  let dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
}

// function myMove() {
    let id = null;
    // let pos = 1;
    clearInterval(id);
    id = setInterval(frame, 2000);
     function frame() {
        if (pos === 4) {
            pos = 1;
            console.log("reset");
        } 
            console.log(pos);
            console.log("reset");
            // showSlides(pos);
            currentSlide(pos);
            pos++;
    }
// }   

