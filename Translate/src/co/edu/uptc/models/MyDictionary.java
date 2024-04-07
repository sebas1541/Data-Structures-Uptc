package co.edu.uptc.models;

import co.edu.uptc.structures.MyBinaryTree;

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
        return searchResult != null ? searchResult.getEnglish() : "No translation found";
    }


    public String getFrenchTranslation(String spanish) {
        Word searchResult = dictionary.search(new Word(spanish, null, null));
        return searchResult != null ? searchResult.getFrench() : "No translation found";
    }


    public int getTotalWords() {
        return dictionary.inOrder().size();
    }

}
