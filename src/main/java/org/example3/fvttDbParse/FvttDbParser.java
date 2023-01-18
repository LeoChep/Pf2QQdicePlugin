package org.example3.fvttDbParse;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class FvttDbParser {
    class Item {
        String name;
        String description;
        String bonus;
        String mod;

        String[] traits;

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    class Monster {
        String name;
        Details details;
        Traits traits;
        Abilities abilities;
        Attributes attributes;
        Saves saves;
        List<Item> items = new ArrayList<>();


        @Override
        public String toString() {
            return "Monster{" +
                    "name='" + name + '\'' +
                    ", details=" + details +
                    ", traits=" + traits +
                    ", abilities=" + abilities +
                    ", attributes=" + attributes +
                    ", saves=" + saves +
                    ", items=" + items +
                    '}';
        }
    }

    public class Box {
        JsonNode jsonNode;

        public Box get(String fieldName) {
            Box box = new Box();
            try {
                JsonNode result = jsonNode.get(fieldName);

                box.jsonNode = result;
            } catch (Exception e) {

            }
            return box;
        }

        public String toString() {
            if (jsonNode == null) return null;
            else return jsonNode.asText();
        }
        public int asInt(){
            if (jsonNode == null) return 0;
            else return jsonNode.asInt();
        }
    }

    public void loadDetails(Monster monster,Box box){
        String name = box.get("name").toString();
        monster.name = name;
        monster.details=new Details();
        monster.details.loadFormBox(box);
    }

    public String parse(JsonNode jsonNode) {
        //input=json;
       String result = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = null;
//        try {
//            jsonNode = objectMapper.readValue(input, JsonNode.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        Box box = new Box();
        box.jsonNode = jsonNode;
        Monster monster = new Monster();
        loadDetails(monster,box);
        monster.saves=new Saves();
        monster.saves.loadFromBox(box);
        monster.traits=new Traits();
        monster.traits.loadFromBox(box);
        monster.abilities=new Abilities();
        monster.abilities.loadFromBox(box);
        monster.attributes=new Attributes();
        monster.attributes.loadFormBox(box);
        JsonNode items = jsonNode.get("items");
        Iterator<JsonNode> it = items.elements();
        while (it.hasNext()) {
            JsonNode temp = it.next();
            String itemName = temp.get("name").asText();
            Item item = new Item();
            item.name = itemName;
            monster.items.add(item);
        }
        result = monster.toString();
        return result;
    }


}