package scrabble.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(BlockJUnit4ClassRunner.class)
public class CoordsTest {

    @Test
    public void CoordsFrom() {
        Coords result = Coords.from(224);
        assertThat(result.getX(), equalTo(14));
        assertThat(result.getY(), equalTo(14));

        result = Coords.from(0);
        assertThat(result.getX(), equalTo(0));
        assertThat(result.getY(), equalTo(0));

        result = Coords.from(112);
        assertThat(result.getX(), equalTo(7));
        assertThat(result.getY(), equalTo(7));
    }

    @Test
    public void CoordsTo() {
        assertThat(Coords.to(new Coords(0, 0)), equalTo(0));
        assertThat(Coords.to(new Coords(7, 7)), equalTo(112));
        assertThat(Coords.to(new Coords(14, 14)), equalTo(224));
    }

    @Test
    public void CoordsTo2() {
        assertThat(Coords.to(0, 0), equalTo(0));
        assertThat(Coords.to(7, 7), equalTo(112));
        assertThat(Coords.to(14, 14), equalTo(224));
    }

}
