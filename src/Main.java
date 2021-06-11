import dao.ClassDAO;

import pojo.Class;
import pojo.Student;
import swing.*;

import javax.swing.*;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new LoginGUI();
        frame.setVisible(true);

        /*Class test = ClassDAO.getClassById(5);
        System.out.println(test.getClassId());
        Set<Student> test2 = test.getStudents();
        for (Student item : test.getStudents()) {
            System.out.println(item.getId());
            System.out.println(item.getClassroom().getClassname());
            System.out.println(item.getClassroom().getClassId());
        }*/
    }
}
