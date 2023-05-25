import DataStructure.Balance;
import DataStructure.FilterTable;
import DataStructure.TableModel.BalanceTableModel;
import Panels.MainPanel;
import Panels.MenuBar;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.ui.FlatFileChooserUI;
import com.formdev.flatlaf.util.SystemInfo;

import javax.swing.*;

/**
 * The type Main.
 */
public class Main {


    private static void __GUI_init__() throws UnsupportedLookAndFeelException {
        if(SystemInfo.isMacOS){
            System.setProperty( "apple.laf.useScreenMenuBar", "true" );
            System.setProperty( "apple.awt.application.name", "Finance Tracker" );
            System.setProperty( "apple.awt.application.appearance", "system" );
        }
       // FlatLightLaf.setup();
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */

    public static void main(String[] args) {

        try {
            __GUI_init__();
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        //variable init
        Balance balance = new Balance();
        JFrame mainFrame = new JFrame("Finance Tracker");
        BalanceTableModel model = new BalanceTableModel(balance);
        JTable table = new JTable(model);
        JTextField totaltxt = new JTextField();
        MainPanel mainpanel = new MainPanel(balance,table,totaltxt);
        FilterTable filter = new FilterTable(table,totaltxt);
        MenuBar menubar = new MenuBar(table,totaltxt,filter);

        //GUI FRAME
        mainFrame.setJMenuBar(menubar);
        mainFrame.add(mainpanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}