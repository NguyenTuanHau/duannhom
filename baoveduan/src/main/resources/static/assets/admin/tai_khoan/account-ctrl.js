app.controller("account-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.form = {};


    $scope.initalize = function () {
        //load tài khoản
        $http.get("/rest/accounts").then(resp => {
            $scope.items = resp.data;
        });
    }

    //NÚT SỬA
    $scope.edit = function (item) {

        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show');
    }

    //NÚT RESET
    $scope.reset = function () {
        $scope.form = {
            image: 'user.png'
        };
    }

    //NÚT THÊM MỚI
    $scope.create = function () {
        var item = angular.copy($scope.form);
        $http.post(`/rest/accounts`, item).then(resp => {
            $scope.items.push(resp.data);
            $scope.reset();
            alert("Thêm mới sản phẩm thành công");
        }).catch(error => {
            alert("Lỗi thêm mới sản phẩm");
            console.log("Error", error);
        })
    }

    //NÚT XÓA
    $scope.delete = function (item) {
        $http.delete(`/rest/accounts/${item.username}`).then(resp => {
            var index = $scope.items.findIndex(ac => ac.username == item.username);
            $scope.items.splice(index, 1);
            $scope.reset();

        }).catch(error => {
            alert("Xoá thất bại");
            console.log("Error", error);
        })
    }

    //UPLOAD HÌNH ẢNH
    $scope.imageChanged = function (files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Lỗi upload hình ảnh");
            console.log("Error", error)
        })
    }
    //KHỜI ĐẦU
    $scope.initalize();
});