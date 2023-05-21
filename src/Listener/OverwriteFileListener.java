package Listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OverwriteFileListener implements ActionListener {

    private boolean returnValue;
    private JFrame frame;
    public OverwriteFileListener(boolean returnValue, JFrame thisframe) {
        this.returnValue = returnValue;
        this.frame = thisframe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Override"))
            returnValue = true;
        else {
            returnValue = false;
            frame.setVisible(false);
        }
    }
}
