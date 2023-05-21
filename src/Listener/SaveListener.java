package Listener;

import DataStructure.TableModel.BalanceTableModel;
import Panels.OverwritePanel;

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

    private boolean checkFileExistance(File file){
        boolean returnvalue = false;
        String path = file.getAbsolutePath().replace(file.getName(),"");
        File listfile = new File(path);
        for(String filename: listfile.list()){
            if (filename.equals(file.getName())){
                JFrame frame = new JFrame("Salvataggio");
                OverwritePanel panel = new OverwritePanel(filename,returnvalue,frame);
                frame.add(panel);
                frame.pack();
                frame.setVisible(true);
                return returnvalue;
            }
        }
        return returnvalue;
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
                boolean exists = checkFileExistance(file);
                //save file
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Binary file (*.bin)",".bin");
                fileChooser.setFileFilter(filter);
                if(exists) {
                    try {
                        model.saveFile(file);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
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
