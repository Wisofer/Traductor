package app;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Transfer {

    //Convertimos de BYTES a OBJETO
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
    
    
    //Converimos de OBJETO a BYTES
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
