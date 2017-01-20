productApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/showProduct/:productId', {
		templateUrl : 'pages/admin/productById.html',
		controller : 'ShowAProductController'
	}).when('/editProduct/:productId', {
		templateUrl : 'pages/admin/editProductById.html',
		controller : 'EditProductController'
	}).when('/addNewProduct', {
		templateUrl : 'pages/admin/editProductById.html',
		controller : 'AddProductController'
	}).when('/', {
		templateUrl : 'pages/admin/showAllProduct.html',
		controller : 'productController'
	}).when('/add5Product', {
		templateUrl : 'pages/admin/success.html',
		controller : 'add5ProductController'
	}).otherwise({
		redirectTo : '/'
	});
} ]);

productApp.controller('ShowAProductController', function($scope, $http,
		$routeParams) {
	var id = $routeParams.productId;
	$scope.productId = id;

	/** get product by id * */
	$http.get(appRoot + "admin-product-" + id + "-show.do").then(
			function(product) {
				$scope.productSlnum = product.data.productSlnum;
				$scope.productName = product.data.productName;
				$scope.productPrice = product.data.productPrice;
			});

});

productApp.controller('add5ProductController', function($scope, $http,
		$routeParams) {

	$scope.message = 'Failed to add products';
	/** get product by id * */
	$http.get(appRoot + "admin-add-five-product.do").then(function() {
		$scope.message = '5 products added';
	});

});

productApp.controller('EditProductController', function($scope, $http,
		$routeParams) {
	var id = $routeParams.productId;
	$scope.productId = id;
	$scope.message = 'Displaying Product for';
	$scope.opcode = "update";
	/** get product by id * */
	$http.get(appRoot + "admin-product-" + id + "-show.do").then(
			function(product) {
				$scope.productSlnum = product.data.productSlnum;
				$scope.productName = product.data.productName;
				$scope.productPrice = product.data.productPrice;
			});

});

productApp.controller('AddProductController', function($scope) {
	$scope.message = 'Add new Product';
	$scope.opcode = "new";
	$scope.productSlnum = '';
	$scope.productName = '';
	$scope.productPrice = '';
});
