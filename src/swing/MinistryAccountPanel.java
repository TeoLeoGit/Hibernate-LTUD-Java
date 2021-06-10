package swing;

import dao.MinistryDAO;
import pojo.Ministry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MinistryAccountPanel extends JPanel {
    private JButton backBtn;
    private JButton findMntBtn;
    private JButton addMntBtn;
    private JTable dataTbl;
    public MinistryAccountPanel(JPanel mainPanel) {
        backBtn = new JButton("Back to main");
        findMntBtn = new JButton("Find ministry");
        addMntBtn = new JButton("Add new ministry");
        JTextField searchBar = new JTextField();
        JLabel lbl = new JLabel("Ministry firstname");

        //table data
        List<Ministry> ministries = MinistryDAO.getAllMinistries();
        this.setDataTble(ministries);
        JScrollPane pane  = new JScrollPane(dataTbl);
        pane.setBounds(20, 100, 860,320);

        backBtn.setBounds(20,10, 160,35);
        searchBar.setBounds(140, 55, 220, 25);
        lbl.setBounds(20, 55, 120, 25);
        findMntBtn.setBounds(370, 55, 120, 25);
        addMntBtn.setBounds(578, 55, 300, 25);
        this.setLayout(null);

        add(backBtn); add(findMntBtn); add(addMntBtn); add(searchBar); add(lbl); add(pane);

        backBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainPanel.repaint();
                mainPanel.setVisible(true);
            }
        });
        findMntBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(searchBar.getText().equals("")) {
                    JOptionPane.showMessageDialog(findMntBtn, "No data to search");
                }
                else {
                    List<Ministry> foundMnts = MinistryDAO.getMinistriesByFirstname(searchBar.getText());
                    if(!foundMnts.isEmpty()) {
                        resetScrollPane(foundMnts);
                    }
                    else {
                        JOptionPane.showMessageDialog(findMntBtn, "No result matched");
                    }
                }
            }
        });
        MinistryAccountPanel panel = this;
        addMntBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame addMntFrame = new AddingMinistryFrame(ministries, panel);
                addMntFrame.setVisible(true);
            }
        });
    }

    public void setDataTble(List<Ministry> ministries) {
        String column[]={"ACCOUNT ID", "MINISTRY ID", "FIRSTNAME","LASTNAME", "DAY OF BIRTH", "EMAIL", "ADDRESS", "PHONE", "EDIT", "DELETE",
            "RESET PWD"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        for (Ministry item : ministries) {
            model.addRow(new Object[]{String.valueOf(item.getId()), item.getMinistryid(), item.getFirstname(), item.getLastname(), item.getDayofbirth(),
                    item.getEmail(), item.getAddress(), item.getPhone(), "Edit", "Delete", "Reset password"});
        }
        dataTbl = new JTable(model);
        ButtonRenderer editBtn = new ButtonRenderer();
        ButtonRenderer deleteBtn = new ButtonRenderer();
        ButtonRenderer resetPwdBtn = new ButtonRenderer();
        ButtonEditor editCell = new ButtonEditor(new JTextField());
        ButtonEditor deleteCell = new ButtonEditor(new JTextField());
        ButtonEditor resetCell = new ButtonEditor(new JTextField());

        dataTbl.getColumnModel().getColumn(8).setCellRenderer(editBtn);
        dataTbl.getColumnModel().getColumn(8).setCellEditor(editCell);
        dataTbl.getColumnModel().getColumn(9).setCellRenderer(deleteBtn);
        dataTbl.getColumnModel().getColumn(9).setCellEditor(deleteCell);
        dataTbl.getColumnModel().getColumn(10).setCellRenderer(resetPwdBtn);
        dataTbl.getColumnModel().getColumn(10).setCellEditor(resetCell);

        //event in table
        deleteCell.getBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = dataTbl.getSelectedRow();
                if (index != -1) {
                    int modelIndex = dataTbl.convertRowIndexToModel(index);
                    try {
                        int deleteId = Integer.parseInt((String) dataTbl.getValueAt(index, 0)) ;
                        if (MinistryDAO.deleteMinistry(deleteId)) {
                            JOptionPane.showMessageDialog(deleteCell.getBtn(), "Deleted ministry with ID " + String.valueOf(deleteId));
                        }
                        else
                            JOptionPane.showMessageDialog(deleteCell.getBtn(), "Failed to delete ministry");
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(deleteCell.getBtn(), "Number format exception");
                    }
                    DefaultTableModel model = (DefaultTableModel) dataTbl.getModel();
                    model.removeRow(modelIndex);
                }
            }
        });

        resetCell.getBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = dataTbl.getSelectedRow();
                if (index != -1) {
                    try {
                        int resetId = Integer.parseInt((String) dataTbl.getValueAt(index, 0)) ;
                        Ministry resetMnt = MinistryDAO.getMinistryById(resetId);
                        resetMnt.setPassword(String.valueOf(resetMnt.getMinistryid()));
                        if (MinistryDAO.updateMinistryAccount(resetMnt)) {
                            JOptionPane.showMessageDialog(deleteCell.getBtn(), "New password is ministry ID number");
                        }
                        else
                            JOptionPane.showMessageDialog(deleteCell.getBtn(), "Failed to reset password");
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(deleteCell.getBtn(), "Number format exception");
                    }
                }
            }
        });
        MinistryAccountPanel panel = this;
        editCell.getBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = dataTbl.getSelectedRow();
                if (index != -1) {
                    try {
                        int editId = Integer.parseInt((String) dataTbl.getValueAt(index, 0)) ;
                        JFrame editFrame = new EditMinistryFrame(ministries, editId, panel);
                        editFrame.setVisible(true);
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(editCell.getBtn(), "Number format exception");
                    }
                }
            }
        });
    }

    public void resetScrollPane(List<Ministry> mnts) {
        Component[] components = getComponents();
        for (Component component : components) {
            if (component.getClass().equals(JScrollPane.class)) {
                remove(component);
            }
        }
        setDataTble(mnts);
        JScrollPane newPane = new JScrollPane(dataTbl);
        newPane.setBounds(20, 100, 860,320);
        add(newPane);
        revalidate();
        repaint();
    }
}
