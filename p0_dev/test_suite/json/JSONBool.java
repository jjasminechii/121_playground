package json;
/*
Wrapper class for boolean type.
 */

public class JSONBool extends JSONType {
    public boolean value;

    public JSONBool(boolean value, int depth) {
        this.value = value;
        this.depth = depth;
    }

    public JSONBool(boolean value) {
        this(value, 0);
    }

    public String toString() {
        return " ".repeat(this.depth * 2) + this.value;
    }
}