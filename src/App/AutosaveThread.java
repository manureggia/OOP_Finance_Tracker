package App;

import DataStructure.SaveLoad.AbstractSaver;
import DataStructure.SaveLoad.BinarySave;
import DataStructure.TableModel.BalanceTableModel;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * La classe autosaveThread, che implementa un thread di salvataggio schedulato per un tempo di minuti prestabiliti.
 */
public class AutosaveThread implements Runnable {
    private final File autsavefile;
    private final BalanceTableModel tableModel;
    private final AbstractSaver saver;
    private ScheduledExecutorService executor;
    private boolean isPaused;


    /**
     * Costruttore del AutosaveThread.
     *
     * @param table la tabella
     * @param file  il file su cui fare autosalvataggio
     */
    public AutosaveThread(JTable table, File file) {
        this.autsavefile = file;
        this.tableModel = (BalanceTableModel) table.getModel();
        this.saver = new BinarySave();
        executor = Executors.newSingleThreadScheduledExecutor();
        isPaused = true;
    }

    /**
     * Fa partire il thread ogni 5 minuti.
     */
    public void start() {
        executor.scheduleWithFixedDelay(this, 0, 5, TimeUnit.MINUTES);
        isPaused = false;
    }

    /**
     * sospende il Thread
     */
    public void pause() {
        if (!isPaused) {
            executor.shutdown();
            isPaused = true;
        }
    }

    /**
     * ricrea il thread per farlo ripartire solo se era in pausa
     */
    public void resume() {
        if (isPaused) {
            executor = Executors.newSingleThreadScheduledExecutor();
            executor.scheduleWithFixedDelay(this, 0, 5, TimeUnit.MINUTES);
            isPaused = false;
        }
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
