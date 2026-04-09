package javasocket;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SocketTest {

    @Test
    void testServerAndClient() throws Exception {
        int port = 5001;
        Server server = new Server(port);

        // Capture all stdout
        PrintStream originalOut = System.out;
        ByteArrayOutputStream anyOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(anyOut));

        try {
            Thread serverThread = new Thread(server::initServer);
            serverThread.start();

            // Give server some time to start
            TimeUnit.MILLISECONDS.sleep(500);

            // Prepare client input
            String input = "Hello Server\n";
            Teclado.setReader(new BufferedReader(new StringReader(input)));

            Client client = new Client("localhost", port);
            client.initClient();

            serverThread.join(2000);
        } finally {
            System.setOut(originalOut);
        }

        String output = anyOut.toString();
        assertTrue(output.contains("Conexión exitosa"), "Output should contain successful connection message.");
        assertTrue(output.contains("HELLO SERVER"), "Output should contain uppercase message.");
        // assertTrue(output.toLowerCase().contains("adios"), "Output should contain goodbye message.");
    }

    @Test
    void testServerException() {
        // Use a port that might be restricted or already in use to trigger an exception
        Server server = new Server(1); // Port 1 usually requires root

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            server.initServer();
        } finally {
            System.setOut(originalOut);
        }

        assertTrue(outContent.toString().contains("Error:"));
    }

    @Test
    void testClientException() {
        Client client = new Client("nonexistent-host", 5000);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            client.initClient();
        } finally {
            System.setOut(originalOut);
        }

        assertTrue(outContent.toString().contains("Error:"));
    }

    @Test
    void testServerHandlesNullMessage() throws Exception {
        int port = 5002;
        Server server = new Server(port);

        Thread serverThread = new Thread(server::initServer);
        serverThread.start();

        TimeUnit.MILLISECONDS.sleep(500);

        // Client connects but sends nothing and closes
        try (Socket socket = new Socket("localhost", port)) {
            // Do nothing, just close
        }

        serverThread.join(2000);
        // If it finished without error, it handled null message (EOF)
    }

    @Test
    void testDefaultConstructors() {
        assertNotNull(new Server());
        assertNotNull(new Client());
    }
}
