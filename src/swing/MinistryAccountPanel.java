package swing;

import dao.MinistryDAO;
import pojo.Ministry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

        //table data
        List<Ministry> ministries = MinistryDAO.getAllMinistries();
        this.setDataTble(ministries);
        JScrollPane pane  = new JScrollPane(dataTbl);
        add(pane);

        backBtn.setBounds(20,10, 160,35);
        searchBar.setBounds(20, 55, 220, 25);
        findMntBtn.setBounds(260, 55, 100, 25);
        addMntBtn.setBounds(590, 55, 290, 25);
        dataTbl.setBounds(20, 100, 860,320);
        this.setLayout(null);

        add(backBtn); add(findMntBtn); add(addMntBtn); add(dataTbl); add(searchBar);

    }

    public void setDataTble(List<Ministry> ministries) {
        String column[]={"ID","FIRSTNAME","LASTNAME", "DAY OF BIRTH", "EMAIL", "ADDRESS", "PHONE", "EDIT", "RESET PASSWORD",
            "DELETE"};
        DefaultTableModel model = new DefaultTableModel(column, 0);
        for (Ministry item : ministries) {
            model.addRow(new Object[]{String.valueOf(item.getId()), item.getFirstname(), item.getLastname(), item.getDayofbirth(),
                    item.getEmail(), item.getAddress(), item.getPhone(), "Edit", "Delete", "Reset Passwprd"});
        }
        dataTbl = new JTable(model);
        ButtonRenderer editBtn = new ButtonRenderer();
        ButtonRenderer deleteBtn = new ButtonRenderer();
        ButtonRenderer resetPwdBtn = new ButtonRenderer();

        dataTbl.getColumnModel().getColumn(7).setCellRenderer(editBtn);
        dataTbl.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JTextField()));
        dataTbl.getColumnModel().getColumn(8).setCellRenderer(deleteBtn);
        dataTbl.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(new JTextField()));
        dataTbl.getColumnModel().getColumn(9).setCellRenderer(resetPwdBtn);
        dataTbl.getColumnModel().getColumn(9).setCellEditor(new ButtonEditor(new JTextField()));
    }
}
