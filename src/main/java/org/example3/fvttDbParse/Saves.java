package org.example3.fvttDbParse;

public class Saves {
    public int fortitude;
    public int reflex;
    public int will;
    public void loadFromBox(FvttDbParser.Box box){
        this.fortitude=box.get("system").
                get("saves").
                get("fortitude").get("value").asInt();
        this.reflex=box.get("system").
                get("saves").
                get("reflex").get("value").asInt();
        this.will=box.get("system").
                get("saves").
                get("will").get("value").asInt();

    }

    @Override
    public String toString() {
        return "Saves{" +
                "fortitude=" + fortitude +
                ", reflex=" + reflex +
                ", will=" + will +
                '}'+"\n";
    }
}
