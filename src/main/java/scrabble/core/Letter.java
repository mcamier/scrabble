package scrabble.core;

public class Letter {

    private char value;
    private Integer score;

    public Letter(char letter, Integer point) {
        this.value = letter;
        this.score = point;
    }

    public char getValue() {
        return value;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}
