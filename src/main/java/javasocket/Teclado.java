package javasocket;

import java.io.*;

public class Teclado {

    Teclado() {}

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void setReader(BufferedReader newReader) {
        reader = newReader;
    }

    public static char LeeCaracter() {
        char ch;
        try {
            ch = LeeCadena().charAt(0);
        } catch (Exception otro) {
            ch = 0;
        }
        return ch;
    }

    public static String LeeCadena() {
        String str;
        try {
            str = reader.readLine();
        } catch (Exception otro) {
            str = "";
        }
        return str;
    }

    public static int LeeEntero() {
        int num = 0;
        String respuesta;
        try {
            num = Integer.parseInt(LeeCadena().trim());
            respuesta = String.valueOf(num);

        } catch (NumberFormatException ex) {
            respuesta = "Se ha introducido caracter no numérico";
            num = 0;
        } catch (Exception otro) {
            respuesta = "Otro tipo de excepcion";
            num = 0;
        }
        System.out.println("Su numero: " + respuesta);
        return num;
    }
}
