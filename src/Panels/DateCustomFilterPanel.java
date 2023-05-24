package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCustomFilterPanel extends JPanel implements ActionListener {

    private JTextField startdatetxt, enddatetxt;
    private JButton daybtn, weekbtn, monthbtn, yearbtn;

    public DateCustomFilterPanel(JTextField startdatetxt, JTextField enddatetxt) {
        super();
        this.startdatetxt = startdatetxt;
        this.enddatetxt = enddatetxt;
        this.setLayout(new BorderLayout());
        JPanel row1 = new JPanel();
        JLabel startdatelbl = new JLabel("Start Date");
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        startdatetxt.setText(format.format(today));
        row1.add(startdatelbl);
        row1.add(startdatetxt);
        add(row1,BorderLayout.NORTH);

        JPanel row2 = new JPanel();
        JLabel enddatelbl = new JLabel("End Date");
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
            cal.setTime(formatter.parse(startdatetxt.getText()));
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
