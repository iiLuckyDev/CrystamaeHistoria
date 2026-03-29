/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 */
package io.github.sefiraat.crystamaehistoria.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.bukkit.Material;

public class BlocksConfigGenerator {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/new_blocks.yml"));){
            BlocksConfigGenerator.writeLine(writer, "blocks:");
            for (Material material : Arrays.stream(Material.values()).sorted(Comparator.comparing(Enum::toString)).collect(Collectors.toList())) {
                if (!material.isItem() || material.isLegacy()) continue;
                BlocksConfigGenerator.writeLine(writer, "  - material: " + material.name());
                BlocksConfigGenerator.writeLine(writer, "    tier: 1");
                BlocksConfigGenerator.writeLine(writer, "    elements:");
                BlocksConfigGenerator.writeLine(writer, "      - ");
                BlocksConfigGenerator.writeLine(writer, "    story:");
                BlocksConfigGenerator.writeLine(writer, "      name: ");
                BlocksConfigGenerator.writeLine(writer, "      type: ");
                BlocksConfigGenerator.writeLine(writer, "      lore: ");
                BlocksConfigGenerator.writeLine(writer, "        - ");
                BlocksConfigGenerator.writeLine(writer, "      shards: ");
                BlocksConfigGenerator.writeLine(writer, "");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeLine(BufferedWriter writer, String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }
}

