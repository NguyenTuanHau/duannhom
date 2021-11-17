app.controller("account-ctrl", function($scope,$http, $location){
    $scope.roles =[];
    $scope.admins = [];
    $scope.authorities = [];
    $scope.form = {};

    $scope.initialize = function(){
        // load tất cả roles
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        })

        // load nhân viên và admin (administrators)
        $http.get("/rest/accounts").then(resp => {
            $scope.admins = resp.data;
        })

        // load authorities được cấp cho staff và directors
        $http.get("/rest/authorities?admin=true").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            $location.path("/unauthorized");
        })
    }

	//So sánh username và role để check lên
    $scope.authority_of = function(acc, role){
        if($scope.authorities){
            return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id==role.id);
        }
    }

	//Cấp quyền
    $scope.authority_changed = function(acc, role){
        var authority = $scope.authority_of(acc, role); //đi tìm acc, role
        if(authority){// đã cấp quyền => thu hồi quyền (xóa)
            $scope.revoke_authority(authority);
        }else{// chưa đc cấp quyền => cấp thêm quyền mới
            authority = {account: acc, role: role};
            $scope.grant_authority(authority);
        }
    }

    // Thêm mới authority
    $scope.grant_authority = function(authority){
        $http.post(`/rest/authorities`, authority).then(resp => {
            $scope.authorities.push(resp.data)
            alert("Cấp quyền sử dụng thành công");
        }).catch(error =>{
            alert("Cấp quyền sử dụng thất bại")
            console.log("Error", error);
        })
    }

     // Xóa authority
     $scope.revoke_authority = function(authority){
        $http.delete(`/rest/authorities/${authority.id}`).then(resp => {
           var index = $scope.authorities.findIndex(a => a.id == authority.id);
           $scope.authorities.splice(index, 1);
            alert("Thu hồi quyền sử dụng thành công");
        }).catch(error =>{
            alert("Thu hồi quyền sử dụng thất bại")
            console.log("Error", error);
        })
    }

    $scope.initialize();
    
    // Show lên form
    $scope.edit = function (acc) {
        $scope.form = angular.copy(acc);
        $(".nav-tabs a:eq(0)").tab('show')
    }
    
    // Reset form
  $scope.reset = function() {
		$scope.form = {}
	}
	
	//NÚT THÊM MỚI
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/accounts`, item).then(resp => {
			$scope.admins.push(resp.data);
			$scope.reset();
			alert("Thêm mới tài khoản thành công");
		}).catch(error => {
			console.log("Error", error);
		})
	}
    
    $scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/accounts/${item.username}`, item).then(resp => {
			var index = $scope.admins.findIndex(c => c.username == item.username);
			$scope.admins[index] = item;
			alert("Cập nhật danh mục thành công");
			
		}).catch(error => {
			alert("Lỗi cập nhật danh mục");
			console.log("Error", error);
		})
	}
	
	//NÚT XÓA
	$scope.delete = function(item) {
		$http.delete(`/rest/accounts/${item.username}`).then(resp => {
			var index = $scope.admins.findIndex(c => c.username == item.username);
			$scope.admins.splice(index, 1);
			$scope.reset();
			alert("Xóa danh mục thành công");
		}).catch(error => {
			alert("Xoá thất bại");
			console.log("Error", error);
		})
	}
});