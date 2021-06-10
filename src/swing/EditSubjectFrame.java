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

public class EditSubjectFrame extends JFrame {
    JPanel mainPanel;
    JButton confirmBtn;
    public EditSubjectFrame(List<Subject> subjects, int editId, MinistrySubjectPanel panel) {
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
                int change = 0;
                Subject temp = new Subject();
                Subject editSubj = new Subject();
                for (Subject item : subjects) {
                    if (item.getId() == editId) {
                        temp = item;
                        editSubj = item;
                        break;
                    }
                }
                if (!subjectnameTxt.getText().equals("")) {
                    change++;
                    editSubj.setSubjectname(subjectnameTxt.getText());
                }
                if (!creditsTxt.getText().equals("")) {
                    try {
                        editSubj.setCredits(Integer.parseInt(creditsTxt.getText()));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(confirmBtn, "Credit must be a number");
                    }
                    change++;
                }

                if (change > 0) {
                    if (SubjectDAO.updateSubject(editSubj)) {
                        subjects.remove(temp);
                        subjects.add(editSubj);
                        panel.resetScrollPane(subjects);
                        JOptionPane.showMessageDialog(confirmBtn, "Update success");
                        dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(confirmBtn, "Update failed");
                } else
                    JOptionPane.showMessageDialog(confirmBtn, "Please fill in one of the fields");
            }
        });
    }
}
