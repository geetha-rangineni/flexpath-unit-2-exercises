-- Using the OrderItems table, write a query to find products that have been ordered more than once.
-- Be sure to sum all Quantity fields for each ProductID to determine the total quantity ordered.
-- The result should display the ProductID and the TotalQuantityOrdered ONLY for products that have
-- been ordered more than once.
-- Order the results by ProductID in ascending order.
SELECT
    ProductID,
    SUM(Quantity) AS TotalQuantityOrdered
FROM OrderItems
GROUP BY ProductID
HAVING TotalQuantityOrdered > 1
ORDER BY ProductID ASC;
