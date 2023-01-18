package org.example3.fvttDbParse.attributes;

import com.fasterxml.jackson.databind.JsonNode;
import org.example3.fvttDbParse.FvttDbParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Resistance {
    List<String> exceptions = new ArrayList<>();
    String type;
    int value;

    @Override
    public String toString() {
        return "Resistance{" +
                "exceptions=" + exceptions +
                ", type='" + type + '\'' +
                ", value=" + value +
                '}';
    }

    public void loadFormJsonNode(JsonNode resistance) {
        type = resistance.get("type").asText();
        value = resistance.get("value").asInt();
        JsonNode exceptions = resistance.get("exceptions");
        Iterator<JsonNode> it = null;
        if (exceptions != null)
            it = exceptions.elements();
        while (it!=null&&it.hasNext()) {
            JsonNode temp = it.next();
            String key = temp.asText();
            this.exceptions.add(key);
        }

    }
}
