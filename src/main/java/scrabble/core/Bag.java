package scrabble.core;

import scrabble.dictionnary.LetterDistribution;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class Bag {

    private final Queue<Letter> content;

    public Bag(LetterDistribution lang) {
        this.content = new ArrayDeque<>(lang.getLetterAmount());
        List<Letter> letters = new ArrayList<>();

        lang.getLetters().forEach((letter, count) -> {
            for (int i = 0; i < count; i++) {
                letters.add(letter);
            }
        });

        Collections.shuffle(letters, new Random(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)));
        content.addAll(letters);
    }

    public boolean isEmpty() {
        return content.isEmpty();
    }

    public Letter draw() {
        return content.poll();
    }


}
