package Panels;
import DataStructure.*;
import DataStructure.TableModel.BalanceTableModel;
import Listener.SaveListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

/**
 * The type Menu bar.
 */
public class MenuBar extends JMenuBar implements ActionListener {

    /**
     * The Table.
     */
    private JTable table;
    private JTextField totaltxt;
    FilterTable filter;



    public MenuBar(JTable table, JTextField totaltxt,FilterTable filter) {
        super();
        this.table = table;
        this.totaltxt = totaltxt;
        this.filter = filter;
        JMenu sorter = new JMenu("Filter");
        JMenu file = new JMenu("File");

        //sorting menu
        JCheckBoxMenuItem none = new JCheckBoxMenuItem("None",true);
        JCheckBoxMenuItem week = new JCheckBoxMenuItem("Last Week");
        JCheckBoxMenuItem month = new JCheckBoxMenuItem("Last Month");
        JCheckBoxMenuItem year = new JCheckBoxMenuItem("Last Year");
        ButtonGroup group = new ButtonGroup();
        group.add(none);group.add(week);group.add(month);group.add(year);
        none.addActionListener(this);
        week.addActionListener(this);
        month.addActionListener(this);
        year.addActionListener(this);
        sorter.add(none);sorter.add(week); sorter.add(month); sorter.add(year);
        this.add(file);
        this.add(sorter);

        //file menu
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenu export = new JMenu("Export");
        JMenuItem csvbutton = new JMenuItem("CSV");
        JMenuItem txtbutton = new JMenuItem("Text");
        SaveListener saveListener = new SaveListener(table);
        save.addActionListener(saveListener);
        open.addActionListener(saveListener);
        export.add(csvbutton);
        export.add(txtbutton);
        file.add(open);
        file.add(save);
        file.add(export);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField start = new JTextField(25), end =  new JTextField(25);
        int response = JOptionPane.showConfirmDialog(table,new DateCustomFilterPanel(start,end),"Custom Date Chooser",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
        if(response == JOptionPane.OK_OPTION){
            try {
                filter.applyFilter(start.getText(),end.getText());
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        BalanceTableModel model = (BalanceTableModel) table.getModel();
        model.fireTableDataChanged();
    }
}
