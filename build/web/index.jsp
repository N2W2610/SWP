<%-- 
    Document   : index.jsp
    Created on : Jun 12, 2025, 9:27:43 PM
    Author     : Dung Thuy
--%>


<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>House Finder</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function toggleTheme() {
            document.body.classList.toggle('dark-mode');
            const themeText = document.getElementById("themeText");
            themeText.innerText = document.body.classList.contains('dark-mode') ? 'Chế độ Sáng' : 'Chế độ Tối';

            const hero = document.querySelector('.hero');
            if (document.body.classList.contains('dark-mode')) {
                hero.classList.add('text-white');
                hero.classList.remove('text-dark');
            } else {
                hero.classList.remove('text-white');
                hero.classList.add('text-dark');
            }
        }

        function navigateTo(url) {
            window.location.href = url;
        }

        window.addEventListener('DOMContentLoaded', () => {
            const hero = document.querySelector('.hero');
            hero.classList.add('text-dark');
        });
    </script>
    <style>
        .hero {
            background-image: url('https://source.unsplash.com/1600x500/?house,rent');
            background-size: cover;
            background-position: center;
            padding: 100px 0;
            text-align: center;
        }

        .dark-mode {
            background-color: #121212 !important;
            color: #f1f1f1;
        }

        .dark-mode .card {
            background-color: #1f1f1f;
            color: #f1f1f1;
        }

        .dark-mode .navbar {
            background-color: #1f1f1f !important;
        }

        .dark-mode .navbar-nav .nav-link {
            color: #f1f1f1 !important;
        }

        .navbar-nav button {
            background: none;
            border: none;
            padding: 0.5rem 1rem;
            color: inherit;
            font: inherit;
            cursor: pointer;
        }
    </style>
</head>
<body class="min-vh-100 d-flex flex-column">
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light px-3">
        <a class="navbar-brand" href="#">FU House Finder</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><button class="nav-link" onclick="navigateTo('/HouseRentalWebApp')">Trang Chủ</button></li>
                <li class="nav-item"><button class="nav-link" onclick="navigateTo('/house-search')">Tìm Nhà</button></li>
                <li class="nav-item"><button class="nav-link" onclick="navigateTo('/views/guest_login.jsp')">Đăng Nhập</button></li>
                <li class="nav-item"><button class="nav-link" onclick="navigateTo('/views/guest_register.jsp')">Đăng Ký</button></li>
            </ul>
            <button onclick="toggleTheme()" class="btn btn-outline-secondary">
                <span id="themeText">Chế độ Tối</span>
            </button>
        </div>
    </nav>

    <!-- Hero Section -->
    <section class="hero">
        <div class="container">
            <h1 class="display-4">Tìm Nhà Trọ Dễ Dàng Với FU House Finder</h1>
            <p class="lead">Khám phá hàng ngàn lựa chọn nhà trọ chất lượng cho sinh viên và người đi làm</p>
        </div>
    </section>

    <!-- Search Section -->
    <section class="py-5">
        <div class="container">
            <form action="/house-search" method="get" class="row g-3">
                <div class="col-md-4">
                    <input type="text" class="form-control" name="location" placeholder="Nhập địa điểm, quận/huyện...">
                </div>
                <div class="col-md-3">
                    <select class="form-select" name="priceRange">
                        <option value="">Khoảng Giá</option>
                        <option value="0-1000000">Dưới 1 triệu</option>
                        <option value="1000000-3000000">1 - 3 triệu</option>
                        <option value="3000000-999999999">Trên 3 triệu</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <select class="form-select" name="category">
                        <option value="">Loại Nhà</option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-2">
                    <button class="btn btn-primary w-100" type="submit">Tìm Kiếm</button>
                </div>
            </form>
        </div>
    </section>

    <!-- Featured Listings -->
    <section class="pb-5">
        <div class="container">
            <h2 class="mb-4">Nhà Trọ Nổi Bật</h2>
            <div class="row g-4">
                <c:forEach var="property" items="${featuredProperties}" varStatus="loop">
                    <div class="col-md-4">
                        <div class="card">
                            <img src="${property.images[0].imageUrl != null ? property.images[0].imageUrl : 'https://source.unsplash.com/400x200/?room,' + loop.index}" class="card-img-top" alt="${property.title}">
                            <div class="card-body">
                                <h5 class="card-title">${property.title}</h5>
                                <p class="card-text">Giá: ${property.price} VNĐ/tháng<br>${property.address}</p>
                                <a href="/house-detail?id=${property.id}" class="btn btn-outline-primary">Xem Chi Tiết</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer class="bg-light text-center py-4 mt-auto">
        <p class="mb-0">© 2025 FU House Finder. Mọi quyền được bảo lưu.</p>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>