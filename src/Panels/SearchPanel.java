package Panels;

import DataStructure.TableModel.BalanceTableModel;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class SearchPanel extends JPanel implements KeyListener {
    private final JTable table;
    private final JTextField searchBar;
    private ArrayList<RowFilter<Object,Object>> filters;

    public ArrayList<RowFilter<Object, Object>> getFilters() {
        return filters;
    }

    public SearchPanel(JTable table, ArrayList<RowFilter<Object,Object>> filters) {
        super();
        this.table = table;
        this.filters = filters;
        JLabel searchlbl = new JLabel("Search: ");
        searchBar = new JTextField(30);
        searchBar.addKeyListener(this);
        add(searchlbl);
        add(searchBar);
    }

    public JTextField getSearchBar() {
        return searchBar;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        BalanceTableModel model = (BalanceTableModel) table.getModel();
        TableRowSorter<BalanceTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        filters.set(1, RowFilter.regexFilter(searchBar.getText()));
        sorter.setRowFilter(RowFilter.andFilter(filters));

    }
}
