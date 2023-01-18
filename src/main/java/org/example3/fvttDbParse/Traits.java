package org.example3.fvttDbParse;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Traits {
    List<String> languages;
    String rarity;
    String senses;
    String size;
    List<String> tags;

    public void  loadFromBox(FvttDbParser.Box box){
        FvttDbParser.Box traits=box.get("system").
                get("traits");
        this.senses=  traits.
                get("senses").get("value").toString();
        this.size= traits.
                get("size").get("value").toString();
        this.rarity= traits.
                get("rarity").toString();
        //load languages
        if (traits.get("languages").get("value").jsonNode!=null) {
            this.languages = new ArrayList<>();
            Iterator<JsonNode> it = traits.
                    get("languages").get("value").jsonNode.elements();
            while (it.hasNext()) {
                JsonNode temp = it.next();
                languages.add(temp.asText());
            }
        }
        if (traits.get("languages").get("custom")!=null){
            Iterator<JsonNode> it = traits.
                    get("languages").get("value").jsonNode.elements();
            while (it.hasNext()) {
                JsonNode temp = it.next();
                languages.add(temp.asText());
            }
        }
        //load tags
        if (traits.get("value").jsonNode!=null){
            this.tags=new ArrayList<>();
            Iterator<JsonNode> it= traits.
                    get("value").jsonNode.elements();
            while (it.hasNext()){
                JsonNode temp=it.next();
                tags.add(temp.asText());
            }
        }

    }

    @Override
    public String toString() {
        return "Traits{" +
                "languages=" + languages +
                ", rarity='" + rarity + '\'' +
                ", senses='" + senses + '\'' +
                ", size='" + size + '\'' +
                ", tags=" + tags +
                '}';
    }
}
