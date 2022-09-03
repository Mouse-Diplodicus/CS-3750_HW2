package cs3750.hw2;
import java.util.Scanner;

public class Decrypt {
    private static final int[] delta = {0x11111111, 0x22222222};
    private static int[] k = new int[4];
    private static int[] l = new int[3];
    private static int[] r = new int[3];
    private static Scanner sysIn = new Scanner(System.in);     // Scanner object for reading user inputs


    public static void main(String[] args) {
        System.out.println("Main Start");
        askForKeys();
        askForPlaintext();
        decrypt();
        printText();
    }

    public static void askForKeys() {
        for (int i=0; i < 4; i++) {
            System.out.printf("Please input K[%d] in Hex String (without \"0x\"): ", i);
            k[i] = Integer.parseUnsignedInt(sysIn.nextLine(), 16);  // Read user input
        }
    }

    public static void askForPlaintext() {
        System.out.print("Please input L[2] in Hex String (without \"0x\"): ");
        l[2] = Integer.parseUnsignedInt(sysIn.nextLine(), 16);  // Read user input

        System.out.print("Please input R[2] in Hex String (without \"0x\"): ");
        r[2] = Integer.parseUnsignedInt(sysIn.nextLine(), 16);  // Read user input

        l[0] = 0x00000000;
        l[1] = 0x00000000;
        r[0] = 0x00000000;
        r[1] = 0x00000000;
    }

    public static void decrypt() {
        int[] temp = new int[3];
        for (int i = 2; i > 0; i--) {
            System.out.printf("Beginning decryption round %d...%n", 3 - i);
            r[i - 1] = l[i];
            temp[0] = (l[i] << 4) + k[(2 * i) - 2];     //k[2], k[0]
            temp[1] = (l[i] >>> 5) + k[(2 * i) - 1];    //k[3], k[1]
            temp[2] = l[i] + delta[i - 1];
            l[i - 1] = r[i] - (temp[0] ^ temp[1] ^ temp[2]);
        }
        System.out.println("Decryption finished");
    }

    public static void printText() {
        for (int i = 0; i < 3; i++){
            System.out.printf("L[%d] = 0x%s     R[%d] = 0x%s%n", i, String.format("%08X", l[i]), i, String.format("%08X", r[i]));
        }
    }

    public static void printKeys() {
        for (int i = 0; i < 4; i++){
            System.out.printf("K[%d] = 0x%s%n", i, String.format("%08X", k[i]));
        }
    }
}
