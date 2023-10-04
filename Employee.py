import csv

# Define employee data
employee_data = [
    {"Role": "manager", "Password": "password1", "Name": "John Doe"},
    {"Role": "cashier", "Password": "password2", "Name": "Jane Smith"},
    {"Role": "cashier", "Password": "password3", "Name": "Alice Johnson"},
    # Add more employees as needed
]

# Write the employee data to a CSV file
csv_file = "employee_file.csv"
with open(csv_file, mode='w', newline='') as file:
    fieldnames = ["Role", "Password", "Name"]
    writer = csv.DictWriter(file, fieldnames=fieldnames)
    writer.writeheader()
    for employee in employee_data:
        writer.writerow(employee)


