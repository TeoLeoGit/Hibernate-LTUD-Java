package swing;

import dao.MinistryDAO;
import pojo.Ministry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class EditMinistryFrame extends JFrame {
    JPanel mainPanel;
    JButton confirmBtn;
    public EditMinistryFrame(List<Ministry> listMnt, int editId, MinistryAccountPanel panel) {
        mainPanel = new JPanel();
        confirmBtn = new JButton("Confirm");

        JLabel idLabel = new JLabel("ID");
        JLabel firstnameLabel = new JLabel("First name");
        JLabel lastnameLabel = new JLabel("Last name");
        JLabel dayOfBirthLabel = new JLabel("Day of birth");
        JLabel emailLabel = new JLabel("Email");
        JLabel addressLabel = new JLabel("Address");
        JLabel phoneLabel = new JLabel("Phone");
        JLabel dayLabel = new JLabel("Day");
        JLabel monthLabel = new JLabel("Month");
        JLabel yearLabel = new JLabel("Year");
        JLabel ministryIdLabel = new JLabel("Ministry ID");

        JTextField fnTxt = new JTextField();
        JTextField lnTxt = new JTextField();
        JTextField dayTxt = new JTextField();
        JTextField monthTxt = new JTextField();
        JTextField yearTxt = new JTextField();
        JTextField emailTxt = new JTextField();
        JTextField addressTxt = new JTextField();
        JTextField phoneTxt = new JTextField();
        JTextField ministryIdTxt = new JTextField();

        mainPanel.add(confirmBtn);
        mainPanel.add(idLabel);
        mainPanel.add(firstnameLabel);
        mainPanel.add(lastnameLabel);
        mainPanel.add(dayOfBirthLabel);
        mainPanel.add(emailLabel);
        mainPanel.add(addressLabel);
        mainPanel.add(phoneLabel);
        mainPanel.add(dayLabel);
        mainPanel.add(monthLabel);
        mainPanel.add(yearLabel);
        mainPanel.add(ministryIdLabel);
        mainPanel.add(fnTxt);
        mainPanel.add(lnTxt);
        mainPanel.add(dayTxt);
        mainPanel.add(monthTxt);
        mainPanel.add(yearTxt);
        mainPanel.add(emailTxt);
        mainPanel.add(addressTxt);
        mainPanel.add(phoneTxt);
        mainPanel.add(ministryIdTxt);
        mainPanel.setLayout(null);

        firstnameLabel.setBounds(20, 20, 120, 25);
        lastnameLabel.setBounds(20, 50, 120, 25);
        dayOfBirthLabel.setBounds(20, 80, 120, 25);
        emailLabel.setBounds(20, 110, 120, 25);
        addressLabel.setBounds(20, 140, 120, 25);
        phoneLabel.setBounds(20, 170, 120, 25);
        ministryIdLabel.setBounds(20, 200, 120, 25);

        fnTxt.setBounds(140, 20, 260, 25);
        lnTxt.setBounds(140, 50, 260, 25);
        dayLabel.setBounds(140, 80, 30, 25);
        dayTxt.setBounds(170, 80, 30, 25);
        monthLabel.setBounds(238, 80, 50, 25);
        monthTxt.setBounds(280, 80, 30, 25);
        yearLabel.setBounds(328, 80, 30, 25);
        yearTxt.setBounds(358, 80, 40, 25);
        emailTxt.setBounds(140, 110, 260, 25);
        addressTxt.setBounds(140, 140, 260, 25);
        phoneTxt.setBounds(140, 170, 260, 25);
        ministryIdTxt.setBounds(140, 200, 260, 25);
        confirmBtn.setBounds(100, 240, 220, 30);

        this.add(mainPanel);
        this.setSize(420, 330);
        this.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int change = 0;
                Ministry temp = new Ministry();
                Ministry editMnt = new Ministry();
                for (Ministry item : listMnt) {
                    if (item.getId() == editId) {
                        temp = item;
                        editMnt = item;
                        break;
                    }
                }
                if (!fnTxt.getText().equals("")) {
                    change++;
                    editMnt.setFirstname(fnTxt.getText());
                }
                if (!lnTxt.getText().equals("")) {
                    change++;
                    editMnt.setLastname(lnTxt.getText());
                }
                if (!dayTxt.getText().equals("") && !monthTxt.getText().equals("") && !yearTxt.getText().equals("")) {
                    change++;
                    try {
                        Integer.parseInt(dayTxt.getText());
                        Integer.parseInt(monthTxt.getText());
                        Integer.parseInt(yearTxt.getText());
                        editMnt.setDayofbirth(Date.valueOf(yearTxt.getText() + "-" +
                                monthTxt.getText() + "-" + dayTxt.getText()));

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(confirmBtn, "Please fill in the text fields a valid date");
                    }
                }
                if (!emailTxt.getText().equals("")) {
                    change++;
                    editMnt.setEmail(emailTxt.getText());
                }
                if (!addressTxt.getText().equals("")) {
                    change++;
                    editMnt.setAddress(addressTxt.getText());
                }
                if (!phoneTxt.getText().equals("")) {
                    change++;
                    editMnt.setPhone(phoneTxt.getText());
                }
                if (!ministryIdTxt.getText().equals("")) {
                    change++;
                    editMnt.setMinistryid(Integer.valueOf(ministryIdTxt.getText()));
                }
                if (change > 0) {
                    if (MinistryDAO.updateMinistryAccount(editMnt)) {
                        listMnt.remove(temp);
                        listMnt.add(editMnt);
                        panel.resetScrollPane();
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
