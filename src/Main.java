import DataStructure.Balance;
import DataStructure.FilterTable;
import DataStructure.TableModel.BalanceTableModel;
import Panels.MainPanel;
import Panels.MenuBar;
import Panels.SearchPanel;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.util.SystemInfo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * La classe main, dove il programma è inizializzato.
 * Per far partire il programma da linea di comando è necessario includere la classe Flatlaf nel seguente modo:
 * javac -classpath "./Lib/flatlaf-3.1.1.jar:" Main.java
 * per farlo partire invece
 * java -classpath "./Lib/flatlaf-3.1.1.jar:" Main
 */
public class Main {


    private static void __GUI_init__() throws UnsupportedLookAndFeelException {
        if(SystemInfo.isMacOS){
            System.setProperty( "apple.laf.useScreenMenuBar", "true" );
            System.setProperty( "apple.awt.application.name", "Finance Tracker" );
            System.setProperty( "apple.awt.application.appearance", "system" );
        }
        else
            FlatLightLaf.setup();
    }


    /**
     * Questo è il main dove il frame principale insieme al suo pannello e alla barra superiore vengono creati
     *
     * @param args gli argomenti di input
     */
    public static void main(String[] args) {

        try {
            __GUI_init__();
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        //variable init
        Balance balance = new Balance();
        ArrayList<RowFilter<Object,Object>> otherFilters = new ArrayList<>();
        otherFilters.add(RowFilter.regexFilter(""));
        otherFilters.add(RowFilter.regexFilter(""));
        JFrame mainFrame = new JFrame("Finance Tracker");
        BalanceTableModel model = new BalanceTableModel(balance);
        JTable table = new JTable(model);
        JTextField totaltxt = new JTextField();
        SearchPanel searchPanel = new SearchPanel(table,otherFilters);
        MainPanel mainpanel = new MainPanel(balance,table,totaltxt);
        mainpanel.add(searchPanel, BorderLayout.NORTH);
        searchPanel.setVisible(false);
        FilterTable filter = new FilterTable(table,totaltxt,otherFilters);
        MenuBar menubar = new MenuBar(table,searchPanel,filter);

        //GUI FRAME
        mainFrame.setJMenuBar(menubar);
        mainFrame.add(mainpanel);
        mainFrame.pack();
        mainFrame.setMinimumSize(mainFrame.getSize());
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}