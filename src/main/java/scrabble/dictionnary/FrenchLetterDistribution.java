package scrabble.dictionnary;

import scrabble.core.Letter;

import java.util.HashMap;
import java.util.Map;

public class FrenchLetterDistribution implements LetterDistribution {

    public final static Letter JOKER = new Letter(' ', 0);
    public final static Letter A = new Letter('a', 1);
    public final static Letter B = new Letter('b', 3);
    public final static Letter C = new Letter('c', 3);
    public final static Letter D = new Letter('d', 2);
    public final static Letter E = new Letter('e', 1);
    public final static Letter F = new Letter('f', 4);
    public final static Letter G = new Letter('g', 2);
    public final static Letter H = new Letter('h', 4);
    public final static Letter I = new Letter('i', 1);
    public final static Letter J = new Letter('j', 8);
    public final static Letter K = new Letter('k', 10);
    public final static Letter L = new Letter('l', 1);
    public final static Letter M = new Letter('m', 2);
    public final static Letter N = new Letter('n', 1);
    public final static Letter O = new Letter('o', 1);
    public final static Letter P = new Letter('p', 3);
    public final static Letter Q = new Letter('q', 8);
    public final static Letter R = new Letter('r', 1);
    public final static Letter S = new Letter('s', 1);
    public final static Letter T = new Letter('t', 1);
    public final static Letter U = new Letter('u', 1);
    public final static Letter V = new Letter('v', 4);
    public final static Letter W = new Letter('w', 10);
    public final static Letter X = new Letter('x', 10);
    public final static Letter Y = new Letter('y', 10);
    public final static Letter Z = new Letter('z', 10);

    private final Map<Letter, Integer> letters;

    public FrenchLetterDistribution() {
        this.letters = new HashMap<Letter, Integer>(26);
        letters.put(JOKER, 2);
        letters.put(A, 9);
        letters.put(B, 2);
        letters.put(C, 2);
        letters.put(D, 3);
        letters.put(E, 15);
        letters.put(F, 2);
        letters.put(G, 2);
        letters.put(H, 2);
        letters.put(I, 8);
        letters.put(J, 1);
        letters.put(K, 1);
        letters.put(L, 5);
        letters.put(M, 3);
        letters.put(N, 6);
        letters.put(O, 6);
        letters.put(P, 2);
        letters.put(Q, 1);
        letters.put(R, 6);
        letters.put(S, 6);
        letters.put(T, 6);
        letters.put(U, 6);
        letters.put(V, 2);
        letters.put(W, 1);
        letters.put(X, 1);
        letters.put(Y, 1);
        letters.put(Z, 1);
    }

    @Override
    public Integer getLetterAmount() {
        return letters.values().stream().mapToInt(Integer::intValue).sum();
    }
    @Override
    public Map<Letter, Integer> getLetters() {
        return letters;
    }
}
