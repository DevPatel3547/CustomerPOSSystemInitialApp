## Run by typing: python inventory.py   ##
## Then look at csv file ##

import csv

# Define a list of inventory items
inventory_items = [
    #Drink
    {"Name": "Flavor Name", "Category": "Flavor", "Quantity": 500, "Price": 5.00},

    # Custom Amounts
    {"Name": "Ice", "Category": "Ingredient", "Quantity": 500, "Price": 1.00},
    {"Name": "Sugar", "Category": "Ingredient", "Quantity": 500, "Price": 1.00},
    {"Name": "Boba", "Category": "Ingredient", "Quantity": 1000, "Price": 1.00},

    #Milk
    {"Name": "Organic Milk", "Category": "Milk", "Quantity": 70, "Price": 9.05},
    {"Name": "Soy Milk", "Category": "Milk", "Quantity": 70, "Price": 10.00},
    {"Name": "Oat Milk", "Category": "Milk", "Quantity": 70, "Price": 10.05},

    # Cup Size
    {"Name": "Large", "Category": "Cup", "Quantity": 500, "Price": 0.50},
    {"Name": "Regular", "Category": "Cup", "Quantity": 500, "Price": 0.40},

    # Other disposables
    {"Name": "Napkins", "Category": "Disposable", "Quantity": 5000, "Price": 0.02},
    {"Name": "Sanitation Wipes", "Category": "Disposable", "Quantity": 3000, "Price": 0.50},
    {"Name": "Trash Bags", "Category": "Disposable", "Quantity": 400, "Price": 0.70},
    {"Name": "Straws", "Category": "Disposable", "Quantity": 400, "Price": 0.70},
    {"Name": "Paper Towels", "Category": "Disposable", "Quantity": 400, "Price": 0.70},

    #Add-Ons
    {"Name": "Coconut Jelly", "Category": "Add-Ons", "Quantity": 500, "Price": 0.60},
    {"Name": "Snow Velvet", "Category": "Add-Ons", "Quantity": 500, "Price": 0.60},
    {"Name": "Rainbow Jelly", "Category": "Add-Ons", "Quantity": 500, "Price": 0.60},
    {"Name": "Brown Sugar Boba", "Category": "Add-Ons", "Quantity": 500, "Price": 0.80},
    {"Name": "Crystal Boba", "Category": "Add-Ons", "Quantity": 500, "Price": 0.80},
    {"Name": "Creme Brulee", "Category": "Add-Ons", "Quantity": 500, "Price": 0.80},
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
