package swing;

import dao.CourseDAO;
import dao.SubjectDAO;
import pojo.Course;
import pojo.Semester;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class AddingCourseFrame extends JFrame {
    JPanel mainPanel;
    JButton confirmBtn;
    public AddingCourseFrame(Set<Course> courses, Semester currentSemester, MinistryCoursePanel panel) {
        mainPanel = new JPanel();
        confirmBtn = new JButton("Confirm");

        JLabel teacher_nameLabel = new JLabel("Teacher name");
        JLabel classroomLabel = new JLabel("Classroom");
        JLabel classDayLabel = new JLabel("Class days");
        JLabel shiftLabel = new JLabel("Shift");
        JLabel subjectIdLabel = new JLabel("Subject ID");
        JLabel maxAttendantLabel = new JLabel("Max attendant");

        JTextField teacher_nameTxt = new JTextField();
        JTextField classroomTxt = new JTextField();
        JTextField classDayTxt = new JTextField();
        JTextField shiftTxt = new JTextField();
        JTextField subjectIdTxt = new JTextField();
        JTextField maxAttendantTxt = new JTextField();

        teacher_nameLabel.setBounds(20, 20, 120, 25);
        classroomLabel.setBounds(20, 50, 120, 25);
        classDayLabel.setBounds(20, 80, 120, 25);
        shiftLabel.setBounds(20, 110, 120, 25);
        subjectIdLabel.setBounds(20, 140, 120, 25);
        maxAttendantLabel.setBounds(20, 170, 120, 25);

        teacher_nameTxt.setBounds(140, 20, 260, 25);
        classroomTxt.setBounds(140, 50, 260, 25);
        classDayTxt.setBounds(140, 80, 260, 25);
        shiftTxt.setBounds(140, 110, 260, 25);
        subjectIdTxt.setBounds(140, 140, 30, 25);
        maxAttendantTxt.setBounds(140, 170, 260, 25);

        confirmBtn.setBounds(100, 210, 220, 30);

        mainPanel.add(confirmBtn);
        mainPanel.add(teacher_nameLabel); mainPanel.add(classroomLabel);
        mainPanel.add(classDayLabel); mainPanel.add(shiftLabel); mainPanel.add(subjectIdLabel); mainPanel.add(maxAttendantLabel);
        mainPanel.add(teacher_nameTxt); mainPanel.add(classroomTxt); mainPanel.add(classDayTxt);
        mainPanel.add(shiftTxt); mainPanel.add(subjectIdTxt); mainPanel.add(maxAttendantTxt);
        mainPanel.add(confirmBtn);
        mainPanel.setLayout(null);


        this.add(mainPanel);
        this.setSize(420, 310);
        this.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course addCourse = new Course();
                int change = 0;
                if (!teacher_nameTxt.getText().equals("")) {
                    change++;
                    addCourse.setTeacherName(teacher_nameTxt.getText());
                }
                if (!classroomTxt.getText().equals("")) {
                    change++;
                    addCourse.setClassroom(classroomTxt.getText());
                }
                if (!shiftTxt.getText().equals("")) {
                    try {
                        int shiftMent = Integer.valueOf(shiftTxt.getText());
                        if (0 < shiftMent && shiftMent < 5) {
                            addCourse.setShift(Integer.valueOf(shiftTxt.getText()));
                            change++;
                        }
                        else
                            JOptionPane.showMessageDialog(confirmBtn, "Shift range 1-4");
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(confirmBtn, "Shift must be a number in rang 1-4");
                    }
                }
                if (!classDayTxt.getText().equals("")) {
                    addCourse.setDayOfWeek(classDayTxt.getText());
                    change++;
                }
                if (!subjectIdTxt.getText().equals("")) {
                    try {
                        int subjectId = Integer.valueOf(subjectIdTxt.getText());
                        if (SubjectDAO.getSubjectById(subjectId) != null) {
                            addCourse.setSubject(SubjectDAO.getSubjectById(Integer.valueOf(subjectIdTxt.getText())));
                            change++;
                        }
                        else {
                            JOptionPane.showMessageDialog(confirmBtn, "No subject match the id");
                        }
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(confirmBtn, "Invalid subject ID");
                    }
                }
                if (!maxAttendantTxt.getText().equals("")) {
                    try {
                        addCourse.setMaxAttendant(Integer.valueOf(maxAttendantTxt.getText()));
                        change++;
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(confirmBtn, "Invalid number of attendant");
                    }
                }
                if (change >= 6) {
                    String[] parts = currentSemester.getSemesteryear().split("-");
                    currentSemester.setSemesteryear(parts[0]);
                    addCourse.setSemester(currentSemester);
                    if (CourseDAO.addCourse(addCourse)) {
                        addCourse.setAttendant(0);
                        courses.add(addCourse);
                        JOptionPane.showMessageDialog(confirmBtn, "Update success");
                        panel.resetScrollPane();
                        dispose();
                    }
                    else
                        JOptionPane.showMessageDialog(confirmBtn, "Update failed");
                } else
                    JOptionPane.showMessageDialog(confirmBtn, "Please fill in all the field properly");
            }
        });
    }
}
