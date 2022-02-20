package com.thealgorithms.ciphers;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

/*
 * Java Implementation of Hill Cipher
 * Hill cipher is a polyalphabetic substitution cipher. Each letter is represented by a number belonging to the set Z26 where A=0 , B=1, ..... Z=25.
 * To encrypt a message, each block of n letters (since matrix size is n x n) is multiplied by an invertible n × n matrix, against modulus 26.
 * To decrypt the message, each block is multiplied by the inverse of the matrix used for encryption.
 * The cipher key and plaintext/ciphertext are user inputs.
 * @author Ojasva Jain
 */
public class HillCipher {

    static ArrayList<Integer> coverageTrack = new ArrayList<Integer>();
    static Set<Integer> coveredBranches = new HashSet<Integer>();
    static Scanner in = new Scanner(System.in);

    /* Following function encrypts the message
     */
    static void encrypt(String message) {
        message = message.toUpperCase();
        // Get key matrix
        System.out.println("Enter key matrix size");
        int n = in.nextInt();
        System.out.println("Enter Key/encryptionKey matrix ");
        int keyMatrix[][] = new int[n][n];
        for (int i = 0; i < n; i++) {   // id 0
            coverageInfo(0);
            for (int j = 0; j < n; j++) {   // id 1
                coverageInfo(1);
                keyMatrix[i][j] = in.nextInt();
            }
            coverageInfo(2);   // id 2
        }
        coverageTrack.add(3);   // id 3

        //check if det = 0
        if (determinant(keyMatrix, n) % 26 == 0) {  
            coverageInfo(4);   // id 4
            System.out.println("Invalid key, as determinant = 0. Program Terminated");
            return;
        }
        coverageInfo(5);   // id 5

        int[][] messageVector = new int[n][1];
        String CipherText = "";
        int cipherMatrix[][] = new int[n][1];
        int j = 0;
        while (j < message.length()) {
            coverageInfo(6);   // id 6
            for (int i = 0; i < n; i++) {
                coverageInfo(7);   // id 7
                if (j >= message.length()) {
                    coverageInfo(8);   // id 8
                    messageVector[i][0] = 23;
                } else {
                    coverageInfo(2);   // id 9
                    messageVector[i][0] = (message.charAt(j)) % 65;
                }
                System.out.println(messageVector[i][0]);
                j++;
            }
            coverageInfo(10);   // id 10
            int x, i;
            for (i = 0; i < n; i++) {
                coverageInfo(11);   // id 11
                cipherMatrix[i][0] = 0;

                for (x = 0; x < n; x++) {
                    coverageInfo(12);   // id 12
                    cipherMatrix[i][0] += keyMatrix[i][x] * messageVector[x][0];
                }
                coverageInfo(13);   // id 13
                System.out.println(cipherMatrix[i][0]);
                cipherMatrix[i][0] = cipherMatrix[i][0] % 26;
            }
            coverageInfo(14);   // id 14
            for (i = 0; i < n; i++) {
                coverageInfo(15);   // id 15
                CipherText += (char) (cipherMatrix[i][0] + 65);
            }
            coverageInfo(16);   // id 16
        }
        coverageInfo(17);   // id 17
        printCoverageInfoToFile(coverageTrack);

        System.out.println("Ciphertext: " + CipherText);
    }

    static void coverageInfo(int branchId) {
        coveredBranches.add(branchId);
        coverageTrack.add(branchId);
    }

    /**
     * Prints branch coverage information to file in /tmp folder
     */
    static void printCoverageInfoToFile(ArrayList<Integer> coverageTrack) {
        String fileName = "/tmp/coverageInfo.txt";
        try {
            File myFile = new File(fileName);
            if (myFile.exists()) {
                myFile.delete();
            }
            myFile.createNewFile();

            StringBuilder sb = new StringBuilder();
            sb.append("Branch Coverage of the function HillCipher::encode\n\nCovered Branches:\n");
            int i = 1;
            for (int branch : coveredBranches) {
                if (i++ != coveredBranches.size()) {
                    sb.append(" id: "+ branch +"\n");
                } else {
                    sb.append(" id: "+ branch + "\n\n");
                }
            }
            sb.append("Taken path:\n{");
            i = 1;
            for (int branch : coverageTrack) {
                if (i++ != coverageTrack.size()) {
                    sb.append(branch +", ");
                } else {
                    sb.append(branch + "}\n\n");
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Following function decrypts a message
    static void decrypt(String message) {
        message = message.toUpperCase();
        // Get key matrix
        System.out.println("Enter key matrix size");
        int n = in.nextInt();
        System.out.println("Enter inverseKey/decryptionKey matrix ");
        int keyMatrix[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                keyMatrix[i][j] = in.nextInt();
            }
        }
        //check if det = 0
        if (determinant(keyMatrix, n) % 26 == 0) {
            System.out.println("Invalid key, as determinant = 0. Program Terminated");
            return;
        }
        //solving for the required plaintext message
        int[][] messageVector = new int[n][1];
        String PlainText = "";
        int plainMatrix[][] = new int[n][1];
        int j = 0;
        while (j < message.length()) {
            for (int i = 0; i < n; i++) {
                if (j >= message.length()) {
                    messageVector[i][0] = 23;
                } else {
                    messageVector[i][0] = (message.charAt(j)) % 65;
                }
                System.out.println(messageVector[i][0]);
                j++;
            }
            int x, i;
            for (i = 0; i < n; i++) {
                plainMatrix[i][0] = 0;

                for (x = 0; x < n; x++) {
                    plainMatrix[i][0] += keyMatrix[i][x] * messageVector[x][0];
                }

                plainMatrix[i][0] = plainMatrix[i][0] % 26;
            }
            for (i = 0; i < n; i++) {
                PlainText += (char) (plainMatrix[i][0] + 65);
            }
        }
        System.out.println("Plaintext: " + PlainText);
    }

    // Determinant calculator
    public static int determinant(int a[][], int n) {
        int det = 0, sign = 1, p = 0, q = 0;

        if (n == 1) {
            det = a[0][0];
        } else {
            int b[][] = new int[n - 1][n - 1];
            for (int x = 0; x < n; x++) {
                p = 0;
                q = 0;
                for (int i = 1; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (j != x) {
                            b[p][q++] = a[i][j];
                            if (q % (n - 1) == 0) {
                                p++;
                                q = 0;
                            }
                        }
                    }
                }
                det = det + a[0][x] * determinant(b, n - 1) * sign;
                sign = -sign;
            }
        }
        return det;
    }

    // Function to implement Hill Cipher
    static void hillcipher(String message) {
        message.toUpperCase();
        System.out.println("What do you want to process from the message?");
        System.out.println("Press 1: To Encrypt");
        System.out.println("Press 2: To Decrypt");
        short sc = in.nextShort();
        if (sc == 1) {
            encrypt(message);
        } else if (sc == 2) {
            decrypt(message);
        } else {
            System.out.println("Invalid input, program terminated.");
        }
    }

    // Driver code
    public static void main(String[] args) {
        // Get the message to be encrypted
        System.out.println("Enter message");
        String message = in.nextLine();
        hillcipher(message);
    }
}
