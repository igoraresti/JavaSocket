package javasocket;

import java.net.*;
import java.io.*;

public class Client {

    private final String host;
    private final int puerto;
    private String mensajeRecibido;

    public Client() {
        this.host = "localhost";
        this.puerto = 5000;
    }

    public Client(String host, int puerto) {
        this.host = host;
        this.puerto = puerto;
    }

    public void initClient() {
        try (Socket sc = new Socket(host, puerto)) {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()), true);

            mensajeRecibido = entrada.readLine();
            System.out.println(mensajeRecibido);

            String mensaje = Teclado.LeeCadena();
            salida.println(mensaje);

            mensajeRecibido = entrada.readLine();
            System.out.println(mensajeRecibido);

            mensajeRecibido = entrada.readLine();
            System.out.println(mensajeRecibido);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
