package scrabble.dictionnary;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dictionary {

    private final String path;

    private Set<String> words;

    public Dictionary(String path) {
        this.path = path;
    }

    public void load() {
        if (words != null && !words.isEmpty()) {
            return;
        }

        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(this.path).toURI()))) {
            this.words = stream
                    .filter(line -> !line.contains(" ") && !line.contains("-") && line.length() <= 15)
                    .collect(Collectors.toSet());

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public boolean contains(String word) {
        if (words == null || words.isEmpty()) {
            load();
        }
        return this.words.contains(word);
    }
}
