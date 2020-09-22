package scrabble.core;

import scrabble.board.Coords;
import scrabble.board.WordAlignment;

public class Word {
    private String value;
    private Coords start;
    private Coords end;
    private WordAlignment direction;
    private int score;

    public Word(String value, Coords start, Coords end, int score) {
        this.value = value;
        this.start = start;
        this.end = end;
        this.direction = WordAlignment.from(start, end);
        this.score = score;
    }

    public String getValue() {
        return value;
    }

    public Coords getStart() {
        return start;
    }

    public Coords getEnd() {
        return end;
    }

    public WordAlignment getDirection() {
        return direction;
    }

    public int getScore() {
        return score;
    }
}
