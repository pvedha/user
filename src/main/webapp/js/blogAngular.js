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

$scope.tryMe = function(){
    alert('hello');
};
    
$scope.addPost = function(titleIn, messageIn, postIdIn){
	//if($scope.contact.validate() == true){
		//$scope.error = '';
        //alert("Content received as " + titleIn + ".." + messageIn);
    	$scope.posts.push({
    		title: titleIn,
    		message: messageIn,
            postId: postIdIn
    	});
		
		//$scope.contacts.push($scope.contact);
    	//$scope.contact.name = '';
    	//$scope.contact.phoneNumber = '';
	//}else{
		//$scope.error = 'Invalid Contact';
	//}
};    

});
