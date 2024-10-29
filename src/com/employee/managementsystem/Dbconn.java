package com.employee.managementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Dbconn {
    Connection connection;
    Statement statement;

    public Dbconn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemanagement","root","lokesh1207");
            statement = connection.createStatement();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
