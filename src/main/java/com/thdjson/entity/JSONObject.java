/*
 * Copyright 2017 ThdLee
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thdjson.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ThdLee
 */
public class JSONObject extends JSONFormat implements Map<String, JSONValue>, Cloneable, Serializable {

    private final JSONValueType type = JSONValueType.OBJECT;;

    private LinkedHashMap<String, JSONValue> map;

    public JSONObject() {
        map = new LinkedHashMap<>();
    }

    public JSONObject(LinkedHashMap<String, JSONValue> map) {
        this.map = map;
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    public JSONValue get(Object key) {
        return map.get(key);
    }

    public JSONValueType getType() {
        return type;
    }

    public JSONValue put(String key, JSONValue value) {
        return map.put(key, value);
    }

    public JSONObject fluentPut(String key, JSONValue value) {
        map.put(key, value);
        return this;
    }

    public void putAll(Map<? extends String, ? extends JSONValue> m) {
        map.putAll(m);
    }

    public JSONObject fluentPutAll(Map<? extends String, ? extends JSONValue> m) {
        map.putAll(m);
        return this;
    }

    public void clear() {
        map.clear();
    }

    public JSONObject fluentClear() {
        map.clear();
        return this;
    }

    public JSONValue remove(Object key) {
        return map.remove(key);
    }

    public JSONObject fluentRemove(String key) {
        map.remove(key);
        return this;
    }

    public Set<Map.Entry<String, JSONValue>> entrySet() {
        return map.entrySet();
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public Collection<JSONValue> values() {
        return map.values();
    }

    public Byte getByte(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getByte();
        return null;
    }

    public byte getByteValue(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getByteValue();
        return 0;
    }

    public Short getShort(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getShort();
        return null;
    }

    public short getShortValue(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getShortValue();
        return 0;
    }

    public Integer getInt(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getInt();
        return null;
    }

    public int getIntValue(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getIntValue();
        return 0;
    }

    public Long getLong(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getLong();
        return null;
    }

    public long getLongValue(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getLongValue();
        return 0;
    }

    public Boolean getBoolean(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getBoolean();
        return null;
    }

    public boolean getBooleanValue(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getBooleanValue();
        return false;
    }

    public Float getFloat(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getFloat();
        return null;
    }

    public float getFloatValue(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getFloatValue();
        return 0;
    }

    public Double getDouble(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getDouble();
        return null;
    }

    public double getDoubleValue(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getDoubleValue();
        return 0;
    }

    public BigDecimal getBigDecimal(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getBigDecimal();
        return null;
    }

    public BigInteger getBigInteger(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getBigInteger();
        return null;
    }

    public String getString(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONElement)
            return ((JSONElement) value).getString();
        if (value == null) return null;
        return value.toString();
    }

    public JSONArray getJSONArray(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONArray) {
            return (JSONArray) value;
        }
        return null;
    }

    public JSONObject getJSONObject(String key) {
        JSONValue value = map.get(key);
        if (value instanceof JSONObject) {
            return (JSONObject) value;
        }
        return null;
    }

    public Object clone() {
        return new JSONObject(map);
    }

    public boolean equals(Object obj) {
        return this.map.equals(obj);
    }

    public int hashCode() {
        return this.map.hashCode();
    }

    public String toString() {
        return format(1);
    }

    @Override
    String format(int layer) {
        StringBuilder str = new StringBuilder();
        str.append("{\n");
        for (Map.Entry entry : map.entrySet()) {
            str.append(withSpace(layer));
            str.append("\"" + entry.getKey() + "\"");
            str.append(": ");
            str.append(matchFormat(entry.getValue(), layer+1));
            str.append(",\n");
        }
        int lastIndex = str.length() - 2;
        if (str.charAt(lastIndex) == ',') str.deleteCharAt(lastIndex);
        str.append(withSpace(layer-1));
        str.append("}");
        return str.toString();
    }
}
