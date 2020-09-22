package scrabble.dictionnary;

import scrabble.core.Letter;

import java.util.Map;

public interface LetterDistribution {

    Integer getLetterAmount();

    Map<Letter, Integer> getLetters();

}
