-- Using the Products and OrderItems tables, write a query to find the total revenue generated for
-- each product. The result should display the ProductID, ProductName, and the TotalRevenue
-- (calculated as the sum of Price * Quantity for each OrderItem entry for the product).
-- Order the results by ProductID in ascending order.
SELECT
    p.ProductID,
    p.ProductName,
    SUM(oi.Price * oi.Quantity) AS TotalRevenue
FROM Products p
JOIN OrderItems oi ON p.ProductID = oi.ProductID
GROUP BY p.ProductID, p.ProductName
ORDER BY p.ProductID ASC;
