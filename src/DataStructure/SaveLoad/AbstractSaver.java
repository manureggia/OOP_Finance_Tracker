package DataStructure.SaveLoad;

import DataStructure.Balance;

import java.io.File;
import java.io.IOException;

/**
 * Il tipo AbstractSaver, classe che implementa il salvataggio del Bilancio in modo astratto.
 */
public abstract class AbstractSaver {

    /**
     * Salvataggio dei dati.
     *
     * @param b Il bilancio
     * @param f Il file
     * @throws IOException io exception
     */
    public abstract void saveData(Balance b, File f) throws IOException;

    /**
     * Caricamento del bilancio da file.
     *
     * @param f il file da cui caricarare
     * @return il bilancio da sovrascrivere
     * @throws IOException io exception
     */
    public abstract Balance loadData(File f) throws IOException;

}
