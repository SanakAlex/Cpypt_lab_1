import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by sanak on 28.02.2016.
 */
public class Text {
    private String text;
    private boolean spaces;
    private int n_gram;
    private double entropy;
    private double index;
    private Map<String, Double> map = new HashMap<>();
    private Map<String, Double> sortedMap = new TreeMap<>(new Comparator<String>() {
        @Override
        public int compare(String lhs, String rhs) {
            return map.get(lhs).compareTo(map.get(rhs));
        }
    });

    public String getText(){
        return this.text;
    }

    public void setText(String text){this.text = text;}

    public static String filterWithoutSpaces(String text){
        return text.toLowerCase().replaceAll("[^а-я]+", "");
    }

    public static String filterWithSpaces(String text){
        return text.toLowerCase().replaceAll("-", "").replaceAll("[ \t\n]+", " ").replaceAll("[^а-я ]+", "");
    }

    public void spaceCheck(){
        if(spaces) {
            //System.out.println("With spaces:");
            text = filterWithSpaces(text);
        } else {
            //System.out.println("Without spaces:");
            text = filterWithoutSpaces(text);
        }
    }

    public void letterQuantityCalc(){
        for (int i = 0;i<text.length();i++){
            String letter = String.valueOf(text.charAt(i));

            if (map.containsKey(letter)) {
                map.put(letter,map.get(letter)+1);
            } else {
                map.put(letter, 1.0);
            }
        }
    }

    public void indexCalc(){
        for (String key:map.keySet()){
            index += (map.get(key)*(map.get(key)-1))/(text.length()*(text.length()-1));
        }
        System.out.format("%.5f\n", index);
    }

    public void letterFrequencyCalc(){
        letterQuantityCalc();
        for (String key:map.keySet()){
            map.put(key,map.get(key)/text.length());
        }
    }

    public void bigramFrequencyCalc(){
        for (int i = 0;i<text.length();i++){
            if (i+1 == text.length()) break;
            String bigramInText = String.valueOf(text.charAt(i)) + String.valueOf(text.charAt(i+1));

            if (map.containsKey(bigramInText)) {
                map.put(bigramInText,map.get(bigramInText)+1);
            } else {
                map.put(bigramInText, 1.0);
            }
        }
        for (String key:map.keySet()){
            map.put(key,map.get(key)/text.length());
        }
    }

    public void entropyCalc(){
        for (double k:map.values()){
            entropy += (k*Math.log(1/k)/Math.log(2.0))/(double) n_gram;
        }
    }

    public void sortMap(){
        sortedMap.putAll(map);
    }

    public void showResult(){
        for (Map.Entry k:sortedMap.entrySet()) {
            System.out.format("%s = %.5f\n", k.getKey(), k.getValue());
        }
        System.out.format("H1 = %.5f\n\n",entropy);
    }

    Text(String fileName, boolean spaces, int n_gram) throws FileNotFoundException {
        text = WorkWithFile.read(fileName);
        this.spaces = spaces;
        this.n_gram = n_gram;
        spaceCheck();
    }
}
