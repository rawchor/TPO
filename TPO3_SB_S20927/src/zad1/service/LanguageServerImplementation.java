package zad1.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public interface LanguageServerImplementation {
    String destination = "dictionary/";
    String extension = ".txt";

    enum LanguagesPossible {
        EN("eng"), RU("ru"), DE("de");
        public String lang;

        LanguagesPossible(String lang) {
            this.lang = lang;
        }

        public String getLang() {
            return this.lang;
        }
    }

    default String getWord(String wordToBeTranslated, LanguagesPossible language) throws IOException {

        String translatedWord = null;

        String filePath = destination + language.getLang() + extension;
        Scanner scanner = new Scanner(new FileReader(filePath));

        Map<String, String> map = new HashMap<String, String>();
        String line;

        while(scanner.hasNextLine()){
            line=scanner.nextLine();
            String[] splitted = line.split("=");
            map.put(splitted[0], splitted[1]);
        }

        System.out.println(map);

        for (Map.Entry<String, String> pair : map.entrySet()) {
            if(Objects.equals(pair.getKey(), wordToBeTranslated)){
                translatedWord = pair.getValue();
            }
        }

        return translatedWord;
    }
}
