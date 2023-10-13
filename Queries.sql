@@ -1,41 +1,46 @@
-- Query 1: Retrieve all records from OrderHistory
SELECT * FROM OrderHistory;
-- Query 2: Count the total number of orders
SELECT COUNT(*) FROM OrderHistory;
-- Query 3: List all distinct flavors available in the history
SELECT DISTINCT Flavor FROM OrderHistory;
-- Query 4: Count the number of orders per flavor
SELECT Flavor, COUNT(*) AS OrderCount FROM OrderHistory GROUP BY Flavor;
-- Query 5: Find all the orders that have "Pearls" as a topping
SELECT * FROM OrderHistory WHERE Toppings LIKE '%Pearls%';
-- Query 6: List the top 3 most ordered flavors
SELECT Flavor, COUNT(*) AS OrderCount FROM OrderHistory GROUP BY Flavor ORDER BY OrderCount DESC LIMIT 3;
-- Query 7: Find the number of orders placed on a specific date 
SELECT COUNT(*) FROM OrderHistory WHERE Date = '2023-09-30';
-- Query 8: Retrieve all orders without toppings
SELECT * FROM OrderHistory WHERE Toppings = '' OR Toppings IS NULL;
-- Query 9: Find the average number of orders per day
SELECT AVG(OrderCount) FROM (SELECT Date, COUNT(*) AS OrderCount FROM OrderHistory GROUP BY Date) AS DailyOrders;
-- Query 10: Find the average number of orders without toppings
SELECT AVG(NoToppingsCount) AS AverageOrdersWithoutToppings FROM ( SELECT Date, COUNT(*) AS NoToppingsCount FROM OrderHistory WHERE Toppings = '' OR Toppings IS NULL GROUP BY Date ) AS OrdersWithoutToppings;
-- Query 11: Find all orders with toppings
SELECT * FROM OrderHistory WHERE Toppings IS NOT NULL AND Toppings != '';
-- Query 12: Find most common topping
SELECT Toppings, COUNT(*) AS ToppingCount FROM OrderHistory WHERE Toppings IS NOT NULL AND Toppings != '' GROUP BY Toppings ORDER BY ToppingCount DESC LIMIT 1;

-- Query 13: Realistic sales history(Req 2)
SELECT HOUR(Date) AS Hour, COUNT(*) AS OrderCount, SUM([Total Price]) AS TotalOrderPrice FROM OrderHistory GROUP BY Hour;
SELECT date_trunc('hour', Date::timestamp) AS HourStart,COUNT(*) AS OrderCount,SUM(totalprice) AS TotalSales
FROM OrderHistory
WHERE Date::timestamp >= NOW() - interval '1 day'
GROUP BY HourStart
ORDER BY HourStart;


--Query 14 2 Peak days(Req 3)
SELECT Date, SUM(totalprice) AS TotalSales FROM OrderHistory GROUP BY Date ORDER BY TotalSales DESC LIMIT 10;

--Query 15  20 items in inventory(Req 4)
SELECT COUNT(*) FROM Inventory;

--Query 16 Find the number of orders per week (Req 1)
SELECT date_trunc('week', Date::date) AS WeekStart,COUNT(*) AS OrderCount 
FROM OrderHistory
WHERE Date::date >= NOW() - interval '52 weeks'
GROUP BY WeekStart
ORDER BY WeekStart DESC;

-- Query 17 Find the Item and Quantity of a single row
SELECT Name, Quantity FROM Inventory LIMIT 1 OFFSET 2;


-- Query 18  looks at the menu table and gets every drink
SELECT "nameofitem" AS Drink, "cost" AS Price, "numbersoldtoday" AS SoldDuringDay FROM menu WHERE Type = 'Drink';

-- Query 19 gets the price of every drink
SELECT "nameofitem" AS Drink, "cost" AS Price FROM menu WHERE Type = 'Drink';

-- Query 20 get category for Beverages
SELECT * FROM inventory WHERE Category = 'Beverage';


-- Query 20 get category for ingredients
SELECT * FROM inventory WHERE Category = 'Ingredient';


-- Query 20 get category for milk
SELECT * FROM inventory WHERE Category = 'Milk';


-- Query 20 get category for toppings
SELECT * FROM inventory WHERE Category = 'Topping';

-- Query 20 get category for cups
SELECT * FROM inventory WHERE Category = 'Cup';


-- Query 20 get category for Disposables
SELECT * FROM inventory WHERE Category = 'Disposable';


-- Query 20 get category for add-ons
SELECT * FROM inventory WHERE Category = 'Add-Ons';

--Query 21 get category for milk and quantity is more than 0
SELECT * FROM inventory WHERE Category = 'Milk' AND Quantity > 0;

--Query 22 name is boba flavoring and quantity > 0
SELECT * FROM inventory WHERE Name = 'Boba Flavoring' AND Quantity > 0;


-- TEST QUERY 23
UPDATE inventory SET Quantity = 0 WHERE "Name" = 'Boba Flavoring';

-- Query 24
INSERT INTO OrderHistory (date, flavor, toppings, itemprice, totalprice) VALUES ('2022-10-06', 'Taro Smoothie', 'Rainbow Jelly, Crystal Boba', 7.85, 7.85);