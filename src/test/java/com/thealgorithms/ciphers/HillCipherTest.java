package com.thealgorithms.ciphers;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileDescriptor;
import static org.junit.jupiter.api.Assertions.*;


public class HillCipherTest {

    /**
     * Test that the encryption function encrypts the string "ACT" with the key "GYBNQKURP" 
     * correctly to the ciphertext "POH". As per the input requirement for the function, 
     * the key is translated to numbers in the Z26 set where A=0, B=1, ..... Z=25. 
     */
    @Test
    public void testThatEncryptionIsCorrect() {
        // User input: matrix dimension 3 and key input string
        String keyInput = "3\n6\n24\n1\n13\n16\n10\n20\n17\n15";

        // Setup to capture prints to System.out 
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        // Provide the function with user input
        ByteArrayInputStream in = new ByteArrayInputStream(keyInput.getBytes());
        System.setIn(in);

        // Execute the encryption function
        HillCipher.encrypt("ACT");

        // Stop capturing the prints from the function to System.out
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                
        // Retreive the captured stream
        String[] output = buffer.toString().split(System.lineSeparator());
        buffer.reset();

        String cipherText = output[output.length-1].split(" ")[1];

        assertEquals("POH", cipherText);
    }
}