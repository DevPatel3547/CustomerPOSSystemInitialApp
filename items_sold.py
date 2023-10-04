import csv

# Define a list of inventory items
inventory_items = [
    {"Name": "Milk Tea", "Category": "Beverage", "Quantity": 50, "Price": 4.50},
    {"Name": "Matcha Powder", "Category": "Ingredient", "Quantity": 20, "Price": 10.00},
    {"Name": "Taro Flavoring", "Category": "Ingredient", "Quantity": 15, "Price": 5.00},
    # Add more inventory items as needed
]

# Load the current inventory data from the CSV file into a list of dictionaries
csv_file = "inventory.csv"
with open(csv_file, mode='r') as file:
    reader = csv.DictReader(file)
    for row in reader:
        item_name = row["Name"]
        item_category = row["Category"]
        item_quantity = int(row["Quantity"])
        item_price = float(row["Price"])
        inventory_items.append({"Name": item_name, "Category": item_category, "Quantity": item_quantity, "Price": item_price})

# Define sales data (example)
sales_data = [
    {"Item Name": "Milk Tea", "Quantity Sold": 5},
    {"Item Name": "Matcha Powder", "Quantity Sold": 2},
    # Add more sales data as needed
]

# Create a new list to store sales data
new_sales_data = []

# Update the inventory based on sales data
for sale in sales_data:
    item_name = sale["Item Name"]
    quantity_sold = sale["Quantity Sold"]

    for item in inventory_items:
        if item["Name"] == item_name:
            if item["Quantity"] >= quantity_sold:
                item["Quantity"] -= quantity_sold
                new_sales_data.append({"Item Name": item_name, "Quantity Sold": quantity_sold})
            else:
                print(f"Error: Not enough {item_name} in stock for the sale.")
            break
    else:
        print(f"Error: {item_name} not found in inventory.")

# Update the inventory CSV file with the new inventory data
with open(csv_file, mode='w', newline='') as file:
    fieldnames = ["Name", "Category", "Quantity", "Price"]
    writer = csv.DictWriter(file, fieldnames=fieldnames)
    writer.writeheader()

    for item in inventory_items:
        writer.writerow(item)

# Write the new sales data to a CSV file
sales_csv_file = "sales_data.csv"
with open(sales_csv_file, mode='w', newline='') as file:
    fieldnames = ["Item Name", "Quantity Sold"]
    writer = csv.DictWriter(file, fieldnames=fieldnames)
    writer.writeheader()

    for sale in new_sales_data:
        writer.writerow(sale)

print("Inventory items and sales data have been updated.")
print("Sales data has been written to sales_data.csv.")

