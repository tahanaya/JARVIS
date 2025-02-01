package com.example;



import org.vosk.Model;
import org.vosk.Recognizer;
import org.vosk.LibVosk;
import org.vosk.LogLevel;

import javax.sound.sampled.*;
import java.io.IOException;

public class VoskMicrophoneRecognizer {

    public static void main(String[] args) {
        LibVosk.setLogLevel(LogLevel.INFO);

        // Path to your Vosk model folder
        String modelPath = "C:\\Users\\utente\\ProjetHadad\\testRepo\\AI Holographic Assistant\\voice-processing\\src\\main\\resources\\vosk-model-small-en-us-0.15\\vosk-model-small-en-us-0.15";

        try (Model model = new Model(modelPath)) {
            // 1) Open your microphone, typically 16 kHz, 16-bit, mono
            AudioFormat format = new AudioFormat(16000, 16, 1, true, false);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            if (!AudioSystem.isLineSupported(info)) {
                System.err.println("Microphone not supported");
                return;
            }
            TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(format);
            microphone.start();

            // 2) Create recognizer
            Recognizer recognizer = new Recognizer(model, 16000.0f);

            System.out.println("Start speaking... (Ctrl+C to stop)");
            byte[] buffer = new byte[4096];

            while (true) {
                int bytesRead = microphone.read(buffer, 0, buffer.length);
                if (bytesRead < 0) {
                    break;
                }
                boolean result = recognizer.acceptWaveForm(buffer, bytesRead);
                if (result) {
                    // If there's a final recognized text
                    System.out.println("Result: " + recognizer.getResult());
                } else {
                    // Partial result
                    System.out.println("Partial: " + recognizer.getPartialResult());
                }
            }

        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
