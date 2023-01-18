package org.example3.fvttDbParse;

public class Abilities {
    int STR;
    int DEX;
    int CON;
    int WIS;
    int INT;
    int CHA;

    public void loadFromBox(FvttDbParser.Box box){
        this.STR=box.get("system").
                get("abilities").
                get("str").get("mod").asInt();
        this.DEX=box.get("system").
                get("abilities").
                get("dex").get("mod").asInt();
        this.CON=box.get("system").
                get("abilities").
                get("con").get("mod").asInt();
        this.WIS=box.get("system").
                get("abilities").
                get("wis").get("mod").asInt();
        this.INT=box.get("system").
                get("abilities").
                get("int").get("mod").asInt();
        this.CHA=box.get("system").
                get("abilities").
                get("cha").get("mod").asInt();
    }

    @Override
    public String toString() {
        return "Abilities{" +
                "STR=" + STR +
                ", DEX=" + DEX +
                ", CON=" + CON +
                ", WIS=" + WIS +
                ", INT=" + INT +
                ", CHA=" + CHA +
                '}';
    }
}
