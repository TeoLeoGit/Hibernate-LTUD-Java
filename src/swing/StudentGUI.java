package swing;

import dao.CourseRegistrationSessionDAO;
import pojo.Courseregistrationsession;
import pojo.Student;
import pojo.Semester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentGUI extends JFrame {
    private JButton logOut;
    private JTextArea info;
    private JButton changeInfo;
    private JPanel mainPanel;
    private JButton registrationBtn;
    private JButton checkRegistrationBtn;

    public StudentGUI(Student student) {
        mainPanel = new JPanel();
        registrationBtn = new JButton("Registration session");
        checkRegistrationBtn = new JButton("Enrolled courses");

        info = new JTextArea("Account information");
        changeInfo = new JButton("Change info");
        logOut = new JButton("Log out");

        //Get opening Registration session
        final Courseregistrationsession[] openingSession = {new Courseregistrationsession()};
        for (Courseregistrationsession item : CourseRegistrationSessionDAO.getOpeningSession()) {
            openingSession[0] = item;
            break;
        }
        //Drawing GUI
        mainPanel.setLayout(null);
        registrationBtn.setBounds(35, 50, 180, 40);
        checkRegistrationBtn.setBounds(35, 100, 180, 40);

        info.setBounds(278, 50, 400, 273);
        changeInfo.setBounds(735, 50, 120, 33);
        logOut.setBounds(735, 100, 120, 33);
        info.setText(student.getStudentId() + "\n" + student.getFirstname() + "\n" + student.getLastname() + "\n" +
                student.getDayofbirth() + "\n" + student.getEmail() + "\n" + student.getAddress() + "\n" + student.getPhone()
                + "\n" + student.getGender());
        info.setFont(new Font("Arial", Font.PLAIN, 20));
        info.setBorder(BorderFactory.createLineBorder(Color.gray));

        mainPanel.add(registrationBtn);
        mainPanel.add(checkRegistrationBtn);
        mainPanel.add(info);
        mainPanel.add(changeInfo);
        mainPanel.add(logOut);

        this.add(mainPanel);
        this.setSize(910, 500);
        this.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //event handle
        changeInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                JPanel infoSetting = new StudentInfoSettingGUI(student, mainPanel);
                add(infoSetting);
            }
        });
        //log out
        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame login = new LoginGUI();
                login.setVisible(true);
                dispose();
            }
        });

        registrationBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                JPanel studentRegistrationPnl = new StudentRegistrationPanel(mainPanel, openingSession, student);
                add(studentRegistrationPnl);
            }
        });

        checkRegistrationBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                JPanel enrolledCrsPnl = new StudentEnrolledCoursePanel(mainPanel, student, openingSession);
                add(enrolledCrsPnl);
            }
        });
    }
}
