package javasocket;

import java.net.*;
import java.io.*;

public class Server {

    private final int puerto;
    private ServerSocket sc;
    private Socket so;
    private String mensajeRecibido;

    public Server() {
        this.puerto = 5000;
    }

    public Server(int puerto) {
        this.puerto = puerto;
    }

    public void initServer() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            this.sc = serverSocket;
            System.out.println("Esperando una conexión:");

            try (Socket socket = sc.accept()) {
                this.so = socket;
                System.out.println("Un cliente se ha conectado.");

                BufferedReader entrada = new BufferedReader(new InputStreamReader(so.getInputStream()));
                PrintWriter salida = new PrintWriter(new OutputStreamWriter(so.getOutputStream()), true);

                System.out.println("Confirmando conexion al cliente....");
                salida.println("Conexión exitosa, envia un mensaje :D");

                mensajeRecibido = entrada.readLine();
                System.out.println(mensajeRecibido);

                if (mensajeRecibido != null) {
                    salida.println("\nSe recibio tu mensaje. Te lo devolvemos en mayúsculas: " + mensajeRecibido.toUpperCase());
                } else {
                    salida.println("\nNo se recibió ningún mensaje.");
                }

                salida.println("\nGracias por conectarte, adios!");
                salida.flush();
                System.out.println("\nCerrando conexión...");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
