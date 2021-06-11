package swing;

import dao.StudentDAO;
import pojo.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class StudentInfoSettingGUI extends JPanel {
    private JButton backBtn;
    private JTextArea currentInfo;
    private JButton updateBtn;
    private JButton changePwdBtn;

    public StudentInfoSettingGUI(Student student, JPanel mainPanel) {
        backBtn = new JButton("Back to main");
        currentInfo = new JTextArea();
        updateBtn = new JButton("Update");
        changePwdBtn = new JButton("Change password");

        JLabel idLabel = new JLabel("New ID");
        JLabel firstnameLabel = new JLabel("New first name");
        JLabel lastnameLabel = new JLabel("New last name");
        JLabel dayOfBirthLabel = new JLabel("New date of birth");
        JLabel emailLabel = new JLabel("New email");
        JLabel addressLabel = new JLabel("New address");
        JLabel phoneLabel = new JLabel("New phone");
        JLabel genderLabel = new JLabel("Gender");
        JLabel dayLabel = new JLabel("Day");
        JLabel monthLabel = new JLabel("Month");
        JLabel yearLabel = new JLabel("Year");

        JTextField fnTxt = new JTextField();
        JTextField lnTxt = new JTextField();
        JTextField dayTxt = new JTextField();
        JTextField monthTxt = new JTextField();
        JTextField yearTxt = new JTextField();
        JTextField emailTxt = new JTextField();
        JTextField addressTxt = new JTextField();
        JTextField phoneTxt = new JTextField();
        JTextField genderTxt = new JTextField();

        add(backBtn);
        add(currentInfo);
        add(updateBtn);
        add(changePwdBtn);
        add(idLabel);
        add(firstnameLabel);
        add(lastnameLabel);
        add(dayOfBirthLabel);
        add(emailLabel);
        add(addressLabel);
        add(phoneLabel);
        add(genderLabel);
        add(dayLabel);
        add(monthLabel);
        add(yearLabel);
        add(fnTxt);
        add(lnTxt);
        add(dayTxt);
        add(monthTxt);
        add(yearTxt);
        add(emailTxt);
        add(addressTxt);
        add(phoneTxt);
        add(genderTxt);

        this.setLayout(null);
        currentInfo.setText(student.getStudentId() + "\n" + student.getFirstname() + "\n" + student.getLastname() + "\n" +
                student.getDayofbirth() + "\n" + student.getEmail() + "\n" + student.getAddress() + "\n" + student.getPhone()
                + "\n" + student.getGender());
        currentInfo.setFont(new Font("Arial", Font.PLAIN, 20));
        currentInfo.setBorder(BorderFactory.createLineBorder(Color.gray));
        backBtn.setBounds(30 ,40, 140 , 40);
        currentInfo.setBounds(30, 96, 420, 195);
        updateBtn.setBounds(736, 315, 146, 35);
        changePwdBtn.setBounds(736, 360, 146, 35);

        firstnameLabel.setBounds(470, 96, 120, 25);
        lastnameLabel.setBounds(470, 126, 120, 25);
        dayOfBirthLabel.setBounds(470, 156, 120, 25);
        emailLabel.setBounds(470, 186, 120, 25);
        addressLabel.setBounds(470, 216, 120, 25);
        phoneLabel.setBounds(470, 246, 120, 25);
        genderLabel.setBounds(470, 276, 120, 25);

        fnTxt.setBounds(590, 96, 260, 25);
        lnTxt.setBounds(590, 126, 260, 25);
        dayLabel.setBounds(590, 156, 30, 25);
        dayTxt.setBounds(620, 156, 30, 25);
        monthLabel.setBounds(688, 156, 50, 25);
        monthTxt.setBounds(730, 156, 30, 25);
        yearLabel.setBounds(778, 156, 30, 25);
        yearTxt.setBounds(808, 156, 40, 25);
        emailTxt.setBounds(590, 186, 260, 25);
        addressTxt.setBounds(590, 216, 260, 25);
        phoneTxt.setBounds(590, 246, 260, 25);
        genderTxt.setBounds(590, 276, 260, 25);

        //event handle
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainPanel.repaint();
                mainPanel.setVisible(true);
            }
        });

        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int change = 0;
                if (!fnTxt.getText().equals("")) {
                    change = 1;
                    student.setFirstname(fnTxt.getText());
                }
                if (!lnTxt.getText().equals("")) {
                    change = 1;
                    student.setLastname(lnTxt.getText());
                }
                if (!dayTxt.getText().equals("") && !monthTxt.getText().equals("") && !yearTxt.getText().equals("")) {
                    change = 1;
                    try {
                        Integer.parseInt(dayTxt.getText());
                        Integer.parseInt(monthTxt.getText());
                        Integer.parseInt(yearTxt.getText());
                        student.setDayofbirth(Date.valueOf(yearTxt.getText() + "-" +
                                monthTxt.getText() + "-" + dayTxt.getText()));

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(updateBtn, "Please fill in the text fields a valid date");
                    }
                }
                if (!emailTxt.getText().equals("")) {
                    change = 1;
                    student.setEmail(emailTxt.getText());
                }
                if (!addressTxt.getText().equals("")) {
                    change = 1;
                    student.setAddress(addressTxt.getText());
                }
                if (!phoneTxt.getText().equals("")) {
                    change = 1;
                    student.setPhone(phoneTxt.getText());
                }
                if (!genderTxt.getText().equals("")) {
                    change = 1;
                    student.setGender(genderTxt.getText());
                }
                if (change == 1) {
                    if (StudentDAO.updateStudentAccount(student)) {
                        JOptionPane.showMessageDialog(updateBtn, "Update success");
                        //update main panel
                        currentInfo.setText(student.getStudentId() + "\n" + student.getFirstname() + "\n" + student.getLastname() + "\n" +
                                student.getDayofbirth() + "\n" + student.getEmail() + "\n" + student.getAddress() + "\n" + student.getPhone()
                                +"\n" + student.getGender());
                        repaint();
                        JTextArea updateInfo = new JTextArea(student.getStudentId() + "\n" + student.getFirstname() + "\n" + student.getLastname() + "\n" +
                                student.getDayofbirth() + "\n" + student.getEmail() + "\n" + student.getAddress() + "\n" + student.getPhone()
                                + "\n" + student.getGender());
                        updateInfo.setBounds(278, 50, 420, 273);
                        updateInfo.setFont(new Font("Arial", Font.PLAIN, 20));
                        updateInfo.setBorder(BorderFactory.createLineBorder(Color.gray));
                        Component[] components = mainPanel.getComponents();
                        for (Component c : components) {
                            if (c instanceof JTextArea) {
                                mainPanel.remove(c);
                                mainPanel.add(updateInfo);
                                mainPanel.repaint();
                            }
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(updateBtn, "Update failed");
                } else
                    JOptionPane.showMessageDialog(updateBtn, "No change found");
            }
        });

        changePwdBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame changePwdFrame = new StudentChangePasswordGUI(student);
                changePwdFrame.setVisible(true);
            }
        });
    }
}
