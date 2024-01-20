import java.io.IOException;
import java.lang.reflect.*;
import json.*;
import java.nio.file.*;

public class TestRunner {
    public static void main(String[] args) throws Exception {
        JSONTestCollection allTestResult = new JSONTestCollection();

        // Get assignment specific test class
        Class<?> c0Test = Class.forName(args[0]);

        // Copying student file from path to current folder
        TestTools.copyFile(args[1]);

        // Find all methods in the test class
        Method[] allMethods = c0Test.getDeclaredMethods();

        // Instantiate the test class dynamically
        Object testInstance = c0Test.getDeclaredConstructor().newInstance();

        // Iterate through each of the method in the test class
        for (Method curMethod : allMethods) {
            // Ensure that the method is tagged with the test annotation
            if (curMethod.isAnnotationPresent(Test.class)) {
                // Execute the method and append the resulting JSONUnitTest into the JSONTestCollection
                JSONTestUnit curTestResult = (JSONTestUnit) curMethod.invoke(testInstance);
                allTestResult.addUnitTest(curTestResult);

                boolean testPassed = ((JSONBool) curTestResult.getValue("passed")).value;
                if (!testPassed) {
                    // If a test fails stop tests!
                    break;
                }
            }
        }

        System.out.println(allTestResult);
    }
}
