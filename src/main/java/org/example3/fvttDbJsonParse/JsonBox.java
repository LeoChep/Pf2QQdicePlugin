package org.example3.fvttDbJsonParse;

import com.fasterxml.jackson.databind.JsonNode;


public class JsonBox {
    JsonNode jsonNode;

    public JsonBox get(String fieldName) {
        JsonBox jsonBox = new JsonBox();
        try {
            JsonNode result = jsonNode.get(fieldName);

            jsonBox.jsonNode = result;
        } catch (Exception e) {

        }
        return jsonBox;
    }
}