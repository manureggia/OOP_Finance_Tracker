package DataStructure.SaveLoad;

import DataStructure.Balance;

import java.io.File;
import java.io.IOException;

public abstract class AbstractSaver {

    public abstract void saveData(Balance b, File f) throws IOException;

    public abstract Balance loadData(File f) throws IOException;

}
