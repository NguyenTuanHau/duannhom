app.controller("category-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.form = {};

	$scope.initalize = function() {
		$http.get("/rest/categories").then(resp => {
			$scope.items = resp.data;
		});
	}

	//NÚT LÀM MỚI
	$scope.reset = function() {
		$scope.form = {}
	}

	//NÚT SỬA
	$scope.edit = function(item) {
		$scope.form = angular.copy(item)
		$(".nav-tabs a:eq(0)").tab('show');
	}

	//NÚT THÊM MỚI
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/categories`, item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Thêm mới sản phẩm thành công");
		}).catch(error => {
			console.log("Error", error);
		})
	}

	//NÚT CẬP NHẬT
	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/categories/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(c => c.categoryid == item.id);
			$scope.items[index] = item;
			$scope.reset();
			alert("Cập nhật danh mục thành công");
			
		}).catch(error => {
			alert("Lỗi cập nhật danh mục");
			console.log("Error", error);
		})
	}
	// Upload hình
	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/img', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name;
		}).catch(error => {
			alert("Lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}

	//NÚT XÓA
	$scope.delete = function(item) {
		$http.delete(`/rest/categories/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(c => c.categoryid == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			alert("Xóa danh mục thành công");
		}).catch(error => {
			alert("Xoá thất bại");
			console.log("Error", error);
		})
	}

	//PHÂN TRANG
	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}
	$scope.initalize();
});