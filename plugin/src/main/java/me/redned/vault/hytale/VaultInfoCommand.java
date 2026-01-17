package me.redned.vault.hytale;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import me.redned.vault.hytale.economy.Economy;

import javax.annotation.Nonnull;
import java.util.List;

public class VaultInfoCommand extends CommandBase {
    private final Vault vault;

    public VaultInfoCommand(Vault vault) {
        super("vault-info", "Displays information about Vault");

        this.vault = vault;
        this.setPermissionGroup(null); // Admin only - requires vault.vault.command.vault-info permission
    }

    @Override
    protected void executeSync(@Nonnull CommandContext commandContext) {
        StringBuilder registeredEcons = null;
        List<Economy> economies = this.vault.getRegisteredEconomies();
        for (Economy economy : economies) {
            if (registeredEcons == null) {
                registeredEcons = new StringBuilder(economy.getName());
            } else {
                registeredEcons.append(", ").append(economy.getName());
            }
        }

        Economy econ = economies.isEmpty() ? null : economies.getFirst();

        commandContext.sender().sendMessage(Message.raw(String.format("[%s] Vault v%s Information", this.vault.getManifest().getName(), this.vault.getManifest().getVersion())));
        commandContext.sender().sendMessage(Message.raw(String.format("[%s] Economy: %s [%s]", this.vault.getManifest().getName(), econ == null ? "None" : econ.getName(), registeredEcons)));
    }
}
