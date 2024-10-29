package com.employee.managementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class Attendance extends JFrame implements ActionListener {
    JTable table;
    JButton markPresent, markAbsent, markLeave, viewRecords;
    Choice choiceEMP;
    String empId;

    Attendance(String empId) {
        this.empId = empId;
        setTitle("Attendance for Employee ID: " + empId);
        setSize(600, 400);
        setLayout(null);
        setLocation(400, 200);


        JLabel label = new JLabel("Attendance Records for Employee ID: " + empId);
        label.setBounds(20, 20, 300, 20);
        add(label);

        choiceEMP = new Choice();
        choiceEMP.setBounds(20, 240, 200, 30);
        populateEmployeeChoices(); // Populate with employee IDs or relevant options
        add(choiceEMP);


        table = new JTable();
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 50, 550, 200);
        add(sp);


        loadAttendanceData();


        markPresent = new JButton("Mark Present");
        markPresent.setBounds(20, 270, 120, 30);
        markPresent.addActionListener(this);
        add(markPresent);

        markAbsent = new JButton("Mark Absent");
        markAbsent.setBounds(160, 270, 120, 30);
        markAbsent.addActionListener(this);
        add(markAbsent);

        markLeave = new JButton("Mark Leave");
        markLeave.setBounds(300, 270, 120, 30);
        markLeave.addActionListener(this);
        add(markLeave);

        viewRecords = new JButton("View Records");
        viewRecords.setBounds(440, 270, 120, 30);
        viewRecords.addActionListener(this);
        add(viewRecords);

        setVisible(true);
    }

    private void populateEmployeeChoices() {
        try {
            Dbconn db = new Dbconn();
            String query = "SELECT empid FROM employee"; // Adjust query as needed
            ResultSet rs = db.statement.executeQuery(query);
            while (rs.next()) {
                choiceEMP.add(rs.getString("empid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAttendanceData() {
        try {
            Dbconn db = new Dbconn();
            String query = "SELECT date, status FROM attendance WHERE empid = '" + empId + "'";
            ResultSet rs = db.statement.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void markAttendance(String status) {
        try {
            Dbconn db = new Dbconn();
            String query = "INSERT INTO attendance (empid, date, status) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE status = ?";
            PreparedStatement pst = db.connection.prepareStatement(query);
            pst.setString(1, empId);
            pst.setDate(2, Date.valueOf(LocalDate.now()));
            pst.setString(3, status);
            pst.setString(4, status);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Attendance marked as " + status);
            loadAttendanceData(); // Refresh the table data
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == markPresent) {
            markAttendance("Present");
        } else if (e.getSource() == markAbsent) {
            markAttendance("Absent");
        } else if (e.getSource() == markLeave) {
            markAttendance("Leave");
        } else if (e.getSource() == viewRecords) {
            empId = choiceEMP.getSelectedItem();
            loadAttendanceData();
        }
    }

    public static void main(String[] args) {
        new Attendance("");
    }
}
