package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * La classe DateCustomFilterPanel implementa un pannello che permette il filtraggio delle date in maniera semplice.
 */
public class DateCustomFilterPanel extends JPanel implements ActionListener {

    private final JTextField enddatetxt;

    /**
     * Inizializzazione del pannello di filtraggio delle date.
     * In questo pannello l'utente sceglie un range di date che starà fra startdatetxt e enddatetxt e poi verrà filtrato
     * dall'action listener presente all'interno del {@link MenuBar}
     *
     * @param startdatetxt la data che è l'estremo superiore
     * @param enddatetxt   la data di estremo inferiore
     */
    public DateCustomFilterPanel(JTextField startdatetxt, JTextField enddatetxt) {
        super();
        this.enddatetxt = enddatetxt;
        this.setLayout(new BorderLayout());
        JButton daybtn, weekbtn, monthbtn, yearbtn;
        JPanel row1 = new JPanel();
        JLabel startdatelbl = new JLabel("End Date");
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        startdatetxt.setText(format.format(today));
        row1.add(startdatelbl);
        row1.add(startdatetxt);
        add(row1,BorderLayout.NORTH);

        JPanel row2 = new JPanel();
        JLabel enddatelbl = new JLabel("Start Date");
        enddatetxt.setText(format.format(today));
        row2.add(enddatelbl);
        row2.add(enddatetxt);
        add(row2,BorderLayout.CENTER);

        JPanel row3 = new JPanel();
        daybtn = new JButton("1 Day");
        daybtn.addActionListener(this);
        weekbtn = new JButton("1 Week");
        weekbtn.addActionListener(this);
        monthbtn = new JButton("1 Month");
        monthbtn.addActionListener(this);
        yearbtn = new JButton("1 Year");
        yearbtn.addActionListener(this);
        row3.add(daybtn);row3.add(weekbtn);row3.add(monthbtn);row3.add(yearbtn);

        add(row3,BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(formatter.parse(enddatetxt.getText()));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        if (e.getActionCommand().equals("1 Day")){
            cal.add(Calendar.DATE,-1);
        } else if (e.getActionCommand().equals("1 Week")) {
            cal.add(Calendar.DATE,-7);
        } else if (e.getActionCommand().equals("1 Month")) {
            cal.add(Calendar.MONTH,-1);
        } else if (e.getActionCommand().equals("1 Year")) {
            cal.add(Calendar.YEAR,-1);
        }
        enddatetxt.setText(formatter.format(cal.getTime()));
    }
}
