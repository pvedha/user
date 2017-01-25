var userIdsResponse = "";
var userIdsResponseReceived = false;
var validUserId = true;
$(document)
		.ready(
				function() {
					$('#viewLink').click(function() {
						$("#viewForm").show();
						$("#openForm").hide();
						$("#openResults").hide();
						$("#viewResults").hide();
					});
					$('#openLink').click(function() {
						$("#openForm").show();
						$("#viewForm").hide();
						$("#openResults").hide();
						$("#viewResults").hide();
					});
					$('#openButton')
							.click(
									function() {
										var name = $("#name").val();
										var phoneNumber = $("#phoneNumber")
												.val();
										var data = {
											name : name,
											phoneNumber : phoneNumber
										};
										$
												.ajax({
													url : 'http://localhost:8080/cmad/online/bank/account',
													type : 'post',
													contentType : 'application/json',
													success : function(response) {
														$("#openResults")
																.html(
																		"Your Account No: "
																				+ response);
														$("#openForm").hide();
														$("#openResults")
																.show();
													},
													data : JSON.stringify(data)
												});
									});
					$('#trythis-button')
							.click(function(){
                               var userIds = getUserIds();
                               alert(userIds);
                                alert(userIdsResponse);
                            }
				    );
                    
                    $('#trythis2-button')
							.click(function(){                               
                               //alert(userIdsResponseReceived + userIdsResponse);
                                console.log(userIdsResponse[2]);
                                console.log(userIdsResponse.length);
                            }
				    );
                    
                    $('#login-button').click( 
                        function() {
                        login();                        
                    });
                    
                    $('#showSignUp').click( 
                        function() {
                        getUserIds();
                        toggleSignform();
                    });
                    
                    $('#signup-button').click( 
                        function() {
                        addUser();
                    });
				});

function addUser() {
    if(!validUserId){
        alert("User Id taken. Please try another");
        return;
    }
    $("#signup-title").html("Creating User...");		
    var userid = $("#userid").val();
    var name = $("#username").val();
    var password = $("#password").val();
    var about = $("#about").val();
    var data = {
        userid : userid,
        name : name,
        password : password,
        about : about
    };
    $
            .ajax({
                url : 'http://localhost:8080/blog/blog/user/addUser',
                type : 'post',
                contentType : 'application/json',
                success : function(response) {
                    alert("success");
                    $("#signup-title")
                            .html("Thanks for signing. Please login.");														
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {                                                         
                    $("#signup-title")
                            .html("Sorry invalid details, please try again. " + textStatus + " : " + errorThrown);		
                },
//                                                    error : function(response) {
//                                                        alert("failed");
//														$("#signup-title")
//																.html(
//																		"Invalid details, Please try again" + response);													
//													},
                data : JSON.stringify(data),                                                    
            });
};

function getUserIds() {
    console.log("receiving user ids");
    $.ajax({
                url : 'http://localhost:8080/blog/blog/user/ids',
                type : 'get',
                accept : 'application/json',
        global: false,
    //async:false,
                success : function(response) {
                    //$("#viewForm").hide();
                    $("#result-div")
                            .html(
                                    response);
                    userIdsResponse = response;
                    userIdsResponseReceived = true;
                    console.log("Rsp 1" + response);
                    $("#result-div")
                            .show();
                    return response;
                }
            })
};

function login() {
    $("#login-message").html("Please wait, validating credentials...");
    $("#login-message").css({ 'color': 'green', 'font-size': '100%' });
    var userId = $("#loginId").val();
    var password = $("#loginPassword").val();
    $.ajax({
                
                url : 'http://localhost:8080/blog/blog/user/' + userId + '/' + password,
                type : 'get',
                accept : 'application/json',
                global: false,
                success : function(response) {
                    //$("#viewForm").hide();
                    console.log("Valid user");                    
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log("Invalid user credentials");
                }
            })
};

function sleep(milliseconds) {
  var start = new Date().getTime();
  for (var i = 0; i < 1e7; i++) {
    if ((new Date().getTime() - start) > milliseconds){
      break;
    }
  }
}

function validateUser(value){
    //console.log(value);
    if(userIdsResponseReceived){        
        for(i=0;i<userIdsResponse.length;i++)
            if(userIdsResponse[i].toLocaleLowerCase() === value.trim().toLocaleLowerCase()){
                console.log("UserId Exists " + value);
                $("#validUser").html("User ID " + value + " not available");
                $("#validUser").css({ 'color': 'red', 'font-size': '100%' });
                validUserId = false;
                return;
            } else {
                $("#validUser").html("User Id " + value + " available");                
                $("#validUser").css({ 'color': 'green', 'font-size': '100%' });
//                $("#validUser").removeClass("red-error");
//                $("#validUser").addClass("green-fine");
                validUserId = true;
            }        
    }
}

function toggleSignform(){
        $("#signup-form").toggle();
        $("#signin-form").toggle();
    } 