package Listener;

import DataStructure.SaveLoad.AbstractSaver;
import DataStructure.SaveLoad.BinarySave;
import DataStructure.SaveLoad.CsvSaveLoader;
import DataStructure.SaveLoad.TabulatedSave;
import DataStructure.TableModel.BalanceTableModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.Objects;

/**
 * La classe SaveListener, è un Listener che implementa il salvataggio e il caricamento da file.
 * È implementata direttamente l'esportazione e l'importazione di file .csv e .txt in nel formato richiesto
 */
public class SaveListener implements ActionListener {

    private final JTable table;
    private String lastPath;

    /**
     * Instanzia un nuovo oggetto SaveListener passando la tablella in modo da riuscire a prendere
     * il TableModel nel quale sono prensenti i dati.
     *
     * @param table la tabella
     */
    public SaveListener(JTable table) {
        this.table = table;
    }

    private int checkFileExistance(File file){
        String path = file.getAbsolutePath().replace(file.getName(),"");
        File listfile = new File(path);
        for(String filename: Objects.requireNonNull(listfile.list())){
            if (filename.equals(file.getName())){
                return JOptionPane.showConfirmDialog(table, filename+" already exists, do you want to overwrite it?","Overwrite",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
            }
        }
        return -2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BalanceTableModel model = (BalanceTableModel) table.getModel();
        AbstractSaver saver;
        JFileChooser fileChooser = new JFileChooser(lastPath);
        FileNameExtensionFilter binary = new FileNameExtensionFilter("Binary File (*.bin)", "bin");
        FileNameExtensionFilter csv = new FileNameExtensionFilter("CSV File (*.csv)", "csv");
        FileNameExtensionFilter text = new FileNameExtensionFilter("Text File (*.txt)", "txt");
        fileChooser.addChoosableFileFilter(binary);
        fileChooser.addChoosableFileFilter(csv);
        fileChooser.addChoosableFileFilter(text);
        fileChooser.setDialogTitle(e.getActionCommand()+" File");
        // set Default option for export or simply save
        switch (e.getActionCommand()){
            case "open" -> fileChooser.setFileFilter(binary);
            case "CSV" -> fileChooser.setFileFilter(csv);
            case "Text" -> fileChooser.setFileFilter(text);
        }
        int userSelection = JOptionPane.CANCEL_OPTION;
        if(e.getActionCommand().equals("Save"))
            userSelection = fileChooser.showSaveDialog(table);
        else if(e.getActionCommand().equals("Open"))
            userSelection = fileChooser.showOpenDialog(table);

        // Save and Export
        if (userSelection == JFileChooser.APPROVE_OPTION && e.getActionCommand().equals("Save")) {
            File file = fileChooser.getSelectedFile();
            String extension = "";
            String filterDescr = fileChooser.getFileFilter().getDescription();
            String filePath = file.getAbsolutePath();

            if(filterDescr.equals("Binary File (*.bin)") || filePath.endsWith("bin")){
                saver = new BinarySave();
                extension = ".bin";
            }
            else if (filterDescr.equals("CSV File (*.csv)") || filePath.endsWith("csv")){
                saver = new CsvSaveLoader();
                extension = ".csv";
            }
            else if (filterDescr.equals("Text File (*.txt)") || filePath.endsWith("txt")){
                saver = new TabulatedSave();
                extension = ".txt";
            }
            else
                saver = new BinarySave();

            if(!filePath.endsWith(extension)) {
                file = new File(filePath + extension);
            }
            // Check if File exists
            int exitStatus = checkFileExistance(file);
            if (exitStatus == -2 || exitStatus == JOptionPane.YES_OPTION) {
                try {
                    lastPath = filePath.replace(file.getName(),"");
                    model.saveFile(saver, file);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (exitStatus == JOptionPane.NO_OPTION) {
                actionPerformed(e);
            }
        }
        // Open and import
        else if (userSelection == JFileChooser.APPROVE_OPTION && e.getActionCommand().equals("Open")) {
            File file = fileChooser.getSelectedFile();
            try {
                String filePath = file.getAbsolutePath();
                String filterDescr = fileChooser.getFileFilter().getDescription();
                if(filterDescr.equals("Binary File (*.bin)") || filePath.endsWith("bin"))
                    saver = new BinarySave();
                else if (filterDescr.equals("CSV File (*.csv)") || filePath.endsWith("csv"))
                    saver = new CsvSaveLoader();
                else if (filterDescr.equals("Text File (*.txt)") || filePath.endsWith("txt"))
                    saver = new TabulatedSave();
                else
                    saver = new BinarySave();
                model.loadData(saver, file);
                lastPath = filePath.replace(file.getName(),"");
            } catch (StreamCorruptedException ex){
                JOptionPane.showConfirmDialog(fileChooser,"Wrong file type!","Error!",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }

    }
}
