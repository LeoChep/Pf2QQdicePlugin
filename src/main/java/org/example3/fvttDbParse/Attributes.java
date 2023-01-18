package org.example3.fvttDbParse;

import com.fasterxml.jackson.databind.JsonNode;
import kotlinx.serialization.json.Json;
import org.example3.fvttDbParse.attributes.AC;
import org.example3.fvttDbParse.attributes.HP;
import org.example3.fvttDbParse.attributes.Resistance;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Attributes {
    AC ac;
    String allSave;
    int hardness;
    HP hp;
    int perception;
    Map<String,Boolean> immunities=new HashMap<>();
    Map<String, Resistance>  resistances=new HashMap<>();
    Map<String,Integer> weaknesses=new HashMap<>();
    Map<String,Integer> speed=new HashMap<>();

    public void  loadFormBox(FvttDbParser.Box box){
        ac=new AC();
        ac.loadFormBox(box);
        hp=new HP();
        hp.loadFormBox(box);
        perception=box.get("system").get("attributes").get("perception").get("value").asInt();
        hardness=box.get("system").get("attributes").get("hardness").get("value").asInt();

        //加载抗性
        JsonNode resistances=box.get("system").get("attributes").get("resistances").jsonNode;
        if (resistances!=null)
        {
            Iterator<JsonNode> it=resistances.elements();
            while (it.hasNext()){
                JsonNode temp=it.next();
                String key=temp.get("type").asText();
                Resistance resistance=new Resistance();


                resistance.loadFormJsonNode(temp);
                this.resistances.put(key,resistance);
            }
        }
        JsonNode immunities=box.get("system").get("attributes").get("immunities").jsonNode;
        //加载免疫
        if (immunities!=null)
        {
            Iterator<JsonNode> it=immunities.elements();
            while (it.hasNext()){
                JsonNode temp=it.next();
                String name=temp.get("type").asText();
              //  Boolean bool=temp.get("value").asBoolean();
                this.immunities.put(name,true);
            }
        }
        //加载速度
        JsonNode speed=box.get("system").get("attributes").get("speed").jsonNode;
        if (speed!=null)
        {
            Integer value=speed.get("value").asInt();
            String key="value";
            this.speed.put(key,value);
            JsonNode otherSpeed=speed.get("otherSpeed");
            Iterator<JsonNode> it = null;
            if (otherSpeed!=null)
             it=otherSpeed.elements();
            while (it!=null&&it.hasNext()){
                JsonNode temp=it.next();
                key = temp.get("type").asText();
                value = temp.get("value").asInt();
                this.speed.put(key,value);
            }
        }
        //加载弱点
        JsonNode weaknesses=box.get("system").get("attributes").get("weaknesses").jsonNode;
        if (speed!=null)
        {
            Iterator<JsonNode> it = null;
            if (weaknesses!=null)
                it=weaknesses.elements();
            while (it!=null&&it.hasNext()){
                JsonNode temp=it.next();
                String key = temp.get("type").asText();
                int value = temp.get("value").asInt();
                this.weaknesses.put(key,value);
            }
        }
    }

    @Override
    public String toString() {
        return "Attributes{" +
                "ac=" + ac +
                ", allSave='" + allSave + '\'' +
                ", hardness=" + hardness +
                ", hp=" + hp +
                ", perception=" + perception +
                ", immunities=" + immunities +
                ", resistances=" + resistances +
                ", weaknesses=" + weaknesses +
                ", speed=" + speed +
                '}';
    }
}
