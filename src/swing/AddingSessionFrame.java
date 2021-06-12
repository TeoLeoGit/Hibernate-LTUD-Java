package swing;

import dao.CourseRegistrationSessionDAO;
import dao.SemesterDAO;
import pojo.Courseregistrationsession;
import pojo.Semester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class AddingSessionFrame extends JFrame {
    JPanel mainPanel;
    JButton confirmBtn;
    public AddingSessionFrame(List<Courseregistrationsession> sessions, Semester currentSem, MinistryCoursesRegistPanel panel) {
        mainPanel = new JPanel();
        confirmBtn = new JButton("Confirm");

        JLabel startDateLabel = new JLabel("Start date");
        JLabel endDateLabel = new JLabel("End date");
        JLabel startDayLabel = new JLabel("Day");
        JLabel startMonthLabel = new JLabel("Month");
        JLabel endDayLabel = new JLabel("Day");
        JLabel endMonthLabel = new JLabel("Month");

        JTextField startDayTxt = new JTextField();
        JTextField startMonthTxt = new JTextField();
        JTextField endDayTxt = new JTextField();
        JTextField endMonthTxt = new JTextField();

        mainPanel.add(confirmBtn);
        mainPanel.add(startDateLabel);
        mainPanel.add(endDateLabel);
        mainPanel.add(startDayLabel);
        mainPanel.add(startMonthLabel);
        mainPanel.add(endDayLabel);
        mainPanel.add(endMonthLabel);
        mainPanel.add(startDayTxt);
        mainPanel.add(startMonthTxt);
        mainPanel.add(endDayTxt);
        mainPanel.add(endMonthTxt);
        mainPanel.setLayout(null);

        startDateLabel.setBounds(20, 20, 120, 25);
        endDateLabel.setBounds(20, 50, 120, 25);

        startDayLabel.setBounds(140, 20, 30, 25);
        startDayTxt.setBounds(170, 20, 30, 25);

        startMonthLabel.setBounds(238, 20, 40, 25);
        startMonthTxt.setBounds(280, 20, 30, 25);

        endDayLabel.setBounds(140, 50, 30, 25);
        endDayTxt.setBounds(170, 50, 30, 25);

        endMonthLabel.setBounds(238, 50, 40, 25);
        endMonthTxt.setBounds(280, 50, 30, 25);

        confirmBtn.setBounds(100, 90, 220, 30);

        this.add(mainPanel);
        this.setSize(420, 160);
        this.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Courseregistrationsession addSession = new Courseregistrationsession();
                int change = 0;
                String[] parts = currentSem.getSemesteryear().split("-");
                if (!startDayTxt.getText().equals("") && !startMonthTxt.getText().equals("")) {
                    change++;
                    try {
                        Integer.parseInt(startDayTxt.getText());
                        Integer.parseInt(startMonthTxt.getText());
                        addSession.setStartdate(Date.valueOf(parts[0] + "-" +
                                startMonthTxt.getText() + "-" + startDayTxt.getText()));

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(confirmBtn, "Please fill in the text fields a valid date");
                    }
                }
                if (!endDayTxt.getText().equals("") && !endMonthTxt.getText().equals("")) {
                    change++;
                    try {
                        Integer.parseInt(endDayTxt.getText());
                        Integer.parseInt(endMonthTxt.getText());
                        addSession.setEnddate(Date.valueOf(parts[0] + "-" +
                                endMonthTxt.getText() + "-" + endDayTxt.getText()));

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(confirmBtn, "Please fill in the text fields a valid date");
                    }
                }
                if (change >= 2) {
                    currentSem.setSemesteryear(parts[0]);
                    addSession.setSemester(currentSem);
                    if(addSession.getStartdate().compareTo(addSession.getEnddate()) < 0) {
                        if (CourseRegistrationSessionDAO.addSession(addSession)) {

                            sessions.add(addSession);
                            JOptionPane.showMessageDialog(confirmBtn, "Update success");
                            panel.resetScrollPane();
                            dispose();
                        } else
                            JOptionPane.showMessageDialog(confirmBtn, "Update failed");
                    }
                    else
                        JOptionPane.showMessageDialog(confirmBtn, "Session must start before it end");
                } else
                    JOptionPane.showMessageDialog(confirmBtn, "Please fill in all the fields");
            }
        });
    }
}