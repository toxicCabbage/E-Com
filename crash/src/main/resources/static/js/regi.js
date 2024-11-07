
// To Do add password validator 
// Email Validator


var phoneNumber = document.getElementById("mobileNumber").value;
var phone  = phoneNumber.toString();
var phoneNumberValidator = /^\d{10}$/;
var validationCounter = 0;
var nameCounter = 0;

// Error span 
// var el = document.createElement("span");
// el.innerHTML = "hello"

function formValidator(){
  
    const parentsForm = document.querySelector(".fieldaWrap")
    const formChild = Array.from(parentsForm.children)

    let label = document.createElement('label');
    label.textContent = "kya hai ye";
    formChild.forEach(cssChange)

    alert(validationCounter);
    if(validationCounter <12){
        validationCounter = 0;
        return false;
        
    }
    else{
        return true;
    }

}

function cssChange(element){
    
    //  alert(element.tagName)
     
    if(element.type =='text'){  
       if(element.value.trim()==""){

           element.style.borderColor =  "red"
        //    var ename = element.innerHTML;
        //    Creator(element);
       }
       else{
        
           element.style.borderColor = "green"
           validationCounter++;
       }
    }

    if(element.innerText == 'Mobile Number :'){
              
          if(phoneNumberValidator.test(phone)){
             alert("ture");
            
          }
          else{
             
            alert("faaalse");
         }
    }
 
  
    if(element.type == 'password'){
        alert("password")
        validationCounter++;
    }
    if(element.tagName =='SELECT'){
        // alert(element.options[element.selectedIndex].value)
        // alert("hello")
       if(element.options[element.selectedIndex].value=='Select') {
            element.style.borderColor = "red"
       }
       else{
           element.style.borderColor = "green"
           validationCounter++;
        }
    }
}

function insertAfter(referenceNode, newNode){
    referenceNode.parentNode.insertBefore(newNode,referenceNode);
}

function Creator(element){
    
   alert("top")
    var elename = 'el'+ nameCounter;
    alert("mid")

    var elename = document.createElement("span");
    alert("end")

    elename.innerText = "hello"
    alert("bottom")

    insertAfter(element,elename)

}