package json;

// Import statements
import java.util.*;

public class JSONTestCollection extends JSONType {
    public JSONObject mainJsonTestObject;

    public JSONTestCollection() {
        this.mainJsonTestObject = new JSONObject();
        mainJsonTestObject.append("testcases", new JSONArray(1));
    }

    // TODO: document
    public void addUnitTest(JSONTestUnit testUnits) {
        JSONArray unitTestCollections = (JSONArray) this.mainJsonTestObject.getValue("testcases");
        unitTestCollections.append(testUnits);
    }

    // TODO: document
    public void addUnitTest(List<JSONTestUnit> testUnits) {
        JSONArray unitTestCollections = (JSONArray) this.mainJsonTestObject.getValue("testcases");
        for (JSONTestUnit curNewUnitTest : testUnits) {
            unitTestCollections.append(curNewUnitTest);
        }
    }

    public String toString() {
        return this.mainJsonTestObject.toString();
    }
}