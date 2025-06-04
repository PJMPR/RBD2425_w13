package org.example;

import org.example.utils.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            Connection connection =DbConnection.getConnection();
            System.out.println(connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}