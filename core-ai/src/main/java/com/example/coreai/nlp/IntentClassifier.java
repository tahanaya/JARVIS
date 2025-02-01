package com.example.coreai.nlp;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.tokenize.SimpleTokenizer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IntentClassifier {

    private final DocumentCategorizerME categorizer;

    public IntentClassifier(String modelPath) throws IOException {
        try (InputStream modelStream = new FileInputStream(modelPath)) {
            DoccatModel model = new DoccatModel(modelStream);
            this.categorizer = new DocumentCategorizerME(model);
        }
    }

    public String classify(String text) {
        String[] tokens = SimpleTokenizer.INSTANCE.tokenize(text);
        double[] outcomes = categorizer.categorize(tokens);
        return categorizer.getBestCategory(outcomes);
    }
}
