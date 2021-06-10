package swing;

import dao.MinistryDAO;
import dao.SubjectDAO;
import pojo.Ministry;
import pojo.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class AddingSubjectFrame extends JFrame {
    JPanel mainPanel;
    JButton confirmBtn;
    public AddingSubjectFrame(List<Subject> subjects, MinistrySubjectPanel panel) {
        mainPanel = new JPanel();
        confirmBtn = new JButton("Confirm");

        JLabel subjectnameLabel = new JLabel("Subject name");
        JLabel creditsLabel = new JLabel("Credits");


        JTextField subjectnameTxt = new JTextField();
        JTextField creditsTxt = new JTextField();

        mainPanel.add(confirmBtn);
        mainPanel.add(subjectnameLabel);
        mainPanel.add(creditsLabel);

        mainPanel.add(subjectnameTxt);
        mainPanel.add(creditsTxt);
        mainPanel.setLayout(null);

        subjectnameLabel.setBounds(20, 20, 120, 25);
        creditsLabel.setBounds(20, 50, 120, 25);


        subjectnameTxt.setBounds(140, 20, 260, 25);
        creditsTxt.setBounds(140, 50, 260, 25);

        confirmBtn.setBounds(100, 90, 220, 30);

        this.add(mainPanel);
        this.setSize(420, 180);
        this.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Subject addSubject = new Subject();
                int change = 0;
                if (!subjectnameTxt.getText().equals("")) {
                    change++;
                    addSubject.setSubjectname(subjectnameTxt.getText());
                }
                if (!creditsTxt.getText().equals("")) {
                    try {
                        addSubject.setCredits(Integer.parseInt(creditsTxt.getText()));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(confirmBtn, "Credit must be a number");
                    }
                    change++;
                }

                if (change >= 2) {
                    if (SubjectDAO.addSubject(addSubject)) {
                        subjects.add(addSubject);
                        JOptionPane.showMessageDialog(confirmBtn, "Update success");
                        panel.resetScrollPane(subjects);
                        dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(confirmBtn, "Update failed");
                } else
                    JOptionPane.showMessageDialog(confirmBtn, "Please fill in all the fields");
            }
        });
    }
}
