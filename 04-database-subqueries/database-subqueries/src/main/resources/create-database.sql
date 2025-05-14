DROP DATABASE IF EXISTS ecommerce;
CREATE DATABASE ecommerce;
USE ecommerce;

-- RESET
DROP TABLE IF EXISTS OrderItems;
DROP TABLE IF EXISTS Payments;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Categories;

-- Users Table
DROP TABLE IF EXISTS Users;
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

-- Categories Table
DROP TABLE IF EXISTS Categories;
CREATE TABLE Categories (
    CategoryID INT PRIMARY KEY AUTO_INCREMENT,
    CategoryName VARCHAR(100) NOT NULL,
    Description TEXT
);

-- Products Table
DROP TABLE IF EXISTS Products;
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

-- Orders Table
DROP TABLE IF EXISTS Orders;
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    TotalAmount DECIMAL(10, 2) NOT NULL,
    Status VARCHAR(50) DEFAULT 'Pending',
    ShippingAddress VARCHAR(255),
    City VARCHAR(50),
    PostalCode VARCHAR(20),
    Country VARCHAR(50),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- OrderItems Table
DROP TABLE IF EXISTS OrderItems;
CREATE TABLE OrderItems (
    OrderItemID INT PRIMARY KEY AUTO_INCREMENT,
    OrderID INT,
    ProductID INT,
    Quantity INT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Payments Table
DROP TABLE IF EXISTS Payments;
CREATE TABLE Payments (
    PaymentID INT PRIMARY KEY AUTO_INCREMENT,
    OrderID INT,
    PaymentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Amount DECIMAL(10, 2) NOT NULL,
    PaymentMethod VARCHAR(50) NOT NULL,
    Status VARCHAR(50) DEFAULT 'Completed',
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);

-- Sample Data for Users Table
INSERT INTO Users (FirstName, LastName, UserName, Password, Address, City, PostalCode, Country, PhoneNumber)
VALUES ('John', 'Doe', 'johndoe', 'password123', '123 Main St', 'New York', '10001', 'USA', '1234567890');
INSERT INTO Users (FirstName, LastName, UserName, Password, Address, City, PostalCode, Country, PhoneNumber)
VALUES ('Jane', 'Smith', 'janesmith', 'password123', '456 Oak Ave', 'Los Angeles', '90001', 'USA', '0987654321');
INSERT INTO Users (FirstName, LastName, UserName, Password, Address, City, PostalCode, Country, PhoneNumber)
VALUES ('Alice', 'Johnson', 'alicejones', 'password123', '789 Elm St', 'Los Angeles', '90001', 'USA', '1357924680');

-- Sample Data for Categories Table
INSERT INTO Categories (CategoryName, Description)
VALUES ('Electronics', 'Devices and gadgets');
INSERT INTO Categories (CategoryName, Description)
VALUES ('Clothing', 'Apparel for men and women');
INSERT INTO Categories (CategoryName, Description)
VALUES ('Books', 'Fiction and non-fiction books');

-- Sample Data for Products Table
INSERT INTO Products (CategoryID, ProductName, Description, Price, Stock, ImageURL)
VALUES (1, 'Smartphone', 'Latest model smartphone with 128GB storage', 699.99, 50, 'https://example.com/images/smartphone.jpg');
INSERT INTO Products (CategoryID, ProductName, Description, Price, Stock, ImageURL)
VALUES (NULL, 'Laptop', '15-inch laptop with 16GB RAM and 512GB SSD', 999.99, 30, 'https://example.com/images/laptop.jpg');
INSERT INTO Products (CategoryID, ProductName, Description, Price, Stock, ImageURL)
VALUES (2, 'T-Shirt', 'Cotton t-shirt in various sizes', 19.99, 100, 'https://example.com/images/tshirt.jpg');
INSERT INTO Products (CategoryID, ProductName, Description, Price, Stock, ImageURL)
VALUES (3, 'Mystery Novel', 'A thrilling mystery novel', 12.99, 75, 'https://example.com/images/novel.jpg');

-- Sample Data for Orders Table
INSERT INTO Orders (UserID, TotalAmount, Status, ShippingAddress, City, PostalCode, Country)
VALUES (1, 719.98, 'Completed', '123 Main St', 'New York', '10001', 'USA');
INSERT INTO Orders (UserID, TotalAmount, Status, ShippingAddress, City, PostalCode, Country)
VALUES (2, 32.98, 'Pending', '456 Oak Ave', 'Los Angeles', '90001', 'USA');

-- Sample Data for OrderItems Table
INSERT INTO OrderItems (OrderID, ProductID, Quantity, Price)
VALUES (1, 1, 1, 699.99);  -- Order 1: 1 Smartphone
INSERT INTO OrderItems (OrderID, ProductID, Quantity, Price)
VALUES (1, 3, 1, 19.99);   -- Order 1: 1 T-Shirt
INSERT INTO OrderItems (OrderID, ProductID, Quantity, Price)
VALUES (2, 4, 2, 12.99);   -- Order 2: 2 Mystery Novels
INSERT INTO OrderItems (OrderID, ProductID, Quantity, Price)
VALUES (2, 1, 1, 699.99);  -- Order 2: 1 Smartphone

-- Sample Data for Payments Table
INSERT INTO Payments (OrderID, Amount, PaymentMethod, Status)
VALUES (1, 719.98, 'Credit Card', 'Completed');
INSERT INTO Payments (OrderID, Amount, PaymentMethod, Status)
VALUES (2, 32.98, 'PayPal', 'Pending');
