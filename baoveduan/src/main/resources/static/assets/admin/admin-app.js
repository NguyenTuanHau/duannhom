app = angular.module("admin-app", ["ngRoute"]);

app.config(function($routeProvider) {
	$routeProvider
		.when("/product", {
			templateUrl: "/assets/admin/san_pham/index.html",
			controller: "product-ctrl"
		})

		.when("/danhmuc", {
			templateUrl: "/assets/admin/danh_muc/index.html",
			controller: "category-ctrl"
		})
		.when("/bangdieukhien", {
			templateUrl: "/assets/admin/bang_dieu_khien/bang_dieukhien.html",
			controller: "product-ctrl"
		})
		.when("/bieudo", {
			templateUrl: "/assets/admin/bieu_do/charts.html",
			controller: "product-ctrl"
		})
		.when("/lich", {
			templateUrl: "/assets/admin/lich/lich.html",
			controller: ""
		})
		.when("/bohinhanh", {
			templateUrl: "/assets/admin/bo_hinh_anh/bo_hinh_anh.html",
			controller: "product-ctrl"
		})
		.when("/tintuc", {
			templateUrl: "/assets/admin/tin_tuc/index.html",
			controller: "tintuc-ctrl"
		})
		.when("/taikhoan", {
			templateUrl: "/assets/admin/tai_khoan/index.html",
			controller: "account-ctrl"
		})
		.when("/order", {
			templateUrl: "/assets/admin/order/index.html",
			controller: "order-ctrl"
		})
		.when("/hosocanhan", {
			templateUrl: "/assets/admin/ho_so/index.html",
			controller: "profile-ctrl"
		})
		.when("/authorize", {
			templateUrl: "/assets/admin/authority/index.html",
			controller: "authority-ctrl"
		})
		.when("/unauthorized", {
			templateUrl: "/assets/admin/authority/unauthorized.html",
			controller: "authority-ctrl"
		})
		.when("/error-403", {
			templateUrl: "/assets/admin/loi/error-403.html",
			controller: ""
		})
		.when("/error-404", {
			templateUrl: "/assets/admin/loi/error-404.html",
			controller: ""
		})
		.when("/error-405", {
			templateUrl: "/assets/admin/loi/error-405.html",
			controller: ""
		})
		.when("/error-500", {
			templateUrl: "/assets/admin/loi/error-500.html",
			controller: ""
		})
		.otherwise({
			template: "<h1 class='text-center'>Trung tâm quản lý OGANI</h1>"
		});
})