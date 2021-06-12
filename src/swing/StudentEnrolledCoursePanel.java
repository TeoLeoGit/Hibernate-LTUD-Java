package swing;

import dao.*;
import pojo.*;
import pojo.Class;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class StudentEnrolledCoursePanel extends JPanel {
    private JButton backBtn;
    private JTable dataTbl;
    private Student loginStudent;
    private Courseregistrationsession openingSess;

    public StudentEnrolledCoursePanel(JPanel mainPanel, Student student, final Courseregistrationsession[] openingSession) {
        backBtn = new JButton("Back to main");
        JTextField searchBar = new JTextField();
        loginStudent = student;
        openingSess = openingSession[0];

        //table data
        List<Coursesregistration> registeredCourses= CourseRegistrationDAO.getCrByStudent(student);
        for (Coursesregistration item : registeredCourses) {
            System.out.println(item.getRegisterdate());
            System.out.println(item.getCourse());
        }
        this.setDataTble(registeredCourses);
        JScrollPane pane  = new JScrollPane(dataTbl);
        pane.setBounds(20, 100, 860,320);

        backBtn.setBounds(20,10, 160,35);
        searchBar.setBounds(140, 55, 220, 25);
        this.setLayout(null);

        add(backBtn); add(pane);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openingSession[0] = openingSess;
                setVisible(false);
                mainPanel.repaint();
                mainPanel.setVisible(true);
            }
        });

    }

    public void setDataTble(List<Coursesregistration> crs) {
        String column[] = {"COURSE ID", "SUBJECT ID", "SUBJECT NAME", "TEACHER NAME", "CLASSROOM", "DAY OF WEEK", "SHIFT", "REMOVE"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        for (Coursesregistration item : crs) {
            Course current = item.getCourse();
            model.addRow(new Object[]{current.getId(), item.getCourse().getSubject().getId(), item.getCourse().getSubject().getId(),
                    current.getTeacherName(), current.getClassroom(), current.getDayOfWeek(), current.getShift(), "Remove"});
        }
        dataTbl = new JTable(model);
        ButtonRenderer removeBtn = new ButtonRenderer();
        ButtonEditor removeCell = new ButtonEditor(new JTextField());

        dataTbl.getColumnModel().getColumn(7).setCellRenderer(removeBtn);
        dataTbl.getColumnModel().getColumn(7).setCellEditor(removeCell);

        removeCell.getBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date today = java.util.Calendar.getInstance().getTime();
                int index = dataTbl.getSelectedRow();
                if (index != -1) {
                    int modelIndex = dataTbl.convertRowIndexToModel(index);
                    try {
                        int removeId = Integer.parseInt(dataTbl.getValueAt(index, 0).toString());
                        Course removeCourse = CourseDAO.getCourseById(removeId);

                        //kiem tra con han dang ky
                        if (removeCourse.getSemester().getId() == openingSess.getSemester().getId() && openingSess.getEnddate().compareTo(today) >= 0
                            && openingSess.getStartdate().compareTo(today) <= 0) {
                            if (CourseRegistrationDAO.deleteCrs(loginStudent, removeCourse)) {
                                removeCourse.setAttendant(removeCourse.getAttendant() - 1);
                                CourseDAO.updateCourse(removeCourse);
                                for (Courseregistrationsession item : CourseRegistrationSessionDAO.getOpeningSession()) {
                                    openingSess = item;
                                    break;
                                }
                                JOptionPane.showMessageDialog(removeCell.getBtn(), "Removed registration");
                                DefaultTableModel model = (DefaultTableModel) dataTbl.getModel();
                                model.removeRow(modelIndex);
                            } else {
                                JOptionPane.showMessageDialog(removeCell.getBtn(), "Failed to remove registration");
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(removeCell.getBtn(), "It's too late to remove");
                        }
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(removeCell.getBtn(), "Number format exception");
                    }
                }
            }
        });


    }

    public void resetScrollPane(List<Coursesregistration> courses) {
        Component[] components = getComponents();
        for (Component component : components) {
            if (component.getClass().equals(JScrollPane.class)) {
                remove(component);
            }
        }
        setDataTble(courses);
        JScrollPane newPane = new JScrollPane(dataTbl);
        newPane.setBounds(20, 100, 860,320);
        add(newPane);
        revalidate();
        repaint();
    }
}
