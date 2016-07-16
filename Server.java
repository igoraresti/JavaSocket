package javasocket;

import java.net.*;
import java.io.*;

public class Server {

    private final int PUERTO = 5000;
    private ServerSocket sc;
    private Socket so;
    private String mensajeRecibido;

    //SERVIDOR
    public void initServer() {
        try {
            sc = new ServerSocket(PUERTO);/* crea socket servidor que escuchara en puerto 5000*/
            so = new Socket();

            System.out.println("Esperando una conexión:");

            so = sc.accept();
            //Inicia el socket, ahora esta esperando una conexión por parte del cliente

            System.out.println("Un cliente se ha conectado.");

            //Canales de entrada y salida de datos
            BufferedReader entrada = new BufferedReader(new InputStreamReader(so.getInputStream()));

            PrintWriter salida = new PrintWriter(new OutputStreamWriter(so.getOutputStream()), true);

            System.out.println("Confirmando conexion al cliente....");

            salida.println("Conexión exitosa, envia un mensaje :D");

            //Recepcion de mensaje	 
            mensajeRecibido = entrada.readLine();

            System.out.println(mensajeRecibido);

            salida.println("\nSe recibio tu mensaje. Te lo devolvemos en mayúsculas: " + mensajeRecibido.toUpperCase());

            salida.println("\nGracias por conectarte, adios!");

            System.out.println("\nCerrando conexión...");

            sc.close();//Aqui se cierra la conexión con el cliente

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
