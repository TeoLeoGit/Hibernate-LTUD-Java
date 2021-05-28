import dao.AccountDAO;
import dao.StudentDAO;
import java.util.List;
import pojo.*;
import swing.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        List<Account> rs = AccountDAO.getAllAccount();
        for (Account item : rs) {
            System.out.println(item.getId() + ", " +  item.getUsername());
        }
        List<Student> st = StudentDAO.getAllStudent();
        for (Student item :st) {
            System.out.println(item.getId() + ", " +  item.getStudentid());
        }

        JFrame frame = new Login("Login");
        frame.setVisible(true);
    }
}
