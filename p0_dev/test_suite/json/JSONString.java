package json;
/*
Wrapper class for String type.
 */

public class JSONString extends JSONType {
    public String value;

    public JSONString(String value, int depth) {
        this.value = value.replace("\n", "\\n").replace("\"", "\\\"").replace("\t", "  ");
        this.depth = depth;
    }

    public JSONString(String value) {
        this(value, 0);
    }

    public String toString() {
        return "\"" + " ".repeat(this.depth * 2) + this.value + "\"";
    }
}
