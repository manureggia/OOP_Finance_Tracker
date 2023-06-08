package App;

import DataStructure.SaveLoad.AbstractSaver;
import DataStructure.SaveLoad.BinarySave;
import DataStructure.TableModel.BalanceTableModel;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

public class AutosaveTask extends TimerTask {
    private final File autsavefile;
    private final BalanceTableModel tableModel;
    private final AbstractSaver saver;

    public AutosaveTask(JTable table) {
        this.autsavefile = new File("autosave.bin");
        this.tableModel = (BalanceTableModel) table.getModel();
        this.saver = new BinarySave();
    }

    @Override
    public void run() {
        try {
            tableModel.saveFile(saver,autsavefile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
