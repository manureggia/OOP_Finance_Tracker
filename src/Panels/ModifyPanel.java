package Panels;

import DataStructure.TableModel.BalanceTableModel;
import DataStructure.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The type Modify panel.
 */
public class ModifyPanel extends JPanel implements ActionListener {
    private final JTextField datetxt, desctxt, ammtxt;
    private final SimpleDateFormat formatter;
    private final JTable table;
    private final Transaction transaction;
    private final JFrame thisframe;


    /**
     * Instantiates a new Modify panel.
     *
     * @param table       the table
     * @param transaction the transaction
     * @param thisframe   the thisframe
     */
    public ModifyPanel(JTable table, Transaction transaction, JFrame thisframe) {
        this.table = table;
        this.transaction = transaction;
        this.thisframe = thisframe;
        this.setLayout(new BorderLayout());
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        JPanel row1 = new JPanel();
        JLabel datelbl = new JLabel("Date (dd/mm/yyyy)");
        datetxt = new JTextField(25);
        datetxt.setText(String.valueOf(transaction.getStringDate()));
        row1.add(datelbl); row1.add(datetxt);
        add(row1,BorderLayout.NORTH);

        JPanel row2 = new JPanel();
        JLabel desclbl = new JLabel("Description");
        desctxt = new JTextField(25);
        desctxt.setText(transaction.getDescription());
        row2.add(desclbl); row2.add(desctxt);
        add(row2,BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        JPanel row3 = new JPanel();
        JLabel ammlbl = new JLabel("Ammount");
        ammtxt = new JTextField(25);
        ammtxt.setText(String.valueOf(transaction.getAmount()));
        row3.add(ammlbl); row3.add(ammtxt);

        bottom.add(row3, BorderLayout.NORTH);

        JPanel row4 = new JPanel();
        JButton add = new JButton("ADD");
        add.addActionListener(this);
        row4.add(add);
        bottom.add(row4,BorderLayout.SOUTH);
        add(bottom,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
        if(isDate) {
            try {
                String formattedtxt = ammtxt.getText().replace(',', '.');
                BalanceTableModel model = (BalanceTableModel) table.getModel();
                model.modifyRow(transaction, formatter.parse(datetxt.getText()),desctxt.getText() ,Double.parseDouble(formattedtxt));
            } catch (NumberFormatException ex) {
                System.err.println(ex);
            } catch (ParseException ex) {
                datetxt.setText("Date is not valid!");
            }
            thisframe.setVisible(false);
        }
    }
}
