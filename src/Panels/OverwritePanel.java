package Panels;

import Listener.OverwriteFileListener;

import javax.swing.*;
import java.awt.*;

public class OverwritePanel extends JPanel {
    private JLabel filelbl;
    private JButton overwriteButton, cancelButton;

    public OverwritePanel(String filename, boolean returnValue, JFrame thisframe) {
        super();
        this.setLayout(new BorderLayout());
        filelbl = new JLabel(" the file "+filename+" already exists, do you want to overwrite it?");
        this.add(filelbl,BorderLayout.NORTH);
        overwriteButton = new JButton("Overwrite");
        cancelButton = new JButton("Cancel");
        OverwriteFileListener listener = new OverwriteFileListener(returnValue, thisframe);
        overwriteButton.addActionListener(listener);
        cancelButton.addActionListener(listener);
        this.add(cancelButton,BorderLayout.CENTER);
        this.add(overwriteButton,BorderLayout.SOUTH);

    }
}
