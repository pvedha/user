var userIdsResponse = "";
var userIdsResponseReceived = false;
var validUserId = true;
var currentUserId = "";
var url = 'http://' +  window.location.host;
var baseURL = url + "/blog/blog"; 
var readPostResponse;
var currentPostId = 0;
var currentPost;
var currentUserFavouriteList;
var userHasFavourites = false;

$(document)
		.ready(
				function() {
                    
                    $("#search-text").focusin(function(){
                        $("#search-text").animate({width: "350px"});
                    });
                    $("#search-text").focusout(function(){
                        $("#search-text").animate({width: "150px"});
                        $("#search-text").val("");
                    });
                    $("#search-text").keypress(function(event){	
                        var keycode = (event.keyCode ? event.keyCode : event.which);
                        if(keycode == '13'){
                            alert('Call the search function from here');	
                        }
                    });
                    
                    $('#trythis-button')
							.click(function(){
                                       retrieveCategory();                       
                            }
				    );
                    
                    $('#trythis2-button')
							.click(function(){                               
                                readAllPosts();
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
                                        
                    $('#newPost-submit-button').click( 
                        function() {
                        newPost();
                    });
                    $('#search-button').click( 
                            function() {
                            searchAllPosts();
                        });
                    $('#post-comment-button').click( 
                            function() {
                            addComment();
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
                url : baseURL + '/user/addUser',
                type : 'post',
                contentType : 'application/json',
                success : function(response) {
                    $("#signup-title")
                            .html("Thanks for signing. Please login.");		
                    toggleSignform();
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {                                                         
                    $("#signup-title")
                            .html("Sorry invalid details, please try again. " + textStatus + " : " + errorThrown);		
                },
                data : JSON.stringify(data),                                                    
            });
};

function getUserIds() {
    console.log("receiving user ids");
    $.ajax({
                url : baseURL + '/user/ids',
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


//Main Functionality after logging in. 
function login() {
    $("#loginMessage").html("Please wait, validating credentials...");
    $("#loginMessagee").css({ 'color': 'green', 'font-size': '100%' });
    var userId = $("#loginId").val();
    var password = $("#loginPassword").val();
    $.ajax({
                
        url : baseURL + '/user/' + userId + '/' + password,
        type : 'get',
        accept : 'application/json',
        global: false,
        success : function(response) {
            //$("#viewForm").hide();
            console.log("Valid user");
            //<img id='current-user-icon' src='img/48px-User_icon_2.svg.png'/> 
            //$("#current-user-icon").css("filter", "none");
            $("#current-user-icon").html("<img src='img/48px-User_icon_2.svg.png' class='img-normal'/>");
            $("#user-detail-div").html("<b>" + response.name + "</b><p><i>" + response.about);
            $("#user-detail-div").append("<a href='" + url + "'>Sign out</a>");
            currentUserId = response.userId;
            console.log("user id assigned" + currentUserId + "complete response "  + response);
            $("#loginPage").hide();
            $("#mainPage").show().fadeIn(50000);
            $("#mainPage").fadeIn(5000);
            readAllPosts();
            retrieveCategory();
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            console.log("Invalid user credentials");
            $("#loginMessage").html("Invalid crendentials, please try again");
            //$("#login-message").css({ 'color': 'green', 'font-size': '100%' });
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
                validUserId = true;
            }        
    }
}

function toggleSignform(){
        $("#signup-form").toggle();
        $("#signin-form").toggle();
    } 


function skipLogin(){
    $("#user-div").html("<br>User : debugger<p><i> A quick way to debug");
    $("#user-div").append("<a href='" + url + "'>Sign out</a>");
    currentUserId = "u";
    console.log("user id assigned" + currentUserId);
    $("#loginPage").hide();
    $("#mainPage").show().fadeIn(50000);
    $("#mainPage").fadeIn(5000);
    readAllPosts();
    retrieveCategory();
}