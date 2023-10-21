import csv

# Define a list of toppings and their prices
toppings = ["Creme Brulee", "Coconut Jelly", "Rainbow Jelly", "Crystal Boba", "Extra Boba", "Extra Extra Boba", "Coconut Jelly", "Snow Velvet", "Brown Sugar Boba", "Reg Boba", "Extra Strawberry", "Lychee Bits"]
toppings_price = [0.80, 0.60, 0.60, 0.80, 0.60, 1.20, 0.60, 0.60, 0.80, 0.60, 0.60, 0.60]

# Create a list to store the number sold during the day for toppings (you can fill this with actual data)
toppings_sold = [100, 80, 120, 90, 60, 40, 75, 110, 95, 85, 70, 55]

# Define a list of boba tea flavors and their prices
boba_flavors = ["Brown Sugar Deerioca Creme Brulee Milk", "Brown Sugar Deerioca Milk", "Ube Creme Brulee Brown Sugar Deerioca Milk", "Ube Taro Brown Sugar Deerioca Milk", "Cocoa Brown Sugar Deerioca Milk", "Matcha Brown Sugar Deerioca Milk", "Peach Oolong Tea", "Jasmine Green Tea", "Royal No. 9 Black Tea", "The Alley Assam Black Tea", "Mango LuLu", "Strawberry Lulu", "Orange Lulu", "Iced Peach Oolong Grape LuLu", "Lychee LuLu", "Snow Velvet Grape LuLu", "Snow Velvet Peach Oolong Tea", "Snow Velvet Jasmine Green Tea", "Snow Velvet Royal No 9", "Snow Velvet Assam Black Tea", "Royal No. 9 Milk Tea", "The Alley Assam Milk Tea", "Jasmine Green Milk Tea", "The Alley Trio", "Lychee Green Tea", "Mango Green Tea with Jelly Ball", "Passion Fruit Green Tea", "Original Yogurt Purple Rice", "Create Your Own Yogurt", "Matcha Purple Rice Yogurt", "Peach Oolong Purple Rice Yogurt", "Yogurt Grape LuLu", "Deerioca Creme Brulee Cold Brew", "Brown Sugar Cream Cold Brew", "Cocoa Cream Cold Brew", "Milk Tea Cold Brew", "Snow Velvet Cream Cold Brew", "Mango Frappe", "Peach Frappe", "Taro Milk Tea", "Taro Coconut Milk Tea", "Taro Smoothie"]
boba_price = [5.99, 5.99, 6.45, 6.45, 5.99, 5.99, 5.25, 4.99, 4.99, 4.99, 6.99, 6.59, 6.59, 6.25, 7.25, 6.25, 5.99, 5.99, 5.99, 5.99, 4.99, 4.99, 4.99, 4.99, 6.59, 6.59, 6.59, 6.99, 6.99, 6.99, 6.99, 6.99, 5.99, 5.75, 5.99, 5.99, 5.99, 6.95, 6.95, 6.45, 6.45, 6.45]

# Create a list to store the number sold during the day for drinks (you can fill this with actual data)
drinks_sold = [200, 180, 220, 150, 160, 210, 240, 195, 205, 190, 175, 185, 230, 215, 205, 210, 220, 230, 240, 180, 195, 190, 185, 220, 215, 200, 210, 195, 190, 180, 175, 210, 230, 220, 205, 215, 240, 185, 190, 195, 200, 175, 220]

# Define a list of ingredients for each drink
drink_ingredients = [
    ["Brown Sugar", "Milk"],
    ["Brown Sugar", "Milk"],
    ["Brown Sugar", "Milk"],
    ["Brown Sugar", "Milk"],
    ["Cocoa", "Brown Sugar", "Milk"],
    ["Matcha", "Brown Sugar", "Milk"],
    ["Peach", "Brew Tea"],
    ["Brew Tea"],
    ["Brew Tea"],
    ["Brew Tea"],
    ["Mango", "LuLu"],
    ["Strawberry", "LuLu"],
    ["Orange", "LuLu"],
    ["Peach", "Milk Tea", "LuLu"],
    ["Lychee", "LuLu"],
    ["Snow Velvet", "LuLu"],
    ["Snow Velvet", "Peach", "Milk Tea"],
    ["Snow Velvet", "Milk Tea"],
    ["Snow Velvet"],
    ["Snow Velvet", "Milk Tea"],
    [ "Milk Tea"],
    [ "Milk Tea"],
    [ "Milk Tea"],
    ["Milk Tea"],
    ["Lychee", "Milk Tea"],
    ["Mango", "Milk Tea", "Pearl"],
    ["Milk Tea"],
    ["Yogurt"],
    ["Create Your Own Yogurt"],
    ["Matcha", "Yogurt"],
    ["Peach","Yogurt"],
    ["Yogurt", "LuLu"],
    ["Deerioca", "Brew Tea"],
    ["Brown Sugar", "Creamer", "Brew Tea"],
    ["Cocoa", "Creamer", "Brew Tea"],
    ["Milk Tea", "Brew Tea"],
    ["Snow Velvet", "Creamer", "Brew Tea"],
    ["Frappe"],
    ["Frappe"],
    [ "Milk Tea"],
    ["Cocoa", "Milk Tea"],
    ["Smoothie"]
]

# Create a list of dictionaries to represent the menu
menu = []

# Add toppings to the menu
for i in range(len(toppings)):
    item = {
        "Type": "Topping",
        "Name of Item": toppings[i],
        "Cost of Item": toppings_price[i],
        "Numbers Sold During Day": toppings_sold[i],
        "Ingredients": "N/A"  # Toppings do not have ingredients
    }
    menu.append(item)

# Add drinks to the menu
for i in range(len(boba_flavors)):
    item = {
        "Type": "Drink",
        "Name of Item": boba_flavors[i],
        "Cost of Item": boba_price[i],
        "Numbers Sold During Day": drinks_sold[i],
        "Ingredients": ", ".join(drink_ingredients[i])  # Join ingredients into a comma-separated string
    }
    menu.append(item)

# Write the menu to a single CSV file
csv_file = "menu.csv"
with open(csv_file, mode='w', newline='') as file:
    fieldnames = ["Type", "Name of Item", "Cost of Item", "Numbers Sold During Day", "Ingredients"]
    writer = csv.DictWriter(file, fieldnames=fieldnames)
    writer.writeheader()
    for item in menu:
        writer.writerow(item)