package json;

// Import statements
import java.util.*;

public class JSONArray extends JSONType {
    public List<JSONType> values;

    public JSONArray(int depth) {
        this.values = new ArrayList<JSONType>();
        this.depth = depth;
    }

    public JSONArray() {
        this(0);
    }

    public void append(JSONType newValue) {
        this.values.add(newValue);
    }

    // TODO: documentation
    public String toString() {
        String result = "[\n";

        for (int i = 0; i < this.values.size() - 1; i++) {
            result += this.values.get(i).toString() + ",\n";
        }

        result += this.values.get(this.values.size() - 1).toString();
        result += "\n" + " ".repeat(this.depth * 2) + "]";

        return result;
    }
}