## Run by typing: python inventory.py   ##
## Then look at csv file ##

import csv

# Define a list of inventory items
inventory_items = [

    # Types of drink , each drink will have one type classification     
    {"Name": "Brew Tea", "Category": "Type", "Quantity": 500, "Price": 5.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Deerioca", "Category": "Type", "Quantity": 500, "Price": 5.00, "min": 10, "threshold": 50, "needRestock": "No"},    
    {"Name": "Lulu", "Category": "Type", "Quantity": 500, "Price": 5.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Velvet", "Category": "Type", "Quantity": 500, "Price": 5.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Milk Tea", "Category": "Type", "Quantity": 500, "Price": 5.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Yogurt", "Category": "Type", "Quantity": 500, "Price": 5.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Frappe", "Category": "Type", "Quantity": 500, "Price": 5.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Cold Brew", "Category": "Type", "Quantity": 500, "Price": 5.00, "min": 10, "threshold": 50, "needRestock": "No"},

    #recipe for drinks
    {"Name": "Brown Sugar", "Category": "Recipe", "Quantity": 500, "Price": 1.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Regular Sugar", "Category": "Recipe", "Quantity": 500, "Price": 1.00, "min": 10, "threshold": 50, "needRestock": "No"},

    {"Name": "Milk", "Category": "Recipe", "Quantity": 500, "Price": 1.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Creamer", "Category": "Recipe", "Quantity": 500, "Price": 1.00, "min": 10, "threshold": 50, "needRestock": "No"},

    {"Name": "Boba", "Category": "Recipe", "Quantity": 500, "Price": 1.00, "min": 10, "threshold": 50, "needRestock": "No"}, #also in custom amounts
    {"Name": "Jelly", "Category": "Recipe", "Quantity": 500, "Price": 1.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Cocoa", "Category": "Recipe", "Quantity": 500, "Price": 1.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Matcha", "Category": "Recipe", "Quantity": 500, "Price": 1.00, "min": 10, "threshold": 50, "needRestock": "No"},

    #Fruits for Lulu Type Drinks
    {"Name": "Mango", "Category": "Fruit", "Quantity": 500, "Price": 1.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Strawberry", "Category": "Fruit", "Quantity": 500, "Price": 1.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Lychee", "Category": "Fruit", "Quantity": 500, "Price": 1.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Peach", "Category": "Fruit", "Quantity": 500, "Price": 1.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Grape", "Category": "Fruit", "Quantity": 500, "Price": 1.00, "min": 10, "threshold": 50, "needRestock": "No"},

    # Custom Amounts
    {"Name": "Ice", "Category": "Ingredient", "Quantity": 500, "Price": 1.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Sugar", "Category": "Ingredient", "Quantity": 500, "Price": 1.00, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Boba", "Category": "Ingredient", "Quantity": 1000, "Price": 1.00, "min": 10, "threshold": 100, "needRestock": "No"},

    # Milk
    {"Name": "Organic Milk", "Category": "Milk", "Quantity": 70, "Price": 9.05, "min": 10, "threshold": 7, "needRestock": "No"},
    {"Name": "Soy Milk", "Category": "Milk", "Quantity": 70, "Price": 10.00, "min": 10, "threshold": 7, "needRestock": "No"},
    {"Name": "Oat Milk", "Category": "Milk", "Quantity": 70, "Price": 10.05, "min": 10, "threshold": 7, "needRestock": "No"},

    # Cup Size
    {"Name": "Large", "Category": "Cup", "Quantity": 500, "Price": 0.50, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Regular", "Category": "Cup", "Quantity": 500, "Price": 0.40, "min": 10, "threshold": 50, "needRestock": "No"},

    # Other disposables
    {"Name": "Napkins", "Category": "Disposable", "Quantity": 5000, "Price": 0.02, "min": 10, "threshold": 500, "needRestock": "No"},
    {"Name": "Sanitation Wipes", "Category": "Disposable", "Quantity": 3000, "Price": 0.50, "min": 10, "threshold": 300, "needRestock": "No"},
    {"Name": "Trash Bags", "Category": "Disposable", "Quantity": 400, "Price": 0.70, "min": 10, "threshold": 40, "needRestock": "No"},
    {"Name": "Straws", "Category": "Disposable", "Quantity": 400, "Price": 0.70, "min": 10, "threshold": 40, "needRestock": "No"},
    {"Name": "Paper Towels", "Category": "Disposable", "Quantity": 400, "Price": 0.70, "min": 10, "threshold": 40, "needRestock": "No"},

    # Add-Ons
    {"Name": "Coconut Jelly", "Category": "Add-Ons", "Quantity": 500, "Price": 0.60, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Snow Velvet", "Category": "Add-Ons", "Quantity": 500, "Price": 0.60, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Rainbow Jelly", "Category": "Add-Ons", "Quantity": 500, "Price": 0.60, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Brown Sugar Boba", "Category": "Add-Ons", "Quantity": 500, "Price": 0.80, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Crystal Boba", "Category": "Add-Ons", "Quantity": 500, "Price": 0.80, "min": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Creme Brulee", "Category": "Add-Ons", "Quantity": 500, "Price": 0.80, "min": 10, "threshold": 50, "needRestock": "No"},
]

def need_restock(quantity, threshold):
    if quantity <= threshold:
        return "Yes"
    else:
        return "No"

# Update the needRestock column based on the "min" value
for item in inventory_items:
    item["needRestock"] = need_restock(item["Quantity"], item["min"])

# Write the inventory items to a CSV file
csv_file = "inventory.csv"
with open(csv_file, mode='w', newline='') as file:
    fieldnames = ["Name", "Category", "Quantity", "Price", "min", "threshold", "needRestock"]
    writer = csv.DictWriter(file, fieldnames=fieldnames)
    writer.writeheader()

    for item in inventory_items:
        writer.writerow(item)

print("Inventory items have been written to inventory.csv")