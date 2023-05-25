package Listener;

import DataStructure.AbstractSaver;
import DataStructure.BynarySave;
import DataStructure.CsvSaveLoader;
import DataStructure.TableModel.BalanceTableModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SaveListener implements ActionListener {

    private JTable table;

    public SaveListener(JTable table) {
        this.table = table;
    }

    private int checkFileExistance(File file){
        String path = file.getAbsolutePath().replace(file.getName(),"");
        File listfile = new File(path);
        for(String filename: Objects.requireNonNull(listfile.list())){
            if (filename.equals(file.getName())){
                return JOptionPane.showConfirmDialog(table, "the file "+filename+" already exists, do you want to overwrite it?","Overwrite",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
            }
        }
        return -2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BalanceTableModel model = (BalanceTableModel) table.getModel();
        AbstractSaver saver;
        if(e.getActionCommand().equals("Save")){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save File");
            int userSelection = fileChooser.showSaveDialog(null);
            if(userSelection == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                int exitStatus = checkFileExistance(file);
                //save file
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Binary file (*.bin)",".bin");
                fileChooser.setFileFilter(filter);
                if(exitStatus == -2 || exitStatus == JOptionPane.YES_OPTION) {
                    try {
                        saver = new BynarySave();
                        model.saveFile(saver, file);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (exitStatus == JOptionPane.NO_OPTION) {
                    actionPerformed(e);
                }

            }
        }
        else if(e.getActionCommand().equals("Open")){
            JFileChooser fileChooser;
            try {
                fileChooser = new JFileChooser(new File(".").getCanonicalPath());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            fileChooser.setDialogTitle("Carica File");
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    model.loadData(new BynarySave(), file);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
        else if(e.getActionCommand().equals("CSV")){
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File (*.csv)",".csv");
            fileChooser.setFileFilter(filter);
            fileChooser.setDialogTitle("Export CSV");
            int userSelection = fileChooser.showSaveDialog(null);
            if(userSelection == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                int exitStatus = checkFileExistance(file);
                //save file

                if(exitStatus == -2 || exitStatus == JOptionPane.YES_OPTION) {
                    try {
                        model.saveFile(new CsvSaveLoader(), file);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (exitStatus == JOptionPane.NO_OPTION) {
                    actionPerformed(e);
                }

            }
        }

    }
}
