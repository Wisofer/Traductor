package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {

    public static void main(String[] args) {

        try {
            System.out.println("Iniciado...");

            //ESCUCHANDO EN EL PUERTO 5001
            DatagramSocket udp = new DatagramSocket(5001);

            while (true) {
                byte[] bff = new byte[256];
                DatagramPacket data = new DatagramPacket(bff, bff.length);

                //Recibo la respuesta
                udp.receive(data);

                //CONVERTIR DE BYTES A OBJETO dataTR
                dataTR r = Transfer.objeto(data.getData());

                //BUSCAMOS
                dataTR respuesta = traduccion(r);

                //CONVERTIMOS EL OBJETO
                byte[] mensaje = Transfer.bytes(respuesta);

                //CREAR DATAGRAM
                DatagramPacket rsp = new DatagramPacket(mensaje, mensaje.length, data.getAddress(), data.getPort());

                //ENVIAR
                udp.send(rsp);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static dataTR traduccion(dataTR r) {
        dataTR obj = new dataTR();
        File fichero = new File("base.txt");
        boolean found = false;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fichero));
            String linea = "";
            int fin = 0;

            while ((linea = br.readLine()) != null) {
                
                if (linea.contains(r.palabra)) {
                    
                    fin = linea.indexOf("-");
                    obj.tipo = 1;
                    found = true;

                    if (r.tipo == 0) {
                        //DE INGLES A ESPANOL
                        obj.palabra = linea.substring(0, fin);
                    } else {
                        //DE ESPANOL A INGLES
                        obj.palabra = linea.substring(fin + 1, linea.length());
                    }

                    break;
                }
            }

            if (!found) {
                obj.tipo = 2;
                obj.palabra = "No se ha encontrado la palabra";
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return obj;
    }

}
