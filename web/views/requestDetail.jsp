<%-- 
    Document   : RequestDetail
    Created on : Jun 9, 2025, 12:21:56 AM
    Author     : Dung Thuy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết yêu cầu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h3 class="mb-4">Chi tiết yêu cầu đăng ký</h3>
    <table class="table table-bordered">
        <tr><th>Họ tên</th><td>${full_name}</td></tr>
        <tr><th>Email</th><td>${email}</td></tr>
        <tr><th>Số điện thoại</th><td>${phone}</td></tr>
        <tr><th>Trạng thái</th><td>${status}</td></tr>
        <tr><th>Ảnh chân dung</th>
            <td><img src="../${portrait_url}" width="150"></td></tr>
        <tr><th>CCCD mặt trước</th>
            <td><img src="../${id_card_front_url}" width="150"></td></tr>
        <tr><th>CCCD mặt sau</th>
            <td><img src="../${id_card_back_url}" width="150"></td></tr>
    </table>
    <a href="admin-requests" class="btn btn-secondary">← Quay lại danh sách</a>
</div>
</body>
</html>
