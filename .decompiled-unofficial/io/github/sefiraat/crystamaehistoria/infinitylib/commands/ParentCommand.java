/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.CommandSender
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.commands;

import io.github.sefiraat.crystamaehistoria.infinitylib.commands.HelpCommand;
import io.github.sefiraat.crystamaehistoria.infinitylib.commands.SubCommand;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.command.CommandSender;

@ParametersAreNonnullByDefault
public class ParentCommand
extends SubCommand {
    private final Map<String, SubCommand> commands = new HashMap<String, SubCommand>();
    private final SubCommand helpCommand = new HelpCommand(this);

    public ParentCommand(String name, String description, String perm) {
        super(name, description, perm);
        this.addSub(this.helpCommand);
    }

    public ParentCommand(String name, String description, boolean op) {
        super(name, description, op);
        this.addSub(this.helpCommand);
    }

    public ParentCommand(String name, String description) {
        super(name, description);
        this.addSub(this.helpCommand);
    }

    @Nonnull
    public final ParentCommand addSub(SubCommand command) {
        if (command == this) {
            throw new IllegalArgumentException("'" + command.name() + "' cannot be added to itself!");
        }
        if (command == this.parent) {
            throw new IllegalArgumentException("Parent command '" + command.name() + "' cannot be added to child " + this.name());
        }
        this.commands.compute(command.name(), (name, cmd) -> {
            if (cmd != null) {
                throw new IllegalArgumentException("Duplicate command '" + command.name() + "' cannot be added to " + this.name());
            }
            command.parent = this;
            return command;
        });
        return this;
    }

    @Override
    protected final void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            this.execute(sender);
        } else {
            SubCommand command = this.commands.get(args[0]);
            if (command != null && command.canUse(sender)) {
                command.execute(sender, Arrays.copyOfRange(args, 1, args.length));
            }
        }
    }

    protected void execute(CommandSender sender) {
        this.helpCommand.execute(sender, new String[0]);
    }

    @Override
    protected final void complete(CommandSender sender, String[] args, List<String> completions) {
        SubCommand command = this.commands.get(args[0]);
        if (command != null && command.canUse(sender)) {
            command.complete(sender, Arrays.copyOfRange(args, 1, args.length), completions);
        } else {
            for (SubCommand sub : this.commands.values()) {
                if (!sub.canUse(sender)) continue;
                completions.add(sub.name());
            }
        }
    }

    @Nonnull
    final Set<SubCommand> available(CommandSender sender) {
        HashSet<SubCommand> set = new HashSet<SubCommand>();
        for (SubCommand command : this.commands.values()) {
            if (!command.canUse(sender)) continue;
            set.add(command);
        }
        return set;
    }
}

