package org.example3.fvttDbParse.attributes;

import org.example3.fvttDbParse.FvttDbParser;

public class HP {
    int max;
    public void loadFormBox(FvttDbParser.Box box){
        this.max = box.get("system").
                get("attributes").
                get("hp").get("max").
                asInt();

    }

    @Override
    public String toString() {
        return "HP{" +
                "max=" + max +
                '}';
    }
}
