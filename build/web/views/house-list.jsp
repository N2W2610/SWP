<%-- 
    Document   : house-list
    Created on : Jun 12, 2025, 9:08:42 PM
    Author     : Dung Thuy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh Sách Nhà Trọ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2>Danh Sách Nhà Trọ Có Sẵn</h2>
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>Mã</th>
                        <th>Tiêu Đề</th>
                        <th>Giá</th>
                        <th>Địa Chỉ</th>
                        <th>Trạng Thái</th>
                        <th>Hành Động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="property" items="${properties}">
                        <tr>
                            <td>${property.id}</td>
                            <td>${property.title}</td>
                            <td>${property.price} VNĐ</td>
                            <td>${property.address}</td>
                            <td>${property.status}</td>
                            <td><a href="/house-detail?id=${property.id}" class="btn btn-sm btn-primary">Xem Chi Tiết</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="text-center">
            <a href="/house-search" class="btn btn-outline-primary">Tìm Kiếm Nâng Cao</a>
        </div>
    </div>
</body>
</html>