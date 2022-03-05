package ru.ar4itek.commands;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ru.ar4itek.main.CommandManager;
import ru.ar4itek.main.Config;

import java.util.List;

public class Help implements ICommand {

    private final CommandManager manager;

    public Help(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(List<String> args, MessageReceivedEvent ctx) {
        TextChannel channel = ctx.getTextChannel();
        if (args.isEmpty()) {
            StringBuilder builder = new StringBuilder();

            builder.append("List of commands\n");

            manager.getCommands().stream().map(ICommand::getName).forEach(
                    (it) -> builder.append('`').append(Config.get("prefix")).append(it).append("`\n")
            );

            channel.sendMessage(builder.toString()).queue();
            return;
        }

        String search = args.get(0);
        ICommand command = manager.getCommand(search);

        if (command == null) {
            channel.sendMessage("Nothing found for " + search).queue();
            return;
        }

        channel.sendMessage(command.getHelp()[1]).queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String[] getHelp() {
        return new String[]{"Shows the bot commands\n" +
                "`help <command>`"};
    }

    @Override
    public List<String> getAliases() {
        return List.of("commands", "cmds", "commandlist");
    }
}
