
function newPost(){
    
    $("#new-post-info").html("Please wait, submitting post...");
    $("#new-post-info").css({ 'color': 'green', 'font-size': '100%' });
    var userId = currentUserId;
    console.log("Current User" + currentUserId);
    userId = "bloguser";
    var title = $("#new-post-title").val();
    var message = $("#new-post-message").val();
    var tags = $("#new-post-tags").val();
    var category = $("#new-post-category").val();
    console.log(userId + title + message + tags + category);
    var data = {
        userId : userId,
        title : title,
        message : message,
        tags: tags,
        category : category
    };
    $.ajax({
                
                url : 'http://localhost:8080/blog/blog/post/newPost',
                type : 'post',
                contentType : 'application/json',
                global: false,
                success : function(response) {
                    //$("#viewForm").hide();
                    //displayPosts(response);
                    $("#post-div").fadeOut(5000);
                    $("#new-post-div").fadeIn(5000);
                    readAllPosts();
                    
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log("Error submitting the post");
                    $("#new-post-info").html("Error submitting the post, please try again");
                    //$("#login-message").css({ 'color': 'green', 'font-size': '100%' });
                },
                data : JSON.stringify(data)
            })    
}


function readAllPosts(){
    
    $("#post-info").html("Please wait, loading posts...");
    $("#post-info").css({ 'color': 'green', 'font-size': '100%' });
    $.ajax({
                
                url : 'http://localhost:8080/blog/blog/post/all',
                type : 'get',
                accept : 'application/json',
                global: false,
                success : function(response) {
                    //$("#viewForm").hide();
                    displayPosts(response);
                    
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log("Error Loading the posts read");
                    $("#post-info").html("Error Loading the posts, please try again");
                    //$("#login-message").css({ 'color': 'green', 'font-size': '100%' });
                }
            })    
}

function searchAllPosts(searchString){
    
    $("#post-info").html("Please wait, loading posts...");
    $("#post-info").css({ 'color': 'green', 'font-size': '100%' });
    $.ajax({
                
                url : 'http://localhost:8080/blog/blog/post/all/' + searchString,
                type : 'get',
                accept : 'application/json',
                global: false,
                success : function(response) {
                    //$("#viewForm").hide();
                    displayPosts(response);
                    
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log("Error Loading the posts search");
                    $("#post-info").html("Error Loading the posts, please try again");
                    //$("#login-message").css({ 'color': 'green', 'font-size': '100%' });
                }
            })    
}


function displayPosts(response){
    console.log("Reading posts done.");
    $("#post-contents").html(""); 
    var htmlContent = "";
    for(i=0;i<response.length;i++){
        var post = response[i];
        htmlContent += "<p class='post-title'> " + post.title + "</p>";
        htmlContent += "<p class='post-message'> ";
        if(post.message.length > 200){
            htmlContent += post.message.substring(0,200) + ".....<a href='#'> read more </a>"; 
        } else {
            htmlContent += post.message; 
        }

        htmlContent += "</p><p class='post-detail'>By : <b>" + post.postedBy + "</b> ,<span>   </span> On : " 
            + post.posted_on +", " + post.comments.length + " Comments</p>";



        htmlContent += "<hr style='height:0.5px; margin: 10px 0 10px 0' color=white >"
    }
    $("#post-contents").append(htmlContent);

    $("#post-info").html("");
}

function showNewPost(){
    $("#post-div").fadeOut(1000);
    $("#new-post-div").fadeIn(1000);
}