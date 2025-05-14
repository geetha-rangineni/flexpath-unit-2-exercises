SELECT
    o.OrderID,
    o.ShippingAddress,
    u.UserName
FROM Orders o
JOIN Users u ON o.UserID = u.UserID
ORDER BY o.OrderID;
