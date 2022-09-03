package cs3750.hw2;
import java.util.Scanner;

public class Encrypt {
    private static final int[] delta = {0x11111111, 0x22222222};
    private static int[] k = new int[4];
    private static int[] l = new int[3];
    private static int[] r = new int[3];
    private static Scanner sysIn = new Scanner(System.in);     // Scanner object for reading user inputs


    public static void main(String[] args) {
        askForKeys();
        askForPlaintext();
        printKeys();
        encrypt();
        printText();
    }

    public static void askForKeys() {
        for (int i=0; i < 4; i++) {
            System.out.printf("Please input K[%d] in Hex String (without \"0x\"): ", i);
            k[i] = Integer.parseUnsignedInt(sysIn.nextLine(), 16);  // Read user input
        }
    }

    public static void askForPlaintext() {
        System.out.print("Please input L[0] in Hex String (without \"0x\"): ");
        l[0] = Integer.parseUnsignedInt(sysIn.nextLine(), 16);  // Read user input

        System.out.print("Please input R[0] in Hex String (without \"0x\"): ");
        r[0] = Integer.parseUnsignedInt(sysIn.nextLine(), 16);  // Read user input

        l[1] = 0x00000000;
        l[2] = 0x00000000;
        r[1] = 0x00000000;
        r[2] = 0x00000000;
    }

    public static void encrypt() {
        int[] temp = new int[3];
        for (int i = 0; i < 2; i++) {
            System.out.printf("Beginning encryption round %d...%n", i + 1);
            l[i + 1] = r[i];
            temp[0] = (r[i] << 4) + k[i * 2];
            temp[1] = (r[i] >>> 5) + k[i * 2 + 1];
            temp[2] = r[i] + delta[i];
            r[i + 1] = l[i] + (temp[0] ^ temp[1] ^ temp[2]);
        }
        System.out.println("Encryption finished");
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
