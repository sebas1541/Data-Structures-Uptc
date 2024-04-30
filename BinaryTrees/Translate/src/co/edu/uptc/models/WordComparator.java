package co.edu.uptc.models;

import java.util.Comparator;

public class WordComparator implements Comparator<Word> {
    @Override
    public int compare(Word word1, Word word2) {
        return word1.getSpanish().compareTo(word2.getSpanish());
    }
}
