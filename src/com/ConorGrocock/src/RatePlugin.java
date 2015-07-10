package com.ConorGrocock.src;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RatePlugin extends JavaPlugin{
	public static RatePlugin plugin;
	public int count = 0;
	
	@Override
	public void onEnable()  {}
	@Override
	public void onDisable() {}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("rateme")){
			if(args.length == 0){
				if(player.isOp()){
					player.sendMessage("----" + ChatColor.GREEN + "RateMe Help (Admin)" + ChatColor.WHITE + "----");
					player.sendMessage(ChatColor.AQUA + "/Rateme" + ChatColor.WHITE + " | " + ChatColor.GREEN + "Shows this screen!");
					player.sendMessage(ChatColor.AQUA + "/Rateme <Admin's name>" + ChatColor.WHITE + " | " + ChatColor.GREEN + "Sends message to Admin/OP for a rateme request ");
					player.sendMessage(ChatColor.AQUA + "/Rateme Left" + ChatColor.WHITE + " | " + ChatColor.GREEN + "Shows the amount of Ratings left to Completed.");
					player.sendMessage(ChatColor.AQUA + "/Rateme Done" + ChatColor.WHITE + " | " + ChatColor.GREEN + "Finished RateMe request");
				}else{
					player.sendMessage("----" + ChatColor.GREEN + "RateMe Help" + ChatColor.WHITE + "----");
					player.sendMessage(ChatColor.AQUA + "/Rateme" + ChatColor.WHITE + " | " + ChatColor.GREEN + "Shows this screen!");
					player.sendMessage(ChatColor.AQUA + "/Rateme <Admin's name>" + ChatColor.WHITE + " | " + ChatColor.GREEN + "Sends message to Admin/OP for a rateme request ");
				}
			}if(args.length == 1){
				if(player.getServer().getPlayer(args[0]) != null){
					Player target = player.getServer().getPlayer(args[0]);
					if(player.isOp()){
						count++;
						target.sendMessage(ChatColor.GREEN + "You have been sent a rate me Request from " + ChatColor.DARK_AQUA + player.getDisplayName());
						target.sendMessage(ChatColor.GREEN + "There are now" + count + " rate requests to complete.");
						target.sendMessage(ChatColor.DARK_GREEN + "REMEMBER! USE " + ChatColor.GOLD + "/Rateme Done" + ChatColor.DARK_GREEN + "When you have finished");
					} else{
						player.sendMessage(ChatColor.DARK_RED + "You are not allowed to send this player a request");
					}
				}if(player.isOp()){
					if(args[0].equalsIgnoreCase("left")){
						if(count == 0){
							player.sendMessage(ChatColor.GREEN + "There are no RateMe requests at the moment");
						}else if(count == 1){
							player.sendMessage(ChatColor.AQUA + "There is a single request for you");
						}else{
							player.sendMessage(ChatColor.AQUA + "There are " + count + " Requests left");
						}
					}else if(args[0].equalsIgnoreCase("done")){
						if(count <= 0){
							player.sendMessage(ChatColor.DARK_RED + "There are no rates to complete!");
						}else{
							count--;
							if(count == 0){
								player.sendMessage(ChatColor.LIGHT_PURPLE + "Rate Completed:) There are 0 reateMe requests left");
							}else if(count == 1){
								player.sendMessage(ChatColor.LIGHT_PURPLE + "Rate Completed:) There is 1 reateMe requests left");
							}else{
								player.sendMessage(ChatColor.LIGHT_PURPLE + "Rate Completed:) There are " + count +" reateMe requests left");
							}
						}
					}
				}else{
					player.sendMessage(ChatColor.DARK_RED + "You are not allowed to do this!");
				}
			}
		}
		return false;
	}
}
