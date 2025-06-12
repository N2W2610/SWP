<%-- 
    Document   : view_landlord_info
    Created on : Jun 12, 2025, 9:28:50 PM
    Author     : Dung Thuy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Thông Tin Chủ Trọ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2>Thông Tin Chủ Trọ</h2>
        <c:if test="${not empty landlord}">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">${landlord.fullName}</h5>
                    <p class="card-text"><strong>Email:</strong> ${landlord.email}</p>
                    <p class="card-text"><strong>Số Điện Thoại:</strong> ${landlord.phone}</p>
                </div>
            </div>
        </c:if>
        <c:if test="${empty landlord}">
            <div class="alert alert-warning">Không Tìm Thấy Chủ Trọ.</div>
        </c:if>
    </div>
</body>
</html>
