package scrabble.board;

import scrabble.core.Letter;
import scrabble.core.Word;
import scrabble.dictionnary.Dictionary;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.summingInt;
import static scrabble.board.WordAlignment.HORIZONTAL;

public class Board {

    private final static Set<Coords> TRIPLE_WORD_SCORE = Set.of(
            new Coords(0,0),
            new Coords(7,0),
            new Coords(14,0),
            new Coords(0,7),
            new Coords(14, 7),
            new Coords(0,14),
            new Coords(7,14),
            new Coords(14,14)
    );

    private final static Set<Coords> DOUBLE_WORD_SCORE = Set.of(
            new Coords(1, 1),
            new Coords(2, 2),
            new Coords(3, 3),
            new Coords(4, 4),
            new Coords(7, 7),
            new Coords(10, 4),
            new Coords(11, 3),
            new Coords(12, 2),
            new Coords(13, 1),
            new Coords(1, 13),
            new Coords(2, 12),
            new Coords(3, 11),
            new Coords(4, 10),
            new Coords(10, 10),
            new Coords(11, 11),
            new Coords(12, 12),
            new Coords(13, 13)
    );

    private final static Set<Coords> DOUBLE_LETTER_SCORE = Set.of(
            new Coords(3,0),
            new Coords(11,0),
            new Coords(6,2),
            new Coords(8,2),
            new Coords(0,3),
            new Coords(7,3),
            new Coords(14,3),
            new Coords(2,6),
            new Coords(6,6),
            new Coords(8,6),
            new Coords(12,6),
            new Coords(3,7),
            new Coords(11,7),
            new Coords(3,14),
            new Coords(11,14),
            new Coords(6,12),
            new Coords(8,12),
            new Coords(0,11),
            new Coords(7,11),
            new Coords(14,11),
            new Coords(2,8),
            new Coords(6,8),
            new Coords(8,8),
            new Coords(12,8)
    );

    private final static Set<Coords> TRIPLE_LETTER_SCORE = Set.of(
            new Coords(5, 1),
            new Coords(9,1),
            new Coords(1,5),
            new Coords(5,5),
            new Coords(9,5),
            new Coords(13,5),
            new Coords(1,9),
            new Coords(5,9),
            new Coords(9,9),
            new Coords(13, 9),
            new Coords(5, 13),
            new Coords(9,13)
    );

    private static final int GRID_SIZE = 225;
    private static final int RACK_SIZE = 7;

    private final List<Cell> grid;
    private List<Cell> lastWordPlayed;

    public Board() {
        this.grid = new ArrayList<Cell>(GRID_SIZE);
        this.lastWordPlayed = new ArrayList<Cell>(RACK_SIZE);

        initializeGrid();
    }

    /**
     *
     * @param letter
     * @param x
     * @param y
     * @return
     */
    public boolean placeLetter(Letter letter, int x, int y) {
        if (this.lastWordPlayed.size() == RACK_SIZE) {
            // max letter already played for a player's turn
            return false;
        }

        if (getCellAt(x, y).containsLetter()) {
            // letter already placed on this cell
            return false;
        }

        getCellAt(x, y).putLetter(letter);
        this.lastWordPlayed.add(getCellAt(x, y));

        return true;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean removeLetter(int x, int y) {
        if (this.lastWordPlayed.removeIf(c -> c.getLocation().getX() == x && c.getLocation().getY() == y) ) {
            getCellAt(x, y).removeLetter();
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public int validateLastWordPlayed(Dictionary dictionary) {
        // validate all played letters are correctly aligned
        WordAlignment wordAlignment = getAlignmentOfCandidateWord();
        if ( !WordAlignment.isValid(wordAlignment) ) {
            return -1;
        }

        // find composed word taking into account letters already put on the board
        // validate it with dictionnary
        Word word = findWord(wordAlignment, this.lastWordPlayed.get(0).getLocation().getX(), this.lastWordPlayed.get(0).getLocation().getY());
        if (!dictionary.contains(word.getValue())) {
            return -1;
        }

        // find perpendiculare words formed
        List<Word> perpendicularWords = findPerpendicularWords(wordAlignment, this.lastWordPlayed);
        // validate perpendicular words with dictionary
        if (!perpendicularWords.stream().allMatch(w -> dictionary.contains(w.getValue())) ) {
            return -1;
        }

        this.lastWordPlayed.clear();

        return  word.getScore() +
                perpendicularWords.stream().map(Word::getScore).mapToInt(Integer::intValue).sum() +
                ((this.lastWordPlayed.size() == 7) ? 50 : 0);
    }

    /**
     *
     * @param direction
     * @param x
     * @param y
     * @return
     */
    public Word findWord(WordAlignment direction, int x, int y) {
        int wordScoreMultiplier = 1;
        int wordScore = 0;

        if(!WordAlignment.isValid(direction)) {
            throw new RuntimeException("NONE is not a valid direction");
        }

        LinkedList<Character> wordToValidate = new LinkedList<Character>();

        Coords start = new Coords(x, y);
        Coords end = new Coords(x, y);
        Coords l = new Coords(x, y);

        Cell c = getCellAt(l.getX(), l.getY());
        wordToValidate.add(c.getLetter().getValue());
        wordScore += c.getLetter().getScore() * c.getLetterMultiplier();
        wordScoreMultiplier *= c.getWordMultiplier();

        // expand to the end of connected letters in the direction of the word
        Cell cell;
        while(null != (cell = getNextLetter(direction, l.getX(), l.getY())) && cell.getLetter() != null) {
            end = new Coords(l.getX(), l.getY());
            wordToValidate.addLast(cell.getLetter().getValue());
            wordScore += isNotYetValidatedCell(cell.getLocation()) ? cell.getLetter().getScore() * cell.getLetterMultiplier() : cell.getLetter().getScore();
            wordScoreMultiplier *= isNotYetValidatedCell(cell.getLocation()) ? cell.getWordMultiplier() : 1;

            l.stepNext(direction);
        }

        // expand to the begin connected letters in the direction of the word
        l = new Coords(x, y);
        while(null != (cell = getPreviousLetter(direction, l.getX(), l.getY())) && cell.getLetter() != null) {
            start = new Coords(l.getX(), l.getY());
            wordToValidate.addFirst(cell.getLetter().getValue());
            wordScore += isNotYetValidatedCell(cell.getLocation()) ? cell.getLetter().getScore() * cell.getLetterMultiplier() : cell.getLetter().getScore();
            wordScoreMultiplier *= isNotYetValidatedCell(cell.getLocation()) ? cell.getWordMultiplier() : 1;

            l.stepPrevious(direction);
        }

        int score = wordScore * wordScoreMultiplier;

        return new Word(wordToValidate.stream().map(String::valueOf).collect(joining()), start, end, score);
    }

    private List<Word> findPerpendicularWords(WordAlignment direction, List<Cell> coordsToScan) {
        if(!WordAlignment.isValid(direction))
            throw new RuntimeException("NONE is not a valid direction");

        return coordsToScan.stream()
                .map(coords -> findWord(WordAlignment.getPerpendicular(direction), coords.getLocation().getX(), coords.getLocation().getY()))
                .filter(word -> word.getValue().length() >= 2)
                .collect(Collectors.toList());
    }

    private WordAlignment getAlignmentOfCandidateWord() {
        if (lastWordPlayed.size() < 1) {
            return WordAlignment.NONE;
        } else if (lastWordPlayed.size() < 2) {
            return HORIZONTAL;
        }

        if (lastWordPlayed.stream().allMatch(xy -> xy.getLocation().getX() == lastWordPlayed.get(0).getLocation().getX()) ) {
            return WordAlignment.VERTICAL;
        }
        if (lastWordPlayed.stream().allMatch(xy -> xy.getLocation().getY() == lastWordPlayed.get(0).getLocation().getY()) ) {
            return HORIZONTAL;
        }

        return WordAlignment.NONE;
    }

    private void setCellAt(Coords coords, Cell cell) {
        grid.set(Coords.to(coords), cell);
    }

    private Cell getCellAt(int x, int y) {
        return grid.get(Coords.to(x, y));
    }

    private Cell getNextLetter(WordAlignment direction, int x, int y) {
        if(!WordAlignment.isValid(direction))
            throw new RuntimeException("NONE is not a valid direction");

        int nextIndex = -1;
        if (HORIZONTAL.equals(direction)) {
            if (x + 1 <= 14) {
                nextIndex = y * 15 + x + 1;
            }
        } else if (WordAlignment.VERTICAL.equals(direction)) {
            if (y + 1 <= 14) {
                nextIndex =  (y + 1) * 15 + x;
            }
        }

        if(nextIndex == -1)
            return null;

        Cell nextCell = grid.get(nextIndex);

        return nextCell;
    }

    private Cell getPreviousLetter(WordAlignment direction, int x, int y) {
        if(!WordAlignment.isValid(direction))
            throw new RuntimeException("NONE is not a valid direction");

        int nextIndex = -1;
        if (HORIZONTAL.equals(direction)) {
            if (x - 1 >= 0) {
                nextIndex = y * 15 + x - 1;
            }
        } else if (WordAlignment.VERTICAL.equals(direction)) {
            if (y - 1 >= 0) {
                nextIndex =  (y - 1) * 15 + x;
            }
        }

        if(nextIndex == -1)
            return null;

        Cell nextCell = grid.get(nextIndex);

        return nextCell;
    }

    private void initializeGrid() {
        for(int i = 0 ; i < GRID_SIZE ; i++) {
            grid.add(new Cell(Coords.from(i)));
        }

        TRIPLE_LETTER_SCORE.forEach(c -> setCellAt(c, Cell.makeTripleLetter(c)));
        DOUBLE_LETTER_SCORE.forEach(c -> setCellAt(c, Cell.makeDoubleLetter(c)));

        TRIPLE_WORD_SCORE.forEach(c -> setCellAt(c, Cell.makeTripleWord(c)));
        DOUBLE_WORD_SCORE.forEach(c -> setCellAt(c, Cell.makeDoubleWord(c)));
    }

    public boolean isNotYetValidatedCell(Coords coords) {
        return this.lastWordPlayed.stream().anyMatch(cell -> cell.getLocation().getX() == coords.getX() && cell.getLocation().getY() == coords.getY());
    }

}
