package com.example.coreai;



import javazoom.jl.player.Player;

import java.io.*;
        import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMusicPlayer {

    private static final String PLAYLIST_FOLDER = "music/Playlist";
    private static final Random RANDOM = new Random();

    /**
     * Plays a random MP3 file from the playlist folder in src/main/resources/music/Playlist.
     */
    public void playRandom() throws IOException {
        // 1. List all mp3 files from the resources folder
        List<String> mp3Resources = listMp3Resources(PLAYLIST_FOLDER);

        if (mp3Resources.isEmpty()) {
            System.out.println("No MP3 files found in " + PLAYLIST_FOLDER);
            return;
        }

        // 2. Pick a random file from the list
        String chosenFile = mp3Resources.get(RANDOM.nextInt(mp3Resources.size()));
        System.out.println("Chosen track: " + chosenFile);

        // 3. Load the resource as a stream from the classpath
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(chosenFile)) {
            if (in == null) {
                System.out.println("Could not load resource: " + chosenFile);
                return;
            }
            try (BufferedInputStream bis = new BufferedInputStream(in)) {
                // 4. Create a new JLayer Player and play
                Player player = new Player(bis);
                System.out.println("Playing...");
                player.play();
                System.out.println("Done playing.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Lists all MP3 resources under the given folder path (within resources).
     */
    private List<String> listMp3Resources(String folder) {
        List<String> result = new ArrayList<>();
        // getResource() on the folder should give the folder as a URL
        URL folderUrl = getClass().getClassLoader().getResource(folder);
        if (folderUrl == null) {
            System.out.println("Folder not found: " + folder);
            return result;
        }
        try {
            // Convert the folder URL to a File path if it's a file-based resource
            // But in some cases (e.g., jar), you'd need a different approach
            File folderFile = new File(folderUrl.toURI());
            File[] files = folderFile.listFiles((dir, name) -> name.toLowerCase().endsWith(".mp3"));
            if (files != null) {
                for (File file : files) {
                    // We'll store the relative path within resources
                    String relativePath = folder + "/" + file.getName();
                    result.add(relativePath);
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

    // A quick test runner
    public static void main(String[] args) throws IOException {
        RandomMusicPlayer player = new RandomMusicPlayer();
        player.playRandom();
    }
}
