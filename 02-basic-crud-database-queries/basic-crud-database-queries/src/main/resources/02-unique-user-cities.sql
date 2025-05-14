-- Using the Users table, write a query to list all the unique cities where users are located, and sort
-- the list in alphabetical order.
SELECT DISTINCT City
FROM Users
WHERE City IS NOT NULL
ORDER BY City ASC;
