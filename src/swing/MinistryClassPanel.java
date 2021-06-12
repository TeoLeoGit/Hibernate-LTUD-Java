package swing;

import dao.ClassDAO;
import pojo.Class;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class MinistryClassPanel extends JPanel {
    private JButton backBtn;
    private JButton findClassBtn;
    private JButton addClassBtn;
    private JTable dataTbl;
    private List<Class> classes;
    public MinistryClassPanel(JPanel mainPanel) {
        backBtn = new JButton("Back to main");
        findClassBtn = new JButton("Find class");
        addClassBtn = new JButton("Add new class");
        JTextField searchBar = new JTextField();
        JLabel lbl = new JLabel("Class name");

        //table data
        classes = ClassDAO.getAllClasses();
        this.setDataTble();
        JScrollPane pane = new JScrollPane(dataTbl);
        pane.setBounds(20, 100, 860, 320);

        backBtn.setBounds(20, 10, 160, 35);
        searchBar.setBounds(100, 55, 220, 25);
        lbl.setBounds(20, 55, 120, 25);
        findClassBtn.setBounds(330, 55, 120, 25);
        addClassBtn.setBounds(578, 55, 300, 25);
        this.setLayout(null);

        add(backBtn);
        add(findClassBtn);
        add(addClassBtn);
        add(searchBar);
        add(lbl);
        add(pane);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainPanel.repaint();
                mainPanel.setVisible(true);
            }
        });
        findClassBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (searchBar.getText().equals("")) {
                    JOptionPane.showMessageDialog(findClassBtn, "No data to search");
                } else {
                    List<Class> foundSems = ClassDAO.getClassesByName(searchBar.getText());
                    if (!foundSems.isEmpty()) {
                        resetScrollPane();
                    } else {
                        JOptionPane.showMessageDialog(findClassBtn, "No result matched");
                    }
                }
            }
        });
        MinistryClassPanel panel = this;
        addClassBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame addingClass = new AddingClassFrame(classes, panel);
                addingClass.setVisible(true);
            }
        });
    }
    public void setDataTble() {
        String column[] = {"CLASS ID", "CLASS NAME", "NUMBER OF STUDENT", "NUMBER OF MALE", "NUMBER OF FEMALE", "DELETE"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        for (Class item : classes) {
            model.addRow(new Object[]{item.getClassId(), item.getClassname(), item.getNumberOfStudent(),
                    item.getMaleNumber(), item.getFemaleNumber(), "Delete"});
        }
        dataTbl = new JTable(model);
        ButtonRenderer deleteBtn = new ButtonRenderer();
        ButtonEditor deleteCell = new ButtonEditor(new JTextField());

        dataTbl.getColumnModel().getColumn(5).setCellRenderer(deleteBtn);
        dataTbl.getColumnModel().getColumn(5).setCellEditor(deleteCell);

        deleteCell.getBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = dataTbl.getSelectedRow();
                if (index != -1) {
                    int modelIndex = dataTbl.convertRowIndexToModel(index);
                    try {
                        int deleteId = Integer.parseInt(dataTbl.getValueAt(index, 0).toString());
                        if (ClassDAO.deleteClass(deleteId)) {
                            for (Iterator<Class> iter = classes.listIterator(); iter.hasNext(); ) {
                                Class a = iter.next();
                                if (a.getClassId() == deleteId) {
                                    iter.remove();
                                    break;
                                }
                            }
                            JOptionPane.showMessageDialog(deleteCell.getBtn(), "Deleted class with ID " + String.valueOf(deleteId));
                            DefaultTableModel model = (DefaultTableModel) dataTbl.getModel();
                            model.removeRow(modelIndex);
                        }
                        else {
                            classes.add(ClassDAO.getClassById(deleteId));
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
