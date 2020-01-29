
$(document).ready(function () {
    $.post('userDetails', {
        email: document.cookie
    }, function (response) {

        var PlayerLoginInfo = JSON.parse(response);
        console.log(PlayerLoginInfo.role);
        if (PlayerLoginInfo.role == "Player")
        {
            player(PlayerLoginInfo);
            console.log(PlayerLoginInfo.role);
        }
        hi()
    });

});

function player(PlayerLoginInfo) {
    // window.location.replace("player.html");

    console.log(PlayerLoginInfo);
    const main = document.getElementById('main');
    console.log(main)

   const template=`
   
  <div id="navbar">
  <button class="openbtn" onclick="openNav()">&#9776; </button>  
      <a href="afterLogin.html"><i class="fa fa-fw fa-home"></i>Home</a>
      <a href="Points_table">Points Table</a>
      <a href="Schedule">Schedule</a>
      <a href="Notifiations">Notifications</a>
      <a href="Rule Book">Rule Book</a>
      <a href="playercontent.html">Participants</a>
  </div>
  <div id="mySidenav" class="sidenav">
      <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
      <a onclick="viewJoinTournament()"><i class="fa fa-fw fa-users"></i> Tournaments</a>
      <a onclick="displayPlayerCard()"><i class="fa fa-fw fa-user"></i> Profile</a>
      <a href="Contact"><i class="fa fa-fw fa-envelope"></i> Contact</a>
      <a onclick="logout()"><i class="fa fa-fw fa-info-circle"></i>&nbsp;Log Out</a>
  </div>`
  
   const profileCard=`<div id = "profileCard">
                                    <div id ="profileImage">
                                    </div>
                                    <div id="formDiv">

                                        <form>
                                            <label style="color:aliceblue"><b>Name:</b></label>&nbsp;&nbsp;&nbsp;
                                            <input id = "changeName" type="text" placeholder="${PlayerLoginInfo.name}" name="Name" disabled><br><br>
                                            <label style="color:aliceblue" ><b>Email:</b></label>&nbsp;&nbsp;&nbsp;
                                            <input id = "changeEmail" type="text" placeholder="${PlayerLoginInfo.email}" name="Email" disabled><br><br>
                                            <label style="color:aliceblue" ><b>Skill</b></label>&nbsp;&nbsp;&nbsp;
                                            <input id = "changeSkill" type="radio" ><input type="radio" ><input type="radio" ><input type="radio" ><input type="radio" ><br><br><br>
                                            <label style="color:aliceblue" ><b>Phone</b></label>&nbsp;&nbsp;&nbsp;
                                            <input id = "changePhone" type="text" placeholder="${PlayerLoginInfo.phone}" name="Phone" disabled>
                                        </form>
                                        <button type="button" onclick= "Edit()">Edit</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <button id ="submitBtn" type="button" onclick="editDetails()" disabled >Submit</button>
                                    </div>
                                </div>
                           <div id="openViewTournamentBox">

                               </div>`
        
        $("#main").append($.parseHTML(template))
        $("#Profile").append($.parseHTML(profileCard))
    
}


function openNav() {
    document.getElementById("mySidenav").style.width = "200px";
    document.getElementById("main").style.marginLeft = "200px";
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
    document.body.style.backgroundColor = "white";
}
var navbar;//----------------------
var sticky;//----------------------
window.onscroll = function() {myFunction(sticky)};

function hi(){
    navbar = document.getElementById("navbar");
    sticky = navbar.offsetTop;
}



function myFunction(sticky) {
    if (window.pageYOffset >= sticky) {
        navbar.classList.add("sticky")
    }
    else {
        navbar.classList.remove("sticky");
    }
}
function Edit(){
    if(document.cookie!=""){
    $( "#changeName" ).prop( "disabled", false );
     $( "#submitBtn" ).prop( "disabled", false );
    $( "#changeName" ).prop( "placeholder", "Enter Name" );
    $( "#changeEmail" ).prop( "disabled", false );
    $( "#changeEmail" ).prop( "required", true );
    $( "#changeEmail" ).prop( "placeholder","Enter Email" );
    $( "#changePhone" ).prop( "disabled", false );
    $( "#changeEmail" ).prop( "required", true );
    $( "#changePhone" ).prop( "placeholder", "Enter Phone" );
    $( "#changeName" ).focus()
}
else
{
alert("Login You *****")
window.location.replace("index.html");
}
}

function displayPlayerCard()
{
    $("#profileCard").show();
}
function hidePlayerCard()
{
    $("#profileCard").hide();
}
function logout(){
       $.ajax({
         type: "POST",
         url: "logout",
         success: function(editResponse) {
         console.log("Success");
         window.location.replace("index.html");
          },
              error: function(error) {
                     alert("InCorrect Credentials");
              }
    })
}
function editDetails(){
ck=document.cookie;
console.log(ck)
if(ck!=""){
var name = $('#changeName').val();
   var email = $('#changeEmail').val();
   var phone = $('#changePhone').val();

 var loginObj = {"Email" : email, "Name" : name, "Phone":phone,"id":document.cookie}
 console.log(loginObj)
   $.ajax({
     type: "POST",
     url: "editForm",
     data: loginObj,
     success: function(editResponse) {
     console.log("Success");
      },
          error: function(error) {
                 alert("InCorrect Credentials");
          }
})
}
else{
alert("Login First")
}
}

function viewJoinTournament(){
     $.ajax({
         type: "GET",
         url: "joinTournament",
         success: function(response) {
                           var resp = JSON.parse(response);
                           console.log(resp);
                           showTournament(resp);
                           },
         error: function(error)
         {alert("cannot view tournaments");}
        })

}
function joinTournament(resp){

//    var name = $('#tName').val();
    console.log(resp);
     var loginObj = {"Tournament" : resp,"Id":document.cookie}
         console.log(loginObj)
         $.ajax({
                  type: "POST",
                  url: "joinTournament",
                  data: loginObj,
                   error: function(error)
                           {alert("You have already registered in this tournament");}
                 })
}
function showTournament(resp){
for(var i=0;i< resp.length;i++){

    const  showTournament=` <div class="showTournaments">
                                        <div>
                                            <div id="tName">
                                                ${resp[i]}
                                                <button id="viewJoinTournamentBtm" onclick="joinTournament('${resp[i]}')">Join</button>
                                            </div>
                                        </div>
                                    </div>`
              $("#openViewTournamentBox").append($.parseHTML(showTournament))}

}
