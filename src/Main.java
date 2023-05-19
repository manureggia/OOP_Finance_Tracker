import DataStructure.Balance;
import DataStructure.TableModel.BalanceTableModel;
import Panels.MainPanel;
import Panels.MenuBar;

import javax.swing.*;

/**
 * The type Main.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        Balance balance = new Balance();
        JFrame mainFrame = new JFrame("Balance");
        BalanceTableModel model = new BalanceTableModel(balance);
        JTable table = new JTable(model);
        JTextField totaltxt = new JTextField();
        MainPanel mainpanel = new MainPanel(balance,table,totaltxt);
        MenuBar menubar = new MenuBar(balance,table,totaltxt);
        mainFrame.setJMenuBar(menubar);
        mainFrame.add(mainpanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}