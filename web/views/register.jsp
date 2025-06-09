<%-- 
    Document   : register
    Created on : Jun 8, 2025, 11:49:09 PM
    Author     : Dung Thuy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký tài khoản</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-7 col-lg-6">
            <div class="card shadow">
                <div class="card-body p-4">
                    <h3 class="card-title text-center mb-4">Đăng ký tài khoản</h3>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <form method="post" action="../register" enctype="multipart/form-data">
                        <!-- Họ tên -->
                        <div class="mb-3">
                            <label class="form-label">Họ và tên *</label>
                            <input type="text" name="fullName" class="form-control" required>
                        </div>

                        <!-- Email -->
                        <div class="mb-3">
                            <label class="form-label">Email *</label>
                            <input type="email" name="email" class="form-control" required>
                        </div>

                        <!-- Mật khẩu -->
                        <div class="mb-3">
                            <label class="form-label">Mật khẩu *</label>
                            <input type="password" name="password" class="form-control" required>
                        </div>

                        <!-- Số điện thoại -->
                        <div class="mb-3">
                            <label class="form-label">Số điện thoại *</label>
                            <input type="text" name="phone" class="form-control" required>
                        </div>

                        <!-- Vai trò -->
                        <div class="mb-3">
                            <label class="form-label">Loại tài khoản *</label><br>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" id="student" value="student" checked>
                                <label class="form-check-label" for="student">Sinh viên</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" id="landlord" value="landlord">
                                <label class="form-check-label" for="landlord">Chủ trọ</label>
                            </div>
                        </div>

                        <!-- Phần dành riêng cho chủ trọ -->
                        <div id="landlordFields" style="display: none;">
                            <!-- Upload CCCD -->
                            <div class="mb-3">
                                <label class="form-label">Tải lên CCCD (Ảnh/PDF) *</label>
                                <input type="file" name="cccd" class="form-control" accept=".jpg,.jpeg,.png,.pdf">
                            </div>

                            <!-- Liên kết tài khoản -->
                            <div class="mb-3 text-center">
                                <a href="../link-google" class="btn btn-outline-danger w-100 mb-2">Liên kết Google</a>
                                <a href="../link-facebook" class="btn btn-outline-primary w-100">Liên kết Facebook</a>
                            </div>
                        </div>

                        <!-- Nút đăng ký -->
                        <div class="d-grid mt-4">
                            <button type="submit" class="btn btn-success">Đăng ký</button>
                        </div>
                    </form>

                    <div class="text-center mt-3">
                        Đã có tài khoản? <a href="login.jsp">Đăng nhập</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- JS hiển thị phần Chủ trọ -->
<script>
    const landlordFields = document.getElementById("landlordFields");
    const roleRadios = document.querySelectorAll("input[name='role']");

    roleRadios.forEach(radio => {
        radio.addEventListener("change", function () {
            if (this.value === "landlord") {
                landlordFields.style.display = "block";
            } else {
                landlordFields.style.display = "none";
            }
        });
    });
</script>

</body>
</html>