package swing;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinistrySubjectUsecaseGUI extends JFrame {
    private JPanel mainPanel;
    private JTable dataTable;

    public MinistrySubjectUsecaseGUI(String title){
        mainPanel = new JPanel();
        //table
        String data[][]={ {"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"}};
        String column[]={"ID","NAME","SALARY"};
        dataTable = new JTable(data,column);
        dataTable.setBounds(32,20,920,364);
        dataTable.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());
        dataTable.getColumnModel().getColumn(1).setCellEditor(new ButtonEditor(new JTextField()));
        JScrollPane pane  = new JScrollPane(dataTable);
        getContentPane().add(pane);
        mainPanel.add(dataTable);

        //frame
        this.setSize(986, 510);
        this.setPreferredSize(new Dimension(this.getSize().width,this.getSize().height));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(mainPanel);
        mainPanel.setLayout(null);

    }
}
//BUTTON RENDERER CLASS
class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        //set
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "":value.toString());
        return this;
    }
}
//BUTTON EDITOR CLASS
class ButtonEditor extends DefaultCellEditor {
    protected JButton btn;
    private String lbl;
    private Boolean isClicked;
    public ButtonEditor(JTextField textField) {
        super(textField);
        btn = new JButton();
        btn.setOpaque(true);

        //btn is clicked
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }
    //Override methods
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        lbl = (value==null)? "":value.toString();
        btn.setText(lbl);
        isClicked = true;
        return btn;
    }
    //if btn cell value changes, if clicked that is
    @Override
    public Object getCellEditorValue() {
        if (isClicked) {
            JOptionPane.showMessageDialog(btn, lbl+"clicked");
        }
        isClicked = false;
        return new String(lbl);
    }

    @Override
    public boolean stopCellEditing() {
        isClicked = false;

        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}