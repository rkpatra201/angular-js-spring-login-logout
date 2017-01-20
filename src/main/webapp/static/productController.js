productApp.controller('productController', function($scope, $http) {

	/** get all products * */

	// get all data
	$http({
		method : 'GET',
		url : appRoot + 'admin-allproducts.do'
	}).then(successCallback, errorCallback);

	function successCallback(products, response) {
		// this callback will be called asynchronously
		// when the response is available
		console.log('success')
		$scope.products = products.data.productResponse;
		$scope.userName = products.data.userName;
		$scope.headerPage = 'pages/admin/header.html';
		$scope.topNav = 'pages/admin/topNavigationPage.html';
		$scope.sideNav = 'pages/admin/sidebarNavigation.html';
		$scope.allProductsPage = "pages/admin/showAllProduct.html";
		$scope.footerPage = 'pages/admin/footer.html';
		// console.log("all pages displayed");
	}

	function errorCallback(response) {
		// called asynchronously if an error occurs
		// or server returns response with an error status.
		console.log('error');
		console.log(response.status);
		if (response.status == '401')
			window.location.href = 'loginPage.do';
	}

	// end of get all data

	$scope.getAllData = function() {
		$http.get(appRoot + 'admin-' + 'allproducts.do').then(
				function(products) {
					console.log("get all data");
					$scope.products = products.data.productResponse;
					$scope.allProductsPage = "pages/admin/showAllProduct.html";
					console.log(products.data);
				});
	}
	$scope.sendData = function() {
		// use $.param jQuery function to serialize data from JSON
		console.log('inside sendData');
		var data = {
			productSlnum : this.productSlnum,
			productName : this.productName,
			productPrice : this.productPrice
		};

		var op = this.opcode;
		
		if (op == 'new') {
			console.log(op);
			// add-new
			$http.post('http://localhost:8090/grocery-app/order-manager', data).success(
					function(data, status) {
						$scope.PostDataResponse = data;
						//$scope.getAllData();
					}).error(
					function(data, status) {
						console.log('failed');
						$scope.ResponseDetails = "Data: " + data
								+ "<hr />status: " + status;
					});
		} else if (op == 'update') {
			console.log(op);
			// update
			var id=this.productSlnum;
			$http.post('admin-update-'+id+'-product.do', data).success(
					function(data, status) {
						$scope.PostDataResponse = data;
						//$scope.getAllData();
					}).error(
					function(data, status) {
						console.log('failed');
						$scope.ResponseDetails = "Data: " + data + "<hr />status: "
								+ status;
					});
		}

	};
	/** remove method * */
	$scope.removeProduct = function(idx, id) {
		// $http.remove("delete-"+id+"-product.rest").then(function(id){alert('deleted')});
		$http.get("admin-delete-" + id + "-product.do").success(
				function(status, headers) {
					$scope.getAllData();
				}).error(
				function(status, header, config) {
					$scope.ServerResponse = htmlDecode("status: " + status
							+ "\n\n\n\nheaders: " + header + "\n\n\n\nconfig: "
							+ config);
				});

	};

});