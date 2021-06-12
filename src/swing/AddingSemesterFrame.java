package swing;

import dao.SemesterDAO;
import pojo.Semester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class AddingSemesterFrame extends JFrame {
    JPanel mainPanel;
    JButton confirmBtn;
    public AddingSemesterFrame(List<Semester> semesters, MinistrySemesterPanel panel) {
        mainPanel = new JPanel();
        confirmBtn = new JButton("Confirm");

        JLabel semester_nameLabel = new JLabel("Semester name");
        JLabel semesterYearLabel = new JLabel("Semester year");
        JLabel startDateLabel = new JLabel("Start date");
        JLabel endDateLabel = new JLabel("End date");
        JLabel startDayLabel = new JLabel("Day");
        JLabel startMonthLabel = new JLabel("Month");
        JLabel endDayLabel = new JLabel("Day");
        JLabel endMonthLabel = new JLabel("Month");

        JTextField semester_nameTxt = new JTextField();
        JTextField semesterYearTxt = new JTextField();
        JTextField startDayTxt = new JTextField();
        JTextField startMonthTxt = new JTextField();
        JTextField endDayTxt = new JTextField();
        JTextField endMonthTxt = new JTextField();

        mainPanel.add(confirmBtn);
        mainPanel.add(semester_nameLabel);
        mainPanel.add(semesterYearLabel);
        mainPanel.add(startDateLabel);
        mainPanel.add(endDateLabel);
        mainPanel.add(startDayLabel);
        mainPanel.add(startMonthLabel);
        mainPanel.add(endDayLabel);
        mainPanel.add(endMonthLabel);
        mainPanel.add(semester_nameTxt);
        mainPanel.add(semesterYearTxt);
        mainPanel.add(startDayTxt);
        mainPanel.add(startMonthTxt);
        mainPanel.add(endDayTxt);
        mainPanel.add(endMonthTxt);
        mainPanel.setLayout(null);

        semester_nameLabel.setBounds(20, 20, 120, 25);
        semesterYearLabel.setBounds(20, 50, 120, 25);
        startDateLabel.setBounds(20, 80, 120, 25);
        endDateLabel.setBounds(20, 110, 120, 25);
        semester_nameTxt.setBounds(140, 20, 260, 25);
        semesterYearTxt.setBounds(140, 50, 260, 25);

        startDayLabel.setBounds(140, 80, 30, 25);
        startDayTxt.setBounds(170, 80, 30, 25);

        startMonthLabel.setBounds(238, 80, 40, 25);
        startMonthTxt.setBounds(280, 80, 30, 25);

        endDayLabel.setBounds(140, 110, 30, 25);
        endDayTxt.setBounds(170, 110, 30, 25);

        endMonthLabel.setBounds(238, 110, 40, 25);
        endMonthTxt.setBounds(280, 110, 30, 25);

        confirmBtn.setBounds(100, 140, 220, 30);

        this.add(mainPanel);
        this.setSize(420, 230);
        this.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Semester addSem = new Semester();
                int change = 0;
                if (!semester_nameTxt.getText().equals("")) {
                    change++;
                    addSem.setSemestername(semester_nameTxt.getText());
                }
                if (!startDayTxt.getText().equals("") && !startMonthTxt.getText().equals("") && !semesterYearTxt.getText().equals("")) {
                    change++;
                    addSem.setSemesteryear(semesterYearTxt.getText());
                    try {
                        Integer.parseInt(startDayTxt.getText());
                        Integer.parseInt(startMonthTxt.getText());
                        Integer.parseInt(semesterYearTxt.getText());
                        addSem.setStartdate(Date.valueOf(semesterYearTxt.getText() + "-" +
                                startMonthTxt.getText() + "-" + startDayTxt.getText()));

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(confirmBtn, "Please fill in the text fields a valid date");
                    }
                }
                if (!endDayTxt.getText().equals("") && !endMonthTxt.getText().equals("") && !semesterYearTxt.getText().equals("")) {
                    change++;
                    addSem.setSemesteryear(semesterYearTxt.getText());
                    try {
                        Integer.parseInt(endDayTxt.getText());
                        Integer.parseInt(endMonthTxt.getText());
                        Integer.parseInt(semesterYearTxt.getText());
                        addSem.setEnddate(Date.valueOf(semesterYearTxt.getText() + "-" +
                                endMonthTxt.getText() + "-" + endDayTxt.getText()));

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(confirmBtn, "Please fill in the text fields a valid date");
                    }
                }
                if (change >= 3) {
                    if(addSem.getStartdate().compareTo(addSem.getEnddate()) < 0) {
                        if (SemesterDAO.addSemester(addSem)) {
                            semesters.add(addSem);
                            JOptionPane.showMessageDialog(confirmBtn, "Update success");
                            panel.resetScrollPane(semesters);
                            dispose();
                        } else
                            JOptionPane.showMessageDialog(confirmBtn, "Update failed");
                    }
                    else
                        JOptionPane.showMessageDialog(confirmBtn, "Semester must start before it end");
                } else
                    JOptionPane.showMessageDialog(confirmBtn, "Please fill in all the fields");
            }
        });
    }
}
