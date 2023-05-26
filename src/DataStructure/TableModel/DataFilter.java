package DataStructure.TableModel;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFilter extends RowFilter<Object, Object> {

    private final Date startDate, endDate;
    private final SimpleDateFormat dateFormat;

    public DataFilter(String startDate, String endDate) throws ParseException {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.startDate = dateFormat.parse(startDate);
        this.endDate = dateFormat.parse(endDate);
    }
    public DataFilter(Date startDate, Date endDate){
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.startDate = startDate;
        this.endDate = endDate;
    }


    @Override
    public boolean include(Entry<?, ?> entry) {
        Date rowDate;
        try {
            rowDate = dateFormat.parse((String) entry.getValue(0));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return rowDate.compareTo(endDate) >= 0 && rowDate.compareTo(startDate) <= 0;
    }

}


