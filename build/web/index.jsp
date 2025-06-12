<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FU House Finder</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
                <li class="nav-item"><button class="nav-link" onclick="navigateTo('index.jsp')">Trang chủ</button></li>
                <li class="nav-item"><button class="nav-link" onclick="navigateTo('search.jsp')">Tìm nhà</button></li>
                <li class="nav-item"><button class="nav-link" onclick="navigateTo('login.jsp')">Đăng nhập</button></li>
                <li class="nav-item"><button class="nav-link" onclick="navigateTo('register.jsp')">Đăng ký</button></li>
            </ul>
            <button onclick="toggleTheme()" class="btn btn-outline-secondary">
                <span id="themeText">Chế độ Tối</span>
            </button>
        </div>
    </nav>

    <!-- Hero Section -->
    <section class="hero">
        <div class="container">
            <h1 class="display-4">Tìm nhà trọ dễ dàng cùng FU House Finder</h1>
            <p class="lead">Hàng ngàn lựa chọn chất lượng dành cho sinh viên và người đi làm</p>
        </div>
    </section>

    <!-- Search Section -->
    <section class="py-5">
        <div class="container">
            <form class="row g-3" onsubmit="event.preventDefault(); navigateTo('search.jsp');">
                <div class="col-md-4">
                    <input type="text" class="form-control" placeholder="Nhập địa điểm, quận huyện...">
                </div>
                <div class="col-md-3">
                    <select class="form-select">
                        <option>Khoảng giá</option>
                        <option>Dưới 1 triệu</option>
                        <option>1 - 3 triệu</option>
                        <option>Trên 3 triệu</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <select class="form-select">
                        <option>Loại phòng</option>
                        <option>Phòng trọ</option>
                        <option>Chung cư mini</option>
                        <option>Nhà nguyên căn</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <button class="btn btn-primary w-100" type="submit">Tìm kiếm</button>
                </div>
            </form>
        </div>
    </section>

    <!-- Featured Listings -->
    <section class="pb-5">
        <div class="container">
            <h2 class="mb-4">Nhà trọ nổi bật</h2>
            <div class="row g-4">
                <%
                    String[] roomTitles = {
                        "Phòng trọ gần ĐH FPT",
                        "Căn hộ mini Dịch Vọng",
                        "Nhà nguyên căn Hà Đông",
                        "Phòng trọ khép kín Cầu Giấy",
                        "Phòng rộng 25m², đầy đủ tiện nghi",
                        "Căn hộ mini giá rẻ khu Đại La"
                    };
                    String[] prices = {
                        "1.500.000 VNĐ/tháng",
                        "2.800.000 VNĐ/tháng",
                        "5.000.000 VNĐ/tháng",
                        "2.000.000 VNĐ/tháng",
                        "2.300.000 VNĐ/tháng",
                        "1.800.000 VNĐ/tháng"
                    };
                    for (int i = 0; i < roomTitles.length; i++) {
                %>
                <div class="col-md-4">
                    <div class="card">
                        <img src="https://source.unsplash.com/400x200/?room,<%= i %>" class="card-img-top" alt="Phòng <%= i+1 %>">
                        <div class="card-body">
                            <h5 class="card-title"><%= roomTitles[i] %></h5>
                            <p class="card-text">Giá: <%= prices[i] %> - An ninh, sạch sẽ</p>
                            <button class="btn btn-outline-primary" onclick="navigateTo('room-detail.jsp')">Xem chi tiết</button>
                        </div>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <footer class="bg-light text-center py-4 mt-auto">
        <p class="mb-0">&copy; 2025 FU House Finder. All rights reserved.</p>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
