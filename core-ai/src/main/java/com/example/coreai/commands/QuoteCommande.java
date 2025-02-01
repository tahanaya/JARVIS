package com.example.coreai.commands;

import com.example.coreai.Command;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuoteCommande implements Command {

    private static final List<String> QUOTES = List.of(
            // Reverend Insanity
            "The strong eat the weak. This is the law of the jungle, the rule of the world. – Fang Yuan",
            "There is no fairness in this world, only strength. – Fang Yuan",
            "To achieve a goal, one must be willing to sacrifice. – Fang Yuan",
            "A person who does not struggle will never be free. – Fang Yuan",
            "Power is not given, it is taken. – Fang Yuan",

            // Lord of Mysteries
            "Madness is not an escape from reality. It is a deeper understanding of it. – Klein Moretti",
            "There is no absolute good or evil, only the consequences of one’s choices. – Klein Moretti",
            "Secrets are the foundation of power. – Klein Moretti",
            "Control is an illusion, but influence is real. – Klein Moretti",
            "Destiny is not written in stone. It is carved with blood and will. – Klein Moretti",

            // The Beginning After The End
            "Strength means nothing if it cannot protect the ones you love. – Arthur Leywin",
            "Power alone does not make a king; wisdom and responsibility do. – Arthur Leywin",
            "A true warrior does not fight to destroy, but to protect. – Arthur Leywin",
            "The past does not define you. It only teaches you. – Arthur Leywin",
            "Losing is not the end; it is the lesson before victory. – Arthur Leywin",

            // Against the Gods
            "In this world, power is everything. Without it, you are nothing. – Yun Che",
            "A man who does not take revenge is not a man. – Yun Che",
            "Heaven may be merciless, but I am not. – Yun Che",
            "Those who betray me shall regret it for eternity. – Yun Che",
            "If the heavens want me dead, I shall destroy the heavens! – Yun Che",

            // Overlord
            "A king must be ruthless to protect his kingdom. – Ainz Ooal Gown",
            "Mercy is a privilege of the strong, not the weak. – Ainz Ooal Gown",
            "Loyalty is not given, it is earned. – Ainz Ooal Gown",
            "Power is meaningless if you don’t know how to use it. – Ainz Ooal Gown",
            "Fear is a tool. Respect is earned through it. – Ainz Ooal Gown",

            // Warlock of the Magus World
            "The world belongs to those who take it, not those who wait. – Leylin Farlier",
            "Knowledge is power, and power is everything. – Leylin Farlier",
            "Morality is a chain for the weak. The strong create their own rules. – Leylin Farlier",
            "Survival is the only justice in this world. – Leylin Farlier",
            "Ambition is the fuel of evolution. – Leylin Farlier",

            // Solo Leveling
            "The only one I can trust is myself. – Sung Jin-Woo",
            "I am not a hero. I am a hunter. – Sung Jin-Woo",
            "The weak have no right to pity the strong. – Sung Jin-Woo",
            "If I cannot become the strongest, then I will die trying. – Sung Jin-Woo",
            "True power comes from facing death and overcoming it. – Sung Jin-Woo",

            // Shadow Slave
            "Victory belongs to those who refuse to kneel. – Sunny",
            "Pain is a teacher, not an enemy. – Sunny",
            "Every choice carries a price. Are you willing to pay it? – Sunny",
            "Fate does not choose its champions; they carve their own path. – Sunny",
            "The shadows whisper, but only the strong listen. – Sunny",

            // I Shall Seal the Heavens
            "If the heavens block my path, then I shall break the heavens! – Meng Hao",
            "The Dao is endless, and so is my journey. – Meng Hao",
            "A true cultivator never bows his head. – Meng Hao",
            "No one decides my fate but me. – Meng Hao",
            "Wealth, longevity, power – these are mere stepping stones on the path to true strength. – Meng Hao"
    );

    @Override
    public String execute(String[] args) {
        int index = ThreadLocalRandom.current().nextInt(QUOTES.size());
        return QUOTES.get(index);
    }
}
