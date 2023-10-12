# 315--Project-2

generate_order_history.py: Wrote into csv file and randomly generates order history for the shop

inventory.py: Wrote into csv file and display the inventory to make each drink along with disposable items 

Queries.sql: List of queries for database

To log into database:
psql -h csce-315-db.engr.tamu.edu -U csce315_910_netID -d csce315331_10b_db

To run database.java:
//Windows: java -cp ".;postgresql-42.2.8.jar" Database
//Mac/Linux: java -cp ".:postgresql-42.2.8.jar" Database

Need to show we can edit a drink in order
Ex: go back to a drink in order and change it
Prove that order is linked to inventory (show that something isnt in stock or show that inventory stock changes as you order)