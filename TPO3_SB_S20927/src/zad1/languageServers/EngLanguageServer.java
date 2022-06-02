package zad1.languageServers;

import zad1.service.LanguageServerImplementation;

import java.io.IOException;

public class EngLanguageServer extends LanguageServer implements LanguageServerImplementation {
    @Override
    public String getWordFromDictionary(String wordToTranslate) throws IOException {
        return getWord(wordToTranslate, LanguagesPossible.EN);
    }
}
