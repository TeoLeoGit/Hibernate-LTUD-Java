package swing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.MinistryDAO;
import dao.StudentDAO;
import pojo.Ministry;
import pojo.Student;

import java.util.List;

public class LoginGUI extends JFrame {
    private JTabbedPane tabbedPanel;
    private JPanel panelMinistry;
    private JPanel panelStudent;
    private JButton ministryLogin;
    private JButton studentLogin;
    public LoginGUI() {
        tabbedPanel = new JTabbedPane();
        panelMinistry = new JPanel();
        panelStudent = new JPanel();
        ministryLogin = new JButton();
        studentLogin = new JButton();
        panelMinistry.setLayout(null);
        panelStudent.setLayout(null);
        tabbedPanel.addTab("Ministry Login", panelMinistry);
        tabbedPanel.addTab("Student Login", panelStudent);

        //panel ministry setting
        JLabel ministryUsernameLabel = new JLabel("Username");
        ministryUsernameLabel.setBounds(10, 20, 80, 25);
        JLabel ministryPasswordLabel = new JLabel("Password");
        ministryPasswordLabel.setBounds(10, 50, 80, 25);
        JTextField ministryUsernameText = new JTextField();
        ministryUsernameText.setBounds(80, 20, 165, 25);
        JTextField ministryPasswordText = new JTextField();
        ministryPasswordText.setBounds(80, 50, 165, 25);
        ministryLogin.setBounds(260, 30, 80, 30);
        ministryLogin.setText("Login");
        panelMinistry.add(ministryUsernameLabel);
        panelMinistry.add(ministryPasswordLabel);
        panelMinistry.add(ministryUsernameText);
        panelMinistry.add(ministryPasswordText);
        panelMinistry.add(ministryLogin);

        //panel student setting
        JLabel studentUsernameLabel = new JLabel("Username");
        studentUsernameLabel.setBounds(10, 20, 80, 25);
        JLabel studentPasswordLabel = new JLabel("Password");
        studentPasswordLabel.setBounds(10, 50, 80, 25);
        JTextField studentUsernameText = new JTextField();
        studentUsernameText.setBounds(80, 20, 165, 25);
        JTextField studentPasswordText = new JTextField();
        studentPasswordText.setBounds(80, 50, 165, 25);
        studentLogin.setBounds(260, 30, 80, 30);
        studentLogin.setText("Login");
        panelStudent.add(studentUsernameLabel);
        panelStudent.add(studentPasswordLabel);
        panelStudent.add(studentUsernameText);
        panelStudent.add(studentPasswordText);
        panelStudent.add(studentLogin);

        this.add(tabbedPanel);
        this.setSize(380, 150);
        this.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //event handle
        ministryLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Ministry> ministries = MinistryDAO.getMinistriesByUsername(ministryUsernameText.getText());
                if (ministries.isEmpty()) JOptionPane.showMessageDialog(ministryLogin, "No account found!");
                else
                    for (Ministry item : ministries) {
                        if (item.getUsername().equals(ministryUsernameText.getText()))
                            if (item.getPassword().equals(ministryPasswordText.getText())) {
                                JFrame ministryGUI = new MinistryGUI(item);
                                ministryGUI .setVisible(true);
                                dispose();
                                break;
                            }
                            else
                                JOptionPane.showMessageDialog(ministryLogin, "Incorrect password");
                    }
            }
        });

        studentLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Student> students = StudentDAO.getStudentsByUsername(studentUsernameText.getText());
                if (students.isEmpty()) JOptionPane.showMessageDialog(studentLogin, "No student found!");
                else
                    for (Student item : students) {
                        if (item.getUsername().equals(studentUsernameText.getText()))
                            if (item.getPassword().equals(studentPasswordText.getText())) {
                                JFrame studentGUI = new StudentGUI(item);
                                studentGUI.setVisible(true);
                                dispose();
                                break;
                            }
                            else
                                JOptionPane.showMessageDialog(studentLogin, "Incorrect password student");
                    }
            }
        });
    }
}
