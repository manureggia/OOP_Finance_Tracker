package Listener;

import DataStructure.TableModel.BalanceTableModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SaveListener implements ActionListener {

    private JTable table;

    public SaveListener(JTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BalanceTableModel model = (BalanceTableModel) table.getModel();
        if(e.getActionCommand().equals("Save")){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save File");
            int userSelection = fileChooser.showSaveDialog(null);
            if(userSelection == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                //check if file aready exists
                //save file
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Binary file (*.bin)",".bin");
                fileChooser.setFileFilter(filter);
                try {
                    model.saveFile(file);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
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
                    model.loadData(file);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }

    }
}
