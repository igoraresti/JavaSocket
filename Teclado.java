package javasocket;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alumno
 */
import java.io.*;

public class Teclado {

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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        try {
            str = br.readLine();
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

        } catch (Exception otro) {
            respuesta = "Otro tipo de excepcion";
            num = 0;
        }
        System.out.println("Su numero: " + respuesta);
        return num;
    }

}
