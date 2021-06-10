package swing;

import dao.SemesterDAO;
import pojo.Semester;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class MinistrySemesterPanel extends JPanel {
    private JButton backBtn;
    private JButton findSemBtn;
    private JButton addSemBtn;
    private JTable dataTbl;
    public MinistrySemesterPanel(JPanel mainPanel, Semester currentSemester) {
        backBtn = new JButton("Back to main");
        findSemBtn = new JButton("Find semester");
        addSemBtn = new JButton("Add new semester");
        JTextField searchBar = new JTextField();
        JLabel lbl = new JLabel("Semester name");

        //table data
        List<Semester> semesters = SemesterDAO.getAllSemester();
        this.setDataTble(semesters);
        JScrollPane pane  = new JScrollPane(dataTbl);
        pane.setBounds(20, 100, 860,320);

        backBtn.setBounds(20,10, 160,35);
        searchBar.setBounds(140, 55, 220, 25);
        lbl.setBounds(20, 55, 120, 25);
        findSemBtn.setBounds(370, 55, 120, 25);
        addSemBtn.setBounds(578, 55, 300, 25);
        this.setLayout(null);

        add(backBtn); add(findSemBtn); add(addSemBtn); add(searchBar); add(lbl); add(pane);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainPanel.repaint();
                mainPanel.setVisible(true);
            }
        });
        findSemBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(searchBar.getText().equals("")) {
                    JOptionPane.showMessageDialog(findSemBtn, "No data to search");
                }
                else {
                    List<Semester> foundSems = SemesterDAO.getSemestersByName(searchBar.getText());
                    if(!foundSems.isEmpty()) {
                        resetScrollPane(foundSems);
                    }
                    else {
                        JOptionPane.showMessageDialog(findSemBtn, "No result matched");
                    }
                }
            }
        });
        MinistrySemesterPanel panel = this;
        addSemBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame addingSemester = new AddingSemesterFrame(semesters, panel);
                addingSemester.setVisible(true);
            }
        });
    }
    public void setDataTble(List<Semester> semList) {
        String column[] = {"SEMESTER ID", "SEMESTER NAME", "YEAR", "START DATE", "END DATE", "SET AS CURRENT SEMESTER", "DELETE"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        for (Semester item : semList) {
            model.addRow(new Object[]{item.getId(), item.getSemestername(), item.getSemesteryear(), item.getStartdate(), item.getEnddate(), "Set", "Delete"});
        }
        dataTbl = new JTable(model);
        ButtonRenderer setBtn = new ButtonRenderer();
        ButtonRenderer deleteBtn = new ButtonRenderer();
        ButtonEditor setCell = new ButtonEditor(new JTextField());
        ButtonEditor deleteCell = new ButtonEditor(new JTextField());

        dataTbl.getColumnModel().getColumn(5).setCellRenderer(setBtn);
        dataTbl.getColumnModel().getColumn(5).setCellEditor(setCell);
        dataTbl.getColumnModel().getColumn(6).setCellRenderer(deleteBtn);
        dataTbl.getColumnModel().getColumn(6).setCellEditor(deleteCell);

        setCell.getBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now));
            }
        });

        deleteCell.getBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = dataTbl.getSelectedRow();
                if (index != -1) {
                    int modelIndex = dataTbl.convertRowIndexToModel(index);
                    try {
                        int deleteId = Integer.parseInt(dataTbl.getValueAt(index, 0).toString()) ;
                        if (SemesterDAO.deleteSemester(deleteId)) {
                            JOptionPane.showMessageDialog(deleteCell.getBtn(), "Deleted semester with ID " + String.valueOf(deleteId));
                        }
                        else
                            JOptionPane.showMessageDialog(deleteCell.getBtn(), "Failed to delete semester");
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(deleteCell.getBtn(), "Number format exception");
                    }
                    DefaultTableModel model = (DefaultTableModel) dataTbl.getModel();
                    model.removeRow(modelIndex);
                }
            }
        });
    }

    public void resetScrollPane(List<Semester> sems) {
        Component[] components = getComponents();
        for (Component component : components) {
            if (component.getClass().equals(JScrollPane.class)) {
                remove(component);
            }
        }
        setDataTble(sems);
        JScrollPane newPane = new JScrollPane(dataTbl);
        newPane.setBounds(20, 100, 860,320);
        add(newPane);
        revalidate();
        repaint();
    }
}
