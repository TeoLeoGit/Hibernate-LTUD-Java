package swing;
import pojo.Ministry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinistryGUI extends JFrame {
    private JPanel mainPanel;
    private JButton ministryAccountBtn;
    private JButton subjectBtn;
    private JButton semesterBtn;
    private JButton classBtn;
    private JButton studentBtn;
    private JButton courseRegistrationSessionBtn;
    private JButton courseBtn;
    private JButton studentRegistrationBtn;
    private JTextArea info;
    private JButton changeInfo;
    private JButton logOut;

    public MinistryGUI(Ministry mnt) {
        mainPanel = new JPanel();
        ministryAccountBtn = new JButton("Ministry accounts");
        subjectBtn = new JButton("Subjects");
        semesterBtn = new JButton("Semesters");
        classBtn = new JButton("Classes");
        studentBtn = new JButton("Students");
        courseRegistrationSessionBtn = new JButton("Courses registration");
        courseBtn = new JButton("Courses");
        studentRegistrationBtn = new JButton("Student registration");
        info = new JTextArea("Account information");
        changeInfo = new JButton("Change info");
        logOut = new JButton("Log out");

        //Drawing GUI
        mainPanel.setLayout(null);
        ministryAccountBtn.setBounds(35,50, 180, 40);
        subjectBtn.setBounds(35,100, 180, 40);
        semesterBtn.setBounds(35,150, 180, 40);
        classBtn.setBounds(35,200, 180, 40);
        studentBtn.setBounds(35,250, 180, 40);
        courseRegistrationSessionBtn.setBounds(35,300, 180, 40);
        courseBtn.setBounds(35,350, 180, 40);
        studentRegistrationBtn.setBounds(35, 400, 180, 40);
        info.setBounds(278, 50, 400, 273);
        changeInfo.setBounds(735, 50, 120, 33);
        logOut.setBounds(735, 100, 120, 33);
        info.setText(mnt.getMinistryid() + "\n" + mnt.getFirstname() + "\n" + mnt.getLastname() + "\n" +
                mnt.getDayofbirth() + "\n" + mnt.getEmail() + "\n" + mnt.getAddress() + "\n" + mnt.getPhone());
        info.setFont(new Font("Arial", Font.PLAIN, 20));
        info.setBorder(BorderFactory.createLineBorder(Color.gray));

        mainPanel.add(ministryAccountBtn);
        mainPanel.add(subjectBtn);
        mainPanel.add(semesterBtn);
        mainPanel.add(classBtn);
        mainPanel.add(studentBtn);
        mainPanel.add(courseRegistrationSessionBtn);
        mainPanel.add(courseBtn);
        mainPanel.add(studentRegistrationBtn);
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
                JPanel infoSetting = new MinistryInfoSettingGUI(mnt, mainPanel);
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

        ministryAccountBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                JPanel ministryAccountPnl = new MinistryAccountPanel(mnt, mainPanel);
                add(ministryAccountPnl);
            }
        });
    }
}
