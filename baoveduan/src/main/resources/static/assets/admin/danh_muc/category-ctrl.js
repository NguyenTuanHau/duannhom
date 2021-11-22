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
        $scope.form = {
        	image: 'box.jpg',
        }
    }
    $scope.reset();

    //NÚT SỬA
    $scope.edit = function(item) {
        $scope.form = angular.copy(item)
        $(".nav-tabs a:eq(0)").tab('show');
    }

    $scope.initalize();

    //NÚT THÊM MỚI
    $scope.create = function() {
        var item = angular.copy($scope.form);
        $http.post(`/rest/categories`, item).then(resp => {
            $scope.items.push(resp.data);
            // Create
            add_success = function(type) {
                'use strict';
                if (type === 'success-message') {
                    swal({
                        title: 'Thêm mới thành công!',
                        type: 'success',
                        button: {
                            text: "Continue",
                            value: true,
                            visible: true,
                            className: "btn btn-primary"
                        }
                    })
                } else {
                    swal("Error occured !");
                }
            }
            $scope.reset();
            $scope.initalize();
        }).catch(error => {
            swal({
                title: 'Thêm mới thất bại!',
                button: {
                    text: "Continue",
                    value: true,
                    visible: true,
                    className: "btn btn-primary"
                }
            })
            console.log("Error", error);
        })
    }

    //NÚT CẬP NHẬT
    $scope.update = function() {
            var item = angular.copy($scope.form);
            $http.put(`/rest/categories/${item.categoryid}`, item).then(resp => {
                var index = $scope.items.findIndex(c => c.categoryid == item.categoryid);
                $scope.items[index] = item;
                // Update
                update_success = function(type) {
                    'use strict';
                    if (type === 'success-message') {
                        swal({
                            title: 'Cập nhật thành công!',
                            type: 'success',
                            button: {
                                text: "Continue",
                                value: true,
                                visible: true,
                                className: "btn btn-primary"
                            }
                        })
                    } else {
                        swal("Error occured !");
                    }
                    $scope.reset();
                }
                $scope.reset();
                $scope.initalize();

            }).catch(error => {
                swal({
                    title: 'Cập nhật thất bại!',
                    button: {
                        text: "Continue",
                        value: true,
                        visible: true,
                        className: "btn btn-primary"
                    }
                })
                console.log("Error", error);
            })
        }
        // Upload hình
    $scope.imageChanged = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            swal({
                title: 'Up load hình lỗi!',
                button: {
                    text: "Continue",
                    value: true,
                    visible: true,
                    className: "btn btn-primary"
                }
            })
            console.log("Error", error);
        })
    }

    //NÚT XÓA
    $scope.delete = function(item) {
        $http.delete(`/rest/categories/${item.categoryid}`).then(resp => {
            var index = $scope.items.findIndex(c => c.categoryid == item.categoryid);
            $scope.items.splice(index, 1);
            // Delete
            dele_success = function(type) {
                'use strict';
                if (type === 'success-message') {
                    swal({
                        title: 'Xóa thành công!',
                        type: 'success',
                        button: {
                            text: "Continue",
                            value: true,
                            visible: true,
                            className: "btn btn-primary"
                        }
                    })
                } else {
                    swal("Error occured !");
                }
            }
            $scope.reset();
            $scope.initalize();
        }).catch(error => {
            swal({
                title: 'Xóa thất bại!',
                button: {
                    text: "Continue",
                    value: true,
                    visible: true,
                    className: "btn btn-primary"
                }
            })
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

});