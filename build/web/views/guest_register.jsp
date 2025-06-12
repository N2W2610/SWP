<%-- 
    Document   : guest_register
    Created on : Jun 12, 2025, 9:07:08 PM
    Author     : Dung Thuy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Đăng Ký Tài Khoản</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Đăng Ký Tài Khoản Khách</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <form action="/guest-register" method="post" class="row g-3">
            <div class="col-md-12">
                <label for="fullName" class="form-label">Họ và Tên</label>
                <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Nhập họ và tên" required>
            </div>
            <div class="col-md-12">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email" required>
            </div>
            <div class="col-md-12">
                <label for="password" class="form-label">Mật Khẩu</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu" required>
            </div>
            <div class="col-md-12">
                <label for="phone" class="form-label">Số Điện Thoại</label>
                <input type="text" class="form-control" id="phone" name="phone" placeholder="Nhập số điện thoại">
            </div>
            <div class="col-12 text-center">
                <button type="submit" class="btn btn-primary">Đăng Ký</button>
            </div>
        </form>
        <div class="mt-3 text-center">
            <p>Đã có tài khoản? <a href="/views/guest_login.jsp">Đăng nhập ngay</a></p>
        </div>
    </div>
</body>
</html>