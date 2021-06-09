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
    public MinistryAccountPanel(Ministry mnt, JPanel mainPanel) {
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
        searchBar.setBounds(110, 55, 220, 25);
        lbl.setBounds(20, 55, 90, 25);
        findMntBtn.setBounds(340, 55, 120, 25);
        addMntBtn.setBounds(590, 55, 290, 25);
        //dataTbl.setBounds(20, 100, 860,320);
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
                        Component[] components = getComponents();
                        for (Component component : components) {
                            if (component.getClass().equals(JScrollPane.class)) {
                                remove(component);
                            }
                        }
                        setDataTble(foundMnts);
                        JScrollPane newPane = new JScrollPane(dataTbl);
                        newPane.setBounds(20, 100, 860,320);
                        add(newPane);
                        revalidate();
                        repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(findMntBtn, "No result matched");
                    }
                }
            }
        });
    }

    public void setDataTble(List<Ministry> ministries) {
        String column[]={"ID","FIRSTNAME","LASTNAME", "DAY OF BIRTH", "EMAIL", "ADDRESS", "PHONE", "EDIT", "RESET PASSWORD",
            "DELETE"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        for (Ministry item : ministries) {
            model.addRow(new Object[]{item.getMinistryid(), item.getFirstname(), item.getLastname(), item.getDayofbirth(),
                    item.getEmail(), item.getAddress(), item.getPhone(), "Edit", "Delete", "Reset password"});
        }
        dataTbl = new JTable(model);
        //dataTbl.getColumnModel().getColumn(0).setWidth(10);
        ButtonRenderer editBtn = new ButtonRenderer();
        ButtonRenderer deleteBtn = new ButtonRenderer();
        ButtonRenderer resetPwdBtn = new ButtonRenderer();
        ButtonEditor editCell = new ButtonEditor(new JTextField());
        ButtonEditor deleteCell = new ButtonEditor(new JTextField());
        ButtonEditor resetCell = new ButtonEditor(new JTextField());

        dataTbl.getColumnModel().getColumn(7).setCellRenderer(editBtn);
        dataTbl.getColumnModel().getColumn(7).setCellEditor(editCell);
        dataTbl.getColumnModel().getColumn(8).setCellRenderer(deleteBtn);
        dataTbl.getColumnModel().getColumn(8).setCellEditor(deleteCell);
        dataTbl.getColumnModel().getColumn(9).setCellRenderer(resetPwdBtn);
        dataTbl.getColumnModel().getColumn(9).setCellEditor(resetCell);

        //event in table
        editCell.getBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(dataTbl.getSelectedRow());
            }
        });
    }
}
