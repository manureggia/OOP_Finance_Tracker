package DataStructure;

import java.io.*;

public class SaverLoader {
    public SaverLoader() {
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
            Balance b = (Balance) in.readObject();
            return b;
        } catch(ClassNotFoundException e) {
            System.err.println("Class not Found. Mabye wrong file?");
        } finally {
            stream.close();
        }

        return null;
    }
}
