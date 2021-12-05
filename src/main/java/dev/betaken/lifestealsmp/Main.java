package dev.betaken.lifestealsmp;

import dev.betaken.lifestealsmp.commands.WithDrawCommand;
import dev.betaken.lifestealsmp.listeners.PlayerListener;
import dev.betaken.lifestealsmp.recipe.RecipeManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private RecipeManager recipeManager;

    @Override
    public void onEnable() {
        recipeManager = new RecipeManager();

        recipeManager.reload();

        getCommand("withdraw").setExecutor(new WithDrawCommand());

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {

    }

}