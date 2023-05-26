package Panels;

import DataStructure.FilterTable;
import DataStructure.TableModel.BalanceTableModel;
import Listener.SaveListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * The type Menu bar.
 */
public class MenuBar extends JMenuBar implements ActionListener {

    /**
     * The Table.
     */
    private final JTable table;
    FilterTable filter;

    SearchPanel searchPanel;



    public MenuBar(JTable table, SearchPanel searchPanel,FilterTable filter) {
        super();
        this.table = table;
        this.filter = filter;
        this.searchPanel = searchPanel;
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



        SaveListener saveListener = new SaveListener(table);
        save.addActionListener(saveListener);
        //  csvbutton.addActionListener(saveListener);
        open.addActionListener(saveListener);
        file.add(open);
        file.add(save);
        // file.add(export);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("None")){
            table.setRowSorter(null);
            searchPanel.setVisible(false);
            searchPanel.getSearchBar().setText("");
            ArrayList<RowFilter<Object, Object>> filters = searchPanel.getFilters();
            filters.set(1,RowFilter.regexFilter(""));
            filters.set(2,RowFilter.regexFilter(""));

        }
        if (e.getActionCommand().equals("By Date")){
            JTextField start = new JTextField(25), end =  new JTextField(25);
            int response = JOptionPane.showConfirmDialog(table,new DateCustomFilterPanel(start,end),"Custom Date Chooser",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(response == JOptionPane.OK_OPTION){
                try {
                    filter.applyFilter(start.getText(),end.getText());
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        if(e.getActionCommand().equals("Search")){
            searchPanel.setVisible(true);
        }

        BalanceTableModel model = (BalanceTableModel) table.getModel();
        model.fireTableDataChanged();
    }
}
