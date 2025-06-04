# Wzorzec Projektowy Repository z użyciem JDBC dla MariaDB

Ten samouczek pokazuje, jak zaimplementować wzorzec projektowy Repository używając czystego JDBC (Java Database Connectivity) do obsługi operacji na bazie danych MariaDB. Zawiera krok po kroku instrukcje oraz przykłady kodu do obsługi tabel: `customers`, `addresses`, `products`, `inventory`, `orders` oraz `order_items`.

## 📌 Wymagania wstępne

* Java Development Kit (JDK 8+)
* Zainstalowana i uruchomiona baza MariaDB
* Sterownik JDBC dla MariaDB (np. `mariadb-java-client-<version>.jar`)
* IDE lub edytor tekstu

---

## 🛠️ Konfiguracja Projektu

1. **Utwórz strukturę katalogów projektu Java:**

```
src/
├── dao/
├── model/
└── util/
```

2. **Dodaj sterownik JDBC za pomocą Maven:**
   W pliku `pom.xml` dodaj zależność do sterownika MariaDB:

```xml
<dependency>
    <groupId>org.mariadb.jdbc</groupId>
    <artifactId>mariadb-java-client</artifactId>
    <version>3.3.1</version>
</dependency>
```

3. **Utwórz bazę danych i załaduj dane:**
   Wykonaj skrypty `01_create_tables.sql`, `02_add_foreign_keys.sql` oraz `sample-inserts.sql` w środowisku MariaDB.

---

## 🔌 Klasa pomocnicza do połączenia z bazą

**`util/DBConnection.java`**

```java
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/your_database";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

---

## 🧩 Przykładowy model danych

**`model/Customer.java`**

```java
package model;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String registrationDate;

    // Getters and setters
}
```

---

## 📦 Interfejs repozytorium

**`dao/CustomerRepository.java`**

```java
package dao;

import model.Customer;
import java.util.List;

public interface CustomerRepository {
    Customer findById(int id);
    List<Customer> findAll();
    void save(Customer customer);
    void update(Customer customer);
    void delete(int id);
}
```

---

## 🧱 Implementacja repozytorium (JDBC)

**`dao/CustomerRepositoryImpl.java`**

```java
package dao;

import model.Customer;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public Customer findById(int id) {
        Customer customer = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE customer_id = ?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setRegistrationDate(rs.getString("registration_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setRegistrationDate(rs.getString("registration_date"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customers (first_name, last_name, email) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE customers SET first_name = ?, last_name = ?, email = ? WHERE customer_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setInt(4, customer.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

---

## 🚀 Co dalej?

* Zaimplementuj analogiczne repozytoria dla pozostałych tabel.
* Dodaj klasę `main()` do testowania działania metod.
* Dodaj obsługę wyjątków i logowanie.

Czy chcesz, żebym teraz dodał implementacje dla kolejnych tabel (np. `orders`, `products`) lub klasę testującą działanie repozytorium?
