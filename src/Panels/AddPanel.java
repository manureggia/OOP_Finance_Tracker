package Panels;
import DataStructure.TableModel.BalanceTableModel;
import DataStructure.Transaction;

import DataStructure.Balance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPanel extends JPanel implements ActionListener {

    private JButton add;
    private JTextField datetxt, desctxt, ammtxt;
    private SimpleDateFormat formatter;
    private JTable table;
    private JFrame thisframe;

    public AddPanel(JTable table, JFrame thisframe){
        super();
        this.table = table;
        this.thisframe = thisframe;
        this.setLayout(new BorderLayout());
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        JPanel row1 = new JPanel();
        JLabel datelbl = new JLabel("Date (dd/mm/yyyy)");
        datetxt = new JTextField(25);
        datetxt.setText(formatter.format(today));
        row1.add(datelbl); row1.add(datetxt);
        add(row1,BorderLayout.NORTH);

        JPanel row2 = new JPanel();
        JLabel desclbl = new JLabel("Description");
        desctxt = new JTextField(25);
        row2.add(desclbl); row2.add(desctxt);
        add(row2,BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        JPanel row3 = new JPanel();
        JLabel ammlbl = new JLabel("Ammount");
        ammtxt = new JTextField(25);
        row3.add(ammlbl); row3.add(ammtxt);

        bottom.add(row3, BorderLayout.NORTH);

        JPanel row4 = new JPanel();
        add = new JButton("ADD");
        add.addActionListener(this);
        row4.add(add);
        bottom.add(row4,BorderLayout.SOUTH);
        add(bottom,BorderLayout.SOUTH);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        BalanceTableModel model = (BalanceTableModel) table.getModel();
        boolean isDate;
        //check if datetxt is a valid date
        try{
            formatter.setLenient(false);
            formatter.parse(datetxt.getText());
            isDate = true;
        }catch (ParseException ex){
            datetxt.setText("Date is not valid!");
            isDate = false;
        }
        if(isDate){
            try{
                String formattedtxt = ammtxt.getText().replace(',','.');
                model.insertRow(new Transaction(formatter.parse(datetxt.getText()),desctxt.getText(),Double.parseDouble(formattedtxt)));
            }catch (NumberFormatException ex){
                System.err.println(ex);
            } catch (ParseException ex) {
                datetxt.setText("Date is not valid!");
            }
            thisframe.setVisible(false);
        }


    }
}
