package swing;

import dao.SubjectDAO;
import pojo.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class MinistrySubjectPanel extends JPanel {
    private JButton backBtn;
    private JButton findSubjBtn;
    private JButton addSubjBtn;
    private JTable dataTbl;
    public MinistrySubjectPanel(JPanel mainPanel) {
        backBtn = new JButton("Back to main");
        findSubjBtn = new JButton("Find subject");
        addSubjBtn = new JButton("Add new subject");
        JTextField searchBar = new JTextField();
        JLabel lbl = new JLabel("Subject name");

        //table data
        List<Subject> subjects = SubjectDAO.getAllSubject();
        this.setDataTble(subjects);
        JScrollPane pane  = new JScrollPane(dataTbl);
        pane.setBounds(20, 100, 860,320);

        backBtn.setBounds(20,10, 160,35);
        searchBar.setBounds(140, 55, 220, 25);
        lbl.setBounds(20, 55, 120, 25);
        findSubjBtn.setBounds(370, 55, 120, 25);
        addSubjBtn.setBounds(578, 55, 300, 25);
        this.setLayout(null);

        add(backBtn); add(findSubjBtn); add(addSubjBtn); add(searchBar); add(lbl); add(pane);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainPanel.repaint();
                mainPanel.setVisible(true);
            }
        });
        findSubjBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(searchBar.getText().equals("")) {
                    JOptionPane.showMessageDialog(findSubjBtn, "No data to search");
                }
                else {
                    List<Subject> foundSjs = SubjectDAO.getSubjectsByName(searchBar.getText());
                    if(!foundSjs.isEmpty()) {
                        resetScrollPane(foundSjs);
                    }
                    else {
                        JOptionPane.showMessageDialog(findSubjBtn, "No result matched");
                    }
                }
            }
        });
        MinistrySubjectPanel panel = this;
        addSubjBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame addingSubject = new AddingSubjectFrame(subjects, panel);
                addingSubject.setVisible(true);
            }
        });
    }
    public void setDataTble(List<Subject> subjects) {
        String column[] = {"SUBJECT ID", "SUBJECT NAME", "CREDITS", "EDIT", "DELETE"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        for (Subject item : subjects) {
            model.addRow(new Object[]{ item.getId(), item.getSubjectname(), String.valueOf(item.getCredits()), "Edit", "Delete"});
        }
        dataTbl = new JTable(model);
        ButtonRenderer editBtn = new ButtonRenderer();
        ButtonRenderer deleteBtn = new ButtonRenderer();
        ButtonEditor editCell = new ButtonEditor(new JTextField());
        ButtonEditor deleteCell = new ButtonEditor(new JTextField());

        dataTbl.getColumnModel().getColumn(3).setCellRenderer(editBtn);
        dataTbl.getColumnModel().getColumn(3).setCellEditor(editCell);
        dataTbl.getColumnModel().getColumn(4).setCellRenderer(deleteBtn);
        dataTbl.getColumnModel().getColumn(4).setCellEditor(deleteCell);

        MinistrySubjectPanel panel = this;
        editCell.getBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = dataTbl.getSelectedRow();
                System.out.println(index);
                if (index != -1) {
                    try {
                        int editId = Integer.parseInt(dataTbl.getValueAt(index, 0).toString()) ;
                        JFrame editFrame = new EditSubjectFrame(subjects, editId, panel);
                        editFrame.setVisible(true);
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(editCell.getBtn(), "Number format exception");
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
                        for (Iterator<Subject> iter = subjects.listIterator(); iter.hasNext(); ) {
                            Subject a = iter.next();
                            if (a.getId() == deleteId) {
                                iter.remove();
                                break;
                            }
                        }
                        if (SubjectDAO.deleteSubject(deleteId)) {
                            JOptionPane.showMessageDialog(deleteCell.getBtn(), "Deleted subject with ID " + String.valueOf(deleteId));
                            DefaultTableModel model = (DefaultTableModel) dataTbl.getModel();
                            model.removeRow(modelIndex);
                        }
                        else {
                            subjects.add(SubjectDAO.getSubjectById(deleteId));
                            JOptionPane.showMessageDialog(deleteCell.getBtn(), "Failed to delete subject");
                        }
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(deleteCell.getBtn(), "Number format exception");
                    }
                }
            }
        });

    }
    public void resetScrollPane(List<Subject> sjs) {
        Component[] components = getComponents();
        for (Component component : components) {
            if (component.getClass().equals(JScrollPane.class)) {
                remove(component);
            }
        }
        setDataTble(sjs);
        JScrollPane newPane = new JScrollPane(dataTbl);
        newPane.setBounds(20, 100, 860,320);
        add(newPane);
        revalidate();
        repaint();
    }
}
