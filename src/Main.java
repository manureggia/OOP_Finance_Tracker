import DataStructure.Balance;
import DataStructure.TableModel.BalanceTableModel;
import Panels.MainPanel;
import Panels.MenuBar;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Balance balance = new Balance();
        JFrame mainFrame = new JFrame("Balance");
        BalanceTableModel model = new BalanceTableModel(balance);
        JTable table = new JTable(model);
        MainPanel mainpanel = new MainPanel(balance,table);
        MenuBar menubar = new MenuBar(balance,table);
        mainFrame.setJMenuBar(menubar);
        mainFrame.add(mainpanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}