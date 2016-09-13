import java.io.FileNotFoundException;
import java.util.Random;

/**
 * Created by sanak on 27.02.2016.
 */
public class Main {

    private static String fileToAnalyze = "C://Users//sanak//IdeaProjects//Cpypt_lab_1//files/ayvengo.txt";
    private static String fileToSave = "C://Users//sanak//IdeaProjects//Cpypt_lab_1//files/ayvengo222.txt";
    private static String v11 = "C:/Users/sanak/IdeaProjects/Cpypt_lab_1/files/v11.txt";
    private static String v11_decrypted = "C:/Users/sanak/IdeaProjects/Cpypt_lab_1/files/v11_decrypted.txt";
    private static String text = "C:/Users/sanak/IdeaProjects/Cpypt_lab_1/files/text.txt";
    private static String alph = "абвгдежзийклмнопрстуфхцчшщьыъэюя";


    public static void main(String[] args) throws FileNotFoundException {
        Text textWithSpaces = new Text("C://Users//sanak//IdeaProjects//Cpypt_lab_1//files/ayvengo.txt",true, 1);
        textWithSpaces.letterFrequencyCalc();
        textWithSpaces.entropyCalc();
        textWithSpaces.sortMap();
        textWithSpaces.showResult();

        Text textWithoutSpaces = new Text("C://Users//sanak//IdeaProjects//Cpypt_lab_1//files/ayvengo.txt",false, 1);
        textWithoutSpaces.letterFrequencyCalc();
        textWithoutSpaces.entropyCalc();
        textWithoutSpaces.sortMap();
        textWithoutSpaces.showResult();

        Text bigramWithoutSpaces = new Text("C://Users//sanak//IdeaProjects//Cpypt_lab_1//files/ayvengo.txt",false, 2);
        bigramWithoutSpaces.bigramFrequencyCalc();
        bigramWithoutSpaces.entropyCalc();
        bigramWithoutSpaces.sortMap();
        bigramWithoutSpaces.showResult();

        Text bigramWithSpaces = new Text("C://Users//sanak//IdeaProjects//Cpypt_lab_1//files/ayvengo.txt",true, 2);
        bigramWithSpaces.bigramFrequencyCalc();
        bigramWithSpaces.entropyCalc();
        bigramWithSpaces.sortMap();
        bigramWithSpaces.showResult();

        Text textToDecr = new Text(v11, false, 2);
        System.out.println(Crypt.keySearch(textToDecr.getText()));
        System.out.println(Crypt.decrypt(textToDecr.getText(), "венецианскийкупец"));
        WorkWithFile.write(v11_decrypted, Crypt.decrypt(textToDecr.getText(), "венецианскийкупец"));

        Text textToEncrypt = new Text(text, false, 1);
        textToEncrypt.letterQuantityCalc();
        textToEncrypt.indexCalc();
        Text encrText2 = new Text(text, false, 1);
        encrText2.setText(Crypt.encrypt(encrText2.getText(), "да"));
        encrText2.letterQuantityCalc();
        encrText2.indexCalc();
        Text encrText3 = new Text(text, false, 1);
        encrText3.setText(Crypt.encrypt(encrText2.getText(), "нет"));
        encrText3.letterQuantityCalc();
        encrText3.indexCalc();
        Text encrText4 = new Text(text, false, 1);
        encrText4.setText(Crypt.encrypt(encrText2.getText(), "вася"));
        encrText4.letterQuantityCalc();
        encrText4.indexCalc();
        Text encrText5 = new Text(text, false, 1);
        encrText5.setText(Crypt.encrypt(encrText2.getText(), "упырь"));
        encrText5.letterQuantityCalc();
        encrText5.indexCalc();

        /*for (int i=10; i<=20; i++){
            String key = "";
            for (int j=0; j<i; j++){
                Random rand = new Random();
                key += alph.charAt(rand.nextInt(31));
            }
            Text txt = new Text(text, false, 1);
            txt.setText(Crypt.encrypt(txt.getText(), key));
            txt.letterQuantityCalc();
            txt.indexCalc();
        }*/
    }
}
