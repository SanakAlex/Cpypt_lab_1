/**
 * Created by sanak on 28.02.2016.
 */
public class Crypt {
    private static int shift = (int)'а';
    public static String encrypt(String openText, String keyWord) {
        StringBuilder encryptedText = new StringBuilder();
        for(int i = 0; i < openText.length();i++) {
            int num = ((openText.charAt(i) + keyWord.charAt(i % keyWord.length()) - 2 * shift) % 32);
            char c = (char)(num + shift);
            encryptedText.append(c);
        }
        return encryptedText.toString();
    }
    public static String decrypt(String cypherText, String keyWord) {
        StringBuilder decryptedText = new StringBuilder();
        for(int i = 0; i < cypherText.length();i++) {
            int num = ((cypherText.charAt(i)  - keyWord.charAt(i % keyWord.length()) + 32) % 32);
            char c = (char)(num + shift);
            decryptedText.append(c);
        }
        return decryptedText.toString();
    }

    public static String keySearch(String cypherText){
        char freqLetter = 'о';
        String key = "";
        int[] quantities = new int[25];
        int p = 0;
        for (int r=6; r<=30; r++,p++){
            for (int i=0;i<cypherText.length()-r;i++){
                String letter1 = String.valueOf(cypherText.charAt(i));
                String letter2 = String.valueOf(cypherText.charAt(i+r));
                if (letter1.equals(letter2)) quantities[p]++;
            }
        }
        int maxVal = 0;
        int period = 0;
        for (int m = 0; m<quantities.length;m++) {
            if (quantities[m]>maxVal) {
                maxVal=quantities[m];
                period = m+6;
            }
        }
        String[] str = new String[period];
        for (int i = 0;i<period;i++) {
            str[i] = "";
            for (int j = 0; j < cypherText.length() / period; j++) {
                str[i] += cypherText.charAt(j*period+i);
            }
            int pos = 0;
            int maxCount = 0;
            int counts[] = new int[str[i].length()];
            for (int j=0; j< str[i].length();j++){
                String letter1 = String.valueOf(str[i].charAt(j));
                for (int k=j; k<str[i].length(); k++){
                    String letter2 = String.valueOf(str[i].charAt(k));
                    if (letter1.equals(letter2)) counts[j]++;
                }
                for (int m = 0; m<counts.length;m++) {
                    if (counts[m]>maxCount) {
                        maxCount=counts[m];
                        pos = m;
                    }
                }

            }
            key += String.valueOf((char) (((str[i].charAt(pos)) - freqLetter + 32) % 32 + 'а'));
        }
        return key;
    }
}
