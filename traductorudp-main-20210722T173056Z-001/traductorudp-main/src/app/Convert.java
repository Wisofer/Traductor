package app;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Convert {

    //CONVERTIR DE BYTES A UN OBJETO
    public static dataTR objeto(byte[] bytes) {

        ByteArrayInputStream bs = new ByteArrayInputStream(bytes); // bytes es el byte[]
        ObjectInputStream is;
        dataTR obj = new dataTR();

        try {
            is = new ObjectInputStream(bs);
            obj = (dataTR) is.readObject();
            is.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return obj;
    }
    
    
    //CONVERTIR DE UN OBJETO A BYTES
    public static byte[] bytes(dataTR d) {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();;
        ObjectOutputStream os;

        try {
            os = new ObjectOutputStream(bs);
            os.writeObject(d);
            os.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return bs.toByteArray();
    }
}
