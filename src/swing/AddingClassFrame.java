package swing;

import dao.ClassDAO;
import pojo.Class;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class AddingClassFrame extends JFrame {
    JPanel mainPanel;
    JButton confirmBtn;
    public AddingClassFrame(List<Class> classes, MinistryClassPanel panel) {
        mainPanel = new JPanel();
        confirmBtn = new JButton("Confirm");

        JLabel class_nameLabel = new JLabel("Class name");


        JTextField class_nameTxt = new JTextField();


        mainPanel.add(confirmBtn);
        mainPanel.add(class_nameLabel);
        mainPanel.add(class_nameTxt);
        mainPanel.setLayout(null);

        class_nameLabel.setBounds(20, 20, 120, 25);
        class_nameTxt.setBounds(100, 20, 260, 25);
        confirmBtn.setBounds(100, 60, 220, 30);

        this.add(mainPanel);
        this.setSize(420, 130);
        this.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Class addClass = new Class();
                int change = 0;
                if (!class_nameTxt.getText().equals("")) {
                    change++;
                    addClass.setClassname(class_nameTxt.getText());
                }
                if (change >= 1) {
                    if (ClassDAO.addClass(addClass)) {
                        addClass.setNumberOfStudent(0);
                        addClass.setMaleNumber(0);
                        addClass.setFemaleNumber(0);
                        classes.add(addClass);
                        JOptionPane.showMessageDialog(confirmBtn, "Update success");
                        panel.resetScrollPane();
                        dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(confirmBtn, "Update failed");
                } else
                    JOptionPane.showMessageDialog(confirmBtn, "Please fill in all the field");
            }
        });
    }
}
