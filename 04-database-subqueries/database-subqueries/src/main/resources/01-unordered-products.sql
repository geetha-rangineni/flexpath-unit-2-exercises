-- Write a query to get all products including the ProductID, and the ProductName that are not in the OrderItems table
-- Use a subquery to get the ProductID values from the OrderItems table.
-- Order the results by ProductID in ascending order.
SELECT *
FROM Products
WHERE ProductID NOT IN (
    SELECT DISTINCT ProductID
    FROM OrderItems
);
