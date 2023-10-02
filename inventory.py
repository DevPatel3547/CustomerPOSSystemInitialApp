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
    {"Name": "Straws", "Category": "Disposable", "Quantity": 1000, "Price": 0.05},
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

print("Inventory items have been written to inventory.csv") ##output to show it is complete
