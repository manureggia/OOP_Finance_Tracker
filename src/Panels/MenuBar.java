package Panels;
import DataStructure.*;
import DataStructure.TableModel.BalanceTableModel;
import DataStructure.TableModel.DataFilter;
import Listener.SaveListener;
import Listener.SorterListener;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
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


    public MenuBar(JTable table, JTextField totaltxt) {
        super();
        this.table = table;
        this.totaltxt = totaltxt;
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
        FilterTable filter = new FilterTable(table,totaltxt);
        BalanceTableModel model = (BalanceTableModel) table.getModel();
        if(e.getActionCommand().equals("None")){
            table.setRowSorter(null);
        }
        else if (e.getActionCommand().equals("Last Month")){
            filter.filterTable("month");
        }
        else if (e.getActionCommand().equals("Last Week")){
            filter.filterTable("week");
        }
        else if (e.getActionCommand().equals("Last Year")) {
            filter.filterTable("year");
        }
        model.fireTableDataChanged();

    }
}
