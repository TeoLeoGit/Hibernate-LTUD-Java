package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.AccountDAO;
import pojo.Account;
import java.util.List;

public class Login extends JFrame {
    private JPanel panelLoginMain;
    private JTextField usernameTextField;
    private JLabel loginLabel;
    private JButton loginButton;
    private JTextField passwordTextField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;


    public Login(String title) {
        super(title);
        this.setSize(400, 150);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(this.getSize().width,this.getSize().height));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelLoginMain);
        this.pack();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                List<Account> foundAccounts = AccountDAO.getAccountsByUsername(username);
                if (foundAccounts == null)
                    System.out.println("No user found.");
                else
                    for(Account item : foundAccounts) {
                        if (item.getPassword().equals(password)) {
                            System.out.println("Welcome");
                            break;
                        }
                    }
            }
        });
    }

}