package com.example.coreai;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream("C:\\Users\\utente\\ProjetHadad\\testRepo\\AI Holographic Assistant\\config.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            System.err.println("Could not load config.properties: " + ex.getMessage());
            // Optionally, you can exit or handle the error as needed.
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
