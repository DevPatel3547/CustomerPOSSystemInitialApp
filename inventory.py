## Run by typing: python inventory.py   ##
## Then look at csv file ##

import csv

# Define a list of inventory items
inventory_items = [
    # Beverages
    {"Name": "Milk Tea", "Category": "Beverage", "Quantity": 50, "Price": 4.50},

    # Ingredients
    {"Name": "Matcha Powder", "Category": "Ingredient", "Quantity": 20, "Price": 10.00},
    {"Name": "Taro Flavoring", "Category": "Ingredient", "Quantity": 15, "Price": 5.00},
    {"Name": "Bubble Tea Syrup", "Category": "Ingredient", "Quantity": 4000, "Price": 1.00},
    {"Name": "Bubble Tea Creamer", "Category": "Ingredient", "Quantity": 2000, "Price": 2.00},
    {"Name": "Brown Sugar", "Category": "Ingredient", "Quantity": 400, "Price": 6.50},
    {"Name": "Strawberries", "Category": "Ingredient", "Quantity": 100, "Price": 5.45},
    {"Name": "Ice", "Category": "Ingredient", "Quantity": 500, "Price": 1.00},
    {"Name": "Sugar", "Category": "Ingredient", "Quantity": 500, "Price": 1.00},
    {"Name": "Boba", "Category": "Ingredient", "Quantity": 1000, "Price": 1.00},

    #Milk
    {"Name": "Organic Milk", "Category": "Milk", "Quantity": 70, "Price": 9.05},
    {"Name": "Soy Milk", "Category": "Milk", "Quantity": 70, "Price": 10.00},
    {"Name": "Oat Milk", "Category": "Milk", "Quantity": 70, "Price": 10.05},

    # Toppings
    {"Name": "Pearls", "Category": "Topping", "Quantity": 100, "Price": 0.50},
    {"Name": "Jelly", "Category": "Topping", "Quantity": 200, "Price": 6.00},
    {"Name": "Pudding", "Category": "Topping", "Quantity": 150, "Price": 5.50},
    {"Name": "Grass Jelly", "Category": "Topping", "Quantity": 250, "Price": 2.00},
    {"Name": "Red Bean", "Category": "Topping", "Quantity": 300, "Price": 3.23},
    {"Name": "Lychee Fruits", "Category": "Topping", "Quantity": 90, "Price": 7.05},

    #Note: need to look into pricing
    # Cup Size
    {"Name": "Large", "Category": "Cup", "Quantity": 500, "Price": 0.50},
    {"Name": "Regular", "Category": "Cup", "Quantity": 500, "Price": 0.40},

    # Other disposables
    {"Name": "Napkins", "Category": "Disposable", "Quantity": 5000, "Price": 0.02},
    {"Name": "Sanitation Wipes", "Category": "Disposable", "Quantity": 3000, "Price": 0.50},
    {"Name": "Trash Bags", "Category": "Disposable", "Quantity": 400, "Price": 0.70},

    # Customization Options

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
