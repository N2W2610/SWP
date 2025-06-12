<%-- 
    Document   : house-map
    Created on : Jun 12, 2025, 9:12:53 PM
    Author     : Dung Thuy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bản Đồ Nhà Trọ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        #map { height: 400px; width: 100%; }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h2>Bản Đồ Nhà Trọ</h2>
        <div id="map" class="mb-4"></div>
        <div class="row">
            <c:forEach var="property" items="${properties}">
                <div class="col-md-4 mb-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${property.title}</h5>
                            <p class="card-text">${property.address}</p>
                            <a href="/house-detail?id=${property.id}" class="btn btn-primary">Xem Chi Tiết</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <script>
        // Placeholder cho Google Maps API
        console.log("Placeholder bản đồ - tích hợp với Google Maps API");
    </script>
</body>
</html>