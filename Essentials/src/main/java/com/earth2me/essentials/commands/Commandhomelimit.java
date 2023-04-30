package com.earth2me.essentials.commands;

import com.earth2me.essentials.CommandSource;
import com.earth2me.essentials.User;
import org.bukkit.Server;

import java.util.Arrays;

import static com.earth2me.essentials.I18n.tl;

public class Commandhomelimit extends EssentialsCommand {
    public Commandhomelimit() {
        super("homelimit");
    }

    @Override
    protected void run(final Server server, final User user, final String commandLabel, final String[] args) throws Exception {
        if (!user.isAuthorized("essentials.homelimit")) {
            throw new Exception(tl("noPerm", "essentials.homelimit"));
        }

        if(args.length < 3) {
            throw new NotEnoughArgumentsException();
        }

        final String[] validActions = {"add", "remove", "set"};
        final boolean isValidAction = Arrays.stream(validActions).anyMatch(args[1]::equals);

        if( !isValidAction ) {
            user.sendMessage("Command usage: /" + commandLabel + " [username] [add/remove/set] [value]");
            return;
        }

        int value = 0;
        try {
            value = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {}
        if(value < 0) {
            value = 0;
        }

        final int finalValue = value;

        final String[] targetUserArray = {args[0]};
        final User targetUser = getPlayer(server, targetUserArray, 0, true, true);
        if(targetUser == null) {
            user.sendMessage("Invalid Essentials user.");
            return;
        }

        String whatWasDone = "";

        if(args[1].equals("add")) {
            targetUser.addAdditionalHomes(finalValue);

            whatWasDone = "Successfully added " + finalValue + " homes to the player.";
        }

        if(args[1].equals("remove")) {
            targetUser.removeAdditionalHomes(finalValue);

            whatWasDone = "Successfully removed " + finalValue + " homes from the player.";
        }

        if(args[1].equals("set")) {
            targetUser.setAdditionalHomes(finalValue);

            whatWasDone = "Successfully set the player's additional homes limit to " + finalValue;
        }

        final String whatWasDoneFinalized = whatWasDone;

        user.sendMessage(whatWasDoneFinalized);
    }

    @Override
    public void run(final Server server, final CommandSource sender, final String commandLabel, final String[] args) throws Exception {
        if(args.length < 3) {
            throw new NotEnoughArgumentsException();
        }

        final String[] validActions = {"add", "remove", "set"};
        final boolean isValidAction = Arrays.stream(validActions).anyMatch(args[1]::equals);

        if( !isValidAction ) {
            ess.getLogger().info("Command usage: /" + commandLabel + " [username] [add/remove/set] [value]");
            return;
        }

        int value = 0;
        try {
            value = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {}
        if(value < 0) {
            value = 0;
        }

        final int finalValue = value;

        final String[] targetUserArray = {args[0]};
        final User targetUser = getPlayer(server, targetUserArray, 0, true, true);
        if(targetUser == null) {
            ess.getLogger().info("Invalid Essentials user.");
            return;
        }

        String whatWasDone = "";

        if(args[1].equals("add")) {
            targetUser.addAdditionalHomes(finalValue);

            whatWasDone = "Successfully added " + finalValue + " homes to the player.";
        }

        if(args[1].equals("remove")) {
            targetUser.removeAdditionalHomes(finalValue);

            whatWasDone = "Successfully removed " + finalValue + " homes from the player.";
        }

        if(args[1].equals("set")) {
            targetUser.setAdditionalHomes(finalValue);

            whatWasDone = "Successfully set the player's additional homes limit to " + finalValue;
        }

        final String whatWasDoneFinalized = whatWasDone;

        ess.getLogger().info(whatWasDoneFinalized);
    }
}
