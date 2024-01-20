import json.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RoundingTest {
    public static final Map<String, String> expectedOutputs = new HashMap<>();
                static {
        expectedOutputs.put("0.0", "Our original number: 0.0\nOur rounded number: 0\n");
        expectedOutputs.put("0.1", "Our original number: 0.1\nOur rounded number: 0\n");
        expectedOutputs.put("0.2", "Our original number: 0.2\nOur rounded number: 0\n");
        expectedOutputs.put("0.3", "Our original number: 0.3\nOur rounded number: 0\n");
        expectedOutputs.put("0.4", "Our original number: 0.4\nOur rounded number: 0\n");
        expectedOutputs.put("0.5", "Our original number: 0.5\nOur rounded number: 1\n");
        expectedOutputs.put("0.6", "Our original number: 0.6\nOur rounded number: 1\n");
        expectedOutputs.put("0.7", "Our original number: 0.7\nOur rounded number: 1\n");
        expectedOutputs.put("0.8", "Our original number: 0.8\nOur rounded number: 1\n");
        expectedOutputs.put("0.9", "Our original number: 0.9\nOur rounded number: 1\n");
        expectedOutputs.put("1.0", "Our original number: 1.0\nOur rounded number: 1\n");
        expectedOutputs.put("101.71", "Our original number: 101.71\nOur rounded number: 102\n");
    }

    @Test(testName = "Check Variable Declarations", testGroupName = "Structure Test")
    public JSONTestUnit testVariableDeclarations() {
        JSONTestUnit result = new JSONTestUnit("Check Variable Declarations");

        try {
            TestTools.copyFile("Rounding.java");

            //boolean declarationsCorrect = TestTools.checkVariableDeclarations("Temp.java", "number", "roundedNumber");
            boolean declarationsCorrect = TestTools.checkVariableDeclarations("Temp.java", "number");
            result.append("passed", new JSONBool(declarationsCorrect));
            result.append("ok", new JSONBool(true));

            if (!declarationsCorrect) {
                result.append("feedback", new JSONString("Variable declaration for 'number' is incorrect or missing!"
                + " Make sure it is declared as exactly double number"));
            }
            TestTools.cleanup();

        } catch (Exception e) {
            String errorStackTrace = TestTools.getStackTrace(e);
            result.append("feedback", new JSONString(errorStackTrace));
            result.append("passed", new JSONBool(false));
        }

        return result;
    }

    public JSONTestUnit performTest(String testName, String inputValue, String outputFileName) {
        JSONTestUnit result = new JSONTestUnit(testName);
        try {
            TestTools.copyFile("Rounding.java");
            TestTools.changeVariableValue("double", "number", inputValue);
            String studentOutput = TestTools.runWithoutRedirect(new String[] {"java", "Temp.java"});
            
            String expectedOutput = expectedOutputs.get(inputValue);

            boolean matches = studentOutput.equals(expectedOutput);
            result.append("passed", new JSONBool(matches));
            result.append("ok", new JSONBool(true));

            if (!matches) {
                result.append("passed", new JSONBool(false));
                result.append("expected", new JSONString(expectedOutput));
                result.append("observed", new JSONString(studentOutput));
            }
            result.append("passed", new JSONBool(false));
            TestTools.cleanup();

        } catch (Exception e) {
            String errorStackTrace = TestTools.getStackTrace(e);
            result.append("feedback", new JSONString(errorStackTrace));
            result.append("passed", new JSONBool(false));
        }
        return result;
    }

    @Test(testName = "Rounding Test for 0.0 -> 0", testGroupName = "Output Test")
    public JSONTestUnit testRoundingOutput0() {
        return performTest("Rounding Test for 0.0 -> 0", "0.0", "rounding_00.out");
    }

    @Test(testName = "Rounding Test for 0.1 -> 0", testGroupName = "Output Test")
    public JSONTestUnit testRoundingOutput1() {
        return performTest("Rounding Test for 0.1 -> 0", "0.1", "rounding_01.out");
    }

    @Test(testName = "Rounding Test for 0.2 -> 0", testGroupName = "Output Test")
    public JSONTestUnit testRoundingOutput2() {
        return performTest("Rounding Test for 0.2 -> 0", "0.2", "rounding_02.out");
    }

    @Test(testName = "Rounding Test for 0.3 -> 0", testGroupName = "Output Test")
    public JSONTestUnit testRoundingOutput3() {
        return performTest("Rounding Test for 0.3 -> 0", "0.3", "rounding_03.out");
    }

    @Test(testName = "Rounding Test for 0.4 -> 0", testGroupName = "Output Test")
    public JSONTestUnit testRoundingOutput4() {
        return performTest("Rounding Test for 0.4 -> 0", "0.4", "rounding_04.out");
    }

    @Test(testName = "Rounding Test for 0.5 -> 1", testGroupName = "Output Test")
    public JSONTestUnit testRoundingOutput5() {
        return performTest("Rounding Test for 0.5 -> 1", "0.5", "rounding_05.out");
    }

    @Test(testName = "Rounding Test for 0.6 -> 1", testGroupName = "Output Test")
    public JSONTestUnit testRoundingOutput6() {
        return performTest("Rounding Test for 0.6 -> 1", "0.6", "rounding_06.out");
    }

    @Test(testName = "Rounding Test for 0.7 -> 1", testGroupName = "Output Test")
    public JSONTestUnit testRoundingOutput7() {
        return performTest("Rounding Test for 0.7 -> 1", "0.7", "rounding_07.out");
    }

    @Test(testName = "Rounding Test for 0.8 -> 1", testGroupName = "Output Test")
    public JSONTestUnit testRoundingOutput8() {
        return performTest("Rounding Test for 0.8 -> 1", "0.8", "rounding_08.out");
    }

    @Test(testName = "Rounding Test for 0.9 -> 1", testGroupName = "Output Test")
    public JSONTestUnit testRoundingOutput9() {
        return performTest("Rounding Test for 0.9 -> 1", "0.9", "rounding_09.out");
    }

    @Test(testName = "Rounding Test for 1.0 -> 1", testGroupName = "Output Test")
    public JSONTestUnit testRoundingOutput10() {
        return performTest("Rounding Test for 1.0 -> 1", "1.0", "rounding_10.out");
    }

    @Test(testName = "Rounding Test for 3.3 -> 3", testGroupName = "Output Test")
    public JSONTestUnit testRoundingOutput11() {
        return performTest("Rounding Test for 3.3 -> 3", "3.3", "rounding_33.out");
    }

    @Test(testName = "Rounding Test for 10.8 -> 11", testGroupName = "Output Test")
    public JSONTestUnit testRoundingOutput12() {
        return performTest("Rounding Test for 10.8 -> 11", "10.8", "rounding_108.out");
    }

    @Test(testName = "Rounding Test for 20.0 -> 20", testGroupName = "Output Test")
    public JSONTestUnit testRoundingOutput13() {
        return performTest("Rounding Test for 20.0 -> 20", "20.0", "rounding_200.out");
    }

    @Test(testName = "Rounding Test for 101.7 -> 102", testGroupName = "Output Test")
    public JSONTestUnit testRoundingOutput14() {
        return performTest("Rounding Test for 101.7 -> 102", "101.7", "rounding_1017.out");
    }
}
