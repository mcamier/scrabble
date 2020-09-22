package scrabble.board;

public enum WordAlignment {

    HORIZONTAL,
    VERTICAL,
    NOT_CONTIGUOUS,
    NOT_ALIGNED,
    NONE;

    public static boolean isValid(WordAlignment alignment) {
        return VERTICAL.equals(alignment) || HORIZONTAL.equals(alignment);
    }

    public static WordAlignment from(Coords start, Coords end) {
        if(start.getX() == end.getX()) return VERTICAL;
        if(start.getY() == end.getY()) return HORIZONTAL;
        return NOT_ALIGNED;
    }

    public static WordAlignment getPerpendicular(WordAlignment wordAlignment) {
        if(VERTICAL.equals(wordAlignment)) return HORIZONTAL;
        if(HORIZONTAL.equals(wordAlignment)) return VERTICAL;
        return NONE;
    }
}
