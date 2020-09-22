package scrabble.board;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import scrabble.dictionnary.Dictionary;
import scrabble.dictionnary.Languages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static scrabble.dictionnary.FrenchLetterDistribution.*;
import static scrabble.dictionnary.FrenchLetterDistribution.O;

@RunWith(BlockJUnit4ClassRunner.class)
public class BoardTest {

    private Board board;
    private static Dictionary DICO;


    @BeforeClass
    public static void beforeClass() {
        DICO = Languages.FRENCH.getDictionary();
        DICO.load();
    }

    @Before
    public void before() {
        board = new Board();
    }

    @Test
    public void putTwoLetterAtSamePlaceShouldFail() {
        assertThat(board.placeLetter(A, 0, 0), is(true));
        assertThat(board.placeLetter(B, 1, 0), is(true));
        assertThat(board.placeLetter(C, 0, 0), is(false));
    }

    @Test
    public void validWord() {
        board.placeLetter(V, 0, 0);
        board.placeLetter(E, 1, 0);
        board.placeLetter(L, 2, 0);
        board.placeLetter(O, 3, 0);

        assertThat(board.validateLastWordPlayed(DICO), is(24));
    }

    @Test
    public void validScrabble() {
        board.placeLetter(C, 0, 0);
        board.placeLetter(O, 1, 0);
        board.placeLetter(M, 2, 0);
        board.placeLetter(P, 3, 0);
        board.placeLetter(T, 4, 0);
        board.placeLetter(E, 5, 0);
        board.placeLetter(S, 6, 0);

        assertThat(board.validateLastWordPlayed(DICO), is(45));
    }

    @Test
    public void notContiguousWord() {
        board.placeLetter(V, 1, 1);
        board.placeLetter(E, 2, 1);
        board.placeLetter(L, 4, 1);
        board.placeLetter(O, 5, 1);

        assertThat(board.validateLastWordPlayed(DICO), is(-1));
    }

    @Test
    public void notAlignedWord() {
        board.placeLetter(V, 1, 1);
        board.placeLetter(E, 2, 1);
        board.placeLetter(L, 3, 2);
        board.placeLetter(O, 4, 2);

        assertThat(board.validateLastWordPlayed(DICO), is(-1));
    }

    @Test
    public void wordNotInDictionary() {
        board.placeLetter(X, 1, 1);
        board.placeLetter(X, 2, 1);
        board.placeLetter(X, 3, 2);
        board.placeLetter(X, 4, 2);

        assertThat(board.validateLastWordPlayed(DICO), is(-1));
    }

    @Test
    public void perpendicularValidWord() {
        board.placeLetter(V, 1, 1);
        board.placeLetter(E, 2, 1);
        board.placeLetter(L, 3, 1);
        board.placeLetter(O, 4, 1);
        board.validateLastWordPlayed(DICO);
        board.placeLetter(E, 5, 5);
        board.placeLetter(T, 6, 5);
        board.placeLetter(E, 7, 5);
        board.validateLastWordPlayed(DICO);

        board.placeLetter(S, 5, 1);
        board.placeLetter(A, 5, 2);
        board.placeLetter(U, 5, 3);
        board.placeLetter(T, 5, 4);
        board.placeLetter(R, 5, 6);

        assertThat(board.validateLastWordPlayed(DICO), is(18));
    }

    @Test
    public void perpendicularInvalidWord() {
        board.placeLetter(V, 1, 1);
        board.placeLetter(E, 2, 1);
        board.placeLetter(L, 3, 1);
        board.placeLetter(O, 4, 1);
        board.validateLastWordPlayed(DICO);
        board.placeLetter(E, 5, 5);
        board.placeLetter(T, 6, 5);
        board.placeLetter(E, 7, 5);
        board.validateLastWordPlayed(DICO);

        board.placeLetter(R, 5, 1);
        board.placeLetter(O, 5, 2);
        board.placeLetter(U, 5, 3);
        board.placeLetter(T, 5, 4);

        assertThat(board.validateLastWordPlayed(DICO), is(-1));
    }

    @Test
    public void removeLetterFromValidatedWordShouldFail() {
        board.placeLetter(V, 1, 1);
        board.placeLetter(E, 2, 1);
        board.placeLetter(L, 3, 1);
        board.placeLetter(O, 4, 1);
        board.validateLastWordPlayed(DICO);

        assertThat(board.removeLetter(1, 1), is(false));
    }

    @Test
    public void removeLetterFromNotValidatedWordShouldSuccess() {
        board.placeLetter(V, 1, 1);
        board.placeLetter(E, 2, 1);
        board.placeLetter(L, 3, 1);
        board.placeLetter(O, 4, 1);

        assertThat(board.removeLetter(1, 1), is(true));
    }
}
