<%-- 
    Document   : house-search
    Created on : Jun 12, 2025, 9:10:10 PM
    Author     : Dung Thuy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tìm Kiếm Nhà Trọ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2>Tìm Kiếm Nhà Trọ</h2>
        <form action="/house-search" method="get" class="row g-3">
            <div class="col-md-4">
                <label for="areaId" class="form-label">Khu Vực</label>
                <select id="areaId" name="areaId" class="form-select">
                    <option value="">Tất Cả Khu Vực</option>
                    <c:forEach var="area" items="${areas}">
                        <option value="${area.id}">${area.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-4">
                <label for="minPrice" class="form-label">Giá Tối Thiểu (VNĐ)</label>
                <input type="text" class="form-control" id="minPrice" name="minPrice" placeholder="Nhập giá tối thiểu">
            </div>
            <div class="col-md-4">
                <label for="maxPrice" class="form-label">Giá Tối Đa (VNĐ)</label>
                <input type="text" class="form-control" id="maxPrice" name="maxPrice" placeholder="Nhập giá tối đa">
            </div>
            <div class="col-12 text-center">
                <button type="submit" class="btn btn-primary">Tìm Kiếm</button>
            </div>
        </form>
        <c:if test="${not empty properties}">
            <div class="table-responsive mt-4">
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
        </c:if>
    </div>
</body>
</html>