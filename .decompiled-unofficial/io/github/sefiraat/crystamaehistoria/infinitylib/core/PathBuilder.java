/*
 * Decompiled with CFR 0.152.
 */
package io.github.sefiraat.crystamaehistoria.infinitylib.core;

import java.util.ArrayList;
import java.util.List;

final class PathBuilder {
    List<String> path = new ArrayList<String>();

    PathBuilder() {
    }

    PathBuilder append(String line) {
        int indent = 0;
        for (char c : line.toCharArray()) {
            if (c != ' ') break;
            ++indent;
        }
        String key = line.substring(indent, line.indexOf(58));
        int size = this.path.size();
        if ((indent /= 2) == 0) {
            this.path.clear();
        } else if (indent < size) {
            if (indent + 1 == size) {
                this.path.remove(indent);
            } else {
                this.path.subList(indent, size).clear();
            }
        }
        this.path.add(key);
        return this;
    }

    boolean inMainSection() {
        return this.path.size() == 1;
    }

    String build() {
        StringBuilder builder = new StringBuilder();
        if (this.path.size() > 0) {
            builder.append(this.path.get(0));
            for (int i = 1; i < this.path.size(); ++i) {
                builder.append('.').append(this.path.get(i));
            }
        }
        return builder.toString();
    }
}

