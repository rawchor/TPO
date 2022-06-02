package zad1.Client;

import zad1.service.LanguageServerImplementation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI{
    private JPanel mainWindow;

    private static String word = null;
    private static String langCode = null;
    private static String dataReceived;

    public GUI() {
        mainWindow = new JPanel();
        mainWindow.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblWord = new JLabel("Enter word: ");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.weightx = 1;
        mainWindow.add(lblWord, gbc);

        //JTextField wordInput = new JTextField();
        JTextField wordInput = new JTextField(10); // Construct TextField
        wordInput.setFont(new Font("Serif", Font.BOLD, 20));
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        mainWindow.add(wordInput, gbc);

        JLabel lblLanguage = new JLabel("Choose Language: ");
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.weightx = 5;
        mainWindow.add(lblLanguage, gbc);

        String[] languageCodes = {"EN","RU","DE"};
        JComboBox languageInput = new JComboBox(languageCodes);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        mainWindow.add(languageInput, gbc);

        JLabel lblTranslated = new JLabel("Translated Word: ");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.weightx = 1;
        mainWindow.add(lblTranslated, gbc);

        //JTextField translatedWord = new JTextField();
        JTextField translatedWord = new JTextField(); // allocate TextField
        translatedWord.setFont(new Font("Serif", Font.BOLD, 20));
        translatedWord.setEditable(false);  // read-only
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainWindow.add(translatedWord, gbc);

        JButton enterButton = new JButton("Enter");
        gbc = new GridBagConstraints();
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainWindow.add(enterButton, gbc);

        enterButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(languageInput.getSelectedItem() == null) {
                    langCode = "";
                }else {
                    word = wordInput.getText();
                    langCode = languageInput.getSelectedItem().toString().toLowerCase();
                }
                setLangCode(langCode);
                setWord(word);

                while(getDataReceived()==null) {
                    try {Thread.sleep(150);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
                translatedWord.setText(getDataReceived());
            }
        });
    }

    public JPanel getUI(){
        return mainWindow;
    }

    public static String getWord() {
        return word;
    }

    public static String getLangCode() {
        return langCode;
    }

    public static void setWord(String word) {
        GUI.word = word;
    }

    public static void setLangCode(String langCode) {
        GUI.langCode = langCode;
    }

    public static String getDataReceived() {
        return dataReceived;
    }

    public static void setDataReceived(String dataReceived) {
        GUI.dataReceived = dataReceived;
    }
}
