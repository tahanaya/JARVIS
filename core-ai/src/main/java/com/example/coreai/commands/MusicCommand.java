package com.example.coreai.commands;

import com.example.coreai.Command;
import com.example.coreai.RandomMusicPlayer;
import java.io.IOException;

public class MusicCommand implements Command {

    private static final RandomMusicPlayer MUSIC_PLAYER = new RandomMusicPlayer();

    @Override
    public String execute(String[] args) {
        // maybe parse further sub-commands (play, stop, etc.) if needed
        try {
            MUSIC_PLAYER.playRandom();
            return "Now playing a random track from the playlist.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error playing music: " + e.getMessage();
        }
    }
}
