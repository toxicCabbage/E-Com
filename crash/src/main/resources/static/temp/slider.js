
var img = document.createElement("img");
img.src = "../image/second.jpg";

var img2 = document.createElement("img");
img2.src = "../image/second.jpg";

var img3 = document.createElement("img");
img3.src = "../image/third.jpg";



var src = document.getElementsByClassName("slide");
var src = document.getElementById("box");
var box1 = document.getElementById("box1");
img.src = "../image/first.jpg";
src.appendChild(img);
src.appendChild(img2);
src.appendChild(img3);
// let pos = 0;

// const box = document.getElementById("box");
// let isAnimating = false;


function myMove() {
    let id = null;
    const elem = document.getElementById("box");
    let pos = 0;
    clearInterval(id);
    id = setInterval(frame, 5);
    async function frame() {
        if (pos == -200) {
            await new Promise(resolve => setTimeout(resolve, 3000)); // 3 secawait new Promise(resolve => setTimeout(resolve, 3000)); // 3 sec
            // clearInterval(id);
            // pos++;
            console.log("reset");
            elem.style.translate = 100 + "%";
        } else {
            // elem.style.backgroundColor = "green";
            console.log(pos);
            pos--;
            // elem.style.top = pos + 'px';
            // elem.style.marginLeft = pos + 'px';
            // elem.style.translate = pos + "px";
            elem.style.translate = pos + "%";
        }
    }
    // alert("called");
}

// function myMove(){
//     if (!isAnimating) {
//         isAnimating = true;
//         // img.style.transform = 'translate(-1900px)';
//         // img2.style.transform = 'translate(-1900px)';
//         box.style.marginLeft = -1920 + "px";
        
//         setTimeout(() => {
//            console.log("helo");
//            bpx.removeChild(img);

//         //    img2.style.transform = 'translate(0px)';
//         }, 3000);

//         isAnimating = false;
        
//     }
// }
// alert("hello");
// const b = document.getElementById("b");
// b.addEventListener('click', () => {
//     if (!isAnimating) {
//         isAnimating = true;
//         b.style.transform = 'translateX(200px)';
        
//         setTimeout(() => {
//             isAnimating = false;
//             b.style.transform = 'translateX(100%)';
//         }, 1000);
//     }
// });