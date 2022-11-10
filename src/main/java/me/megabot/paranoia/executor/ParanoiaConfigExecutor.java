package me.megabot.paranoia.executor;

import me.megabot.paranoia.config.ParanoiaConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class ParanoiaConfigExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
            return true;
        }

        if (args.length > 2 || args.length < 1) return false;

        CommandArg cmdArg = CommandArg.getArgument(args[0]);
        if (cmdArg == null) return false;

        switch (cmdArg) {
            case ON:
                if (args.length == 2) onHandler(sender, args[1]);
                else onHandler(sender, "");
                break;
            case OFF:
                if (args.length == 2) offHandler(sender, args[1]);
                else offHandler(sender, "");
                break;
            case CHANCE:
                if (!isNumeric(args[1])) return false;
                if (args.length == 1) return false;
                chanceHandler(sender, Integer.parseInt(args[1]));
                break;
            default:
                return false;
        }


        return true;
    }

    private void onHandler(CommandSender sender, String username) {
        if (username.isEmpty()) {
            ParanoiaConfig.setIsPluginEnabled(true);
            sender.sendMessage("Paranoia is enabled for non-disabled users");
            return;
        }

        Player player = getServer().getPlayer(username);
        if (player == null) {
            sender.sendMessage(username + " was not found or is offline");
            return;
        }

        if (!ParanoiaConfig.enablePlayerParanoia(player.getUniqueId())) {
            sender.sendMessage(username + " is already paranoid");
            return;
        }
        sender.sendMessage(username + " is now paranoid");
    }

    private void offHandler(CommandSender sender, String username) {
        if (username.isEmpty()) {
            ParanoiaConfig.setIsPluginEnabled(false);
            sender.sendMessage("Paranoia is disabled for all users");
            return;
        }

        Player player = getServer().getPlayer(username);
        if (player == null) {
            sender.sendMessage(username + " was not found or is offline");
            return;
        }

        if (!ParanoiaConfig.disablePlayerParanoia(player.getUniqueId())) {
            sender.sendMessage(username + " is not paranoid");
            return;
        }
        sender.sendMessage(username + " is no longer paranoid");
    }

    private void chanceHandler(CommandSender sender, int percentage) {
        if (percentage < 0 || percentage > 100) {
            sender.sendMessage("Pick a percentage between 0 and 100");
            return;
        }
        ParanoiaConfig.setPercentageChance(percentage);
        sender.sendMessage("Percentage chance set to " + percentage);
    }

    private boolean isNumeric(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private enum CommandArg {
        ON,
        OFF,
        CHANCE;

        public static CommandArg getArgument(String arg) {
            arg = arg.toLowerCase();
            return switch (arg) {
                case "on" -> ON;
                case "off" -> OFF;
                case "set-chance" -> CHANCE;
                default -> null;
            };
        }
    }
}
