DROP DATABASE IF EXISTS ecommerce;
CREATE DATABASE ecommerce;
USE ecommerce;
-- RESET
DROP TABLE IF EXISTS OrderItems;
DROP TABLE IF EXISTS Payments;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS Users;

-- Users
CREATE TABLE Users (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    UserName VARCHAR(100) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Address VARCHAR(255),
    City VARCHAR(50),
    PostalCode VARCHAR(20),
    Country VARCHAR(50),
    PhoneNumber VARCHAR(20),
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO Users (FirstName, LastName, UserName, Password, Address, City, PostalCode, Country, PhoneNumber)
VALUES
('John', 'Doe', 'johndoe', 'pass', '123 Main St', 'New York', '10001', 'USA', '1234567890'),
('Jane', 'Smith', 'janesmith', 'pass', '456 Oak Ave', 'Los Angeles', '90001', 'USA', '0987654321'),
('Alice', 'Johnson', 'alicejones', 'pass', '789 Elm St', 'Los Angeles', '90001', 'USA', '1357924680');

-- Categories
CREATE TABLE Categories (
    CategoryID INT PRIMARY KEY AUTO_INCREMENT,
    CategoryName VARCHAR(100) NOT NULL,
    Description TEXT
);

INSERT INTO Categories (CategoryName, Description)
VALUES ('Electronics', 'Devices'), ('Clothing', 'Apparel'), ('Books', 'Literature');

-- Products
CREATE TABLE Products (
    ProductID INT PRIMARY KEY AUTO_INCREMENT,
    CategoryID INT NULL,
    ProductName VARCHAR(100) NOT NULL,
    Description TEXT,
    Price DECIMAL(10, 2) NOT NULL,
    Stock INT DEFAULT 0,
    ImageURL VARCHAR(255),
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

INSERT INTO Products (CategoryID, ProductName, Description, Price, Stock, ImageURL)
VALUES
(1, 'Smartphone', '128GB smartphone', 699.99, 50, NULL),
(NULL, 'Laptop', '16GB RAM Laptop', 999.99, 30, NULL),
(2, 'T-Shirt', 'Cotton T-shirt', 19.99, 100, NULL),
(3, 'Mystery Novel', 'Thriller book', 12.99, 75, NULL);

-- Orders
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    TotalAmount DECIMAL(10, 2),
    Status VARCHAR(50),
    ShippingAddress VARCHAR(255),
    City VARCHAR(50),
    PostalCode VARCHAR(20),
    Country VARCHAR(50),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- alicejones = OrderID 1 (ShippingAddress NULL)
-- johndoe = OrderID 2
-- janesmith = OrderID 3
INSERT INTO Orders (UserID, TotalAmount, Status, ShippingAddress, City, PostalCode, Country)
VALUES
(3, 1045.96, 'Completed', NULL, 'Los Angeles', '90001', 'USA'),         -- Order #1
(1, 719.98, 'Completed', '123 Main St', 'New York', '10001', 'USA'),    -- Order #2
(2, 32.98, 'Completed', '456 Oak Ave', 'Los Angeles', '90001', 'USA');  -- Order #3

-- OrderItems
CREATE TABLE OrderItems (
    OrderItemID INT PRIMARY KEY AUTO_INCREMENT,
    OrderID INT,
    ProductID INT,
    Quantity INT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

INSERT INTO OrderItems (OrderID, ProductID, Quantity, Price)
VALUES
(1, 2, 1, 999.99),  -- Laptop
(1, 4, 2, 22.99),
(2, 1, 1, 699.99),
(2, 3, 1, 19.99),
(3, 4, 2, 12.99);

-- Payments
CREATE TABLE Payments (
    PaymentID INT PRIMARY KEY AUTO_INCREMENT,
    OrderID INT,
    PaymentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Amount DECIMAL(10, 2),
    PaymentMethod VARCHAR(50),
    Status VARCHAR(50),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);

INSERT INTO Payments (OrderID, Amount, PaymentMethod, Status)
VALUES
(1, 1045.96, 'Card', 'Completed'),
(2, 719.98, 'Card', 'Completed'),
(3, 32.98, 'Card', 'Completed');
