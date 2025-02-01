package com.example.coreai.nlp;

import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class IntentTrainer {

    public static void trainModel(String trainingFilePath, String modelOutputPath) {
        try {
            // Prepare the input stream for the training data file
            InputStreamFactory inputStreamFactory =
                    new MarkableFileInputStreamFactory(new File(trainingFilePath));
            ObjectStream<String> lineStream = new PlainTextByLineStream(inputStreamFactory, "UTF-8");
            ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);

            // Set up training parameters
            TrainingParameters params = new TrainingParameters();
            params.put(TrainingParameters.ITERATIONS_PARAM, 100);
            params.put(TrainingParameters.CUTOFF_PARAM, 1);
            // Use "MAXENT" as the algorithm identifier for the maximum entropy algorithm.
            params.put(TrainingParameters.ALGORITHM_PARAM, "MAXENT");

            // Train the DoccatModel using the provided samples and parameters
            DoccatModel model = DocumentCategorizerME.train(
                    "en",
                    sampleStream,
                    params,
                    new DoccatFactory()
            );

            // Save the trained model to the specified output file
            try (FileOutputStream modelOut = new FileOutputStream(modelOutputPath)) {
                model.serialize(modelOut);
            }
            sampleStream.close();
            System.out.println("Model training complete. Model saved to " + modelOutputPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Quick test runner
    public static void main(String[] args) {
        String trainingFilePath = "core-ai/src/main/resources/intents.train";
        String modelOutputPath = "core-ai/src/main/resources/doccat-model.bin";
        trainModel(trainingFilePath, modelOutputPath);
    }
}
