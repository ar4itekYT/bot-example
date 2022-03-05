package ru.ar4itek.main;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private static final Dotenv dotenv = Dotenv.configure().filename("config").load();

    public static String get(String key) {
        return dotenv.get(key.toUpperCase());
    }

}
