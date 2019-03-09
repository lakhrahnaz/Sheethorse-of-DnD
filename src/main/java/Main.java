import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import javax.imageio.ImageIO;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static List<String> results;
    //public static BufferedImage img = null;
    public static Path primed = Paths.get("PrimedChamber.png");
    public static void main(String[] args) throws LoginException, IOException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String input = new String(Files.readAllBytes(Paths.get("token.txt")));
        String token = input;
        results = Files.readAllLines(Paths.get("Unearthed_metronome_table.txt"));

        /*try {
            img = ImageIO.read(new File("PrimedChamber.png"));
        } catch (IOException e) {
        }*/
        //primed = Files.;
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
        if (event.getAuthor().isBot()) {
            return;
        }
        if (event.getMessage().getContentRaw().equals("!asshelp")) {
            event.getChannel().sendMessage("Bot Functions: ").queue();
            event.getChannel().sendMessage("!ass        Summons ASS").queue();
            event.getChannel().sendMessage("!roll ndx   Rolls a dx n times").queue();
            event.getChannel().sendMessage("!ur         Displays the link to the unofficial rules").queue();
            event.getChannel().sendMessage("!die        Matt-magic command, nobody else should use this to avoid spoilers").queue();
            //event.getChannel().sendMessage("Wait, that's all the bot can do?").queue();
            //event.getChannel().sendMessage("What a disappointing bot...").queue();
            //event.getChannel().sendMessage("Nai waa...").queue();

        } else if (event.getMessage().getContentRaw().equals("!ass")) {
            event.getChannel().sendMessage("ASS").queue();

        } else if (event.getMessage().getContentRaw().contains("!roll")) {
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
                for (int i = 1; i < numofdie + 1; i++) {
                    int dieresult = rand.nextInt(dieofnum) + 1;
                    String printable = ("Result of roll " + i + " : " + dieresult).toString();
                    event.getChannel().sendMessage(printable).queue();
                    sum = sum + dieresult;
                }
                String finalprint = ("Sum of rolls: " + sum).toString();
                event.getChannel().sendMessage(finalprint).queue();
            } else {
                throw new IllegalArgumentException("Error: Dice not found");
            }

        } else if (event.getMessage().getContentRaw().contains("!die")/*&&event.getMessage().getAuthor().getName() == "hullbreach"*/) {

            Random mattrand = new Random();
            int rollchicken = mattrand.nextInt(20) + 1;
            int outcome = 0;
            if (rollchicken == 1) {
                outcome = mattrand.nextInt(100) + 1;
            }
            if (outcome == 0) {
                event.getChannel().sendMessage("Rolled a " + rollchicken + ". Effect failed to occur.").queue();
            } else {
                String response = results.get(outcome - 1);
                event.getChannel().sendMessage("Rolled a " + outcome + ".").queue();
                event.getChannel().sendMessage(response).queue();
            }


        } else if (event.getMessage().getContentRaw().contentEquals("!ur")) {
            event.getChannel().sendMessage("Unofficial rules: https://docs.google.com/document/d/1ix5Xemnw059fKiMGwf-hJWjN43LLVBNE2DL84G1nSNc/edit?usp=sharing").queue();
        } else if (event.getMessage().getContentRaw().contains("ass") || event.getMessage().getContentRaw().contains("Ass") || event.getMessage().getContentRaw().contains("ASS")) {
            event.getChannel().sendMessage("asses").queue();
        } else if (event.getMessage().getContentRaw().contains("warframe") || event.getMessage().getContentRaw().contains("Warframe") || event.getMessage().getContentRaw().contains("WARFRAME")) {
            event.getChannel().sendMessage("The first stage is denial.").queue();
            event.getChannel().sendFile(primed.toFile()).queue();
        }
    }


}
