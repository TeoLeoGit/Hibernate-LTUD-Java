package swing;

import dao.MinistryDAO;
import dao.StudentDAO;
import pojo.Class;
import pojo.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Set;

public class EditStudentFrame extends JFrame {
    JPanel mainPanel;
    JButton confirmBtn;
    public EditStudentFrame(Class selectedClass, Set<Student> students, int editId, MinistryStudentPanel panel) {
        mainPanel = new JPanel();
        confirmBtn = new JButton("Confirm");

        JLabel studentIdLabel = new JLabel("Student ID");
        JLabel firstnameLabel = new JLabel("First name");
        JLabel lastnameLabel = new JLabel("Last name");
        JLabel dateOfBirthLabel = new JLabel("Date of birth");
        JLabel emailLabel = new JLabel("Email");
        JLabel addressLabel = new JLabel("Address");
        JLabel phoneLabel = new JLabel("Phone");
        JLabel dayLabel = new JLabel("Day");
        JLabel monthLabel = new JLabel("Month");
        JLabel yearLabel = new JLabel("Year");
        JLabel genderLabel = new JLabel("Gender");

        JTextField fnTxt = new JTextField();
        JTextField lnTxt = new JTextField();
        JTextField dayTxt = new JTextField();
        JTextField monthTxt = new JTextField();
        JTextField yearTxt = new JTextField();
        JTextField emailTxt = new JTextField();
        JTextField addressTxt = new JTextField();
        JTextField phoneTxt = new JTextField();
        JTextField genderTxt = new JTextField();
        JTextField studentIdTxt = new JTextField();

        mainPanel.add(confirmBtn);
        mainPanel.add(studentIdLabel);
        mainPanel.add(firstnameLabel);
        mainPanel.add(lastnameLabel);
        mainPanel.add(dateOfBirthLabel);
        mainPanel.add(emailLabel);
        mainPanel.add(addressLabel);
        mainPanel.add(phoneLabel);
        mainPanel.add(dayLabel);
        mainPanel.add(monthLabel);
        mainPanel.add(yearLabel);
        mainPanel.add(genderLabel);

        mainPanel.add(fnTxt);
        mainPanel.add(lnTxt);
        mainPanel.add(dayTxt);
        mainPanel.add(monthTxt);
        mainPanel.add(yearTxt);
        mainPanel.add(emailTxt);
        mainPanel.add(addressTxt);
        mainPanel.add(phoneTxt);
        mainPanel.add(genderTxt);
        mainPanel.add(studentIdTxt);
        mainPanel.setLayout(null);

        firstnameLabel.setBounds(20, 20, 120, 25);
        lastnameLabel.setBounds(20, 50, 120, 25);
        dateOfBirthLabel.setBounds(20, 80, 120, 25);
        emailLabel.setBounds(20, 110, 120, 25);
        addressLabel.setBounds(20, 140, 120, 25);
        phoneLabel.setBounds(20, 170, 120, 25);
        genderLabel.setBounds(20, 200, 120, 25);
        studentIdLabel.setBounds(20, 230, 120, 25);

        fnTxt.setBounds(140, 20, 260, 25);
        lnTxt.setBounds(140, 50, 260, 25);
        dayLabel.setBounds(140, 80, 30, 25);
        dayTxt.setBounds(170, 80, 30, 25);
        monthLabel.setBounds(238, 80, 50, 25);
        monthTxt.setBounds(280, 80, 30, 25);
        yearLabel.setBounds(328, 80, 30, 25);
        yearTxt.setBounds(358, 80, 40, 25);
        emailTxt.setBounds(140, 110, 260, 25);
        addressTxt.setBounds(140, 140, 260, 25);
        phoneTxt.setBounds(140, 170, 260, 25);
        genderTxt.setBounds(140, 200, 260, 25);
        studentIdTxt.setBounds(140, 230, 260, 25);
        confirmBtn.setBounds(100, 260, 220, 30);

        this.add(mainPanel);
        this.setSize(420, 360);
        this.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Student temp = new Student();
                Student editStd = new Student();
                for (Student item : students) {
                    if (item.getId() == editId) {
                        temp = item;
                        editStd = item;
                        break;
                    }
                }
                int change = 0;
                if (!fnTxt.getText().equals("")) {
                    change++;
                    editStd .setFirstname(fnTxt.getText());
                }
                if (!lnTxt.getText().equals("")) {
                    change++;
                    editStd .setLastname(lnTxt.getText());
                }
                if (!dayTxt.getText().equals("") && !monthTxt.getText().equals("") && !yearTxt.getText().equals("")) {
                    change++;
                    try {
                        Integer.parseInt(dayTxt.getText());
                        Integer.parseInt(monthTxt.getText());
                        Integer.parseInt(yearTxt.getText());
                        editStd .setDayofbirth(Date.valueOf(yearTxt.getText() + "-" +
                                monthTxt.getText() + "-" + dayTxt.getText()));

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(confirmBtn, "Please fill in the text fields a valid date");
                    }
                }
                if (!emailTxt.getText().equals("")) {
                    change++;
                    editStd .setEmail(emailTxt.getText());
                }
                if (!addressTxt.getText().equals("")) {
                    change++;
                    editStd .setAddress(addressTxt.getText());
                }
                if (!phoneTxt.getText().equals("")) {
                    change++;
                    editStd .setPhone(phoneTxt.getText());
                }
                if (!genderTxt.getText().equals("")) {
                    change++;
                    editStd.setGender(genderTxt.getText());
                }

                if (!studentIdTxt.getText().equals("")) {
                    change++;
                    editStd.setStudentId(Integer.valueOf(studentIdTxt.getText()));
                }
                if (change > 0) {
                    if (StudentDAO.updateStudentAccount(editStd)) {
                        students.remove(temp);
                        students.add(editStd);
                        selectedClass.getStudents().remove(temp);
                        selectedClass.getStudents().add(editStd);
                        panel.resetScrollPane();
                        JOptionPane.showMessageDialog(confirmBtn, "Update success");
                        dispose();
                    } else
                        JOptionPane.showMessageDialog(confirmBtn, "Update failed");
                } else
                    JOptionPane.showMessageDialog(confirmBtn, "Please fill in all the fields");
            }
        });
    }
}
