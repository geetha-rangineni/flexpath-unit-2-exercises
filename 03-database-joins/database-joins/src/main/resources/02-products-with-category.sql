SELECT
    p.ProductID,
    p.ProductName,
    c.CategoryName
FROM Products p
LEFT JOIN Categories c ON p.CategoryID = c.CategoryID
ORDER BY p.ProductID;
