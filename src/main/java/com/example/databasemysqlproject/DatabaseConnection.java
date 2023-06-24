package com.example.databasemysqlproject;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashSet;
import java.util.Set;

public class DatabaseConnection {
    public Connection connection;

    public DatabaseConnection () throws SQLException {
        String dbName = "mystaffinfo";
        connect(null);
        if (!getDatabases().contains(dbName)) {
            createDatabase();
        }
        Statement stmt = connection.createStatement();
        stmt.execute("use mystaffinfo");
    }

    private void connect(String dbName) throws SQLException {
        dbName = (dbName == null) ? "" : "/" + dbName;
        String connectStr = "jdbc:mysql://localhost:3306" + dbName;
        connection = DriverManager.getConnection(connectStr);
        //replace 'root' with any 'user' with MySQL permissions and 'adminadmin' with user password
        //connection = DriverManager.getConnection(connectStr, "root", "adminadmin");
    }

        //This block of code learned and implemented from Database.java supplement
        //Creates a database named 'mystaffinfo' with variables for this program
    public void createDatabase() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create database mystaffinfo");
        stmt.execute("use mystaffinfo");
        stmt.execute("create table staff" +
                "(id int primary key, " +
                "lastName varchar(15), " +
                "lastName varchar(15), " +
                "mi char(1), " +
                "address varchar(25), " +
                "city varchar(20), " +
                "state char(2), " +
                "telephone char(10), " +
                "email varchar(40))");
    }

        //This block of code learned and implemented from Database.java supplement
        //Creates a hashset of MySQL tables and returns their name as a string
    private Set<String> getDatabases() throws SQLException {
        HashSet<String> tableNames = new HashSet<>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("show databases");
        while (rs.next()) {
            tableNames.add(rs.getString(1));
        }
        return tableNames;
    }

    public String[] viewInfo(int id) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT lastName, firstName, mi, " +
                "address, city, state, telephone, email FROM staff WHERE id = "+ id +" ");
        String[] str = new String[8];

        while(resultSet.next()) {
            for (int i =0; i<8;i++) {
                str[i] = resultSet.getString(i+1);
            }
        }
        return str;
    }

    public void insertInfo(Staff staff) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("INSERT INTO staff (id, lastName, firstName, mi, address, city, state, telephone, email) "
                + "VALUES ("+ staff.getId()
                + ", \"" + staff.getLastName()
                + "\", \"" + staff.getFirstName()
                + "\", \"" + staff.getMi()
                + "\", \"" + staff.getAddress()
                + "\", \"" + staff.getCity()
                + "\", \"" + staff.getState()
                + "\", \"" + staff.getTelephone()
                + "\", \"" + staff.getEmail()
                + "\")");
    }

    public void updateInfo(Staff staff) throws SQLException {
        Statement stmt = connection.createStatement();
        if(!staff.getLastName().equals("") || staff.getLastName()==null)
        {stmt.execute("UPDATE staff SET lastName = \""+ staff.getLastName() + "\" WHERE id = "+ staff.getId() +" ");}
        if(!staff.getFirstName().equals("") || staff.getFirstName()==null)
        {stmt.execute("UPDATE staff SET firstName = \""+ staff.getFirstName() + "\" WHERE id = "+ staff.getId() +" ");}
        if(!staff.getMi().equals("") || staff.getMi()==null)
        {stmt.execute("UPDATE staff SET mi = \""+ staff.getMi() + "\" WHERE id = "+ staff.getId() +" ");}
        if(!staff.getAddress().equals("") || staff.getAddress()==null)
        {stmt.execute("UPDATE staff SET address = \""+ staff.getAddress() + "\" WHERE id = "+ staff.getId() +" ");}
        if(!staff.getCity().equals("") || staff.getCity()==null)
        {stmt.execute("UPDATE staff SET city = \""+ staff.getCity() + "\" WHERE id = "+ staff.getId() +" ");}
        if(!staff.getState().equals("") || staff.getState()==null)
        {stmt.execute("UPDATE staff SET state = \""+ staff.getState() + "\" WHERE id = "+ staff.getId() +" ");}
        if(!staff.getTelephone().equals("") || staff.getTelephone()==null)
        {stmt.execute("UPDATE staff SET telephone = \""+ staff.getTelephone() + "\" WHERE id = "+ staff.getId() +" ");}
        if(!staff.getEmail().equals("") || staff.getEmail()==null)
        {stmt.execute("UPDATE staff SET email = \""+ staff.getEmail() + "\" WHERE id = "+ staff.getId() +" ");}
    }
}