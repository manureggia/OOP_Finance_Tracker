package Listener;

import App.AutosaveThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * La classe Autosave Listener che quando il bottone è cliccato permette di far ripartire o fermare la task
 * dell'autosave.
 */
public class AutosaveListener implements ActionListener {

    private final JTable table;
    private boolean isCreated;
    private AutosaveThread mythread;

    /**
     * Costruttore dell'autosave.
     *
     * @param table la tabella
     */
    public AutosaveListener(JTable table) {
        this.table = table;
        isCreated = false;
    }

    private void startThread(){
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(table);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            mythread  = new AutosaveThread(table, file);
            mythread.start();
            isCreated = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBoxMenuItem check = (JCheckBoxMenuItem) e.getSource();
        if(!isCreated){
            startThread();
        }
        else {
            if(check.getState()){
                mythread.resume();
            }
            else{
                mythread.pause();
            }
        }
    }
}
