package Panels;

import DataStructure.TableModel.BalanceTableModel;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Il pannello di ricerca all'interno della tabella.
 */
public class SearchPanel extends JPanel implements KeyListener {
    private final JTable table;
    private final JTextField searchBar;
    private final ArrayList<RowFilter<Object,Object>> filters;

    /**
     * Inizializzazione del pannello di ricerca. <br>
     * Filtra la tabella in base al contenuto della casella di testo mostrata. La ricerca viene fatta su ogni colonna
     * della tabella per ogni riga.
     *
     * @param table   La tabella
     * @param filters L'array contenente i filtri
     */
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

    /**
     * Restituisce la Barra di ricerca.
     *
     * @return JTextField
     */
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
