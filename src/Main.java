import dao.AccountDAO;
import dao.StudentDAO;
import java.util.List;
import pojo.*;
import swing.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new Login("Login");
        frame.setVisible(true);
        //JFrame minGUI = new MinistryGUI(("Ministry"));
        //minGUI.setVisible(true);
        //MinistryAccountUsecaseGUI frame2 = new MinistryAccountUsecaseGUI(("Mini"));
        JFrame fram3 = new MinistrySubjectUsecaseGUI("sfsdf");
        fram3.setVisible(true);
    }
}
