package DataStructure;

import DataStructure.TableModel.BalanceTableModel;
import DataStructure.TableModel.DataFilter;
import Listener.SorterListener;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
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
    public void applyFilter(String period){
        try {
            Calendar cal = Calendar.getInstance();
            Date filterDate = null;
            switch (period){
                case "week":
                    cal.add(Calendar.DATE, -7);
                    filterDate = cal.getTime();
                    break;
                case "month":
                    cal.add(Calendar.MONTH, -1);
                    filterDate = cal.getTime();
                    break;
                case "year":
                    cal.add(Calendar.YEAR, -1);
                    filterDate = cal.getTime();
                    break;
            }
            BalanceTableModel model = (BalanceTableModel) table.getModel();
            // create a row filter to show only dates after the filter date
            DataFilter dataFilter = new DataFilter(filterDate);
            TableRowSorter<BalanceTableModel> rowFilter = new TableRowSorter<>(model);
            // set the row filter on the table sorter
            rowFilter.setRowFilter(dataFilter);
            table.setRowSorter(rowFilter);
            rowFilter.addRowSorterListener(new SorterListener(table,totaltxt));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
