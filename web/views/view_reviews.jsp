<%-- 
    Document   : view_reviews
    Created on : Jun 12, 2025, 9:27:43 PM
    Author     : Dung Thuy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Đánh Giá Nhà Trọ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2>Đánh Giá Nhà Trọ</h2>
        <c:if test="${not empty reviews}">
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>Người Dùng</th>
                            <th>Điểm Đánh Giá</th>
                            <th>Bình Luận</th>
                            <th>Ngày</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="review" items="${reviews}">
                            <tr>
                                <td>${review.user.fullName}</td>
                                <td>${review.rating}</td>
                                <td>${review.comment}</td>
                                <td>${review.createdAt}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${empty reviews}">
            <div class="alert alert-info">Chưa Có Đánh Giá Nào.</div>
        </c:if>
        <h3>Gửi Đánh Giá</h3>
        <form action="/submit-review" method="post" class="row g-3">
            <input type="hidden" name="propertyId" value="${param.propertyId}">
            <div class="col-md-12">
                <label for="rating" class="form-label">Điểm Đánh Giá (1-5)</label>
                <input type="number" class="form-control" id="rating" name="rating" min="1" max="5" placeholder="Nhập điểm từ 1 đến 5" required>
            </div>
            <div class="col-md-12">
                <label for="comment" class="form-label">Bình Luận</label>
                <textarea class="form-control" id="comment" name="comment" rows="4" placeholder="Nhập bình luận của bạn"></textarea>
            </div>
            <div class="col-12 text-center">
                <button type="submit" class="btn btn-primary">Gửi Đánh Giá</button>
            </div>
        </form>
    </div>
</body>
</html>