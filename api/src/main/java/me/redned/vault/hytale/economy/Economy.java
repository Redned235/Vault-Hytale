package me.redned.vault.hytale.economy;

import java.util.List;
import java.util.UUID;

/**
 * The main economy API
 *
 */
public interface Economy {

    /**
     * Checks if economy method is enabled.
     * @return Success or Failure
     */
    boolean isEnabled();

    /**
     * Gets name of economy method
     * @return Name of Economy Method
     */
    String getName();

    /**
     * Returns true if the given implementation supports banks.
     * @return true if the implementation supports banks
     */
    boolean hasBankSupport();

    /**
     * Some economy plugins round off after a certain number of digits.
     * This function returns the number of digits the plugin keeps
     * or -1 if no rounding occurs.
     * @return number of digits after the decimal point kept
     */
    int fractionalDigits();

    /**
     * Format amount into a human readable String This provides translation into
     * economy specific formatting to improve consistency between plugins.  
     *
     * @param amount to format
     * @return Human readable string describing amount
     */
    String format(double amount);

    /**
     * Returns the name of the currency in plural form.
     * If the economy being used does not support currency names then an empty string will be returned.
     *
     * @return name of the currency (plural)
     */
    String currencyNamePlural();


    /**
     * Returns the name of the currency in singular form.
     * If the economy being used does not support currency names then an empty string will be returned.
     *
     * @return name of the currency (singular)
     */
    String currencyNameSingular();

    /**
     * Checks if this player has an account on the server yet
     * This will always return true if the player has joined the server at least once
     * as all major economy plugins auto-generate a player account when the player joins the server
     *
     * @param uuid to check
     * @return if the player has an account
     */
    boolean hasAccount(UUID uuid);

    /**
     * Checks if this player has an account on the server yet on the given world
     * This will always return true if the player has joined the server at least once
     * as all major economy plugins auto-generate a player account when the player joins the server
     *
     * @param uuid to check in the world
     * @param worldName world-specific account
     * @return if the player has an account
     */
    boolean hasAccount(UUID uuid, String worldName);

    /**
     * Gets balance of a player
     *
     * @param uuid of the player
     * @return Amount currently held in players account
     */
    double getBalance(UUID uuid);

    /**
     * Gets balance of a player on the specified world.
     * IMPLEMENTATION SPECIFIC - if an economy plugin does not support this the global balance will be returned.
     * @param uuid to check
     * @param world name of the world
     * @return Amount currently held in players account
     */
    double getBalance(UUID uuid, String world);

    /**
     * Checks if the player account has the amount - DO NOT USE NEGATIVE AMOUNTS
     *
     * @param uuid to check
     * @param amount to check for
     * @return True if <b>player</b> has <b>amount</b>, False else wise
     */
    boolean has(UUID uuid, double amount);

    /**
     * Checks if the player account has the amount in a given world - DO NOT USE NEGATIVE AMOUNTS
     * IMPLEMENTATION SPECIFIC - if an economy plugin does not support this the global balance will be returned.
     *
     * @param uuid to check
     * @param worldName to check with
     * @param amount to check for
     * @return True if <b>player</b> has <b>amount</b>, False else wise
     */
    boolean has(UUID uuid, String worldName, double amount);

    /**
     * Withdraw an amount from a player - DO NOT USE NEGATIVE AMOUNTS
     *
     * @param uuid to withdraw from
     * @param amount Amount to withdraw
     * @return Detailed response of transaction
     */
    EconomyResponse withdrawPlayer(UUID uuid, double amount);

    /**
     * Withdraw an amount from a player on a given world - DO NOT USE NEGATIVE AMOUNTS
     * IMPLEMENTATION SPECIFIC - if an economy plugin does not support this the global balance will be returned.
     * @param uuid to withdraw from
     * @param worldName - name of the world
     * @param amount Amount to withdraw
     * @return Detailed response of transaction
     */
    EconomyResponse withdrawPlayer(UUID uuid, String worldName, double amount);

    /**
     * Deposit an amount to a player - DO NOT USE NEGATIVE AMOUNTS
     *
     * @param uuid to deposit to
     * @param amount Amount to deposit
     * @return Detailed response of transaction
     */
    EconomyResponse depositPlayer(UUID uuid, double amount);

    /**
     * Deposit an amount to a player - DO NOT USE NEGATIVE AMOUNTS
     * IMPLEMENTATION SPECIFIC - if an economy plugin does not support this the global balance will be returned.
     *
     * @param uuid to deposit to
     * @param worldName name of the world
     * @param amount Amount to deposit
     * @return Detailed response of transaction
     */
    EconomyResponse depositPlayer(UUID uuid, String worldName, double amount);

    /**
     * Creates a bank account with the specified name and the player as the owner
     * @param name of account
     * @param uuid the account should be linked to
     * @return EconomyResponse Object
     */
    EconomyResponse createBank(String name, UUID uuid);

    /**
     * Deletes a bank account with the specified name.
     * @param name of the back to delete
     * @return if the operation completed successfully
     */
    EconomyResponse deleteBank(String name);

    /**
     * Returns the amount the bank has
     * @param name of the account
     * @return EconomyResponse Object
     */
    EconomyResponse bankBalance(String name);

    /**
     * Returns true or false whether the bank has the amount specified - DO NOT USE NEGATIVE AMOUNTS
     *
     * @param name of the account
     * @param amount to check for
     * @return EconomyResponse Object
     */
    EconomyResponse bankHas(String name, double amount);

    /**
     * Withdraw an amount from a bank account - DO NOT USE NEGATIVE AMOUNTS
     *
     * @param name of the account
     * @param amount to withdraw
     * @return EconomyResponse Object
     */
    EconomyResponse bankWithdraw(String name, double amount);

    /**
     * Deposit an amount into a bank account - DO NOT USE NEGATIVE AMOUNTS
     *
     * @param name of the account
     * @param amount to deposit
     * @return EconomyResponse Object
     */
    EconomyResponse bankDeposit(String name, double amount);

    /**
     * Check if a player is the owner of a bank account
     *
     * @param name of the account
     * @param uuid to check for ownership
     * @return EconomyResponse Object
     */
    EconomyResponse isBankOwner(String name, UUID uuid);

    /**
     * Check if the player is a member of the bank account
     *
     * @param name of the account
     * @param uuid to check membership
     * @return EconomyResponse Object
     */
    EconomyResponse isBankMember(String name, UUID uuid);

    /**
     * Gets the list of banks
     * @return the List of Banks
     */
    List<String> getBanks();

    /**
     * Attempts to create a player account for the given player
     * @param uuid UUID
     * @return if the account creation was successful
     */
    boolean createPlayerAccount(UUID uuid);

    /**
     * Attempts to create a player account for the given player on the specified world
     * IMPLEMENTATION SPECIFIC - if an economy plugin does not support this then false will always be returned.
     * @param uuid UUID
     * @param worldName String name of the world
     * @return if the account creation was successful
     */
    boolean createPlayerAccount(UUID uuid, String worldName);
}
