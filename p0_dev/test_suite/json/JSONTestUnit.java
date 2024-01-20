package json;

// TODO: document that JSONTestUnit is depth 2 default
// TODO: define what depth is in markdown
public class JSONTestUnit extends JSONType {
    public final String[] AVAILABLE_KEYS = {
            "name",
            "hidden",
            "private",
            "score",
            "ok",
            "passed",
            "feedback",
            "expected",
            "observed",
            "expand_feedback"
    };
    public final int DEPTH = 2; // For indentation formatting

    public JSONObject unitTestJSON;

    public JSONTestUnit(String testName) {
        this.unitTestJSON = new JSONObject();
        this.unitTestJSON.append("name", new JSONString(testName));
    }

    public JSONType getValue(String key) {
        return this.unitTestJSON.getValue(key);
    }

    public void append(String key, JSONType values) {
        if (!keyIsValid(key)) {
            throw new IllegalArgumentException("ERROR: Passed key is not one of the 10 unit test keys.");
        } else if (!valueIsValid(key, values)) {
            throw new IllegalArgumentException("ERROR: Passed value has incorrect JSONType in relation to passed key");
        }

        this.unitTestJSON.append(key, values);
    }

    public String toString() {
        String result = " ".repeat(DEPTH * 2) + "{\n";
        result += " ".repeat((DEPTH + 1) * 2) + "\"name\": " + this.unitTestJSON.getValue("name").toString();

        // Loop through the testing keys in the order defined in AVAILABLE_KEYS
        for (int i = 1; i < AVAILABLE_KEYS.length; i++) {
            String curKey = AVAILABLE_KEYS[i];

            // Check if the current key is active
            if (this.unitTestJSON.containsKey(curKey)) {
                result += ",\n" + " ".repeat((DEPTH + 1) * 2) + "\"" + curKey + "\": "
                        + this.unitTestJSON.getValue(curKey).toString();
            }
        }

        result += "\n" + " ".repeat(DEPTH * 2) + "}";
        return result;
    }

    // TODO: document that this is case insensitive
    private boolean keyIsValid(String key) {
        for (int i = 0; i < AVAILABLE_KEYS.length; i++) {
            if (key.equals(AVAILABLE_KEYS[i])) {
                return true;
            }
        }

        return false;
    }

    private boolean valueIsValid(String key, JSONType value) {
        if (key.equals(AVAILABLE_KEYS[0])) {
            return value instanceof JSONString;
        } else if (key.equals(AVAILABLE_KEYS[1])) {
            return value instanceof JSONBool;
        } else if (key.equals(AVAILABLE_KEYS[2])) {
            return value instanceof JSONBool;
        } else if (key.equals(AVAILABLE_KEYS[3])) {
            return value instanceof JSONInt;
        } else if (key.equals(AVAILABLE_KEYS[4])) {
            return value instanceof JSONBool;
        } else if (key.equals(AVAILABLE_KEYS[5])) {
            return value instanceof JSONBool;
        } else if (key.equals(AVAILABLE_KEYS[6])) {
            return value instanceof JSONString;
        } else if (key.equals(AVAILABLE_KEYS[7])) {
            return value instanceof JSONString;
        } else if (key.equals(AVAILABLE_KEYS[8])) {
            return value instanceof JSONString;
        } else {
            return value instanceof JSONBool;
        }
    }

}
