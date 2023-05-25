package DataStructure;

import java.io.*;

public class BynarySave extends AbstractSaver{
    public BynarySave() {
    }


    public void saveData(Balance balance, File file) throws IOException {
        FileOutputStream stream = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(stream);
        try {
            if(balance != null) {
                synchronized (balance) {
                    out.writeObject(balance);
                }
            }
        } finally {
            stream.close();
        }
    }

    public Balance loadData(File file) throws IOException {
        FileInputStream stream = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(stream);
        try {
            return (Balance) in.readObject();
        } catch(ClassNotFoundException e) {
            System.err.println("Class not Found. Mabye wrong file?");
        } finally {
            stream.close();
        }
        return null;
    }
}
