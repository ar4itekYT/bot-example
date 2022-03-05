package ru.ar4itek.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.List;

/* author: ar4itek.ru */
public class Ping implements ICommand {

    @Override
    public void handle(List<String> args, MessageReceivedEvent ctx) {
        long msgTime = ctx.getMessage().getTimeCreated().toInstant().toEpochMilli();
        long curTime = System.currentTimeMillis();
        long time = curTime - msgTime;
        ctx.getMessage().reply(String.format("Pong! %d ms.", time)).queue();
    }

    @Override
    public String[] getHelp() {
        return new String[]{"Pong!",
                "`ping`"};
    }

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public List<String> getAliases() {
        return List.of("pong", "пинг", "понг");
    }

}
