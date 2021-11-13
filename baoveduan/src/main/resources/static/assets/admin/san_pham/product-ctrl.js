app.controller("product-ctrl", function($scope, $http) {
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};

    $scope.initialize = function() {
            // load products
            $http.get("/rest/products").then(resp => {
                $scope.items = resp.data;
                $scope.items.forEach(item => {
                    item.createDate = new Date(item.createDate)
                })
            });
            // load categories
            $http.get("/rest/categories").then(resp => {
                $scope.cates = resp.data;
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
                image3: 'box.jpg',
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
            $http.post(`/rest/products`, item).then(resp => {
                resp.data.createDate = new Date(resp.data.createDate)
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
            $http.post(`/rest/products/${item.id}`, item).then(resp => {
                var index = $scope.items.findIndex(p => p.productid == item.id);
                $scope.items[index] = item;
                alert("Cập nhật sản phẩm thành công!");
            }).catch(error => {
                alert("Lỗi cập nhật sản phẩm");
                console.log("Error", error);
            });
        }
        // Xóa sp
    $scope.delete = function(item) {
            $http.delete(`/rest/products/${item.productid}`).then(resp => {
                var index = $scope.items.findIndex(p => p.productid == item.productid);
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

    // $scope.pager = {
    //     page: 0,
    //     size: 10,
    //     get items(){
    //         var start = this.page*this.size;
    //         return $scope.items.slice(start, start + this.size);
    //     },
    //     get count(){
    //     return Math.ceil(1.0 * $scope.items.length / this.size);
    // 	},
    //     first(){
    //         this.page = 0;
    //     },
    //     prev(){
    //         this.page--;
    //         if(this.page < 0){
    //             this.last;
    //         }
    //     },
    //     next(){
    //     	this.page++;
    //    		if(this.page >= this.count){
    //     	this.first();}
    // 	}, 
    //     last(){
    //         this.page = this.count - 1;
    //     }
    // }

    //Phân trang
    $scope.pagesizes = [5, 10, 15, 20];
    $scope.pagesize = $scope.pagesizes[0]; //jumlah baris dalam 1 halaman
    $scope.currentpage = 0; //lokasi halama saat ini
    $scope.pagenumber = Math.ceil($scope.items.length / $scope.pagesize) //jumlah total halaman

    $scope.paging = function(type) {
        if (type == 0 && $scope.currentpage > 0) {
            --$scope.currentpage;
        } else if (type == 1 && $scope.currentpage < $scope.pagenumber - 1) {
            ++$scope.currentpage;
        }
    }

    $scope.$watchCollection('items', function() {
        if ($scope.items == undefined) return;
        $scope.currentpage = 0;
        $scope.pagenumber = Math.ceil($scope.items.length / $scope.pagesize);
    });

    $scope.changeAction = function() {
        $scope.currentpage = 0;
        $scope.pagenumber = Math.ceil($scope.items.length / $scope.pagesize);
    }

    $scope.ordering = function(ordvar, by) {
        ordvar = !ordvar;
        $scope.ordstatus = ordvar;
        $scope.ord = by;
        return ordvar;

    }
});