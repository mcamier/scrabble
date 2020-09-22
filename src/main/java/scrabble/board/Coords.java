package scrabble.board;

import java.util.Objects;

public class Coords {
    private int x;
    private int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void stepPrevious(WordAlignment direction) {
        if(WordAlignment.HORIZONTAL.equals(direction)) {
            this.x--;
        } else if(WordAlignment.VERTICAL.equals(direction)) {
            this.y--;
        }
    }

    public void stepNext(WordAlignment direction) {
        if(WordAlignment.HORIZONTAL.equals(direction)) {
            this.x++;
        } else if(WordAlignment.VERTICAL.equals(direction)) {
            this.y++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coords coords = (Coords) o;
        return x == coords.x &&
                y == coords.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Coords from(int flatGridLocation) {
        return new Coords(flatGridLocation % 15, flatGridLocation / 15);
    }

    public static int to(Coords coords) {
        return 15 * coords.y + coords.x;
    }

    public static int to(int x, int y) {
        return 15 * y + x;
    }
}