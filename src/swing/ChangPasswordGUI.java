package swing;

import dao.MinistryDAO;
import pojo.Ministry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangPasswordGUI extends JFrame {
    private JButton confirmBtn;
    private JPanel mainPanel;
    private JTextField newPwdTxt;

    public ChangPasswordGUI(Ministry mnt) {
        confirmBtn = new JButton("Confirm");
        mainPanel = new JPanel();
        newPwdTxt = new JTextField();

        JLabel newPwdLb = new JLabel("New password");
        newPwdLb.setBounds(20, 30, 90, 25);
        newPwdTxt.setBounds(120, 30, 215, 25);
        confirmBtn.setBounds(120, 70, 140, 25);
        mainPanel.setLayout(null);

        mainPanel.add(confirmBtn);
        mainPanel.add(newPwdTxt);
        mainPanel.add(newPwdLb);
        this.add(mainPanel);

        this.setSize(380, 150);
        this.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //event handle
        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!newPwdTxt.getText().equals("")) {
                    mnt.setPassword(newPwdTxt.getText());
                    if (MinistryDAO.updateMinistryAccount(mnt))
                        JOptionPane.showMessageDialog(confirmBtn, "Update success");
                    else
                        JOptionPane.showMessageDialog(confirmBtn, "Update failed");
                } else
                    JOptionPane.showMessageDialog(confirmBtn, "No password entered");
                dispose();
            }
        });
    }
}
