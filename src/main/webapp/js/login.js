//$(document).ready(function(){
//$("#loginbtn").on("click", function(){
//console.log("hi");
//var email = $('#Email').val();
//var password = $('#pwd').val();
//$.get('log', {
//Email : email,
//pwd : password
//}, function(responseText) {
//if(responseText=="Success")
//window.location.replace("loginplayer.html");
//else
//alert(responseText);
//});
//});


$(document).ready(function(){
  $("#loginBtn").on("click",function(){

   var email = $('#Email').val();
   var password = $('#pwd').val();

   var loginObj = {"Email" : email, "pwd" : password}
   $.ajax({
     type: "POST",
     url: "login",
     data: loginObj,
     success: function(response) {
     var resp = JSON.parse(response);
        if(resp.role=="Player")
          window.location.replace("afterLogin.html");
        else if(resp.role=="TeamManager")
          window.location.replace("manager.html");

     },
     error: function(error) {
            alert("InCorrect Credentials");
     }
   });
   })
   })
