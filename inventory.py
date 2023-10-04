## Run by typing: python inventory.py   ##
## Then look at csv file ##

import csv

# Define a list of inventory items
inventory_items = [
    {"Name": "Milk Tea", "Category": "Beverage", "Quantity": 50, "Price": 4.50},
    {"Name": "Matcha Powder", "Category": "Ingredient", "Quantity": 20, "Price": 10.00},
    {"Name": "Taro Flavoring", "Category": "Ingredient", "Quantity": 15, "Price": 5.00},
    {"Name": "Pearls", "Category": "Topping", "Quantity": 100, "Price": 0.50},
    {"Name": "Cups", "Category": "Disposable", "Quantity": 500, "Price": 0.10},
    {"Name": "Large Cups", "Category": "Disposable", "Quantity": 500, "Price": 0.12},
    {"Name": "Straws", "Category": "Disposable", "Quantity": 1000, "Price": 0.05},
    {"Name": "Large Straws", "Category": "Disposable", "Quantity": 1000, "Price": 0.07},
    {"Name": "Napkins", "Category": "Disposable", "Quantity": 5000, "Price": 0.02},
    {"Name": "Jelly", "Category": "Topping", "Quantity": 200, "Price": 6.00},
    {"Name": "Pudding", "Category": "Topping", "Quantity": 150, "Price": 5.50},
    {"Name": "Grass Jelly", "Category": "Topping", "Quantity": 250, "Price": 2.00},
    {"Name": "Red Bean", "Category": "Topping", "Quantity": 300, "Price": 3.23},
    {"Name": "Lychee Fruits", "Category": "Topping", "Quantity": 90, "Price": 7.05},
    {"Name": "Toilet Paper", "Category": "Disposable", "Quantity": 2000, "Price": 0.10},
    {"Name": "Dome Lid", "Category": "Disposable", "Quantity": 1000, "Price": 0.04},
    {"Name": "Sealing Film", "Category": "Disposable", "Quantity": 6000, "Price": 0.05},
    {"Name": "Bubble Tea Syrup", "Category": "Ingredient", "Quantity": 4000, "Price": 1.00},
    {"Name": "Bubble Tea Creamer", "Category": "Ingredient", "Quantity": 2000, "Price": 2.00},
    {"Name": "Sanitation Wipes", "Category": "Disposable", "Quantity": 3000, "Price": 0.50},
    {"Name": "Trash Bags", "Category": "Disposable", "Quantity": 400, "Price": 0.70},
    {"Name": "Brown Sugar", "Category": "Ingredient", "Quantity": 400, "Price": 6.50},
    {"Name": "Ice", "Category": "Ingredient", "Quantity": 100, "Price": 0.01},
    {"Name": "Strawberries", "Category": "Ingredient", "Quantity": 100, "Price": 5.45},
    {"Name": "Organic Milk", "Category": "Ingredient", "Quantity": 70, "Price": 9.05},
    {"Name": "Soy Milk", "Category": "Ingredient", "Quantity": 70, "Price": 10.00},
    {"Name": "Oat Milk", "Category": "Ingredient", "Quantity": 70, "Price": 10.05},

    # will need to add more items
]

# Write the inventory items to a CSV file
csv_file = "inventory.csv"
with open(csv_file, mode='w', newline='') as file:
    fieldnames = ["Name", "Category", "Quantity", "Price"]
    writer = csv.DictWriter(file, fieldnames=fieldnames)
    writer.writeheader()

    for item in inventory_items:
        writer.writerow(item)

print("Inventory items have been written to inventory.csv") ##output to show it is complete\\\
