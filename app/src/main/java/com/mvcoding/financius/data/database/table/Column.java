/*
 * Copyright (C) 2015 Mantas Varnagiris.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package com.mvcoding.financius.data.database.table;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.MoreObjects;

public final class Column {
    private final String tableName;
    private final String name;
    private final Type type;
    private final String defaultValue;
    private final String selectName;

    Column(@NonNull String tableName, @NonNull String name, @NonNull Type type) {
        this(tableName, name, type, null);
    }

    Column(@NonNull String tableName, @NonNull String name, @NonNull Type type, @Nullable String defaultValue) {
        this.tableName = tableName;
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;
        selectName = tableName + "." + name;
    }

    @NonNull String getCreateScript() {
        return name + " " + type + (defaultValue == null || defaultValue.isEmpty() ? "" : " default " + defaultValue);
    }

    @NonNull public String name() {
        return name;
    }

    @NonNull public String selectName() {
        return selectName;
    }

    @Override public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("tableName", tableName)
                .add("name", name)
                .add("type", type)
                .add("defaultValue", defaultValue)
                .toString();
    }

    enum Type {
        IntegerAutoIncrement("integer autoincrement"),
        Text("text"),
        TextPrimaryKey("text primary key"),
        Integer("integer"),
        Real("real"),
        Boolean("boolean"),
        DateTime("datetime");

        final private String dataType;

        Type(String dataType) {
            this.dataType = dataType;
        }

        @Override public String toString() {
            return dataType;
        }
    }
}