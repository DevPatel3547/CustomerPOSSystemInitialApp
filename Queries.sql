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

-- Query 12: Find the average number of orders without toppings
SELECT AVG(NoToppingsCount) AS AverageOrdersWithoutToppings FROM ( SELECT Date, COUNT(*) AS NoToppingsCount FROM OrderHistory WHERE Toppings = '' OR Toppings IS NULL GROUP BY Date ) AS OrdersWithoutToppings;

-- Query 13: Find all orders with toppings
SELECT * FROM OrderHistory WHERE Toppings IS NOT NULL AND Toppings != '';

-- Query 14: Find most common topping
SELECT Toppings, COUNT(*) AS ToppingCount FROM OrderHistory WHERE Toppings IS NOT NULL AND Toppings != '' GROUP BY Toppings ORDER BY ToppingCount DESC LIMIT 1;

-- Query 15: Find the most expensive order
SELECT Date, Flavor, Toppings, MAX([Total Price]) AS MostExpensiveOrder FROM YourTableName WHERE [Total Price] = ( SELECT MAX([Total Price]) FROM YourTableName );

-- Query 16: Realistic sales history
SELECT HOUR(Date) AS Hour, COUNT(*) AS OrderCount, SUM([Total Price]) AS TotalOrderPrice FROM OrderHistory GROUP BY Hour;

--Query 17 2 Peak days
SELECT Date, SUM([Total Price]) AS DailyTotal FROM OrderHistory GROUP BY Date ORDER BY DailyTotal DESC LIMIT 2;

--Query 18  20 items in inventory
SELECT COUNT(*) FROM Inventory;

--Query 19 Find the number of orders per week
SELECT EXTRACT(YEAR FROM TO_DATE(Date, 'MM/DD/YYYY')) AS Year, EXTRACT(WEEK FROM TO_DATE(Date, 'MM/DD/YYYY')) AS Week, COUNT(*) AS OrderCount FROM OrderHistory GROUP BY Year, Week ORDER BY Year, Week;

-- Query 20 Find the Item and Quantity of a single row
SELECT Name, Quantity FROM Inventory LIMIT 1 OFFSET 2;

--Query 21 Find the number of orders per week
SELECT date_trunc('week', Date::date) AS WeekStart, COUNT(*) AS OrderCount FROM OrderHistory WHERE Date::date >= NOW() - interval '52 weeks' GROUP BY WeekStart ORDER BY WeekStart DESC;


