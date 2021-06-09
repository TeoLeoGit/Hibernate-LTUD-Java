package swing;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableCellRenderer;

//Tham khao Column button tai stackoverflow
public class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() {
        //set
        setOpaque(false);
        //transparent
        setContentAreaFilled(false);
        setBorderPainted(false);
        setForeground(Color.BLUE);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "":value.toString());
        return this;
    }
}
