app.controller("tintuc-ctrl", function($scope, $http) {
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};

    $scope.initialize = function() {
            // load tintuc
            $http.get("/rest/blogs").then(resp => {
                $scope.items = resp.data;
                $scope.items.forEach(item => {
                    item.createDate = new Date(item.createDate)
                })
            });

        }
        // Khởi đầu
    $scope.initialize();

    // Xóa form
    $scope.reset = function() {
            $scope.form = {
                createDate: new Date(),
                image: 'box.jpg',
                image1: 'box.jpg',
                image2: 'box.jpg',
                status: true,
            }
        }
        // Show lên form
    $scope.edit = function(item) {
            $scope.form = angular.copy(item);
            $(".nav-tabs a:eq(0)").tab('show')
        }
        // Thêm sp
    $scope.create = function() {
            var item = angular.copy($scope.form);
            $http.post(`/rest/blogs`, item).then(resp => {
                resp.data.createdate = new Date(resp.data.createdate)
                $scope.items.push(resp.data);
                $scope.reset();
                alert("Thêm sản phẩm thành công!");
                $scope.form.image = resp.data.name;
            }).catch(error => {
                alert("Lỗi thêm sản phẩm");
                console.log("Error", error);
            });
        }
        // Cập nhật sp
    $scope.update = function() {
            var item = angular.copy($scope.form);
            $http.put(`/rest/blogs/${item.id}`, item).then(resp => {
                var index = $scope.items.findIndex(blg => blg.id == item.id);
                $scope.items[index] = item;
                alert("Cập nhật sản phẩm thành công!");
            }).catch(error => {
                alert("Lỗi cập nhật sản phẩm");
                console.log("Error", error);
            });
        }
        // Xóa sp
    $scope.delete = function(item) {
            $http.delete(`/rest/blogs/${item.id}`).then(resp => {
                var index = $scope.items.findIndex(blg => blg.id == item.id);
                $scope.items.splice(index, 1);
                $scope.reset();
                alert("Xóa sản phẩm thành công!");
            }).catch(error => {
                alert("Lỗi xóa sản phẩm");
                console.log("Error", error);
            });
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

    $scope.pager = {
        page: 0,
        size: 3,
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
});