import java.util.Scanner;

public class test {

    public static void main(String[] args)
    {
//        int a=32, b=12;
//        System.out.print(a^b);
        Scanner myVar = new Scanner(System.in);
        System.out.println("Enter a message");
        String message;
        message = myVar.nextLine();
        System.out.println("Enter a key");
        String key;
        key = myVar.nextLine();


        char[] arrayOfWords = message.toCharArray();
        char[] arrayOfKey = key.toCharArray();
        int lengthOfMessage = 0;
        lengthOfMessage = arrayOfWords.length;
        int lengthOfKey = 0;
        lengthOfKey = arrayOfKey.length;

        int[] messageArray = charToAscii(arrayOfWords, lengthOfMessage);
        int[] keyArray = charToAscii(arrayOfKey, lengthOfKey);
        int keySum = sumOfKey(keyArray, lengthOfKey);

        int[] encryptNo = encrypt(messageArray, keySum, lengthOfMessage);
        System.out.println("\nEncryption String: " + encryptNo[0]);

        System.out.println("\nEnter the key");
        String newKey;
        newKey = myVar.nextLine();

        char[] arrayOfNewKey = newKey.toCharArray();
        int[] newKeyArray = charToAscii(arrayOfNewKey, lengthOfKey);
        int keySumNew = sumOfKey(newKeyArray, lengthOfKey);
        System.out.println(keySum + " vs " + keySumNew);

        int[] decryptNo = decrypt(encryptNo, keySumNew, lengthOfMessage);
        System.out.println("\nDecryption String: " + decryptNo[0]);

        char[] finalMessage = asciiToChar(decryptNo, lengthOfMessage);
        String messageSum = sumOfMessage(finalMessage, lengthOfMessage);
        System.out.println("\nMessage: " + messageSum);
    }

    public static int[] charToAscii(char[] arrayOfStrings, int lengthOfString)
    {
        int[] ascii = new int[lengthOfString];

        for(int i=0; i<lengthOfString; i++) {
            ascii[i] = arrayOfStrings[i];
        }
        return ascii;
    }

    public static int sumOfKey(int[] keyArray, int lengthOfKey)
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int n : keyArray)
            stringBuilder.append(n);

        int bigNum = 0;
        int factor = 1;

        for(int i = stringBuilder.length()-1; i >= 0; i--) {
            bigNum += Character.digit(stringBuilder.charAt(i), 10) * factor;
            factor *= 10;
        }
        return bigNum;
    }

    public static int[] encrypt(int[] messageArray, int keySum, int lengthOfString)
    {
        int[] encrypt = new int[lengthOfString];
        for(int j=0; j<lengthOfString; j++) {
            encrypt[j] = messageArray[j] ^ keySum;
        }
        return encrypt;
    }

    public static int[] decrypt(int[] encryptNo, int keySum, int lengthOfString)
    {
        int[] decrypt = new int[lengthOfString];
        for(int j=0; j<lengthOfString; j++) {
            decrypt[j] = keySum ^ encryptNo[j];
        }
        return decrypt;
    }

    public static char[] asciiToChar(int[] decryptNo, int lengthOfString)
    {
        char[] messageNew = new char[lengthOfString];

        for(int i=0; i<lengthOfString; i++) {
            messageNew[i] = (char)decryptNo[i];
        }
        return messageNew;
    }

    public static String sumOfMessage(char[] finalMessage, int lengthOfMessage)
    {
        String sum = "";
        for(int l=0; l<lengthOfMessage; l++) {
            sum += finalMessage[l];
        }
//        return sum.replaceAll("[^A-Za-z0-9]", "at");
        return sum;
    }

}


//        int character = arrayOfWords[0];
//        System.out.print(character + " " + arrayOfWords.length);
//        int x = 113;
//        char y = (char)x;
//        System.out.print(y);