Piyush Piyush(22050267)
Some help is taken from GPT4 for generating some code and correcting errors.

Pizza Ordering System (JavaFX + MySQL)

This project is a GUI-based Pizza Ordering System built using **JavaFX** and connected to a **MySQL database** using **JDBC**.

---

Key Features

- JavaFX GUI with input fields and a table to manage pizza orders
- Add, update, delete, and view pizza order records
- Stores data like customer name, mobile number, pizza size, toppings, and total bill
- MySQL used for persistent backend storage
- JDBC used for database communication

---

 Requirements

- Java JDK 8 or above
- JavaFX SDK
- MySQL Server
- MySQL Connector/J (JDBC Driver)
- IDE (e.g., IntelliJ, Eclipse, or Replit)
- Optional: Maven for dependency management

---

Project Structure
PizzaOrderingSystem/
├── Main.java # JavaFX application setup
├── PizzaOrder.java # Data model class
├── mysql-connector.jar # MySQL JDBC Driver
├── pom.xml # Maven config (optional)
└── README.md


---

Database Setup

Run this SQL in MySQL:

```sql
CREATE DATABASE pizza_db;

USE pizza_db;

CREATE TABLE pizza_orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(50),
    mobile_number VARCHAR(15),
    pizza_size VARCHAR(5),
    toppings_count INT,
    total_bill DECIMAL(5,2)
);

INSERT INTO pizza_orders (customer_name, mobile_number, pizza_size, toppings_count, total_bill) VALUES
('Piyush', '3653417123', 'XL', 3, 22.43),
('Preet', '6478917456', 'XL', 1, 18.98),
('Param', '6772687981', 'L', 4, 20.70);

DATABASE CONNECTION
String url = "jdbc:mysql://localhost:3306/pizza_db";
String user = "sharmapiyush_17";
String password = "Piyush@123";
Connection conn = DriverManager.getConnection(url, user, password);

▶️ Running the Application
If using an IDE:
Import the project

Add mysql-connector JAR to your project library

Run Main.java

If using terminal:
javac -cp .:mysql-connector-j-8.0.29.jar Main.java PizzaOrder.java
java -cp .:mysql-connector-j-8.0.29.jar Main




