/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.configuration.MemoryConfiguration
 */
package io.github.sefiraat.crystamaehistoria.slikey.effectlib.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;

public class ConfigUtils {
    public static Collection<ConfigurationSection> getNodeList(ConfigurationSection node, String path) {
        ArrayList<ConfigurationSection> results = new ArrayList<ConfigurationSection>();
        List mapList = node.getMapList(path);
        for (Map map : mapList) {
            results.add(ConfigUtils.toConfigurationSection(map));
        }
        return results;
    }

    @Deprecated
    public static ConfigurationSection toNodeList(Map<?, ?> nodeMap) {
        return ConfigUtils.toConfigurationSection(nodeMap);
    }

    public static ConfigurationSection toConfigurationSection(Map<?, ?> nodeMap) {
        MemoryConfiguration newSection = new MemoryConfiguration();
        for (Map.Entry<?, ?> entry : nodeMap.entrySet()) {
            newSection.set(entry.getKey().toString(), entry.getValue());
        }
        return newSection;
    }

    public static ConfigurationSection convertConfigurationSection(Map<?, ?> nodeMap) {
        MemoryConfiguration newSection = new MemoryConfiguration();
        for (Map.Entry<?, ?> entry : nodeMap.entrySet()) {
            ConfigUtils.set((ConfigurationSection)newSection, entry.getKey().toString(), entry.getValue());
        }
        return newSection;
    }

    public static ConfigurationSection toStringConfiguration(Map<String, String> stringMap) {
        if (stringMap == null) {
            return null;
        }
        MemoryConfiguration configMap = new MemoryConfiguration();
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            configMap.set(entry.getKey(), (Object)entry.getValue());
        }
        return configMap;
    }

    public static void set(ConfigurationSection node, String path, Object value) {
        if (value == null) {
            node.set(path, null);
            return;
        }
        boolean isTrue = value.equals("true");
        boolean isFalse = value.equals("false");
        if (isTrue || isFalse) {
            node.set(path, (Object)isTrue);
            return;
        }
        try {
            Integer i = value instanceof Integer ? (Integer)value : Integer.parseInt(value.toString());
            node.set(path, (Object)i);
        }
        catch (Exception ex) {
            try {
                double d = value instanceof Double ? (Double)value : (value instanceof Float ? (double)((Float)value).floatValue() : Double.parseDouble(value.toString()));
                node.set(path, (Object)d);
            }
            catch (Exception ex2) {
                node.set(path, value);
            }
        }
    }

    public static ConfigurationSection getConfigurationSection(ConfigurationSection base, String key) {
        ConfigurationSection section = base.getConfigurationSection(key);
        if (section != null) {
            return section;
        }
        Object value = base.get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof ConfigurationSection) {
            return (ConfigurationSection)value;
        }
        if (value instanceof Map) {
            ConfigurationSection newChild = base.createSection(key);
            Map map = (Map)value;
            for (Map.Entry entry : map.entrySet()) {
                newChild.set((String)entry.getKey(), entry.getValue());
            }
            base.set(key, (Object)newChild);
            return newChild;
        }
        return null;
    }

    public static boolean isMaxValue(String stringValue) {
        return stringValue.equalsIgnoreCase("infinite") || stringValue.equalsIgnoreCase("forever") || stringValue.equalsIgnoreCase("infinity") || stringValue.equalsIgnoreCase("max");
    }
}

