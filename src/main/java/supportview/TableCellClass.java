package supportview;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class TableCellClass {

    private JFrame frame = new JFrame("Table Demo");
    private String[] columnNames = {"String", "Integer", "Float", "Double", "Locale & Double", "Boolean"};
    private Object[][] data = {
        {"aaa", new Integer(12), new Float(12.15), new Double(100.05), new Double(12.05), true},
        {"bbb", new Integer(5), new Float(7.154), new Double(6.1555), new Double(417.55), false},
        {"CCC", new Integer(92), new Float(0.1135), new Double(3.1455), new Double(11.05), true},
        {"ddd", new Integer(12), new Float(31.15), new Double(10.05), new Double(23.05), true},
        {"eee", new Integer(5), new Float(5.154), new Double(16.1555), new Double(17.55), false},
        {"fff", new Integer(92), new Float(4.1135), new Double(31.1455), new Double(3.05), true}};
    private TableModel model = new DefaultTableModel(data, columnNames) {

        private static final long serialVersionUID = 1L;

        @Override
        public Class<?> getColumnClass(int column) {
            return getValueAt(0, column).getClass();
        }
    };
    private JTable table = new JTable(model);

    public TableCellClass() {
        table.setRowHeight(20);
        //table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setBackground(Color.red);
        JScrollPane scroll = new JScrollPane(table);
        scroll.getViewport().setBackground(Color.blue);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scroll);
        frame.pack();
        frame.setLocation(150, 150);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                TableCellClass tableCellClass = new TableCellClass();
            }
        });
    }
}