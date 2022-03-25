let userId;

window.onload = function(){
    
    //this is used to retrieve query param
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
      });

    userId = params.userId;
    }


function chosenType(){

  let types = document.getElementsByName("type");
  for(var i =0; i < types.length; i++){
       let type =  Array.from(types).find(types => types.checked);
            return type.value 
  }
}


function showBtnC(e){
    e.preventDefault();
    var togg = document.getElementById('addReimb');
     togg.classList.toggle('hide');
     togg.classList.toggle('opacity');
}

function showBtnP(e){
    e.preventDefault();
    var togg = document.getElementById('all-rimb');
     togg.classList.toggle('hide');
     togg.classList.toggle('opacity');
}

function showBtn(e){
    e.preventDefault();
    var togg = document.getElementById('all-rimb');
     togg.classList.toggle('hide');
    
}

function showBtnO(e){
    e.preventDefault();
    
    var togg = document.getElementById('one-reimb');
     togg.classList.toggle('hide');
  
}
async function createReimb(e){
    e.preventDefault();

    let amountInputElem = document.getElementById("amount");

    

    let reimbData = {
        amount : amountInputElem.value,
        author_fk : userId,
        type_fk : chosenType()

    }
    console.log(reimbData);
   //send the http request
    let response = await fetch(`${domain}/newrequest`, {
        method: "POST",
        body: JSON.stringify(reimbData)
    })

    //retrieve the response body
    let responseBody = await response.json();


    //logic after response body

    console.log(responseBody)
    if(!responseBody.success){
        let messageElem = document.getElementById("message")
        messageElem.innerText = responseBody.message
}
}

async function getAllReimbs(e){
    e.preventDefault();
    
    let response = await fetch(`${domain}/${userId}/allpasttickets`);

    let responseBody = await response.json();

    console.log(responseBody)

    let listOfTickets = responseBody.data;

    listOfTickets.submitted = new Date(listOfTickets.submitted).toDateString();

    let table = document.getElementById("reimbs");

    listOfTickets.forEach(list => {
        
            let ticket = document.createElement("tr")
            ticket.classList.add("ticket-row")
        
           ticket.innerHTML = 
          `<tr>
            <td>${list.id}</td>
            <td>${list.amount}</td>
            <td>${list.submitted}</td>
            <td>${list.author_fk}</td>
            <td>${list.resolver_fk}</td>
            <td>${list.type}</td>
            <td>${list.status}</td>
            </tr> `;   
            table.appendChild(ticket);
        
        
    });
    
}


async function getOneReimb(e){
    e.preventDefault();

   let reimbIdElem = document.getElementById("one");

   let reimbId = reimbIdElem.value;
    
    let response = await fetch(`${domain}/${reimbId}/pastticket`);

    let responseBody = await response.json();

    let tickets = responseBody.data;
    
    let table = document.getElementById("reimbs");
    table.innerHTML = "";
     
    let header = document.createElement("thead")
    header.innerHTML =
            ` <thead><tr> <th>Id</th>         	<th>Amount</th>	    <th>Submit Time</th> <th>Author</th>	
            <th>Resolver</th>	<th>Type</th>	          <th>Status</th> </tr>
              </thead>`;

              table.appendChild(header);

    tickets.submitted = new Date(tickets.submitted).toDateString();

    
     
    if(tickets.author_fk != userId){
        console.log("The number provided does not belong to you");

        let message = ("The number provided does not belong to you");

        let messageElem = document.getElementById("message");
        messageElem.innerText = message;

    }else       
    
     ticket = document.createElement("tr")
    ticket.classList.add("ticket-row")

   ticket.innerHTML = 
  ` <tr>
    <td>${tickets.id}</td>
    <td>${tickets.amount}</td>
    <td>${tickets.submitted}</td>
    <td>${tickets.author_fk}</td>
    <td>${tickets.resolver_fk}</td>
    <td>${tickets.type}</td>
    <td>${tickets.status}</td>
    </tr>` ;   
    table.appendChild(ticket);





}