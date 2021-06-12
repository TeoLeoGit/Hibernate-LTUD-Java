package swing;

import dao.CourseDAO;
import dao.CourseRegistrationDAO;
import pojo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class StudentRegistrationPanel extends JPanel {
    private JButton backBtn;
    private JTable dataTbl;
    private Courseregistrationsession regisSession;
    private Student loginStudent;

    public StudentRegistrationPanel(JPanel mainPanel, final Courseregistrationsession[] openingSession, Student student) {
        backBtn = new JButton("Back to main");
        JTextField searchBar = new JTextField();
        regisSession = openingSession[0];
        loginStudent = student;
        //table data
        List<Course> courses = new ArrayList<Course>();
        courses.addAll(regisSession.getSemester().getSemCourses());
        this.setDataTble(courses);
        JScrollPane pane  = new JScrollPane(dataTbl);
        pane.setBounds(20, 100, 860,320);

        backBtn.setBounds(20,10, 160,35);
        searchBar.setBounds(140, 55, 220, 25);
        this.setLayout(null);

        add(backBtn); add(pane);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openingSession[0] = regisSession;
                setVisible(false);
                mainPanel.repaint();
                mainPanel.setVisible(true);
            }
        });

    }

    public void setDataTble(List<Course> courses) {
        String column[] = {"COURSE ID", "SUBJECT ID", "SUBJECT NAME","TEACHER NAME", "CLASSROOM", "DAY OF WEEK", "SHIFT", "MAX ATTENDANT", "ATTENDED", "ENROLL"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        for (Course item : courses) {
            model.addRow(new Object[]{item.getId(), item.getSubject().getId(), item.getSubject().getSubjectname(),
                    item.getTeacherName(), item.getClassroom(), item.getDayOfWeek(), item.getShift(),
                    item.getMaxAttendant(), item.getAttendant(),"Enroll"});
        }
        dataTbl = new JTable(model);
        ButtonRenderer enrollBtn = new ButtonRenderer();
        ButtonEditor enrollCell = new ButtonEditor(new JTextField());

        dataTbl.getColumnModel().getColumn(9).setCellRenderer(enrollBtn);
        dataTbl.getColumnModel().getColumn(9).setCellEditor(enrollCell);

        enrollCell.getBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = dataTbl.getSelectedRow();
                if (index != -1) {
                    try {
                        int enrollId = Integer.parseInt(dataTbl.getValueAt(index, 0).toString());
                        Course enrollCourse = new Course();
                        int i =0;
                        for (Iterator<Course> iter = courses.listIterator(); iter.hasNext(); ) {
                            Course a = iter.next();
                            i++;
                            if (a.getId() == enrollId) {
                                i--;
                                enrollCourse = a;
                                break;
                            }
                        }
                        Coursesregistration newRegistration = new Coursesregistration();
                        newRegistration.setCourse(enrollCourse);
                        newRegistration.setStudent(loginStudent);
                        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                        Date now = java.util.Calendar.getInstance().getTime();
                        String today = sdformat.format(now);
                        newRegistration.setRegisterdate(java.sql.Date.valueOf(today));
                        if (CourseRegistrationDAO.addCourseRegistration(newRegistration)) {
                            enrollCourse.setAttendant(enrollCourse.getAttendant() + 1);
                            if (CourseDAO.updateCourse(enrollCourse)) {
                                courses.set(i, enrollCourse);
                                Set<Course> updateCourses = new HashSet<>(courses);
                                regisSession.getSemester().setSemCourses(updateCourses);
                                JOptionPane.showMessageDialog(enrollCell.getBtn(), "Enrolled ");
                                dataTbl.setValueAt(enrollCourse.getAttendant(), index, 8);
                            } else {
                                JOptionPane.showMessageDialog(enrollCell.getBtn(), "Failed to update");
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(enrollCell.getBtn(), "You have already enrolled this course");
                        }
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(enrollCell.getBtn(), "Invalid input");
                    }
                }
            }
        });


    }

    public void resetScrollPane(List<Course> courses) {
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
