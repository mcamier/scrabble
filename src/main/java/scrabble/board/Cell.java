package scrabble.board;

import scrabble.core.Letter;

public class Cell {

    private final Coords location;
    private Letter letter;
    private int letterMultiplier;
    private int wordMultiplier;

    public Cell(Coords c) {
        this.letterMultiplier = 1;
        this.wordMultiplier = 1;
        this.location = new Coords(c.getX(), c.getY());
    }


    private Cell(Coords c, int wordMultiplier, int letterMultiplier) {
        this.letterMultiplier = letterMultiplier;
        this.wordMultiplier = wordMultiplier;
        this.location = new Coords(c.getX(), c.getY());
    }

    public static Cell makeTripleWord(Coords c) {
        return new Cell(c, 3, 1);
    }

    public static Cell makeDoubleWord(Coords c) {
        return new Cell(c, 2, 1);
    }

    public static Cell makeTripleLetter(Coords c) {
        return new Cell(c, 1, 3);
    }

    public static Cell makeDoubleLetter(Coords c) {
        return new Cell(c, 1, 2);
    }

    public boolean containsLetter() {
        return letter != null;
    }

    public void putLetter(Letter letter) {
        this.letter = letter;
    }

    public Letter getLetter() {
        return letter;
    }

    public void removeLetter() {
        letter = null;
    }

    public int getWordMultiplier() {
        return this.wordMultiplier;
    }

    public int getLetterMultiplier() {
        return this.letterMultiplier;
    }

    public Coords getLocation() {
        return this.location;
    }

}
