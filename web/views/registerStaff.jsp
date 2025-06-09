<%-- 
    Document   : registerStaff
    Created on : Jun 9, 2025, 12:03:14 AM
    Author     : Dung Thuy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký nhân viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow">
                <div class="card-body p-4">
                    <h3 class="card-title text-center mb-4">Đăng ký tài khoản nhân viên</h3>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <form method="post" action="../registerStaff" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label class="form-label">Họ và tên *</label>
                            <input type="text" name="fullName" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Email *</label>
                            <input type="email" name="email" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Mật khẩu *</label>
                            <input type="password" name="password" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Số điện thoại *</label>
                            <input type="text" name="phone" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Ảnh chân dung *</label>
                            <input type="file" name="portrait" accept="image/*" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">CCCD mặt trước *</label>
                            <input type="file" name="cccdFront" accept="image/*" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">CCCD mặt sau *</label>
                            <input type="file" name="cccdBack" accept="image/*" class="form-control" required>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Gửi yêu cầu</button>
                        </div>
                    </form>

                    <div class="text-center mt-3">
                        <a href="login.jsp">Quay lại đăng nhập</a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
