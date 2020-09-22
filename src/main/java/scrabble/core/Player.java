package scrabble.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player {

    private String name;
    private List<Letter> rack;
    private Map<String, Integer> playedWords;

    public Player(String name, Integer rackSize) {
        this.name = name;
        this.rack = new ArrayList<>(rackSize);
    }

    public Integer getScore() {
        return playedWords.values().stream().mapToInt(Integer::intValue).sum();
    }
}
