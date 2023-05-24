package DataStructure;

import DataStructure.TableModel.BalanceTableModel;
import DataStructure.TableModel.DataFilter;
import Listener.SorterListener;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Filter table.
 */
public class FilterTable {
    /**
     * The Table.
     */
    private JTable table;
    private JTextField totaltxt;

    /**
     * Instantiates a new Filter table.
     *
     * @param table the table
     */
    public FilterTable(JTable table, JTextField totaltxt) {
        this.table = table;
        this.totaltxt = totaltxt;
    }

    /**
     * Filter table.
     *
     * @param period the period
     */
    public void applyFilter(Date d1, Date d2){
            BalanceTableModel model = (BalanceTableModel) table.getModel();
            // create a row filter to show only dates after the filter date
            DataFilter dataFilter = new DataFilter(d1,d2);
            TableRowSorter<BalanceTableModel> rowFilter = new TableRowSorter<>(model);
            // set the row filter on the table sorter
            rowFilter.setRowFilter(dataFilter);
            table.setRowSorter(rowFilter);
            rowFilter.addRowSorterListener(new SorterListener(table,totaltxt));
    }

    public void applyFilter(String d1, String d2) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        applyFilter(format.parse(d1),format.parse(d2));
    }
}
