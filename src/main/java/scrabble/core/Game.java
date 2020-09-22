package scrabble.core;

import scrabble.board.Board;
import scrabble.dictionnary.Languages;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {

    private final static int RACK_SIZE = 7;

    private final Languages lang;
    private final List<Player> players;

    private GameStatus status;
    private Set<String> playerNames;
    private Board board;
    private Bag bag;
    private LinkedList<PlayerMove> playerMoves;

    public Game(Languages lang, Set<String> playerNames) {
        this.status = GameStatus.NOT_STARTED;
        this.lang = lang;
        this.players = playerNames.stream()
                .map(playerName -> new Player(playerName, RACK_SIZE))
                .collect(Collectors.toList());
        this.board = new Board();
        this.bag = new Bag(this.lang.getLetterDistribution());
        this.playerMoves = new LinkedList<PlayerMove>();
    }

    public void start() {
        this.status = GameStatus.STARTED;
        this.lang.getDictionary().load();
        // ...
    }
}
