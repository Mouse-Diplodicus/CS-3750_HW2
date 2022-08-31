package cs3750.hw2;
import java.util.Scanner;

public class Main {
    private final int DELTAONE = 0x11111111;
    private final int DELTATWO   = 0x11111111;
    private static int[] k = new int[4];
    private static int[] l = new int[3];
    private static int[] r = new int[3];


    public static void main(String[] args) {
        System.out.println("Main Start");
        String test = "ABCD1234";
        int testInt = Integer.parseUnsignedInt(test, 16);
        System.out.printf("Original String: %s %n", test);
        System.out.printf("Int: %d %n", testInt);
        System.out.printf("0x%s%n", String.format("%08X", testInt));
        askForKeys();
    }

    public static void askForKeys() {
        Scanner sysIn = new Scanner(System.in);     // Create a Scanner object

        for (int i=0; i < 4; i++) {
            System.out.printf("Please input K[%d] in Hex String (without \"0x\"): ", i);
            k[i] = hexStringToInt(sysIn.nextLine());  // Read user input
        }

        for (int i : k){
            System.out.printf("Int: %d %n", i);
            System.out.printf("String: %s %n", Integer.toString(i));
            System.out.printf("0x%d%n", Integer.parseUnsignedInt(Integer.toString(i), 16));
        }
    }

    public static int hexStringToInt(String hexString) {
        int hexValue = 0;
        char[] hexChars = hexString.toCharArray();

        if (hexChars.length != 8) {
            throw new IllegalArgumentException("Hexadecimal string provided has the wrong number of characters");
        }

        for (int i = 0; i < 8; i++){
            int charValue = Character.getNumericValue(hexChars[i]);
            if (charValue > 15)
                throw new IllegalArgumentException("Hexadecimal string provided has invalid characters");
            hexValue += Math.pow(16, 7-i) * charValue;
        }
        return hexValue;
    }
}
