## Run by typing: python inventory.py   ##
## Then look at csv file ##

import csv

# Define a list of inventory items
inventory_items = [
    # Drink
    {"Name": "Flavor Name", "Category": "Flavor", "Quantity": 500, "Price": 5.00, "Min_Quantity": 10,"threshold": 50,"needRestock": "No"},

    # Custom Amounts
    {"Name": "Ice", "Category": "Ingredient", "Quantity": 500, "Price": 1.00, "Min_Quantity": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Sugar", "Category": "Ingredient", "Quantity": 500, "Price": 1.00, "Min_Quantity": 10, "threshold": 50,"needRestock": "No"},
    {"Name": "Boba", "Category": "Ingredient", "Quantity": 1000, "Price": 1.00, "Min_Quantity": 10, "threshold": 100,"needRestock": "No"},

    # Milk
    {"Name": "Organic Milk", "Category": "Milk", "Quantity": 70, "Price": 9.05, "Min_Quantity": 10, "threshold": 7,"needRestock": "No"},
    {"Name": "Soy Milk", "Category": "Milk", "Quantity": 70, "Price": 10.00, "Min_Quantity": 10, "threshold": 7, "needRestock": "No"},
    {"Name": "Oat Milk", "Category": "Milk", "Quantity": 70, "Price": 10.05, "Min_Quantity": 10 , "threshold": 7,"needRestock": "No"},

    # Cup Size
    {"Name": "Large", "Category": "Cup", "Quantity": 500, "Price": 0.50, "Min_Quantity": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Regular", "Category": "Cup", "Quantity": 500, "Price": 0.40, "Min_Quantity": 10, "threshold": 50, "needRestock": "No"},

    # Other disposables
    {"Name": "Napkins", "Category": "Disposable", "Quantity": 5000, "Price": 0.02, "Min_Quantity": 10, "threshold": 500, "needRestock": "No"},
    {"Name": "Sanitation Wipes", "Category": "Disposable", "Quantity": 3000, "Price": 0.50, "Min_Quantity": 10, "threshold": 300, "needRestock": "No"},
    {"Name": "Trash Bags", "Category": "Disposable", "Quantity": 400, "Price": 0.70, "Min_Quantity": 10, "threshold": 40, "needRestock": "No"},
    {"Name": "Straws", "Category": "Disposable", "Quantity": 400, "Price": 0.70, "Min_Quantity": 10, "threshold": 40, "needRestock": "No"},
    {"Name": "Paper Towels", "Category": "Disposable", "Quantity": 400, "Price": 0.70, "Min_Quantity": 10, "threshold": 40, "needRestock": "No"},

    # Add-Ons
    {"Name": "Coconut Jelly", "Category": "Add-Ons", "Quantity": 500, "Price": 0.60, "Min_Quantity": 10,"threshold": 50,  "needRestock": "No"},
    {"Name": "Snow Velvet", "Category": "Add-Ons", "Quantity": 500, "Price": 0.60, "Min_Quantity": 10,"threshold": 50,  "needRestock": "No"},
    {"Name": "Rainbow Jelly", "Category": "Add-Ons", "Quantity": 500, "Price": 0.60, "Min_Quantity": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Brown Sugar Boba", "Category": "Add-Ons", "Quantity": 500, "Price": 0.80, "Min_Quantity": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Crystal Boba", "Category": "Add-Ons", "Quantity": 500, "Price": 0.80, "Min_Quantity": 10, "threshold": 50, "needRestock": "No"},
    {"Name": "Creme Brulee", "Category": "Add-Ons", "Quantity": 500, "Price": 0.80, "Min_Quantity": 10, "threshold": 50, "needRestock": "No"},
]




def need_restock(quantity, threshold):
    if quantity <=  threshold:
        return "Yes"
    else:
        return "No"

# Add the "needRestock" column to each item
for item in inventory_items:
    item["needRestock"] = need_restock(item["Quantity"], item["threshold"])

# Write the inventory items to a CSV file
csv_file = "inventory.csv"
with open(csv_file, mode='w', newline='') as file:
    fieldnames = ["Name", "Category", "Quantity", "Price", "Min_Quantity","threshold", "needRestock"]
    writer = csv.DictWriter(file, fieldnames=fieldnames)
    writer.writeheader()

    for item in inventory_items:
        writer.writerow(item)

print("Inventory items have been written to inventory.csv")

