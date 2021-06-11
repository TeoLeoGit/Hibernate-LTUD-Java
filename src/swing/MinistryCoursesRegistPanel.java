package swing;

import dao.CourseRegistrationSessionDAO;
import pojo.Courseregistrationsession;
import pojo.Semester;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;


public class MinistryCoursesRegistPanel extends JPanel {
    private JButton backBtn;
    private JButton addSessionBtn;
    private JTable dataTbl;
    private List<Courseregistrationsession> sessions;
    public MinistryCoursesRegistPanel(Semester currentSem, JPanel mainPanel) {
        backBtn = new JButton("Back to main");
        addSessionBtn = new JButton("Add new session");

        //table data
        sessions = CourseRegistrationSessionDAO.getAllSession();
        this.setDataTble();
        JScrollPane pane = new JScrollPane(dataTbl);
        pane.setBounds(20, 100, 860, 320);

        backBtn.setBounds(20, 10, 160, 35);
        addSessionBtn.setBounds(578, 55, 300, 25);
        this.setLayout(null);

        add(backBtn);
        add(addSessionBtn);
        add(pane);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainPanel.repaint();
                mainPanel.setVisible(true);
            }
        });

        MinistryCoursesRegistPanel panel = this;
        addSessionBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame addingClass = new AddingSessionFrame(sessions, currentSem, panel);
                addingClass.setVisible(true);
            }
        });
    }
    public void setDataTble() {
        String column[] = {"ID", "START DATE", "END DATE", "SEMESTER"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        for (Courseregistrationsession item : sessions) {
            model.addRow(new Object[]{item.getId(), item.getStartdate(), item.getEnddate(),
                    item.getSemester().getSemestername()});
        }
        dataTbl = new JTable(model);
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
