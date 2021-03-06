package ru.ar4itek.main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import ru.ar4itek.events.OnMessageReceived;

import javax.security.auth.login.LoginException;

/* author: ar4itek.ru */
public class Main {

    protected static JDA jda;
    protected static final String TOKEN = Config.get("token");

    public static void main(String[] args) throws LoginException, InterruptedException {
        if (TOKEN == null || TOKEN.length() == 0) {
            System.err.println("token no found.");
            System.exit(1);
        }
        login();
    }

    protected static void login() throws LoginException, InterruptedException {
        jda = JDABuilder.createDefault(TOKEN, GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new OnMessageReceived())
                .setActivity(Activity.competing(Config.get("prefix") + "help"))
                .setAutoReconnect(true)
                .build();
        jda.awaitReady();
    }

}
