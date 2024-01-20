package json;

// Import Statement
import java.util.*;

public class JSONObject extends JSONType {
    public Map<String, JSONType> keyValues;

    public JSONObject(int depth) {
        this.keyValues = new HashMap<String, JSONType>();
        this.depth = depth;
    }

    public JSONObject() {
        this(0);
    }

    // TODO: documentation
    public void append(String key, JSONType values) {
        this.keyValues.put(key, values);
    }

    public boolean containsKey(String key) {
        return this.keyValues.containsKey(key);
    }

    public JSONType getValue(String key) {
        return this.keyValues.get(key);
    }

    public int numOfActiveKeys() {
        return this.keyValues.size();
    }

    // TODO: documentation
    public String toString() {
        String result = " ".repeat(this.depth * 2) + "{\n";
        Set<String> allKeys = this.keyValues.keySet();
        Iterator<String> allKeysIterator = allKeys.iterator();

        // Get the first key-value pair
        if (allKeysIterator.hasNext()) {
            String firstKey = allKeysIterator.next();
            String firstValue = this.keyValues.get(firstKey).toString();

            result += " ".repeat((this.depth + 1) * 2) + "\"" + firstKey + "\": " + firstValue;
        }

        // Get any remaining key-value pair
        while (allKeysIterator.hasNext()) {
            String curKey = allKeysIterator.next();
            String curValue = this.keyValues.get(curKey).toString();

            result += ",\n" + " ".repeat((this.depth + 1) * 2) + "\"" + curKey + "\": " + curValue;
        }

        result += "\n" + " ".repeat(this.depth * 2) + "}";
        return result;
    }
}