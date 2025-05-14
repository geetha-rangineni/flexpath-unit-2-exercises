SELECT o.OrderID, u.FirstName, u.LastName
FROM Orders o
JOIN Users u ON o.UserID = u.UserID
WHERE o.OrderID IN (1, 2)
ORDER BY o.OrderID;
