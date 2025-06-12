<%-- 
    Document   : room-list
    Created on : Jun 12, 2025, 9:15:50 PM
    Author     : Dung Thuy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh Sách Phòng Trọ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2>Danh Sách Phòng Trọ Có Sẵn</h2>
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>Mã</th>
                        <th>Tiêu Đề</th>
                        <th>Giá</th>
                        <th>Địa Chỉ</th>
                        <th>Hành Động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="room" items="${rooms}">
                        <tr>
                            <td>${room.id}</td>
                            <td>${room.title}</td>
                            <td>${room.price} VNĐ</td>
                            <td>${room.address}</td>
                            <td><a href="/room-detail?id=${room.id}" class="btn btn-sm btn-primary">Xem Chi Tiết</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>