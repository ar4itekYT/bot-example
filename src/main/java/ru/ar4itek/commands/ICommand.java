package ru.ar4itek.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.Arrays;
import java.util.List;

/* author: ar4itek.ru */
public interface ICommand {

    void handle(List<String> args, MessageReceivedEvent ctx);

    String getName();

    String[] getHelp();

    default List<String> getAliases(){
        return Arrays.asList();
    }

}