package org.example3.fvttDbParse.attributes;

import org.example3.fvttDbParse.FvttDbParser;

public class AC {
     int value;
     String detail;
    public void loadFormBox(FvttDbParser.Box box){
        this.value = box.get("system").
                get("attributes").
                get("ac").get("value").
                asInt();
        this.detail = box.get("system").
                get("attributes").
                get("ac").get("detail").
                toString();
    }

    @Override
    public String toString() {
        return "AC{" +
                "value=" + value +
                ", detail='" + detail + '\'' +
                '}';
    }
}
