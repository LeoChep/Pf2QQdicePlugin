package org.example3.fvttDbParse;

public class Details {
    String publicNote;
    String level;
    String source;
    String alignment;
    public void loadFormBox(FvttDbParser.Box box){
        this.publicNote = box.get("system").
                get("details").
                get("publicNotes").toString();
        this.level= box.get("system").
                get("details").
                get("level").get("value").toString();
        this.source= box.get("system").
                get("details").
                get("source").get("value").toString();
        this.alignment=box.get("system").
                get("details").
                get("alignment").get("value").toString();
    }

    @Override
    public String toString() {
        return "Details{" +
                "publicNote='" + publicNote + '\'' +
                ", level='" + level + '\'' +
                ", source='" + source + '\'' +
                ", alignment='" + alignment + '\'' +
                '}'+"\n";
    }
}
