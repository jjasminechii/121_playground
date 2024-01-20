package json;
/*
Wrapper class for int type.
 */

public class JSONInt extends JSONType {
    public int value;

    public JSONInt(int value, int depth) {
        this.value = value;
        this.depth = depth;
    }

    public JSONInt(int value) {
        this(value, 0);
    }

    public String toString() {
        return " ".repeat(this.depth * 2) + this.value;
    }
}