package DataStructure.TableModel;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * La classe che implementa il filtraggio delle date estesa da {@link RowFilter }.
 */
public class DataFilter extends RowFilter<Object, Object> {

    /**
     * le date di inizio e fine del filtro
     */
    private final Date startDate, endDate;
    /**
     * il formattatore delle date
     */
    private final SimpleDateFormat dateFormat;

    /**
     * Costruttore di un nuovo filtro di tipo data passando delle stringhe.
     * le date che risultano corrette saranno quello comprese tra la endDate e la startDate.
     * viene inizializzato un formattatore di date del tipo dd/MM/yyyy
     * @param startDate la data da cui iniziare il filtro
     * @param endDate   la data di fine filtro
     * @throws ParseException parse exception
     */
    public DataFilter(String startDate, String endDate) throws ParseException {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.startDate = dateFormat.parse(startDate);
        this.endDate = dateFormat.parse(endDate);
    }

    /**
     * Costruttore di un nuovo filtro passando dei tipi {@link Date}.
     * le date che risultano corrette saranno quello comprese tra la endDate e la startDate
     * viene inizializzato un formattatore di date del tipo dd/MM/yyyy
     * @param startDate la data di inizio
     * @param endDate   la data di fine
     */
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


