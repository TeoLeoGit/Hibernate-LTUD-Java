package swing;

import dao.ClassDAO;
import dao.StudentDAO;
import pojo.Class;
import pojo.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinistryStudentPanel extends JPanel {
    private JButton backBtn;
    private JButton findStdBtn;
    private JButton addStdBtn;
    private JTable dataTbl;
    private Set<Student> students;
    public MinistryStudentPanel(JPanel mainPanel) {
        backBtn = new JButton("Back to main");
        findStdBtn = new JButton("Find student");
        addStdBtn = new JButton("Add new student");
        JTextField searchBar = new JTextField();
        JLabel lbl = new JLabel("Student ID");

        //ComboBox list
        String[] classNameList;
        List<Class> allClass = ClassDAO.getAllClasses();
        classNameList = new String[allClass.size()];
        int i = 0;
        for (Class item : allClass) {
            classNameList[i] = item.getClassname();
            i++;
        }
        JComboBox classListBox = new JComboBox(classNameList);
        //table data
        //Class currentClass = allClass.get(0);
        students = allClass.get(0).getStudents();
        this.setDataTble();
        JScrollPane pane = new JScrollPane(dataTbl);
        pane.setBounds(20, 120, 860, 340);
        backBtn.setBounds(20, 10, 160, 35);
        searchBar.setBounds(140, 55, 220, 25);
        lbl.setBounds(20, 55, 120, 25);
        findStdBtn.setBounds(370, 55, 120, 25);
        addStdBtn.setBounds(578, 55, 300, 25);
        classListBox.setBounds(20, 100, 220, 25);
        this.setLayout(null);

        add(backBtn);
        add(findStdBtn);
        add(addStdBtn);
        add(searchBar);
        add(lbl);
        add(pane);
        add(classListBox);
        add(classListBox);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainPanel.repaint();
                mainPanel.setVisible(true);
            }
        });
        findStdBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (searchBar.getText().equals("")) {
                    JOptionPane.showMessageDialog(findStdBtn, "No data to search");
                } else {
                    List<Student> foundStds = StudentDAO.getStudentsByStudentId(Integer.valueOf(searchBar.getText()));
                    Set<Student> founds = new HashSet<Student>(foundStds);
                    students.addAll(founds);
                    if (!foundStds.isEmpty()) {
                        resetScrollPane();
                    } else {
                        JOptionPane.showMessageDialog(findStdBtn, "No result matched");
                    }
                }
            }
        });
        MinistryStudentPanel panel = this;
        addStdBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //JFrame addMntFrame = new AddingMinistryFrame(ministries, panel);
                //addMntFrame.setVisible(true);
            }
        });
    }

    public void setDataTble() {
        String column[]={"CLASS", "ACCOUNT ID", "STUDENT ID", "FIRSTNAME", "LASTNAME", "EDIT", "RESET PWD"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        for (Student item : students) {
            model.addRow(new Object[]{ item.getClassroom().getClassname(), String.valueOf(item.getId()),
                    item.getStudentId(), item.getFirstname(), item.getLastname(),"Edit", "Reset password"});
        }
        dataTbl = new JTable(model);
        ButtonRenderer editBtn = new ButtonRenderer();
        ButtonRenderer resetPwdBtn = new ButtonRenderer();
        ButtonEditor editCell = new ButtonEditor(new JTextField());
        ButtonEditor resetCell = new ButtonEditor(new JTextField());

        dataTbl.getColumnModel().getColumn(5).setCellRenderer(editBtn);
        dataTbl.getColumnModel().getColumn(5).setCellEditor(editCell);
        dataTbl.getColumnModel().getColumn(6).setCellRenderer(resetPwdBtn);
        dataTbl.getColumnModel().getColumn(6).setCellEditor(resetCell);

        //event in table

        resetCell.getBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = dataTbl.getSelectedRow();
                if (index != -1) {
                    try {
                        int resetId = Integer.parseInt((String) dataTbl.getValueAt(index, 0)) ;
                        Student resetStd = StudentDAO.getStudentById(resetId);
                        resetStd.setPassword(String.valueOf(resetStd.getStudentId()));
                        if (StudentDAO.updateStudentAccount(resetStd)) {
                            JOptionPane.showMessageDialog(resetCell.getBtn(), "New password is ministry ID number");
                        }
                        else
                            JOptionPane.showMessageDialog(resetCell.getBtn(), "Failed to reset password");
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(resetCell.getBtn(), "Number format exception");
                    }
                }
            }
        });
        MinistryStudentPanel panel = this;
        editCell.getBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = dataTbl.getSelectedRow();
                if (index != -1) {
                    try {
                        //int editId = Integer.parseInt((String) dataTbl.getValueAt(index, 0)) ;
                        //JFrame editFrame = new EditMinistryFrame(ministries, editId, panel);
                        //editFrame.setVisible(true);
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(editCell.getBtn(), "Number format exception");
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
        newPane.setBounds(20, 120, 860,340);
        add(newPane);
        revalidate();
        repaint();
    }
}
