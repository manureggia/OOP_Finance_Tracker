package DataStructure.SaveLoad;

import DataStructure.Balance;

import java.io.*;

public class BynarySave extends AbstractSaver {
    public BynarySave() {
    }


    public void saveData(Balance balance, File file) throws IOException {
        FileOutputStream stream = new FileOutputStream(file);
        try (stream) {
            ObjectOutputStream out = new ObjectOutputStream(stream);
            if (balance != null) {
                synchronized (balance) {
                    out.writeObject(balance);
                }
            }
        }
    }

    public Balance loadData(File file) throws IOException {
        FileInputStream stream = new FileInputStream(file);
        try (stream) {
            ObjectInputStream in = new ObjectInputStream(stream);
            return (Balance) in.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not Found. Mabye wrong file?");
        }
        return null;
    }
}
