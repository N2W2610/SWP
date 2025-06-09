<%-- 
    Document   : AdminRequestList
    Created on : Jun 9, 2025, 12:15:31 AM
    Author     : Dung Thuy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Yêu cầu đăng ký nhân viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h3 class="mb-4">Danh sách yêu cầu đăng ký nhân viên</h3>

    <form method="get" action="admin-requests" class="mb-3">
        <div class="row g-2">
            <div class="col-auto">
                <select name="status" class="form-select" onchange="this.form.submit()">
                    <option value="pending" ${status == 'pending' ? 'selected' : ''}>Chờ duyệt</option>
                    <option value="approved" ${status == 'approved' ? 'selected' : ''}>Đã duyệt</option>
                    <option value="rejected" ${status == 'rejected' ? 'selected' : ''}>Từ chối</option>
                </select>
            </div>
        </div>
    </form>

    <c:if test="${empty requests}">
        <div class="alert alert-info">Không có yêu cầu nào.</div>
    </c:if>

    <c:if test="${not empty requests}">
        <table class="table table-bordered">
            <thead class="table-light">
                <tr>
                    <th>Họ tên</th>
                    <th>Email</th>
                    <th>Điện thoại</th>
                    <th>Ảnh chân dung</th>
                    <th>CCCD mặt trước</th>
                    <th>CCCD mặt sau</th>
                    <th>Trạng thái</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="r" items="${requests}">
                <tr>
                    <td><a href="view-request-detail?id=${r[0]}" class="text-decoration-none">${r[1]}</a></td>
                    <td>${r[2]}</td>
                    <td>${r[3]}</td>
                    <td><img src="../${r[4]}" width="80"></td>
                    <td><img src="../${r[5]}" width="80"></td>
                    <td><img src="../${r[6]}" width="80"></td>
                    <td>${r[7]}</td>
                    <td>
                        <c:if test="${r[7] == 'pending'}">
                            <form method="post" action="admin-requests" style="display:inline">
                                <input type="hidden" name="id" value="${r[0]}">
                                <button name="action" value="approve" class="btn btn-success btn-sm">✔ Duyệt</button>
                            </form>
                            <form method="post" action="admin-requests" style="display:inline">
                                <input type="hidden" name="id" value="${r[0]}">
                                <button name="action" value="reject" class="btn btn-danger btn-sm">✖ Từ chối</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- Phân trang thực -->
        <nav>
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="admin-requests?page=${currentPage - 1}&status=${status}">Trước</a>
                    </li>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="admin-requests?page=${i}&status=${status}">${i}</a>
                    </li>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="admin-requests?page=${currentPage + 1}&status=${status}">Tiếp</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>
</div>
</body>
</html>

