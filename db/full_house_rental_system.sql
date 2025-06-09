-- SQL Script for House Rental System
DROP DATABASE IF EXISTS HouseRentalDB;
CREATE DATABASE HouseRentalDB;
GO
USE HouseRentalDB;
GO

CREATE TABLE Users (
    id INT PRIMARY KEY IDENTITY(1,1),
    full_name NVARCHAR(100),
    email NVARCHAR(100) UNIQUE,
    password NVARCHAR(100),
    phone NVARCHAR(20),
    role_id INT,
    status BIT DEFAULT 1,
    created_at DATETIME DEFAULT GETDATE()
);
GO

CREATE TABLE Roles (
    id INT PRIMARY KEY,
    name NVARCHAR(50)
);
GO

CREATE TABLE Properties (
    id INT PRIMARY KEY IDENTITY(1,1),
    title NVARCHAR(255),
    description NVARCHAR(MAX),
    price DECIMAL(10,2),
    address NVARCHAR(255),
    area_id INT,
    landlord_id INT,
    status NVARCHAR(50),
    created_at DATETIME DEFAULT GETDATE()
);
GO

CREATE TABLE Areas (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100)
);
GO

CREATE TABLE PropertyImages (
    id INT PRIMARY KEY IDENTITY(1,1),
    property_id INT,
    image_url NVARCHAR(255)
);
GO

CREATE TABLE Bookings (
    id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT,
    property_id INT,
    booking_date DATETIME,
    status NVARCHAR(50)
);
GO

CREATE TABLE Reviews (
    id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT,
    property_id INT,
    rating INT,
    comment NVARCHAR(255),
    created_at DATETIME DEFAULT GETDATE()
);
GO

CREATE TABLE Categories (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100)
);
GO

CREATE TABLE PropertyCategories (
    property_id INT,
    category_id INT,
    PRIMARY KEY (property_id, category_id)
);
GO

CREATE TABLE Notifications (
    id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT,
    message NVARCHAR(255),
    seen BIT DEFAULT 0,
    created_at DATETIME DEFAULT GETDATE()
);
GO

CREATE TABLE Payments (
    id INT PRIMARY KEY IDENTITY(1,1),
    booking_id INT,
    amount DECIMAL(10,2),
    payment_date DATETIME,
    status NVARCHAR(50)
);
GO

CREATE TABLE Favorites (
    user_id INT,
    property_id INT,
    PRIMARY KEY (user_id, property_id)
);
GO

CREATE TABLE Amenities (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100)
);
GO

CREATE TABLE PropertyAmenities (
    property_id INT,
    amenity_id INT,
    PRIMARY KEY (property_id, amenity_id)
);
GO

CREATE TABLE Messages (
    id INT PRIMARY KEY IDENTITY(1,1),
    sender_id INT,
    receiver_id INT,
    message NVARCHAR(MAX),
    sent_at DATETIME DEFAULT GETDATE()
);
GO

CREATE TABLE Reports (
    id INT PRIMARY KEY IDENTITY(1,1),
    reporter_id INT,
    property_id INT,
    reason NVARCHAR(255),
    created_at DATETIME DEFAULT GETDATE()
);
GO

CREATE TABLE Contracts (
    id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT,
    property_id INT,
    start_date DATE,
    end_date DATE,
    status NVARCHAR(50)
);
GO

CREATE TABLE Logs (
    id INT PRIMARY KEY IDENTITY(1,1),
    action NVARCHAR(255),
    user_id INT,
    created_at DATETIME DEFAULT GETDATE()
);
GO

CREATE TABLE SystemSettings (
    [key] NVARCHAR(100) PRIMARY KEY,
    [value] NVARCHAR(255)
);

GO

CREATE TABLE Feedbacks (
    id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT,
    message NVARCHAR(500),
    created_at DATETIME DEFAULT GETDATE()
);
GO




-- FOREIGN KEYS
ALTER TABLE Users ADD CONSTRAINT FK_Users_Roles FOREIGN KEY (role_id) REFERENCES Roles(id);
ALTER TABLE Properties ADD CONSTRAINT FK_Properties_Landlord FOREIGN KEY (landlord_id) REFERENCES Users(id);
ALTER TABLE Properties ADD CONSTRAINT FK_Properties_Area FOREIGN KEY (area_id) REFERENCES Areas(id);
ALTER TABLE PropertyImages ADD CONSTRAINT FK_PropertyImages_Property FOREIGN KEY (property_id) REFERENCES Properties(id);
ALTER TABLE Bookings ADD CONSTRAINT FK_Bookings_User FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE Bookings ADD CONSTRAINT FK_Bookings_Property FOREIGN KEY (property_id) REFERENCES Properties(id);
ALTER TABLE Reviews ADD CONSTRAINT FK_Reviews_User FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE Reviews ADD CONSTRAINT FK_Reviews_Property FOREIGN KEY (property_id) REFERENCES Properties(id);
ALTER TABLE PropertyCategories ADD CONSTRAINT FK_PropertyCategories_Property FOREIGN KEY (property_id) REFERENCES Properties(id);
ALTER TABLE PropertyCategories ADD CONSTRAINT FK_PropertyCategories_Category FOREIGN KEY (category_id) REFERENCES Categories(id);
ALTER TABLE Notifications ADD CONSTRAINT FK_Notifications_User FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE Payments ADD CONSTRAINT FK_Payments_Booking FOREIGN KEY (booking_id) REFERENCES Bookings(id);
ALTER TABLE Favorites ADD CONSTRAINT FK_Favorites_User FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE Favorites ADD CONSTRAINT FK_Favorites_Property FOREIGN KEY (property_id) REFERENCES Properties(id);
ALTER TABLE PropertyAmenities ADD CONSTRAINT FK_PropertyAmenities_Property FOREIGN KEY (property_id) REFERENCES Properties(id);
ALTER TABLE PropertyAmenities ADD CONSTRAINT FK_PropertyAmenities_Amenity FOREIGN KEY (amenity_id) REFERENCES Amenities(id);
ALTER TABLE Messages ADD CONSTRAINT FK_Messages_Sender FOREIGN KEY (sender_id) REFERENCES Users(id);
ALTER TABLE Messages ADD CONSTRAINT FK_Messages_Receiver FOREIGN KEY (receiver_id) REFERENCES Users(id);
ALTER TABLE Reports ADD CONSTRAINT FK_Reports_User FOREIGN KEY (reporter_id) REFERENCES Users(id);
ALTER TABLE Reports ADD CONSTRAINT FK_Reports_Property FOREIGN KEY (property_id) REFERENCES Properties(id);
ALTER TABLE Contracts ADD CONSTRAINT FK_Contracts_User FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE Contracts ADD CONSTRAINT FK_Contracts_Property FOREIGN KEY (property_id) REFERENCES Properties(id);
ALTER TABLE Logs ADD CONSTRAINT FK_Logs_User FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE Feedbacks ADD CONSTRAINT FK_Feedbacks_User FOREIGN KEY (user_id) REFERENCES Users(id);



-- INDEXES
CREATE INDEX IX_Users_Email ON Users(email);
CREATE INDEX IX_Properties_Area_Price ON Properties(area_id, price);
CREATE INDEX IX_Reviews_Property ON Reviews(property_id);
CREATE INDEX IX_Bookings_User ON Bookings(user_id);
CREATE INDEX IX_Favorites_User ON Favorites(user_id);
CREATE INDEX IX_PropertyCategories_Category ON PropertyCategories(category_id);
CREATE INDEX IX_PropertyAmenities_Amenity ON PropertyAmenities(amenity_id);
CREATE INDEX IX_Contracts_Property ON Contracts(property_id);



-- VIEWS
-- View: Chi tiết phòng
CREATE VIEW View_PropertyDetails AS
SELECT 
    p.id AS property_id,
    p.title,
    p.price,
    p.address,
    a.name AS area,
    u.full_name AS landlord,
    p.status,
    p.created_at
FROM Properties p
JOIN Areas a ON p.area_id = a.id
JOIN Users u ON p.landlord_id = u.id;
GO

-- View: Lịch sử đặt phòng
CREATE VIEW View_UserBookings AS
SELECT 
    b.id AS booking_id,
    u.full_name,
    pr.title AS property_title,
    b.booking_date,
    b.status
FROM Bookings b
JOIN Users u ON b.user_id = u.id
JOIN Properties pr ON b.property_id = pr.id;
GO

-- View: Đánh giá phòng
CREATE VIEW View_PropertyReviews AS
SELECT 
    r.id,
    u.full_name AS reviewer,
    p.title AS property,
    r.rating,
    r.comment,
    r.created_at
FROM Reviews r
JOIN Users u ON r.user_id = u.id
JOIN Properties p ON r.property_id = p.id;
GO


-- INSERT sample roles
INSERT INTO Roles (id, name) VALUES
(1, N'Quản trị viên'),
(2, N'Nhân viên'),
(3, N'Sinh viên'),
(4, N'Chủ trọ');

-- INSERT sample users
INSERT INTO Users (full_name, email, password, phone, role_id, status)
VALUES
(N'Nguyễn Văn A', 'admin@gmail.com', '123456', '0909123456', 1, 1),
(N'Trần Thị B', 'staff@gmail.com', '123456', '0909123457', 2, 1),
(N'Lê Văn C', 'student1@gmail.com', '123456', '0909123458', 3, 1),
(N'Phạm Thị D', 'landlord1@gmail.com', '123456', '0909123459', 4, 1);

-- INSERT sample areas
INSERT INTO Areas (name) VALUES
(N'Quận 1'),
(N'Quận 2'),
(N'Quận Bình Thạnh');

-- INSERT sample categories
INSERT INTO Categories (name) VALUES
(N'Căn hộ'),
(N'Nhà nguyên căn'),
(N'Phòng trọ');

-- INSERT sample properties
INSERT INTO Properties (title, description, price, address, area_id, landlord_id, status)
VALUES
(N'Phòng trọ tiện nghi', N'Phòng đầy đủ tiện nghi, gần trung tâm', 2500000, N'123 Lý Thường Kiệt, Q1', 1, 4, N'Đang cho thuê'),
(N'Căn hộ mini', N'Căn hộ mini mới xây, có gác lửng', 3500000, N'456 Nguyễn Trãi, Q5', 2, 4, N'Còn trống');

-- INSERT sample property images
INSERT INTO PropertyImages (property_id, image_url)
VALUES
(1, 'images/room1.jpg'),
(2, 'images/apartment1.jpg');

-- INSERT sample bookings
INSERT INTO Bookings (user_id, property_id, booking_date, status)
VALUES
(3, 1, GETDATE(), N'Chờ xác nhận');

-- INSERT sample reviews
INSERT INTO Reviews (user_id, property_id, rating, comment)
VALUES
(3, 1, 5, N'Phòng sạch sẽ, chủ nhà thân thiện');

-- INSERT sample favorites
INSERT INTO Favorites (user_id, property_id)
VALUES
(3, 1);

-- INSERT sample amenities
INSERT INTO Amenities (name) VALUES
(N'Máy lạnh'),
(N'Wifi miễn phí'),
(N'Chỗ để xe');

-- INSERT sample property amenities
INSERT INTO PropertyAmenities (property_id, amenity_id)
VALUES
(1, 1),
(1, 2),
(2, 3);

-- INSERT sample contracts
INSERT INTO Contracts (user_id, property_id, start_date, end_date, status)
VALUES
(3, 1, '2024-06-01', '2025-05-31', N'Đang hiệu lực');

-- INSERT sample reports
INSERT INTO Reports (reporter_id, property_id, reason)
VALUES
(3, 1, N'Phòng không giống hình');

-- INSERT sample notifications
INSERT INTO Notifications (user_id, message)
VALUES
(3, N'Bạn đã đặt phòng thành công.');

-- INSERT sample messages
INSERT INTO Messages (sender_id, receiver_id, message)
VALUES
(3, 4, N'Chào anh/chị, em muốn hỏi thêm về phòng trọ.');

-- INSERT sample logs
INSERT INTO Logs (action, user_id)
VALUES
(N'Đăng nhập hệ thống', 3);

-- INSERT sample feedback
INSERT INTO Feedbacks (user_id, message)
VALUES
(3, N'Hệ thống dễ sử dụng, giao diện thân thiện.');

-- Mở rộng bảng Users để hỗ trợ đăng nhập Google/Facebook
ALTER TABLE Users
ADD oauth_provider NVARCHAR(50),         -- 'google', 'facebook', hoặc NULL
    oauth_id NVARCHAR(100);              -- ID duy nhất do Google/Facebook cung cấp

INSERT INTO Users (full_name, email, oauth_provider, oauth_id, status, role_id)
VALUES (N'Trần Văn Google', 'googleuser@gmail.com', 'google', 'google-123456', 1, 3);
INSERT INTO Users (full_name, email, oauth_provider, oauth_id, status, role_id)
VALUES (N'Lê Thị Facebook', 'fbuser@gmail.com', 'facebook', 'fb-789012', 1, 3);

-- Chỉ thêm nếu chưa có
IF NOT EXISTS (
    SELECT * FROM INFORMATION_SCHEMA.COLUMNS
    WHERE TABLE_NAME = 'Users' AND COLUMN_NAME = 'phone'
)
BEGIN
    ALTER TABLE Users ADD phone NVARCHAR(20);
END;

IF NOT EXISTS (
    SELECT * FROM INFORMATION_SCHEMA.COLUMNS
    WHERE TABLE_NAME = 'Users' AND COLUMN_NAME = 'id_card_url'
)
BEGIN
    ALTER TABLE Users ADD id_card_url NVARCHAR(255);
END;

CREATE TABLE UserRequests (
    id INT PRIMARY KEY IDENTITY(1,1),
    full_name NVARCHAR(100),
    email NVARCHAR(100),
    password NVARCHAR(100),
    phone NVARCHAR(20),
    role_id INT, -- mặc định = 2 (staff)
    status NVARCHAR(20), -- 'pending', 'approved', 'rejected'
    created_at DATETIME DEFAULT GETDATE()
);
ALTER TABLE Users
ADD id_card_front_url NVARCHAR(255),   -- ảnh mặt trước
    id_card_back_url NVARCHAR(255);    -- ảnh mặt sau

-- Thêm cột status nếu chưa có
IF NOT EXISTS (
    SELECT * FROM INFORMATION_SCHEMA.COLUMNS
    WHERE TABLE_NAME = 'UserRequests' AND COLUMN_NAME = 'status'
)
BEGIN
    ALTER TABLE UserRequests ADD status NVARCHAR(20) DEFAULT 'pending';
END;

-- Thêm cột ảnh chân dung
IF NOT EXISTS (
    SELECT * FROM INFORMATION_SCHEMA.COLUMNS
    WHERE TABLE_NAME = 'UserRequests' AND COLUMN_NAME = 'portrait_url'
)
BEGIN
    ALTER TABLE UserRequests ADD portrait_url NVARCHAR(255);
END;

-- Thêm cột CCCD mặt trước và mặt sau
IF NOT EXISTS (
    SELECT * FROM INFORMATION_SCHEMA.COLUMNS
    WHERE TABLE_NAME = 'UserRequests' AND COLUMN_NAME = 'id_card_front_url'
)
BEGIN
    ALTER TABLE UserRequests ADD id_card_front_url NVARCHAR(255);
END;

IF NOT EXISTS (
    SELECT * FROM INFORMATION_SCHEMA.COLUMNS
    WHERE TABLE_NAME = 'UserRequests' AND COLUMN_NAME = 'id_card_back_url'
)
BEGIN
    ALTER TABLE UserRequests ADD id_card_back_url NVARCHAR(255);
END;

CREATE TABLE Rooms (
    id INT PRIMARY KEY IDENTITY(1,1),
    title NVARCHAR(255),
    image NVARCHAR(255),
    address NVARCHAR(255),
    price FLOAT,
    created_at DATETIME DEFAULT GETDATE()
);

INSERT INTO Rooms (title, image, address, price)
VALUES 
(N'Phòng trọ Q1 gần ĐH KHTN', 'uploads/room1.jpg', N'12 Nguyễn Văn Cừ, Q1', 2500000),
(N'Phòng mới xây full nội thất', 'uploads/room2.jpg', N'Đường 3/2, Q10', 3200000),
(N'Phòng cho nữ sinh viên', 'uploads/room3.jpg', N'Hồng Bàng, Q5', 1800000),
(N'Căn hộ mini Thủ Đức', 'uploads/room4.jpg', N'Linh Trung, Thủ Đức', 2700000),
(N'Phòng rẻ gần ĐH Sư Phạm', 'uploads/room5.jpg', N'An Dương Vương, Q5', 2000000),
(N'Phòng tiện nghi có ban công', 'uploads/room6.jpg', N'Trường Sa, Q3', 3500000),
(N'Phòng trọ gần Bến Thành', 'uploads/room7.jpg', N'Lê Lai, Q1', 4000000),
(N'Phòng mới cho thuê', 'uploads/room8.jpg', N'Nguyễn Trãi, Q1', 3000000);
