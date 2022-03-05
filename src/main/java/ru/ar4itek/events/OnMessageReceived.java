package ru.ar4itek.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ru.ar4itek.main.CommandManager;
import ru.ar4itek.main.Config;

public class OnMessageReceived extends ListenerAdapter {

    private final CommandManager manager = new CommandManager();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message msg = event.getMessage();
        String contentMsg = msg.getContentRaw();
        MessageChannel channel = event.getChannel();
        String guildId = msg.getGuild().getId();
        String authorId = msg.getAuthor().getId();

        final String PREFIX = Config.get("token");
        if (event.getMessage().getContentRaw().equals(String.format("<@!%s>", event.getJDA().getSelfUser().getId()))) {
            event.getMessage().reply(String.format("Hi, my prefix: \"%s\"", PREFIX)).queue();
        }
        assert PREFIX != null;
        if (contentMsg.startsWith(PREFIX)) {
            manager.handle(event, PREFIX);
            return;
        }
    }

}
