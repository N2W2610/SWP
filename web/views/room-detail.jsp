<%-- 
    Document   : room-detail
    Created on : Jun 12, 2025, 9:16:47 PM
    Author     : Dung Thuy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Chi Tiết Phòng Trọ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2>Chi Tiết Phòng Trọ</h2>
        <c:if test="${not empty room}">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">${room.title}</h5>
                    <p class="card-text"><strong>Giá:</strong> ${room.price} VNĐ</p>
                    <p class="card-text"><strong>Địa Chỉ:</strong> ${room.address}</p>
                    <c:if test="${not empty room.image}">
                        <img src="${room.image}" class="img-fluid rounded" alt="Hình Ảnh Phòng Trọ">
                    </c:if>
                </div>
            </div>
        </c:if>
        <c:if test="${empty room}">
            <div class="alert alert-warning">Không Tìm Thấy Phòng Trọ.</div>
        </c:if>
    </div>
</body>
</html>