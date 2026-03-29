/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 *  org.bukkit.permissions.ServerOperator
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.commands;

import io.github.sefiraat.crystamaehistoria.infinitylib.commands.ParentCommand;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.ServerOperator;

@ParametersAreNonnullByDefault
public abstract class SubCommand {
    private final Predicate<CommandSender> permission;
    private final String description;
    private final String name;
    private String fullName;
    ParentCommand parent;

    protected SubCommand(String name, String description, String perm) {
        this.name = name.toLowerCase(Locale.ROOT);
        this.description = description;
        this.permission = sender -> sender.hasPermission(perm);
    }

    protected SubCommand(String name, String description, boolean op) {
        this.name = name.toLowerCase(Locale.ROOT);
        this.description = description;
        this.permission = op ? ServerOperator::isOp : sender -> true;
    }

    protected SubCommand(String name, String description) {
        this(name, description, false);
    }

    protected abstract void execute(CommandSender var1, String[] var2);

    protected abstract void complete(CommandSender var1, String[] var2, List<String> var3);

    public final boolean canUse(CommandSender sender) {
        return this.permission.test(sender);
    }

    public final String name() {
        return this.name;
    }

    public final String description() {
        return this.description;
    }

    String fullName() {
        if (this.fullName == null) {
            this.fullName = this.parent.fullName() + " " + this.name();
        }
        return this.fullName;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object obj) {
        return obj instanceof SubCommand && ((SubCommand)obj).name.equals(this.name);
    }
}

