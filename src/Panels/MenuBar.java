package Panels;

import DataStructure.TableModel.BalanceTableModel;
import DataStructure.TableModel.DataFilter;
import Listener.SaveListener;
import Listener.SorterListener;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * The type Menu bar.
 */
public class MenuBar extends JMenuBar implements ActionListener {

    /**
     * The Table.
     */
    private final JTable table;
    private final ArrayList<RowFilter<Object,Object>> otherFilters;
    private final JTextField totaltxt;

    private final SearchPanel searchPanel;



    public MenuBar(JTable table, SearchPanel searchPanel, ArrayList<RowFilter<Object,Object>> otherFilters, JTextField totaltxt) {
        super();
        this.table = table;
        this.otherFilters = otherFilters;
        this.searchPanel = searchPanel;
        this.totaltxt =  totaltxt;
        JMenu sorter = new JMenu("Filter");
        JMenu file = new JMenu("File");

        //sorting menu
        JCheckBoxMenuItem none = new JCheckBoxMenuItem("None",true);
        JCheckBoxMenuItem byDate = new JCheckBoxMenuItem("By Date");
        JCheckBoxMenuItem search = new JCheckBoxMenuItem("Search");
        ButtonGroup group = new ButtonGroup();
        group.add(none);group.add(byDate);group.add(search);
        none.addActionListener(this);
        byDate.addActionListener(this);
        search.addActionListener(this);
        sorter.add(none);sorter.add(byDate);sorter.add(search);
        this.add(file);
        this.add(sorter);

        //file menu
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem print = new JMenuItem("Print");


        SaveListener saveListener = new SaveListener(table);
        save.addActionListener(saveListener);
        open.addActionListener(saveListener);
        print.addActionListener(this);
        file.add(open);
        file.add(save);
        file.add(print);




    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("None")){
            table.setRowSorter(null);
            searchPanel.setVisible(false);
            searchPanel.getSearchBar().setText("");
            ArrayList<RowFilter<Object, Object>> filters = searchPanel.getFilters();
            filters.set(0,RowFilter.regexFilter(""));
            filters.set(1,RowFilter.regexFilter(""));

        }
        if (e.getActionCommand().equals("By Date")){
            JTextField start = new JTextField(25), end =  new JTextField(25);
            int response = JOptionPane.showConfirmDialog(table,new DateCustomFilterPanel(start,end),"Custom Date Chooser",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(response == JOptionPane.OK_OPTION){
                BalanceTableModel model = (BalanceTableModel) table.getModel();
                // create a row filter to show only dates after the filter date
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    DataFilter dataFilter = new DataFilter(format.parse(start.getText()),format.parse(end.getText()));
                    otherFilters.set(0,dataFilter);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                TableRowSorter<BalanceTableModel> rowFilter = new TableRowSorter<>(model);
                // set the row filter on the table sorter
                rowFilter.setRowFilter(RowFilter.andFilter(otherFilters));
                table.setRowSorter(rowFilter);
                rowFilter.addRowSorterListener(new SorterListener(table,totaltxt));

            }
        }
        if(e.getActionCommand().equals("Search")){
            searchPanel.setVisible(true);
        }

        if(e.getActionCommand().equals("Print")){
            try {
                table.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
