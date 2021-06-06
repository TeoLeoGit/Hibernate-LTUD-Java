package swing;

import javax.swing.*;


public class MinistryAccountUsecaseGUI {

     public MinistryAccountUsecaseGUI(String title) {
            JPanel mainPanel = new JPanel();
            JFrame frame = new JFrame();
            frame.setSize(100, 100);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.add(mainPanel);
            mainPanel.setLayout(null);

            JLabel label = new JLabel("Test");
            label.setBounds(10, 20, 80, 25);
            mainPanel.add(label);

            frame.setVisible(true);
     }
}
