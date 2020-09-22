package scrabble.dictionnary;

public enum Languages {

    FRENCH (new FrenchLetterDistribution(), new Dictionary("french.txt")),
    ENGLISH (new EnglishLetterDistribution(), new Dictionary("english.txt"));

    private final LetterDistribution letterDistribution;
    private final Dictionary dictionary;

    Languages(LetterDistribution letterDistribution, Dictionary dictionary) {
        this.letterDistribution = letterDistribution;
        this.dictionary = dictionary;
    }

    public LetterDistribution getLetterDistribution() {
        return letterDistribution;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }
}
