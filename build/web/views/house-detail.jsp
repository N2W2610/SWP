<%-- 
    Document   : house-detail
    Created on : Jun 12, 2025, 9:11:27 PM
    Author     : Dung Thuy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Chi Tiết Nhà Trọ</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2>Chi Tiết Nhà Trọ</h2>
        <c:if test="${not empty property}">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">${property.title}</h5>
                    <p class="card-text"><strong>Mô Tả:</strong> ${property.description}</p>
                    <p class="card-text"><strong>Giá:</strong> ${property.price} VNĐ</p>
                    <p class="card-text"><strong>Địa Chỉ:</strong> ${property.address}</p>
                    <p class="card-text"><strong>Trạng Thái:</strong> ${property.status}</p>
                    <p class="card-text"><strong>Khu Vực:</strong> ${property.area.name}</p>
                    <p class="card-text"><strong>Chủ Trọ:</strong> <a href="/landlord-info?id=${property.landlord.id}">${property.landlord.fullName}</a></p>
                    <c:if test="${not empty property.images}">
                        <h6>Hình Ảnh:</h6>
                        <div class="row">
                            <c:forEach var="image" items="${property.images}">
                                <div class="col-md-4 mb-3">
                                    <img src="${image.imageUrl}" class="img-fluid rounded" alt="Hình Ảnh Nhà Trọ">
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                    <a href="/views/view_reviews.jsp?propertyId=${property.id}" class="btn btn-primary mt-3">Xem Đánh Giá</a>
                </div>
            </div>
        </c:if>
        <c:if test="${empty property}">
            <div class="alert alert-warning">Không Tìm Thấy Nhà Trọ.</div>
        </c:if>
    </div>
</body>
</html>
