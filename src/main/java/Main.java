import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Random;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, IOException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String input = new String(Files.readAllBytes(Paths.get("token.txt")));
        String token = input;
        builder.setToken(token);
        builder.addEventListener(new Main());
        builder.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println("Message received from " +
                event.getAuthor().getName() + ": " +
                event.getMessage().getContentDisplay()
        );
        if(event.getAuthor().isBot()) {
            return;
        }
        if (event.getMessage().getContentRaw().equals("!asshelp")) {
            event.getChannel().sendMessage("Bot Functions: ").queue();
            event.getChannel().sendMessage("!ass        Summons ASS").queue();
            event.getChannel().sendMessage("!roll ndx   Rolls a dx n times").queue();
            //event.getChannel().sendMessage("Wait, that's all the bot can do?").queue();
            //event.getChannel().sendMessage("What a disappointing bot...").queue();
            //event.getChannel().sendMessage("Nai waa...").queue();
        }
        else if(event.getMessage().getContentRaw().equals("!ass")) {
            event.getChannel().sendMessage("ASS").queue();
        }

        else if (event.getMessage().getContentRaw().contains("!roll")){
            String rollin = event.getMessage().getContentRaw();
            String[] prep = rollin.split(" ");
            String diceinput = prep[1];
            if (diceinput.contains("d")) {
                String[] parts = diceinput.split("d");
                String numdie = parts[0];
                String dienum = parts[1];
                Random rand = new Random();

                int numofdie = Integer.parseInt(numdie);
                int dieofnum = Integer.parseInt(dienum);
                int sum = 0;
                for (int i = 1; i < numofdie+1; i++){
                    int dieresult = rand.nextInt(dieofnum)+1;
                    String printable = ("Result of roll " + i + " : " + dieresult).toString();
                    event.getChannel().sendMessage(printable).queue();
                    sum = sum + dieresult;
                }
                String finalprint = ("Sum of rolls: " + sum).toString();
                event.getChannel().sendMessage(finalprint).queue();
            }
            else {
                throw new IllegalArgumentException("Error: Dice not found");
            }
        }
        else if (event.getMessage().getContentRaw().contains("ass") || event.getMessage().getContentRaw().contains("Ass") || event.getMessage().getContentRaw().contains("ASS")) {
            event.getChannel().sendMessage("asses").queue();
        }
        else if (event.getMessage().getContentRaw().contains("warframe") || event.getMessage().getContentRaw().contains("Warframe") || event.getMessage().getContentRaw().contains("WARFRAME")) {
            event.getChannel().sendMessage("Blookh doesn't have a PROBLEM!").queue();
        }
    }



}
