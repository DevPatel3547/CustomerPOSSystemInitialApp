## Run by typing: python generate_order_history.py   ##
## Then look at order_history.csv file ##

import random
import csv
from datetime import datetime, timedelta

# Define a list of boba tea flavors
boba_flavors = ["Milk Tea", "Matcha Latte", "Taro Milk", "Thai Tea", "Brown Sugar Pearl", "Honey Lemon Green Tea", "Strawberry Smoothie", "Mango Slush"]

# Define List of boba flavor prices
boba_price = [4.50, 5.00, 4.75, 4.25, 5.50, 4.25, 5.25, 5.50]
    
# Define a list of toppings
toppings = ["Pearls", "Jelly", "Pudding", "Grass Jelly", "Red Bean", "Lychee Fruit"]

# define list of topping prices
toppings_price = [0.5, 0.75, 0.69, 0.80, 1.00, 1.25]

# Define the number of orders you want for the past 52 weeks (1 order per day)
num_orders = 7 * 52

# Define the start date (52 weeks ago from today)
start_date = datetime.now() - timedelta(weeks=52)
# get the price of the order
def calculate_order_total(order):
    total = 0
    for drink in order["Items"]:
        flavor = drink["Flavor"]
        toppings = drink["Toppings"]
      
        flavor_index = boba_flavors.index(flavor)
        flavor_price = boba_price[flavor_index]
        
        topping_price = 0
        for topping in toppings:
            topping_index = toppings.index(topping)
            topping_price += toppings_price[topping_index]
        total = flavor_price + topping_price
    
    return round(total, 2)

# Function to generate a random order
def generate_order():
    order_date = start_date + timedelta(days=random.randint(0, 365))
    order = {
        "Date": order_date.strftime("%Y-%m-%d"),
        "Items": []
    }
    
    num_drinks = random.randint(1, 5)  #adjust the range as needed
    for _ in range(num_drinks):
        drink = {
            "Flavor": random.choice(boba_flavors),
            "Toppings": random.sample(toppings, random.randint(0, 3))
        }
        order["Items"].append(drink)
    
    return order

# Generate orders and store them in a list
order_history = []
for _ in range(num_orders):
    order = generate_order()
    order_history.append(order)

# Sort orders by date
order_history.sort(key=lambda x: datetime.strptime(x["Date"], "%Y-%m-%d"))

# Write the order history to a CSV file
csv_file = "order_history.csv"
with open(csv_file, mode='w', newline='') as file:
    fieldnames = ["Date", "Flavor", "Toppings", "Total Price"]
    writer = csv.writer(file)
    writer.writerow(fieldnames)
    
    for order in order_history:
        date = order["Date"]
        for drink in order["Items"]:
            flavor = drink["Flavor"]
            toppings = ", ".join(drink["Toppings"])
            total_price = calculate_order_total(order)
            writer.writerow([date, flavor, toppings, total_price])
