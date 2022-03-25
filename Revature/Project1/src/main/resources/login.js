// function that runs when login is clicked
document.getElementById("login-form").addEventListener("submit", async function login(event){
    event.preventDefault(); //prevents default of form to refresh page


let usernameInputElem = document.getElementById("username");

let passwordInputElem = document.getElementById("password");

let userCred = {
    username: usernameInputElem.value,
    password: passwordInputElem.value
}

let response = await fetch(`${domain}/login`,{
    method: "POST",
    body: JSON.stringify(userCred)
})

let responseBody = await response.json();

if(responseBody.success == false){
    let messageElem = document.getElementById("message");
    messageElem.innerText = responseBody.message
}else{
    if(responseBody.data.user_role == "Finance Manager"){
        window.location = `./dashboard/Manager/?userId=${responseBody.data.id}`

        }else
        window.location = `./dashboard/Employee/?userId=${responseBody.data.id}`
        console.log(responseBody.data);
        //window.location = "./dashboard?userId=" + responseBody.data.id
}

});