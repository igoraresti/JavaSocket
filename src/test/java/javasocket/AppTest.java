package javasocket;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testClientAppMain() {
        // This will try to connect to a non-existent server and should print an error
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            ClientApp.main(new String[]{});
        } finally {
            System.setOut(originalOut);
        }

        assertTrue(outContent.toString().contains("Error:"));
    }

    @Test
    void testServerAppMain() throws Exception {
        new ServerApp();
        new ClientApp();

        Thread t = new Thread(() -> ServerApp.main(new String[]{}));
        t.start();

        // Give it a moment to start the ServerSocket
        TimeUnit.MILLISECONDS.sleep(500);

        // Connect and then let it finish
        try (Socket s = new Socket("localhost", 5000)) {
            // just connect to unblock accept()
        }

        t.join(2000);
    }
}
