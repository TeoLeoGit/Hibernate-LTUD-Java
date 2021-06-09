package swing;

import pojo.Ministry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinistryInfoSettingGUI extends JPanel {
    private JButton backBtn;
    private JTextArea currentInfo;
    private JButton updateBtn;
    private JButton changePwdBtn;

    public MinistryInfoSettingGUI(Ministry mnt, JPanel mainPanel) {
        backBtn = new JButton("Back to main");
        currentInfo = new JTextArea();
        updateBtn = new JButton("Update");
        changePwdBtn = new JButton("Change password");

        add(backBtn);
        add(currentInfo);
        add(updateBtn);
        add(changePwdBtn);

        this.setLayout(null);
        backBtn.setBounds(30 ,40, 140 , 40);
        currentInfo.setBounds(30, 96, 420, 195);
        updateBtn.setBounds(736, 315, 146, 35);
        changePwdBtn.setBounds(736, 360, 146, 35);

        //event handle
        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);
                mainPanel.setVisible(true);
            }
        });
    }

}
