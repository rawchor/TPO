package zad1.languageServers;

import java.io.IOException;

public interface LanguageServerTemplate {
    void start(int port ) throws IOException, ClassNotFoundException;
}
