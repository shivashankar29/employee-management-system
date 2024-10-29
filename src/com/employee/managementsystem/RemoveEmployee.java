package com.employee.managementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class RemoveEmployee extends JFrame implements ActionListener {

    Choice choiceEMPID;
    JButton delete,back;
    RemoveEmployee() {

        setTitle("Remove Employee");
        JLabel heading = new JLabel("Remove Employee");
        heading.setBounds(380,0,500,50);
        heading.setFont(new Font("serif", Font.BOLD,25));
        heading.setForeground(Color.WHITE);
        add(heading);

        JLabel label = new JLabel("Employee ID");
        label.setBounds(50,50,100,30);
        label.setFont(new Font("Tahoma",Font.BOLD,15));
        label.setForeground(Color.WHITE);
        add(label);

        choiceEMPID = new Choice();
        choiceEMPID.setBounds(200,50,150,30);
        add(choiceEMPID);

        try {
            Dbconn db = new Dbconn();
            ResultSet resultSet = db.statement.executeQuery("select * from employee");
            while (resultSet.next()) {
                choiceEMPID.add(resultSet.getString("empid"));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(50,100,100,30);
        labelName.setFont(new Font("Tahoma",Font.BOLD,15));
        labelName.setForeground(Color.WHITE);
        add(labelName);

        JLabel textName = new JLabel();
        textName.setBounds(200,100,100,30);
        textName.setForeground(Color.WHITE);
        add(textName);

        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(50,150,100,30);
        labelPhone.setFont(new Font("Tahoma",Font.BOLD,15));
        labelPhone.setForeground(Color.WHITE);
        add(labelPhone);

        JLabel textPhone = new JLabel();
        textPhone.setBounds(200,150,100,30);
        textPhone.setForeground(Color.WHITE);
        add(textPhone);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(50,200,100,30);
        labelEmail.setFont(new Font("Tahoma",Font.BOLD,15));
        labelEmail.setForeground(Color.WHITE);
        add(labelEmail);

        JLabel textEmail = new JLabel();
        textEmail.setBounds(200,200,100,30);
        textEmail.setForeground(Color.WHITE);
        add(textEmail);

        try {
            Dbconn db = new Dbconn();
            ResultSet resultSet = db.statement.executeQuery("select * from employee where empid = '"+choiceEMPID.getSelectedItem()+"'");
            while (resultSet.next()) {
                textName.setText(resultSet.getString("name"));
                textPhone.setText(resultSet.getString("phone"));
                textEmail.setText(resultSet.getString("email"));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        choiceEMPID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Dbconn db = new Dbconn();
                    ResultSet resultSet = db.statement.executeQuery("select * from employee where empid = '"+choiceEMPID.getSelectedItem()+"'");
                    while (resultSet.next()) {
                        textName.setText(resultSet.getString("name"));
                        textPhone.setText(resultSet.getString("phone"));
                        textEmail.setText(resultSet.getString("email"));
                    }
                }catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        delete = new JButton("DELETE");
        delete.setBounds(80,300,100,30);
        delete.setBackground(Color.RED);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("BACK");
        back.setBounds(220,300,100,30);
        back.setBackground(Color.RED);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("backgrounds/delete back.jpg"));
        Image i22 = i11 .getImage().getScaledInstance(1120,630,Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel img = new JLabel(i33);
        img.setBounds(0,0,1120,630);
        add(img);

        setSize(1000,400);
        setLocation(300,150);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            try {
                Dbconn db = new Dbconn();
                String query = "delete from employee where empid = '"+choiceEMPID.getSelectedItem()+"'";
                db.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Employee Deleted Successfully");
                setVisible(false);
                new Main_class();
            }catch (Exception E) {
                E.printStackTrace();
            }
        }else  {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new  RemoveEmployee();
    }
}
