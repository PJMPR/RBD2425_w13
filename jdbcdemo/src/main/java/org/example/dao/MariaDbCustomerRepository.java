package org.example.dao;


import org.example.model.Customer;
import org.example.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MariaDbCustomerRepository{

    public Customer findById(int id) {
        Customer customer = null;
        try (Connection conn = DbConnection.getConnection();
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

    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DbConnection.getConnection();
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

    public void save(Customer customer) {
        String sql = "INSERT INTO customers (first_name, last_name, email) VALUES (?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Customer customer) {
        String sql = "UPDATE customers SET first_name = ?, last_name = ?, email = ? WHERE customer_id = ?";
        try (Connection conn = DbConnection.getConnection();
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

    public void delete(int id) {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
