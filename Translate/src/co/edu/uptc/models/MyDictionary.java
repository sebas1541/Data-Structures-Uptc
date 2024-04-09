package co.edu.uptc.models;

import co.edu.uptc.structures.MyBinaryTree;

import java.util.List;

public class MyDictionary {
    private MyBinaryTree<Word> dictionary;

    public MyDictionary() {
        this.dictionary = new MyBinaryTree<>(new WordComparator());
    }


    public void insertTranslation(String spanish, String english, String french) {
        dictionary.insert(new Word(spanish, english, french));
    }


    public String getEnglishTranslation(String spanish) {
        Word searchResult = dictionary.search(new Word(spanish, null, null));
        return searchResult != null ? searchResult.getEnglish() : "Aún no hay traducción";
    }


    public String getFrenchTranslation(String spanish) {
        Word searchResult = dictionary.search(new Word(spanish, null, null));
        return searchResult != null ? searchResult.getFrench() : "Aún no hay traducción";
    }


    public int getTotalWords() {
        return dictionary.inOrder().size();
    }

    public void displayDictionaryContents() {
        List<Word> wordsInOrder = dictionary.inOrder();
        for (Word word : wordsInOrder) {
            System.out.println("Español: " + word.getSpanish() + ", Inglés: " + word.getEnglish() + ", Francés: " + word.getFrench());
        }
    }

}
