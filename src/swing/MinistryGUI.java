package swing;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MinistryGUI extends JFrame {
    private JPanel PanelMinistry;
    private JButton subjectCaseButton;
    private JButton semesterCaseButton;
    private JButton ministryCaseButton;
    private JButton classCaseButton;
    private JButton studentCaseButton;
    private JButton registryCaseButton;
    private JButton courseCaseButton;
    private JButton studentRegCaseButton;
    private JButton button9;
    private JButton button10;
    private JPanel middlePanel;
    private JTextPane informationTextPane;
    //private JLabel informationLabel;

    public MinistryGUI(String title) {
        //JFrame setting
        this.setSize(820, 510);
        this.setPreferredSize(new Dimension(this.getSize().width,this.getSize().height));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(PanelMinistry);
        //component setting
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
        //informationLabel.setBorder(border);
        this.pack();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
