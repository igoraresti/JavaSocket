package javasocket;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class TecladoTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        Teclado.setReader(new BufferedReader(new InputStreamReader(System.in)));
    }

    @Test
    void testConstructor() throws Exception {
        Constructor<Teclado> constructor = Teclado.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    void testLeeCadena() {
        String input = "test string\n";
        Teclado.setReader(new BufferedReader(new StringReader(input)));
        assertEquals("test string", Teclado.LeeCadena());
    }

    @Test
    void testLeeCadenaException() throws IOException {
        BufferedReader mockReader = new BufferedReader(new StringReader("")) {
            @Override
            public String readLine() throws IOException {
                throw new IOException("Mock exception");
            }
        };
        Teclado.setReader(mockReader);
        assertEquals("", Teclado.LeeCadena());
    }

    @Test
    void testLeeCaracter() {
        String input = "abc\n";
        Teclado.setReader(new BufferedReader(new StringReader(input)));
        assertEquals('a', Teclado.LeeCaracter());
    }

    @Test
    void testLeeCaracterEmpty() {
        String input = "\n";
        Teclado.setReader(new BufferedReader(new StringReader(input)));
        assertEquals((char) 0, Teclado.LeeCaracter());
    }

    @Test
    void testLeeCaracterException() throws IOException {
         BufferedReader mockReader = new BufferedReader(new StringReader("")) {
            @Override
            public String readLine() throws IOException {
                throw new IOException("Mock exception");
            }
        };
        Teclado.setReader(mockReader);
        assertEquals((char) 0, Teclado.LeeCaracter());
    }

    @Test
    void testLeeEntero() {
        String input = "123\n";
        Teclado.setReader(new BufferedReader(new StringReader(input)));
        assertEquals(123, Teclado.LeeEntero());
        assertTrue(outContent.toString().contains("123"));
    }

    @Test
    void testLeeEnteroInvalid() {
        String input = "not a number\n";
        Teclado.setReader(new BufferedReader(new StringReader(input)));
        assertEquals(0, Teclado.LeeEntero());
        assertTrue(outContent.toString().contains("caracter no numérico"));
    }

    @Test
    void testLeeEnteroNull() {
        Teclado.setReader(new BufferedReader(new StringReader("")) {
            @Override
            public String readLine() {
                return null;
            }
        });
        assertEquals(0, Teclado.LeeEntero());
    }

    @Test
    void testLeeEnteroException() throws IOException {
        Teclado.setReader(new BufferedReader(new StringReader("")) {
            @Override
            public String readLine() throws IOException {
                throw new IOException("Mock exception");
            }
        });

        assertEquals(0, Teclado.LeeEntero());
    }
}
