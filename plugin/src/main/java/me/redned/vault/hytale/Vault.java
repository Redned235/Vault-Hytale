package me.redned.vault.hytale;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import me.redned.vault.hytale.economy.Economy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.CompletableFuture;

public class Vault extends JavaPlugin {
    private ServiceLoader<Economy> economyLoader;

    public Vault(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Nullable
    @Override
    public CompletableFuture<Void> preLoad() {
        this.economyLoader = ServiceLoader.load(Economy.class);
        return super.preLoad();
    }

    @Override
    protected void setup() {
        this.getCommandRegistry().registerCommand(new VaultInfoCommand(this));

        super.setup();
    }

    @Nonnull
    public List<Economy> getRegisteredEconomies() {
        return this.economyLoader.stream()
                .map(ServiceLoader.Provider::get)
                .toList();
    }
}
