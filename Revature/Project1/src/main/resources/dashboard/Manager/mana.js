let user_role;
let userId;

window.onload = function(e){
    e.preventDefault();
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),

       
      });

    userId = params.userId;

    }

    async function getAllReimbs(e){
        e.preventDefault();
        
        let user_role_id_fk = 1;

        let response = await fetch(`${domain}/${user_role_id_fk}/viewAllSubmissions`);
    
        let responseBody = await response.json();
    
        console.log(responseBody)
    
        let listOfTickets = responseBody.data;

        listOfTickets.submitted = new Date(listOfTickets.submitted).toDateString();
        
        let table = document.getElementById("reimbs");
        table.innerHTML = "";

        let header = document.createElement("thead")
        header.innerHTML =
                ` <thead><tr> <th>Id</th>         	<th>Amount</th>	    <th>Submit Time</th> <th>Author</th>	
                <th>Resolver</th>	<th>Type</th>	          <th>Status</th> </tr>
                  </thead>`;
                  table.appendChild(header);
    
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
                </tr>`;
                
               if(list.status == "Pending"){
                ticket.innerHTML = 
              `  
              <tr>
                <td>${list.id}</td>
                <td>${list.amount}</td>
                <td>${list.submitted}</td>
                <td>${list.author_fk}</td>
                <td>${list.resolver_fk}</td>
                <td>${list.type}</td>
                <td>${list.status}</td>
                </tr>

                <form >
                <input type="number" class="hide" id="unprocessedReimb" value="${list.id}">
              </form>
                  <button id="vone-btn" onclick=" updateReimbA(event)">Approve</button>
                  <button id="vpast-btn" onclick=" updateReimbD(event)">Deny</button>`
               
              
              ;   
               }

                table.appendChild(ticket);
            
                
        });
    }
    

  async function updateReimbA(e){
    e.preventDefault();

      let reimbToProcessElem = document.getElementById("unprocessedReimb");

      let info = {
        author_fk: userId,
        id : reimbToProcessElem.value
      };

      let response = await fetch(`${domain}/${userId}/approve`, {
        method: "POST",
        body: JSON.stringify(info)
    });

      let responseBody = await response.json;

      console.log(responseBody);



  }

  async function updateReimbD(e){
    e.preventDefault();

      let reimbToProcessElem = document.getElementById("unprocessedReimb");

      let info = {
        author_fk: userId,
        id : reimbToProcessElem.value
      };

      let response = await fetch(`${domain}/${userId}/deny`, {
        method: "POST",
        body: JSON.stringify(info)
    });

      let responseBody = await response.json;

      console.log(responseBody);



  }

  async function sortS(e){
       e.preventDefault();

        let author_fk = userId;

      let response = await fetch(`${domain}/${author_fk}/${userId}/allReimbursementsOrdered`);

     
      let responseBody = await response.json();
    
      console.log(responseBody)
  
      let listOfTickets = responseBody.data;

      listOfTickets.submitted = new Date(listOfTickets.submitted).toDateString();
      
      let table = document.getElementById("reimbs");
      table.innerHTML = "";

      let header = document.createElement("thead")
      header.innerHTML =
              ` <thead><tr> <th>Id</th>         	<th>Amount</th>	    <th>Submit Time</th> <th>Author</th>	
              <th>Resolver</th>	<th>Type</th>	          <th>Status</th> </tr>
                </thead>`;

                table.appendChild(header);
  
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
              </tr>`;
              
             if(list.status == "Pending"){
              ticket.innerHTML = 
            `  
            <tr>
              <td>${list.id}</td>
              <td>${list.amount}</td>
              <td>${list.submitted}</td>
              <td>${list.author_fk}</td>
              <td>${list.resolver_fk}</td>
              <td>${list.type}</td>
              <td>${list.status}</td>
              </tr>

              <form >
              <input type="number" class="hide" id="unprocessedReimb" value="${list.id}">
            </form>
                <button id="vone-btn" onclick=" updateReimbA(event)">Approve</button>
                <button id="vpast-btn" onclick=" updateReimbD(event)">Deny</button>`
             
            
            ;   
             }

              table.appendChild(ticket);
            
            });
          }

          async function sortI(e){
            e.preventDefault();
     
             let author_fk = userId;
     
           let response = await fetch(`${domain}/${author_fk}/${userId}/allReimbursementsIdOrdered`);
     
          
           let responseBody = await response.json();
         
           console.log(responseBody)
       
           let listOfTickets = responseBody.data;

           listOfTickets.submitted = new Date(listOfTickets.submitted).toDateString();
           
           let table = document.getElementById("reimbs");
            table.innerHTML = "";
     
           let header = document.createElement("thead")
           header.innerHTML =
                   ` <thead><tr> <th>Id</th>         	<th>Amount</th>	    <th>Submit Time</th> <th>Author</th>	
                   <th>Resolver</th>	<th>Type</th>	          <th>Status</th> </tr>
                     </thead>`;
     
                     table.appendChild(header);
       
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
                   </tr>`;
                   
                  if(list.status == "Pending"){
                   ticket.innerHTML = 
                 `  
                 <tr>
                   <td>${list.id}</td>
                   <td>${list.amount}</td>
                   <td>${list.submitted}</td>
                   <td>${list.author_fk}</td>
                   <td>${list.resolver_fk}</td>
                   <td>${list.type}</td>
                   <td>${list.status}</td>
                   </tr>
     
                   <form >
                   <input type="number" class="hide" id="unprocessedReimb" value="${list.id}">
                 </form>
                     <button id="vone-btn" onclick=" updateReimbA(event)">Approve</button>
                     <button id="vpast-btn" onclick=" updateReimbD(event)">Deny</button>`
                  
                 
                 ;   
                  }
     
                   table.appendChild(ticket);
                 
                 });
               }
