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

        ByteArrayOutputStream buffer = setupInputOutput(keyInput);

        // Execute the encryption function
        HillCipher.initScanner();
        HillCipher.encrypt("ACT");
        HillCipher.closeScanner();

        String cipherText = getOutput(buffer);

        assertEquals("POH", cipherText);
    }

    /**
     * Test that the encryption decryption function decrypts the string "POH" with the inversed 
     * key of the matrix [GYB,NQK,URP]" correctly to the plaintext "ACT". As per the input requirement 
     * for the function, the key is translated to numbers in the Z26 set where A=0, B=1, ..... Z=25. 
     */
    @Test
    public void testThatDecryptionIsCorrect() {
        // User input: matrix dimension 3 and inverse key matrix
        String keyInput = "3\n8\n5\n10\n21\n8\n21\n21\n12\n8";
        
        ByteArrayOutputStream buffer = setupInputOutput(keyInput);

        HillCipher.initScanner();
        HillCipher.decrypt("POH");
        HillCipher.closeScanner();

        String plainText = getOutput(buffer);
        
        assertEquals("ACT", plainText);
    }

    /**
     * Prepares for providing user input on System.in and reading output on System.out.
     * 
     * @param userInput user input to send to System.in
     * @return printstream that Standard.out is redirected to
     */
    public ByteArrayOutputStream setupInputOutput(String userInput) {
        // Setup to capture prints to System.out 
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        // Provide the function with user input
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        return buffer;
    }

    /**
     * Retreive the function output from the buffer
     * 
     * @param buffer the buffer
     * @return the last string in the buffer - this is either a cipher text or plain text
     */
    public String getOutput(ByteArrayOutputStream buffer) {
        // Stop capturing the prints from the function to System.out
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                
        // Retreive the captured stream
        String[] output = buffer.toString().split(System.lineSeparator());
        buffer.reset();

        return output[output.length-1].split(" ")[1];
    }
}