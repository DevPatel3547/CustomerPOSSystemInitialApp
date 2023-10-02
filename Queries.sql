-- Query 1: Retrieve all records from OrderHistory
SELECT * FROM OrderHistory;

-- Query 2: Count the total number of orders
SELECT COUNT(*) FROM OrderHistory;

-- Query 3: List all distinct flavors available in the history
SELECT DISTINCT Flavor FROM OrderHistory;

-- Query 4: Count the number of orders per flavor
SELECT Flavor, COUNT(*) AS OrderCount FROM OrderHistory GROUP BY Flavor;

-- Query 5: Find the date when "Milk Tea" was last ordered
SELECT MAX(Date) FROM OrderHistory WHERE Flavor = 'Milk Tea';

-- Query 6: Find all the orders that have "Pearls" as a topping
SELECT * FROM OrderHistory WHERE Toppings LIKE '%Pearls%';

-- Query 7: List the top 3 most ordered flavors
SELECT Flavor, COUNT(*) AS OrderCount FROM OrderHistory GROUP BY Flavor ORDER BY OrderCount DESC LIMIT 3;

-- Query 8: Find the number of orders placed on a specific date 
SELECT COUNT(*) FROM OrderHistory WHERE Date = '2023-09-30';

-- Query 9: Retrieve all orders without toppings
SELECT * FROM OrderHistory WHERE Toppings = '' OR Toppings IS NULL;

-- Query 10: List the first 5 orders of "Matcha Latte"
SELECT * FROM OrderHistory WHERE Flavor = 'Matcha Latte' LIMIT 5;

-- Query 11: Find the average number of orders per day
SELECT AVG(OrderCount) FROM (SELECT Date, COUNT(*) AS OrderCount FROM OrderHistory GROUP BY Date) AS DailyOrders;




