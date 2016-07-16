package javasocket;

import java.net.*;
import java.io.*;

public class Client {

    final String HOST = "localhost";
    final int PUERTO = 5000;

    Socket sc;

    private String mensajeRecibido;

    public void initClient() /*ejecuta este metodo para correr el cliente */ {
        try {
            sc = new Socket(HOST, PUERTO);
            /*conectar a un servidor en localhost con puerto 5000*/

            //Canales de entrada y salida de datos

            BufferedReader entrada = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()), true);

            mensajeRecibido = entrada.readLine();
            System.out.print(mensajeRecibido);

            //enviamos el mensaje
            String mensaje = Teclado.LeeCadena();
            salida.println(mensaje);

            mensajeRecibido = entrada.readLine();
            System.out.print(mensajeRecibido);

            mensajeRecibido = entrada.readLine();
            System.out.print(mensajeRecibido);

            //cerramos la conexión
            sc.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}
