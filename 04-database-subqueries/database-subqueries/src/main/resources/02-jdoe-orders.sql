-- Write a query that gets all orders from the User with the username 'jdoe'
-- Include the OrderID, and ShippingAddress
-- Use a subquery to get the UserID for the user with the username 'jdoe' from the Users table
-- Order the results by OrderID in descending order

SELECT *
FROM Orders
WHERE UserID = (
    SELECT UserID
    FROM Users
    WHERE UserName = 'johndoe'
);
