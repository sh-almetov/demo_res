package com.example.demo_res;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CountWords {

    public Map<String, Integer> getWordsCount(Resource res) throws IOException {

        Map<String, Integer> wordCount = new HashMap<>();

        List<String> lines = Files.readAllLines(Paths.get(res.getURI()),
                StandardCharsets.UTF_8);

        for (String line : lines) {

            String[] words = line.split("\\s+");

            for (String word : words) {

                if (word.endsWith(".") || word.endsWith(",")) {

                    word = word.substring(0, word.length() - 1);
                }

                if (wordCount.containsKey(word)) {

                    wordCount.put(word, wordCount.get(word) + 1);

                } else {

                    wordCount.put(word, 1);
                }
            }
        }

        return wordCount;
    }
}