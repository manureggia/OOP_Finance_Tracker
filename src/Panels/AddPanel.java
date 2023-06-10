package Panels;

import DataStructure.TableModel.BalanceTableModel;
import DataStructure.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * L'Add panel, qui un semplice pannello è creato per l'aggiunta di una transazione al bilancio
 */
public class AddPanel extends JPanel implements ActionListener {

    private final JTextField datetxt, desctxt, ammtxt;
    private final SimpleDateFormat formatter;
    private final JTable table;
    private final JDialog thisframe;

    /**
     * Creazione di un nuovo pannello di tipo ADD.
     * In questo pannello è anche gestita la pressione del tasto ADD. dopo aver controllato la correttezza dei dati
     * inseriti (e nel caso comunicati all'utente) aggiunge la transazione al bilancio.
     *
     * @param table     La tabella in cui verrà aggiunta la transazione
     * @param thisframe il Frame in cui il pannello è visibile
     */
    public AddPanel(JTable table, JDialog thisframe){
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
        JButton addbtn = new JButton("ADD");
        addbtn.addActionListener(this);
        row4.add(addbtn);
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
                throw new NumberFormatException();
            } catch (ParseException ex) {
                datetxt.setText("Date is not valid!");
            }
            thisframe.setVisible(false);
        }


    }
}
