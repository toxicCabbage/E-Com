// var flag = false;
// var bar1  = document.getElementById("bar").classList;
// var bar2  = document.getElementById("bar1").classList;
// var bar3  = document.getElementById("bar2").classList;
// var sidebar = document.getElementById("sb");
// var bm = document.getElementById("bm");
// var blur = document.getElementById("blur");
// var dropdown = document.getElementById("dropDown");
var slideInside = document.getElementsByClassName("inside-slide").classList;
var slides = document.getElementById("slides").classList;
let slideIndex = 1;
showSlides(slideIndex);

// Next/previous controls
function plusSlides(n) {
  showSlides(slideIndex += n);
}

// Thumbnail image controls
function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  let i;
  let slides = document.getElementsByClassName("mySilde");
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


var originMargin = -1900;
var originMargin1 = -3800;
var originMargin2 = -5700;

var tempm = "0 0 0 -3800";
var temp = "0 0 0 -1900";




slide();
// isAnimation();
 
// if(window.innerWidth < 767){


//     function trigger(){

    
//         if(flag==false){
//             burgeranimation();
//             sidebar.style.left ="0px";
//             bm.style.left = "320px"
//             blur.style.left = "300px"
//             blur.style.display = "block";
    
//         }
//         else{
//          if(flag==true){
//              closeBurgerAnimation();
//              sidebar.style.left ="-500px";
//              bm.style.left = "20px"
//              blur.style.left = "3000px"
//              blur.style.display = "none";
//          }
    
//         }
    
//     }
    
//     function burgeranimation(){
//         bar1.remove("closeone");
//         bar2.remove("closetwo")
//         bar3.remove("closethrie");
    
//         bar1.add("barone");
//         bar2.add("bartwo")
//         bar3.add("barthire");
    
//         flag = true;
    
//     }
    
//     function closeBurgerAnimation(){
        
//         bar1.remove("barone");
//         bar2.remove("bartwo")
//         bar3.remove("barthire");
    
//         bar1.add("closeone");
//         bar2.add("closetwo")
//         bar3.add("closethrie");
    
//         flag = false;
//     }
    
//     // Slide show container
    

// }
// else{


//     function trigger(){

    
//         if(flag==false){
//             burgeranimation();
//             sidebar.style.left ="0px";
//             bm.style.left = "320px"
//             blur.style.left = "300px"
//             blur.style.display = "block";
    
//         }
//         else{
//          if(flag==true){
//              closeBurgerAnimation();
//              sidebar.style.left ="-500px";
//              bm.style.left = "20px"
//              blur.style.left = "3000px"
//              blur.style.display = "none";
//          }
    
//         }
    
//     }
    
//     function burgeranimation(){
//         bar1.remove("closeone");
//         bar2.remove("closetwo")
//         bar3.remove("closethrie");
    
//         bar1.add("barone");
//         bar2.add("bartwo")
//         bar3.add("barthire");
    
//         flag = true;
    
//     }
    
//     function closeBurgerAnimation(){
        
//         bar1.remove("barone");
//         bar2.remove("bartwo")
//         bar3.remove("barthire");
    
//         bar1.add("closeone");
//         bar2.add("closetwo")
//         bar3.add("closethrie");
    
//         flag = false;
//     }
    
//     // Slide show container
    

// }


// function dropDown(){
  
//     // dropdown.add("active");
//     if(dropdown.style.display == "none"){
//         dropdown.style.display = "block";
//     }
//     else{
//         dropdown.style.display = "none";
//     }

// }





function slide(){
    // alert("hello");
    // slides.add("slideOne");
    // slides.remove("slideanimation");
    slides.remove("slideanimation1");

    slides.add("slideanimation");

    // slideInside.add("slideanimation");
  
    setTimeout(slideReset,2000);
}

function slideReset(){

    slides.remove("slideanimation");
    slides.add("slideanimation1");


    // slideInside.add("slideanimation");
  
    setTimeout(slide,2000);
}


var tt = 1;
var extra = -900;
var marginLEft = "marginLeft";
// gsap.to('.box',{duration: 2,marginLeft:extra ,ease:'bounce'})

// gsap.to('.insideSlide',{duration:2,marginLeft:extra,ease:'boucne'})
var imageCounter = 0;

function isAnimation(){

    if(imageCounter == 0){
        gsap.to('.insideSlide',{duration:2,marginRight:originMargin,delay:2,ease:'none'})
        imageCounter--;
    }
    if(imageCounter == 1){
        gsap.to('.insideSlide',{duration:2,marginLeft: originMargin1 ,delay:6,ease:'none'})
        imageCounter++;
    }
    if(imageCounter ==2){
        gsap.to('.insideSlide',{duration:2,marginLeft: originMargin2,delay:10,ease:'none'})
        imageCounter++;
    }
    if(imageCounter == 3){
        gsap.to('.insideSlide',{duration:0,margin:'0 0 0 0',delay:12,ease:'none'})
        imageCounter = 0;
    }
    if(imageCounter == -1){
        gsap.to('.insideSlide',{duration:2,marginRight:originMargin,delay:2,ease:'none'})
        imageCounter++;
    }


setTimeout(isAnimation,12000);

}
isAnimation();

