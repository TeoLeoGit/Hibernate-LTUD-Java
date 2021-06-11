package swing;

import dao.ClassDAO;
import dao.CourseDAO;
import dao.SemesterDAO;
import pojo.Course;
import pojo.Semester;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MinistryCoursePanel extends JPanel {
    private JButton backBtn;
    private JButton findCourseBtn;
    private JButton addCourseBtn;
    private JTable dataTbl;
    private Set<Course> courses;
    private Semester selectedSem;
    public MinistryCoursePanel(final Semester[] currentSem, JPanel mainPanel) {
        backBtn = new JButton("Back to main");
        findCourseBtn = new JButton("Find course");
        addCourseBtn = new JButton("Add new Course");
        JTextField searchBar = new JTextField();
        JLabel lbl = new JLabel("Course name");
        selectedSem = currentSem[0];
        //table data
        courses = selectedSem.getSemCourses();
        this.setDataTble();
        JScrollPane pane = new JScrollPane(dataTbl);
        pane.setBounds(20, 100, 860, 320);

        backBtn.setBounds(20, 10, 160, 35);
        searchBar.setBounds(100, 55, 220, 25);
        lbl.setBounds(20, 55, 120, 25);
        findCourseBtn.setBounds(330, 55, 120, 25);
        addCourseBtn.setBounds(578, 55, 300, 25);
        this.setLayout(null);

        add(backBtn);
        add(addCourseBtn);
        add(findCourseBtn); add(lbl); add(searchBar);
        add(pane);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                currentSem[0] = selectedSem;
                mainPanel.repaint();
                mainPanel.setVisible(true);
            }
        });

        MinistryCoursePanel panel = this;
        addCourseBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame addingCourse = new AddingCourseFrame(courses, selectedSem, panel);
                addingCourse.setVisible(true);
            }
        });
    }
    public void setDataTble() {
        String column[] = {"COURSE ID", "SUBJECT ID", "SUBJECT NAME", "TEACHER NAME", "CLASSROOM", "CLASS DAY", "SHIFT",
                "MAX ATTENDANT", "DELETE"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        String shiftName = "Not setted";
        for (Course item : courses) {
            switch (item.getShift()) {
                case 1:
                    shiftName = "7:30 - 9:30";
                    break;
                case 2:
                    shiftName = "9:30 - 11:30";
                    break;
                case 3:
                    shiftName = "13:30 - 15:30";
                    break;
                case 4:
                    shiftName = "15:30 - 17:30";
                    break;
                default:
                    break;
            }
            model.addRow(new Object[]{item.getId(), item.getSubject().getId(), item.getSubject().getSubjectname(),
            item.getTeacherName(), item.getClassroom(), item.getDayOfWeek(), shiftName, item.getMaxAttendant(), "Delete"});
        }
        dataTbl = new JTable(model);
        ButtonRenderer deleteBtn = new ButtonRenderer();
        ButtonEditor deleteCell = new ButtonEditor(new JTextField());

        dataTbl.getColumnModel().getColumn(8).setCellRenderer(deleteBtn);
        dataTbl.getColumnModel().getColumn(8).setCellEditor(deleteCell);

        deleteCell.getBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = dataTbl.getSelectedRow();
                if (index != -1) {
                    int modelIndex = dataTbl.convertRowIndexToModel(index);
                    try {
                        int deleteId = Integer.parseInt(dataTbl.getValueAt(index, 0).toString());
                        if (CourseDAO.deleteCourse(deleteId)) {
                            //update semester
                            selectedSem = SemesterDAO.getSemesterById(selectedSem.getId());
                            courses = selectedSem.getSemCourses();
                            JOptionPane.showMessageDialog(deleteCell.getBtn(), "Deleted course with ID " + String.valueOf(deleteId));
                            DefaultTableModel model = (DefaultTableModel) dataTbl.getModel();
                            model.removeRow(modelIndex);
                        }
                        else {
                            JOptionPane.showMessageDialog(deleteCell.getBtn(), "Failed to delete class");
                        }
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(deleteCell.getBtn(), "Number format exception");
                    }
                }
            }
        });
    }

    public void resetScrollPane() {
        Component[] components = getComponents();
        for (Component component : components) {
            if (component.getClass().equals(JScrollPane.class)) {
                remove(component);
            }
        }
        setDataTble();
        JScrollPane newPane = new JScrollPane(dataTbl);
        newPane.setBounds(20, 100, 860,320);
        add(newPane);
        revalidate();
        repaint();
    }
}
