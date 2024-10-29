package com.employee.managementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewEmployee extends JFrame implements ActionListener {

    JTable table;
    Choice choiceEMP;
    JButton searchbtn,print,update,payroll,attendance,back;

    ViewEmployee() {

        setTitle("View Employees");
        getContentPane().setBackground(new Color(255,201,81));
        JLabel search = new JLabel("Search by Employee ID");
        search.setBounds(20,20,150,20);
        add(search);

        choiceEMP = new Choice();
        choiceEMP.setBounds(180,20,150,20);
        add(choiceEMP);

        try {
            Dbconn db = new Dbconn();
            ResultSet resultSet = db.statement.executeQuery("select * from employee");
            while (resultSet.next()) {
                choiceEMP.add(resultSet.getString("empid"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();
        try {
            Dbconn db = new Dbconn();
            ResultSet resultSet= db.statement.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0,100,900,600);
        add(jp);

        searchbtn = new JButton("Search");
        searchbtn.setBounds(20,70,80,20);
        searchbtn.addActionListener(this);
        add(searchbtn);

        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(220,70,80,20);
        update.addActionListener(this);
        add(update);

        payroll = new JButton("Payroll");
        payroll.setBounds(320, 70, 80, 20);
        payroll.addActionListener(this);
        add(payroll);


        attendance = new JButton("Attendance");
        attendance.setBounds(420, 70, 100, 20);
        attendance.addActionListener(this);
        add(attendance);


        back = new JButton("Back");
        back.setBounds(540, 70, 80, 20);
        back.addActionListener(this);
        add(back);

        setSize(900,700);
        setLayout(null);
        setLocation(300,100);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchbtn) {
            String query = "select * from employee where empid = '"+choiceEMP.getSelectedItem()+"'";
            try {
                Dbconn db = new Dbconn();
                ResultSet resultSet = db.statement.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try {
                table.print();

            }catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == update) {
            setVisible(false);
            new UpdateEmployee(choiceEMP.getSelectedItem());
        } else if (e.getSource() == payroll) {
            new Payroll(choiceEMP.getSelectedItem());
        }else if (e.getSource() == attendance) {
            new Attendance(choiceEMP.getSelectedItem());
        }

        else {
            setVisible(false);
            new Main_class();
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}


