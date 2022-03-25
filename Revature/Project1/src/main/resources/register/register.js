// function that runs when register is clicked
document.getElementById("register-form").addEventListener("submit", async function(event){
    event.preventDefault(); //prevents default of form to refresh page

let usernameInputElem = document.getElementById("username");

let passwordInputElem = document.getElementById("password");

let firstNameInputElem = document.getElementById("firstname");

let lastNameInputElem = document.getElementById("lastname");

let emailInputElem = document.getElementById("email");

function setRole(e){
    document.getElementById("user_role").value;
    console.log("role changed to manager")
}

if(!setRole(e.target)){

let userNewE = {

    username: usernameInputElem.value,
    password: passwordInputElem.value,
    firstname: firstNameInputElem.value,
    lastname: lastNameInputElem.value,
    email: emailInputElem.value,
    user_role_id_fk: 0
}
let response = await fetch("http://localhost:9000/user",{
        method: "POST",
        body: JSON.stringify(userNewE) 
    })
      let  responseBody = await response.json();
    
    if(responseBody.success == false){
        let messageElem = document.getElementById("message");
        messageElem.innerText = responseBody.message
    }
       
    console.log(responseBody.data, responseBody.message)
   
}else{

    let userNewM = {

        username: usernameInputElem.value,
        password: passwordInputElem.value,
        firstname: firstNameInputElem.value,
        lastname: lastNameInputElem.value,
        email: emailInputElem.value,
        user_role_id_fk: 1
    }
    

    let response = await fetch("${domain}/user",{
        method: "POST",
        body: JSON.stringify(userNewM) 
    })       
     let  responseBody = await response.json();
    
    
    if(!responseBody.success){
        let messageElem = document.getElementById("message");
        messageElem.innerText = responseBody.message
    }
       
    console.log(responseBody.data, responseBody.message)
    
}    

});
