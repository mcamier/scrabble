package scrabble.dictionnary;

import scrabble.core.Letter;

import java.util.HashMap;
import java.util.Map;

public class EnglishLetterDistribution implements LetterDistribution {

    private final static Letter JOKER = new Letter(' ', 0);
    private final static Letter A = new Letter('a', 1);
    private final static Letter B = new Letter('b', 3);
    private final static Letter C = new Letter('c', 3);
    private final static Letter D = new Letter('d', 2);
    private final static Letter E = new Letter('e', 1);
    private final static Letter F = new Letter('f', 4);
    private final static Letter G = new Letter('g', 2);
    private final static Letter H = new Letter('h', 4);
    private final static Letter I = new Letter('i', 1);
    private final static Letter J = new Letter('j', 8);
    private final static Letter K = new Letter('k', 5);
    private final static Letter L = new Letter('l', 1);
    private final static Letter M = new Letter('m', 3);
    private final static Letter N = new Letter('n', 1);
    private final static Letter O = new Letter('o', 1);
    private final static Letter P = new Letter('p', 3);
    private final static Letter Q = new Letter('q', 10);
    private final static Letter R = new Letter('r', 1);
    private final static Letter S = new Letter('s', 1);
    private final static Letter T = new Letter('t', 1);
    private final static Letter U = new Letter('u', 1);
    private final static Letter V = new Letter('v', 4);
    private final static Letter W = new Letter('w', 4);
    private final static Letter X = new Letter('x', 8);
    private final static Letter Y = new Letter('y', 4);
    private final static Letter Z = new Letter('z', 10);

    private final Map<Letter, Integer> letters;

    public EnglishLetterDistribution() {
        this.letters = new HashMap<Letter, Integer>(26);
        letters.put(JOKER, 2);
        letters.put(A, 9);
        letters.put(B, 2);
        letters.put(C, 2);
        letters.put(D, 4);
        letters.put(E, 12);
        letters.put(F, 2);
        letters.put(G, 3);
        letters.put(H, 2);
        letters.put(I, 9);
        letters.put(J, 1);
        letters.put(K, 1);
        letters.put(L, 4);
        letters.put(M, 2);
        letters.put(N, 6);
        letters.put(O, 8);
        letters.put(P, 2);
        letters.put(Q, 1);
        letters.put(R, 6);
        letters.put(S, 4);
        letters.put(T, 6);
        letters.put(U, 4);
        letters.put(V, 2);
        letters.put(W, 2);
        letters.put(X, 1);
        letters.put(Y, 2);
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
