package DataStructure.TableModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.RowFilter;

public class DataFilter extends RowFilter<Object, Object> {

    private Date date;
    private SimpleDateFormat dateFormat;

    public DataFilter(String date) throws ParseException {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.date = dateFormat.parse(date);
    }
    public DataFilter(Date date){
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.date = date;
    }

    @Override
    public boolean include(Entry<?, ?> entry) {
        Date rowDate = null;
        try {
            rowDate = dateFormat.parse((String) entry.getValue(0));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return rowDate.compareTo(date) >= 0;
    }

}


