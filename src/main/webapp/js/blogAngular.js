var blogPostModule = angular.module("BlogPostApp", []);
blogPostModule.controller("BlogPostController", function($scope) {
$scope.post = {
	title:'',
	message: '',
	validate: function(){
		if($scope.contact.name == '' || $scope.contact.phoneNumber == '')
			return false;
		else
			return true;
	}
};
//$scope.error = '';
$scope.posts = [];
$scope.add = function(){
	//if($scope.contact.validate() == true){
		//$scope.error = '';
    	$scope.posts.push({
    		title: $scope.contact.name,
    		message: $scope.post.message
    	});
		$scope.addPost("Inside","addFunction");
		//$scope.contacts.push($scope.contact);
    	//$scope.contact.name = '';
    	//$scope.contact.phoneNumber = '';
	//}else{
		//$scope.error = 'Invalid Contact';
	//}
};

$scope.clear = function(){
    //alert('hello');
    $scope.posts = [];
};
    
$scope.addPost = function(titleIn, messageIn, postIdIn, response){
	//if($scope.contact.validate() == true){
		//$scope.error = '';
        //alert("Content received as " + titleIn + ".." + messageIn);
    	$scope.posts.push({
    		title: titleIn,
    		message: messageIn,
            postId: postIdIn
    	});
		
};    
    
    
$scope.addPosts = function(response){
    $scope.posts = [];
    for (i = 0; i < response.length; i++) {
		var post = response[i];		
        var postMessage = "";
		if (post.message.length > 200) {
			postMessage = post.message.substring(0, 200)
					+ ".....<a href='#' onClick=viewPost(" + response[i].postId
					+ ")> read more </a>";
		} else {
			postMessage = post.message;
		}       
        $scope.posts.push({
    		title: post.title,
    		message: postMessage,
            postId: post.postId,
            userName: post.userName,
            postedOn: post.postedOn,
            commentsCount: post.comments.length
    	});
	}
};

});
