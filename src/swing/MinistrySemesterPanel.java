package swing;

import dao.SemesterDAO;
import pojo.Semester;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MinistrySemesterPanel extends JPanel {
    private JButton backBtn;
    private JButton findSemBtn;
    private JButton addSemBtn;
    private JTable dataTbl;
    private Semester setCurrentSem;

    public MinistrySemesterPanel(JPanel mainPanel, final Semester[] currentSem) {
        backBtn = new JButton("Back to main");
        findSemBtn = new JButton("Find semester");
        addSemBtn = new JButton("Add new semester");
        JTextField searchBar = new JTextField();
        JLabel lbl = new JLabel("Semester name");
        setCurrentSem = currentSem[0];

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
                currentSem[0] = setCurrentSem;
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
                SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                Date today = java.util.Calendar.getInstance().getTime();
                int index = dataTbl.getSelectedRow();
                if (index != -1) {
                    try {
                        try {
                            Date checkEndDate = sdformat.parse(dataTbl.getValueAt(index, 4).toString());
                            if (checkEndDate.compareTo(today) > 0) {
                                setCurrentSem = SemesterDAO.getSemesterById(Integer.parseInt(dataTbl.
                                        getValueAt(index, 0).toString()));
                                JOptionPane.showMessageDialog(setCell.getBtn(), "Current semester is set");
                            }
                            else
                                JOptionPane.showMessageDialog(setCell.getBtn(), "Cannot set ended semester as " +
                                        "current semester");
                        }
                        catch (ParseException pe) {
                            JOptionPane.showMessageDialog(setCell.getBtn(), "Error converting datetime format");
                        }
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(setCell.getBtn(), "Number format exception");
                    }
                }
            }
        });

        deleteCell.getBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = dataTbl.getSelectedRow();
                if (index != -1) {
                    int modelIndex = dataTbl.convertRowIndexToModel(index);
                    try {
                        int deleteId = Integer.parseInt(dataTbl.getValueAt(index, 0).toString());
                        if (setCurrentSem.getId() == deleteId) {
                            JOptionPane.showMessageDialog(deleteCell.getBtn(), "Cannot delete semester set as " +
                                    " current semester");
                        }
                        else {
                            for (Iterator<Semester> iter = semList.listIterator(); iter.hasNext(); ) {
                                Semester a = iter.next();
                                if (a.getId() == deleteId) {
                                    iter.remove();
                                    break;
                                }
                            }
                            if (SemesterDAO.deleteSemester(deleteId)) {
                                JOptionPane.showMessageDialog(deleteCell.getBtn(), "Deleted semester with ID " + String.valueOf(deleteId));
                                DefaultTableModel model = (DefaultTableModel) dataTbl.getModel();
                                model.removeRow(modelIndex);
                            } else {
                                semList.add(SemesterDAO.getSemesterById(deleteId));
                                JOptionPane.showMessageDialog(deleteCell.getBtn(), "Failed to delete semester");
                            }
                        }
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(deleteCell.getBtn(), "Number format exception");
                    }
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
