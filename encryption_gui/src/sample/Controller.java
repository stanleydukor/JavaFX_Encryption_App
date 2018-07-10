package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.*;
import javafx.scene.control.Label;

import java.lang.String;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        outputText.setEditable(false);
    }

    @FXML
    JFXTextArea inputText = new JFXTextArea();
    @FXML
    JFXTextArea outputText = new JFXTextArea();
    @FXML
    JFXButton btn1 = new JFXButton();
    @FXML
    JFXButton btn2 = new JFXButton();
    @FXML
    JFXButton clear = new JFXButton();
    @FXML
    JFXPasswordField ki = new JFXPasswordField();
    @FXML
    JFXPasswordField newKi = new JFXPasswordField();
    @FXML
    Label confirm = new Label();
    @FXML
    Label confirmD = new Label();



    char[] arrayOfWords;
    char[] arrayOfKey;
    int lengthOfMessage;
    int lengthOfKey;
    int[] encryptNo;
    int[] decryptNo;

    @FXML
    private void clearScreen(ActionEvent event) {
        inputText.clear();
        outputText.clear();
        ki.clear();
        newKi.clear();
        confirm.setText("");
        confirmD.setText("");
    }

    @FXML
    private void getData(ActionEvent event) {
        String message = inputText.getText();
        String key = ki.getText();

        if(message != null && !message.isEmpty() && key != null && !key.isEmpty()) {
            arrayOfWords = message.toCharArray();
            arrayOfKey = key.toCharArray();
            lengthOfMessage = arrayOfWords.length;
            lengthOfKey = arrayOfKey.length;
            int[] messageArray = charToAscii(arrayOfWords, lengthOfMessage);
            int[] keyArray = charToAscii(arrayOfKey, lengthOfKey);
            int keySum = sumOfKey(keyArray);
            encryptNo = encrypt(messageArray, keySum, lengthOfMessage);
            confirm.setText("Message Encrypted!");
        }
        else
            confirm.setText("Message could not be \nEncrypted. Make sure \nyou have a message and key");
    }
    @FXML
    private void printData(ActionEvent event) {
        String newKey = newKi.getText();
        if(encryptNo != null && newKey != null && !newKey.isEmpty()) {
            outputText.setEditable(true);
            char[] arrayOfNewKey = newKey.toCharArray();
            int[] newKeyArray = charToAscii(arrayOfNewKey, lengthOfKey);
            int keySumNew = sumOfKey(newKeyArray);

            decryptNo = decrypt(encryptNo, keySumNew, lengthOfMessage);
            char[] finalMessage = asciiToChar(decryptNo, lengthOfMessage);
            outputText.setText(sumOfMessage(finalMessage, lengthOfMessage));
            confirmD.setText("Message Decrypted");
        }
        else {
            confirmD.setText("Unable to Decrypt");
        }

    }

    public static int[] charToAscii(char[] arrayOfStrings, int lengthOfString)
    {
        int[] ascii = new int[lengthOfString];

        for(int i=0; i<lengthOfString; i++) {
            ascii[i] = arrayOfStrings[i];
        }
        return ascii;
    }

    public static int sumOfKey(int[] keyArray)
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