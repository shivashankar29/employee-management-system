package com.employee.managementsystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Payroll extends JFrame implements ActionListener {
     JTextField txtBasicSalary, txtAllowances, txtDeductions, txtGrossSalary, txtNetSalary;
    JButton btnCalculate;

    public Payroll(String empId) {

        setTitle("Payroll for Employee ID: " + empId);
        setSize(400, 400);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblBasicSalary = new JLabel("Basic Salary:");
        lblBasicSalary.setBounds(50, 50, 100, 30);
        add(lblBasicSalary);

        JLabel lblAllowances = new JLabel("Allowances:");
        lblAllowances.setBounds(50, 100, 100, 30);
        add(lblAllowances);

        JLabel lblDeductions = new JLabel("Deductions:");
        lblDeductions.setBounds(50, 150, 100, 30);
        add(lblDeductions);

        JLabel lblGrossSalary = new JLabel("Gross Salary:");
        lblGrossSalary.setBounds(50, 250, 100, 30);
        add(lblGrossSalary);

        JLabel lblNetSalary = new JLabel("Net Salary:");
        lblNetSalary.setBounds(50, 300, 100, 30);
        add(lblNetSalary);


        txtBasicSalary = new JTextField();
        txtBasicSalary.setBounds(180, 50, 150, 30);
        add(txtBasicSalary);

        txtAllowances = new JTextField();
        txtAllowances.setBounds(180, 100, 150, 30);
        add(txtAllowances);

        txtDeductions = new JTextField();
        txtDeductions.setBounds(180, 150, 150, 30);
        add(txtDeductions);

        txtGrossSalary = new JTextField();
        txtGrossSalary.setBounds(180, 250, 150, 30);
        txtGrossSalary.setEditable(false);
        add(txtGrossSalary);

        txtNetSalary = new JTextField();
        txtNetSalary.setBounds(180, 300, 150, 30);
        txtNetSalary.setEditable(false);
        add(txtNetSalary);

        btnCalculate = new JButton("Calculate");
        btnCalculate.setBounds(130, 200, 120, 30);
        btnCalculate.addActionListener(this);
        add(btnCalculate);

        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalculate) {
            try {

                double basicSalary = Double.parseDouble(txtBasicSalary.getText());
                double allowances = Double.parseDouble(txtAllowances.getText());
                double deductions = Double.parseDouble(txtDeductions.getText());


                double grossSalary = basicSalary + allowances;
                double netSalary = grossSalary - deductions;

                txtGrossSalary.setText(String.format("%.2f", grossSalary));
                txtNetSalary.setText(String.format("%.2f", netSalary));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    }

