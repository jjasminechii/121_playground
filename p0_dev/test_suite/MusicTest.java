import json.*;
import java.lang.reflect.Method;
import java.util.*;
import java.io.*;

public class MusicTest {
    private Method studentComposeSongMethod;

    @Test(testName = "Check composeSong method exist")
    public JSONTestUnit test1() {
        JSONTestUnit result = new JSONTestUnit("[Preliminary Check] check composeSong method exist");
        try {
            studentComposeSongMethod = TestTools.checkMethodExist("Music", "composeSong", String[][].class, new Class<?>[] {Scanner.class});
            boolean methodExist = studentComposeSongMethod != null;
            result.append("passed", new JSONBool(methodExist));
            result.append("ok", new JSONBool(true));
            
            if (!methodExist) {
                String feedback = "composeSong method with:\\n  - Return type: String[][]\\n  - Parameter: Scanner\\nis NOT found.\\nMake sure you don't have more than 1 parameter!";
                result.append("feedback", new JSONString(feedback));
            }
        } catch (Exception e) {
            result.append("passed", new JSONBool(false));
            result.append("feedback", new JSONString(TestTools.getStackTrace(e)));
        }
        return result;
    }

    @Test(testName = "[EMPTY MELODY] num of melodies = 0, length of melody = 0", testGroupName = "Output Test")
    public JSONTestUnit test2() {
        JSONTestUnit result = new JSONTestUnit("[EMPTY MELODY] Output Test");
    
        try {
            // Copy student file -> Temp.java
            TestTools.copyFile("Music.java");

            // Run java program
            String studentOutput = TestTools.runWithRedirect(new String[] { "java", "Temp.java" }, "ioresources/allzero.in");

            // Get expected output
            String expectedOutput = TestTools.readFile("ioresources/allzero.out");
            result.append("feedback", new JSONString("User input:\\n0\\n0"));

            boolean matches = studentOutput.equals(expectedOutput);
            result.append("passed", new JSONBool(matches));
            result.append("ok", new JSONBool(true));
            if (!matches) {
                result.append("expected", new JSONString(expectedOutput));
                result.append("observed", new JSONString(studentOutput));
            }

            // TODO: cleanup file
            TestTools.cleanup();

        } catch (Exception e) {
            String errorStackTrace = TestTools.getStackTrace(e);
            result.append("feedback", new JSONString(errorStackTrace));
        }

        return result;
    }

    @Test(testName = "[EMPTY MELODY] num of melodies = 0, length of melody = 0", testGroupName = "Return Test")
    public JSONTestUnit test21() {
        JSONTestUnit result = new JSONTestUnit("[EMPTY MELODY] Return Test");
        result.append("ok", new JSONBool(true));

        // Prepare to redirect System.out
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        try {
            // Check if the composeSong method exist or not
            if (studentComposeSongMethod == null) {
                result.append("passed", new JSONBool(false));
                result.append("feedback", new JSONString("No composeSong method is found."));
                return result;
            }

            result.append("feedback", new JSONString("This test checks the content of the 2D array returned by your composeSong method.\\n \\nThe user input used for this test is the same as the user input used in the [EMPTY MELODY] Output Test"));

            

            // Create a scanner that we will pass to the student's method
            Scanner customTestScanner = new Scanner("0\n0\n");

            // Invoke the method
            String[][] studentArrayResult = (String[][]) studentComposeSongMethod.invoke(null, customTestScanner);

            // Restore System.out
            System.setOut(originalOut);

            // Get the deepToString of the student's 2D array
            String studentArrayStringResult = Arrays.deepToString(studentArrayResult);

            // Prepare expected result
            String expectedArrayStringResult = "[]";


            if (studentArrayStringResult.equals(expectedArrayStringResult)) {
                result.append("passed", new JSONBool(true));
            } else {
                result.append("passed", new JSONBool(false));
                result.append("expected", new JSONString(expectedArrayStringResult + "\\n"));
                result.append("observed", new JSONString(studentArrayStringResult + "\\n"));
            }
        } catch (Exception e) {
            // Restore System.out
            System.setOut(originalOut);
            
            String errorStackTrace = TestTools.getStackTrace(e.getCause());
            result.append("feedback", new JSONString("A runtime error has happened in your code:\\n \\n" + errorStackTrace));
        }

        return result;
    }

    @Test(testName = "[ONE NOTE: NATURAL] num of melodies = 1, length of melody = 1", testGroupName = "Output Test")
    public JSONTestUnit test3() {
        JSONTestUnit result = new JSONTestUnit("[ONE NOTE] Natural Note Output Test");
    
        try {
            // Copy student file -> Temp.java
            TestTools.copyFile("Music.java");

            // Run java program
            String studentOutput = TestTools.runWithRedirect(new String[] { "java", "Temp.java" }, "ioresources/onenatural.in");

            // Get expected output
            String expectedOutput = TestTools.readFile("ioresources/onenatural.out");
            result.append("feedback", new JSONString("User input:\\n1\\n1\\nA"));

            // Compare output
            boolean matches = studentOutput.equals(expectedOutput);
            result.append("passed", new JSONBool(matches));
            result.append("ok", new JSONBool(true));
            if (!matches) {
                result.append("expected", new JSONString(expectedOutput));
                result.append("observed", new JSONString(studentOutput));
            }

            // TODO: cleanup file
            TestTools.cleanup();

        } catch (Exception e) {
            String errorStackTrace = TestTools.getStackTrace(e);
            result.append("feedback", new JSONString(errorStackTrace));
        }

        return result;
    }

    @Test(testName = "[ONE NOTE: NATURAL] num of melodies = 1, length of melody = 1", testGroupName = "Return Test")
    public JSONTestUnit test31() {
        JSONTestUnit result = new JSONTestUnit("[ONE NOTE] Natural Note Return Test");
        result.append("ok", new JSONBool(true));

        // Prepare to redirect System.out
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        try {
            // Check if the composeSong method exist or not
            if (studentComposeSongMethod == null) {
                result.append("passed", new JSONBool(false));
                result.append("feedback", new JSONString("No composeSong method is found."));
                return result;
            }

            result.append("feedback", new JSONString("This test checks the content of the 2D array returned by your composeSong method.\\n \\nThe user input used for this test is the same as the user input used in the [ONE NOTE] Natural Note Output Test"));

            

            // Create a scanner that we will pass to the student's method
            Scanner customTestScanner = new Scanner("1\n1\nA");

            // Invoke the method
            String[][] studentArrayResult = (String[][]) studentComposeSongMethod.invoke(null, customTestScanner);

            // Restore System.out
            System.setOut(originalOut);

            // Get the deepToString of the student's 2D array
            String studentArrayStringResult = Arrays.deepToString(studentArrayResult);

            // Prepare expected result
            String expectedArrayStringResult = "[[A]]";


            if (studentArrayStringResult.equals(expectedArrayStringResult)) {
                result.append("passed", new JSONBool(true));
            } else {
                result.append("passed", new JSONBool(false));
                result.append("expected", new JSONString(expectedArrayStringResult + "\\n"));
                result.append("observed", new JSONString(studentArrayStringResult + "\\n"));
            }
        } catch (Exception e) {
            // Restore System.out
            System.setOut(originalOut);
            
            String errorStackTrace = TestTools.getStackTrace(e.getCause());
            result.append("feedback", new JSONString("A runtime error has happened in your code:\\n \\n" + errorStackTrace));
        }

        return result;
    }

    @Test(testName = "[ONE NOTE: SHARP] num of melodies = 1, length of melody = 1", testGroupName = "Output Test")
    public JSONTestUnit test4() {
        JSONTestUnit result = new JSONTestUnit("[ONE NOTE] Sharp Note Output Test");
    
        try {
            // Copy student file -> Temp.java
            TestTools.copyFile("Music.java");

            // Run java program
            String studentOutput = TestTools.runWithRedirect(new String[] { "java", "Temp.java" }, "ioresources/onesharp.in");

            // Get expected output
            String expectedOutput = TestTools.readFile("ioresources/onesharp.out");
            result.append("feedback", new JSONString("User input:\\n1\\n1\\nC♯"));

            // Compare output
            boolean matches = studentOutput.equals(expectedOutput);
            result.append("passed", new JSONBool(matches));
            result.append("ok", new JSONBool(true));
            if (!matches) {
                result.append("expected", new JSONString(expectedOutput));
                result.append("observed", new JSONString(studentOutput));
            }

            // TODO: cleanup file
            TestTools.cleanup();

        } catch (Exception e) {
            String errorStackTrace = TestTools.getStackTrace(e);
            result.append("feedback", new JSONString(errorStackTrace));
        }

        return result;
    }

    @Test(testName = "[ONE NOTE: SHARP] num of melodies = 1, length of melody = 1", testGroupName = "Return Test")
    public JSONTestUnit test41() {
        JSONTestUnit result = new JSONTestUnit("[ONE NOTE] Sharp Note Return Test");
        result.append("ok", new JSONBool(true));

        // Prepare to redirect System.out
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        try {
            // Check if the composeSong method exist or not
            if (studentComposeSongMethod == null) {
                result.append("passed", new JSONBool(false));
                result.append("feedback", new JSONString("No composeSong method is found."));
                return result;
            }

            result.append("feedback", new JSONString("This test checks the content of the 2D array returned by your composeSong method.\\n \\nThe user input used for this test is the same as the user input used in the [ONE NOTE] Sharp Note Output Test"));

            

            // Create a scanner that we will pass to the student's method
            Scanner customTestScanner = new Scanner("1\n1\nC♯");

            // Invoke the method
            String[][] studentArrayResult = (String[][]) studentComposeSongMethod.invoke(null, customTestScanner);

            // Restore System.out
            System.setOut(originalOut);

            // Get the deepToString of the student's 2D array
            String studentArrayStringResult = Arrays.deepToString(studentArrayResult);

            // Prepare expected result
            String expectedArrayStringResult = "[[C♯]]";


            if (studentArrayStringResult.equals(expectedArrayStringResult)) {
                result.append("passed", new JSONBool(true));
            } else {
                result.append("passed", new JSONBool(false));
                result.append("expected", new JSONString(expectedArrayStringResult + "\\n"));
                result.append("observed", new JSONString(studentArrayStringResult + "\\n"));
            }
        } catch (Exception e) {
            // Restore System.out
            System.setOut(originalOut);
            
            String errorStackTrace = TestTools.getStackTrace(e.getCause());
            result.append("feedback", new JSONString("A runtime error has happened in your code:\\n \\n" + errorStackTrace));
        }

        return result;
    }

    @Test(testName = "[ONE NOTE: FLAT] num of melodies = 1, length of melody = 1", testGroupName = "Output Test")
    public JSONTestUnit test5() {
        JSONTestUnit result = new JSONTestUnit("[ONE NOTE] Flat Note Output Test");
    
        try {
            // Copy student file -> Temp.java
            TestTools.copyFile("Music.java");

            // Run java program
            String studentOutput = TestTools.runWithRedirect(new String[] { "java", "Temp.java" }, "ioresources/oneflat.in");

            // Get expected output
            String expectedOutput = TestTools.readFile("ioresources/oneflat.out");
            result.append("feedback", new JSONString("User input:\\n1\\n1\\nF♭"));

            // Compare output
            boolean matches = studentOutput.equals(expectedOutput);
            result.append("passed", new JSONBool(matches));
            result.append("ok", new JSONBool(true));
            if (!matches) {
                result.append("expected", new JSONString(expectedOutput));
                result.append("observed", new JSONString(studentOutput));
            }

            // TODO: cleanup file
            TestTools.cleanup();

        } catch (Exception e) {
            String errorStackTrace = TestTools.getStackTrace(e);
            result.append("feedback", new JSONString(errorStackTrace));
        }

        return result;
    }

    @Test(testName = "[ONE NOTE: FLAT] num of melodies = 1, length of melody = 1", testGroupName = "Return Test")
    public JSONTestUnit test51() {
        JSONTestUnit result = new JSONTestUnit("[ONE NOTE] Flat Note Return Test");
        result.append("ok", new JSONBool(true));

        // Prepare to redirect System.out
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        try {
            // Check if the composeSong method exist or not
            if (studentComposeSongMethod == null) {
                result.append("passed", new JSONBool(false));
                result.append("feedback", new JSONString("No composeSong method is found."));
                return result;
            }

            result.append("feedback", new JSONString("This test checks the content of the 2D array returned by your composeSong method.\\n \\nThe user input used for this test is the same as the user input used in the [ONE NOTE] Flat Note Output Test"));

            

            // Create a scanner that we will pass to the student's method
            Scanner customTestScanner = new Scanner("1\n1\nF♭");

            // Invoke the method
            String[][] studentArrayResult = (String[][]) studentComposeSongMethod.invoke(null, customTestScanner);

            // Restore System.out
            System.setOut(originalOut);

            // Get the deepToString of the student's 2D array
            String studentArrayStringResult = Arrays.deepToString(studentArrayResult);

            // Prepare expected result
            String expectedArrayStringResult = "[[F♭]]";


            if (studentArrayStringResult.equals(expectedArrayStringResult)) {
                result.append("passed", new JSONBool(true));
            } else {
                result.append("passed", new JSONBool(false));
                result.append("expected", new JSONString(expectedArrayStringResult + "\\n"));
                result.append("observed", new JSONString(studentArrayStringResult + "\\n"));
            }
        } catch (Exception e) {
            // Restore System.out
            System.setOut(originalOut);
            
            String errorStackTrace = TestTools.getStackTrace(e.getCause());
            result.append("feedback", new JSONString("A runtime error has happened in your code:\\n \\n" + errorStackTrace));
        }

        return result;
    }

     @Test(testName = "[MULTIPLE NOTE: NATURAL] num of melodies = 3, length of melody = 3", testGroupName = "Output Test")
    public JSONTestUnit test6() {
        JSONTestUnit result = new JSONTestUnit("[MULTIPLE NOTE] Natural Note Output Test");
    
        try {
            // Copy student file -> Temp.java
            TestTools.copyFile("Music.java");

            // Run java program
            String studentOutput = TestTools.runWithRedirect(new String[] { "java", "Temp.java" }, "ioresources/allnatural.in");

            // Get expected output
            String expectedOutput = TestTools.readFile("ioresources/allnatural.out");
            result.append("feedback", new JSONString("User input:\\n3\\n3\\nA\\nC\\nG\\nE\\nF\\nC\\nD\\nA\\nF"));

            // Compare output
            boolean matches = studentOutput.equals(expectedOutput);
            result.append("passed", new JSONBool(matches));
            result.append("ok", new JSONBool(true));
            if (!matches) {
                result.append("expected", new JSONString(expectedOutput));
                result.append("observed", new JSONString(studentOutput));
            }

            // TODO: cleanup file
            TestTools.cleanup();

        } catch (Exception e) {
            String errorStackTrace = TestTools.getStackTrace(e);
            result.append("feedback", new JSONString(errorStackTrace));
        }

        return result;
    }

    @Test(testName = "[MULTIPLE NOTE: NATURAL] num of melodies = 3, length of melody = 3", testGroupName = "Return Test")
    public JSONTestUnit test61() {
        JSONTestUnit result = new JSONTestUnit("[MULTIPLE NOTE] Natural Note Return Test");
        result.append("ok", new JSONBool(true));

        // Prepare to redirect System.out
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        try {
            // Check if the composeSong method exist or not
            if (studentComposeSongMethod == null) {
                result.append("passed", new JSONBool(false));
                result.append("feedback", new JSONString("No composeSong method is found."));
                return result;
            }

            result.append("feedback", new JSONString("This test checks the content of the 2D array returned by your composeSong method.\\n \\nThe user input used for this test is the same as the user input used in the [MULTIPLE NOTE] Natural Note Output Test"));

            

            // Create a scanner that we will pass to the student's method
            Scanner customTestScanner = new Scanner("3\n3\nA\nC\nG\nE\nF\nC\nD\nA\nF");

            // Invoke the method
            String[][] studentArrayResult = (String[][]) studentComposeSongMethod.invoke(null, customTestScanner);

            // Restore System.out
            System.setOut(originalOut);

            // Get the deepToString of the student's 2D array
            String studentArrayStringResult = Arrays.deepToString(studentArrayResult);

            // Prepare expected result
            String expectedArrayStringResult = "[[A, C, G], [E, F, C], [D, A, F]]";


            if (studentArrayStringResult.equals(expectedArrayStringResult)) {
                result.append("passed", new JSONBool(true));
            } else {
                result.append("passed", new JSONBool(false));
                result.append("expected", new JSONString(expectedArrayStringResult + "\\n"));
                result.append("observed", new JSONString(studentArrayStringResult + "\\n"));
            }
        } catch (Exception e) {
            // Restore System.out
            System.setOut(originalOut);
            
            String errorStackTrace = TestTools.getStackTrace(e.getCause());
            result.append("feedback", new JSONString("A runtime error has happened in your code:\\n \\n" + errorStackTrace));
        }

        return result;
    }

    @Test(testName = "[MULTIPLE NOTE: SHARP] num of melodies = 2, length of melody = 3", testGroupName = "Output Test")
    public JSONTestUnit test7() {
        JSONTestUnit result = new JSONTestUnit("[MULTIPLE NOTE] Sharp Note Output Test");
    
        try {
            // Copy student file -> Temp.java
            TestTools.copyFile("Music.java");

            // Run java program
            String studentOutput = TestTools.runWithRedirect(new String[] { "java", "Temp.java" }, "ioresources/allsharp.in");

            // Get expected output
            String expectedOutput = TestTools.readFile("ioresources/allsharp.out");
            result.append("feedback", new JSONString("User input:\\n2\\n3\\nA♯\\nB♯\\nC♯\\nD♯\\nE♯\\nF♯"));

            // Compare output
            boolean matches = studentOutput.equals(expectedOutput);
            result.append("passed", new JSONBool(matches));
            result.append("ok", new JSONBool(true));
            if (!matches) {
                result.append("expected", new JSONString(expectedOutput));
                result.append("observed", new JSONString(studentOutput));
            }

            // TODO: cleanup file
            TestTools.cleanup();

        } catch (Exception e) {
            String errorStackTrace = TestTools.getStackTrace(e);
            result.append("feedback", new JSONString(errorStackTrace));
        }

        return result;
    }

    @Test(testName = "[MULTIPLE NOTE: NATURAL] num of melodies = 2, length of melody = 3", testGroupName = "Return Test")
    public JSONTestUnit test71() {
        JSONTestUnit result = new JSONTestUnit("[MULTIPLE NOTE] Sharp Note Return Test");
        result.append("ok", new JSONBool(true));

        // Prepare to redirect System.out
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        try {
            // Check if the composeSong method exist or not
            if (studentComposeSongMethod == null) {
                result.append("passed", new JSONBool(false));
                result.append("feedback", new JSONString("No composeSong method is found."));
                return result;
            }

            result.append("feedback", new JSONString("This test checks the content of the 2D array returned by your composeSong method.\\n \\nThe user input used for this test is the same as the user input used in the [MULTIPLE NOTE] Sharp Note Output Test"));
        
            

            // Create a scanner that we will pass to the student's method
            Scanner customTestScanner = new Scanner("2\n3\nA♯\nB♯\nC♯\nD♯\nE♯\nF♯\n");

            // Invoke the method
            String[][] studentArrayResult = (String[][]) studentComposeSongMethod.invoke(null, customTestScanner);

            // Restore System.out
            System.setOut(originalOut);

            // Get the deepToString of the student's 2D array
            String studentArrayStringResult = Arrays.deepToString(studentArrayResult);

            // Prepare expected result
            String expectedArrayStringResult = "[[A♯, B♯, C♯], [D♯, E♯, F♯]]";


            if (studentArrayStringResult.equals(expectedArrayStringResult)) {
                result.append("passed", new JSONBool(true));
            } else {
                result.append("passed", new JSONBool(false));
                result.append("expected", new JSONString(expectedArrayStringResult + "\\n"));
                result.append("observed", new JSONString(studentArrayStringResult + "\\n"));
            }
        } catch (Exception e) {
            // Restore System.out
            System.setOut(originalOut);
            
            String errorStackTrace = TestTools.getStackTrace(e.getCause());
            result.append("feedback", new JSONString("A runtime error has happened in your code:\\n \\n" + errorStackTrace));
        }

        return result;
    }

    @Test(testName = "[MULTIPLE NOTE: FLAT] num of melodies = 2, length of melody = 3", testGroupName = "Output Test")
    public JSONTestUnit test8() {
        JSONTestUnit result = new JSONTestUnit("[MULTIPLE NOTE] Flat Note Output Test");
    
        try {
            // Copy student file -> Temp.java
            TestTools.copyFile("Music.java");

            // Run java program
            String studentOutput = TestTools.runWithRedirect(new String[] { "java", "Temp.java" }, "ioresources/allflat.in");

            // Get expected output
            String expectedOutput = TestTools.readFile("ioresources/allflat.out");
            result.append("feedback", new JSONString("User input:\\n2\\n3\\nC♭\\nD♭\\nE♭\\nF♭\\nG♭\\nA♭"));

            // Compare output
            boolean matches = studentOutput.equals(expectedOutput);
            result.append("passed", new JSONBool(matches));
            result.append("ok", new JSONBool(true));
            if (!matches) {
                result.append("expected", new JSONString(expectedOutput));
                result.append("observed", new JSONString(studentOutput));
            }

            // TODO: cleanup file
            TestTools.cleanup();

        } catch (Exception e) {
            String errorStackTrace = TestTools.getStackTrace(e);
            result.append("feedback", new JSONString(errorStackTrace));
        }

        return result;
    }   

    @Test(testName = "[MULTIPLE NOTE: Flat] num of melodies = 2, length of melody = 3", testGroupName = "Return Test")
    public JSONTestUnit test81() {
        JSONTestUnit result = new JSONTestUnit("[MULTIPLE NOTE] Flat Note Return Test");
        result.append("ok", new JSONBool(true));

        // Prepare to redirect System.out
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        try {
            // Check if the composeSong method exist or not
            if (studentComposeSongMethod == null) {
                result.append("passed", new JSONBool(false));
                result.append("feedback", new JSONString("No composeSong method is found."));
                return result;
            }

            result.append("feedback", new JSONString("This test checks the content of the 2D array returned by your composeSong method.\\n \\nThe user input used for this test is the same as the user input used in the [MULTIPLE NOTE] Flat Note Output Test"));

            

            // Create a scanner that we will pass to the student's method
            Scanner customTestScanner = new Scanner("2\n3\nC♭\nD♭\nE♭\nF♭\nG♭\nA♭\n");

            // Invoke the method
            String[][] studentArrayResult = (String[][]) studentComposeSongMethod.invoke(null, customTestScanner);

            // Restore System.out
            System.setOut(originalOut);

            // Get the deepToString of the student's 2D array
            String studentArrayStringResult = Arrays.deepToString(studentArrayResult);

            // Prepare expected result
            String expectedArrayStringResult = "[[C♭, D♭, E♭], [F♭, G♭, A♭]]";


            if (studentArrayStringResult.equals(expectedArrayStringResult)) {
                result.append("passed", new JSONBool(true));
            } else {
                result.append("passed", new JSONBool(false));
                result.append("expected", new JSONString(expectedArrayStringResult + "\\n"));
                result.append("observed", new JSONString(studentArrayStringResult + "\\n"));
            }
        } catch (Exception e) {
            // Restore System.out
            System.setOut(originalOut);
            
            String errorStackTrace = TestTools.getStackTrace(e.getCause());
            result.append("feedback", new JSONString("A runtime error has happened in your code:\\n \\n" + errorStackTrace));
        }

        return result;
    } 

    @Test(testName = "[MULTIPLE NOTE: MIXED NOTE] num of melodies = 3, length of melody = 2", testGroupName = "Output Test")
    public JSONTestUnit test9() {
        JSONTestUnit result = new JSONTestUnit("[MULTIPLE NOTE] Mixed Note Output Test");
    
        try {
            // Copy student file -> Temp.java
            TestTools.copyFile("Music.java");

            // Run java program
            String studentOutput = TestTools.runWithRedirect(new String[] { "java", "Temp.java" }, "ioresources/mixed.in");

            // Get expected output
            String expectedOutput = TestTools.readFile("ioresources/mixed.out");
            result.append("feedback", new JSONString("User input:\\n3\\n2\\nC\\nC♭\\nE\\nE♭\\nA\\nG♯"));

            // Compare output
            boolean matches = studentOutput.equals(expectedOutput);
            result.append("passed", new JSONBool(matches));
            result.append("ok", new JSONBool(true));
            if (!matches) {
                result.append("expected", new JSONString(expectedOutput));
                result.append("observed", new JSONString(studentOutput));
            }

            // TODO: cleanup file
            TestTools.cleanup();

        } catch (Exception e) {
            String errorStackTrace = TestTools.getStackTrace(e);
            result.append("feedback", new JSONString(errorStackTrace));
        }

        return result;
    }

    @Test(testName = "[MULTIPLE NOTE: MIXED NOTE] num of melodies = 3, length of melody = 2", testGroupName = "Return Test")
    public JSONTestUnit test91() {
        JSONTestUnit result = new JSONTestUnit("[MULTIPLE NOTE] Mixed Note Return Test");
        result.append("ok", new JSONBool(true));

        // Prepare to redirect System.out
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        try {
            // Check if the composeSong method exist or not
            if (studentComposeSongMethod == null) {
                result.append("passed", new JSONBool(false));
                result.append("feedback", new JSONString("No composeSong method is found."));
                return result;
            }

            result.append("feedback", new JSONString("This test checks the content of the 2D array returned by your composeSong method.\\n \\nThe user input used for this test is the same as the user input used in the [MULTIPLE NOTE] Mixed Note Output Test"));

            

            // Create a scanner that we will pass to the student's method
            Scanner customTestScanner = new Scanner("3\n2\nC\nC♭\nE\nE♭\nA\nG♯\n");

            // Invoke the method
            String[][] studentArrayResult = (String[][]) studentComposeSongMethod.invoke(null, customTestScanner);

            // Restore System.out
            System.setOut(originalOut);

            // Get the deepToString of the student's 2D array
            String studentArrayStringResult = Arrays.deepToString(studentArrayResult);

            // Prepare expected result
            String expectedArrayStringResult = "[[C, C♭], [E, E♭], [A, G♯]]";


            if (studentArrayStringResult.equals(expectedArrayStringResult)) {
                result.append("passed", new JSONBool(true));
            } else {
                result.append("passed", new JSONBool(false));
                result.append("expected", new JSONString(expectedArrayStringResult + "\\n"));
                result.append("observed", new JSONString(studentArrayStringResult + "\\n"));
            }
        } catch (Exception e) {
            // Restore System.out
            System.setOut(originalOut);
            
            String errorStackTrace = TestTools.getStackTrace(e.getCause());
            result.append("feedback", new JSONString("A runtime error has happened in your code:\\n \\n" + errorStackTrace));
        }

        return result;
    } 


    @Test(testName = "[MULTIPLE NOTE: NO NATURAL] num of melodies = 3, length of melody = 2", testGroupName = "Output Test")
    public JSONTestUnit test10() {
        JSONTestUnit result = new JSONTestUnit("[MULTIPLE NOTE] No Natural Note Output Test");
    
        try {
            // Copy student file -> Temp.java
            TestTools.copyFile("Music.java");

            // Run java program
            String studentOutput = TestTools.runWithRedirect(new String[] { "java", "Temp.java" }, "ioresources/mixed.in");

            // Get expected output
            String expectedOutput = TestTools.readFile("ioresources/mixed.out");
            result.append("feedback", new JSONString("User input:\\n2\\n3\\nF♯\\nD♭\\nG♯\\nA♯\\nC♯\\nB♭"));


            // Compare output
            boolean matches = studentOutput.equals(expectedOutput);
            result.append("passed", new JSONBool(matches));
            result.append("ok", new JSONBool(true));
            if (!matches) {
                result.append("expected", new JSONString(expectedOutput));
                result.append("observed", new JSONString(studentOutput));
            }

            // TODO: cleanup file
            TestTools.cleanup();

        } catch (Exception e) {
            String errorStackTrace = TestTools.getStackTrace(e);
            result.append("feedback", new JSONString(errorStackTrace));
        }

        return result;
    }

    @Test(testName = "[MULTIPLE NOTE: NO NATURAL] num of melodies = 2, length of melody = 3", testGroupName = "Return Test")
    public JSONTestUnit test101() {
        JSONTestUnit result = new JSONTestUnit("[MULTIPLE NOTE] No Natural Note Return Test");
        result.append("ok", new JSONBool(true));

        // Prepare to redirect System.out
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        try {
            // Check if the composeSong method exist or not
            if (studentComposeSongMethod == null) {
                result.append("passed", new JSONBool(false));
                result.append("feedback", new JSONString("No composeSong method is found."));
                return result;
            }

            result.append("feedback", new JSONString("This test checks the content of the 2D array returned by your composeSong method.\\n \\nThe user input used for this test is the same as the user input used in the [MULTIPLE NOTE] No Natural Note Output Test"));

            

            // Create a scanner that we will pass to the student's method
            Scanner customTestScanner = new Scanner("2\n3\nF♯\nD♭\nG♯\nA♯\nC♯\nB♭\n");

            // Invoke the method
            String[][] studentArrayResult = (String[][]) studentComposeSongMethod.invoke(null, customTestScanner);

            // Restore System.out
            System.setOut(originalOut);

            // Get the deepToString of the student's 2D array
            String studentArrayStringResult = Arrays.deepToString(studentArrayResult);

            // Prepare expected result
            String expectedArrayStringResult = "[[F♯, D♭, G♯], [A♯, C♯, B♭]]";


            if (studentArrayStringResult.equals(expectedArrayStringResult)) {
                result.append("passed", new JSONBool(true));
            } else {
                result.append("passed", new JSONBool(false));
                result.append("expected", new JSONString(expectedArrayStringResult + "\\n"));
                result.append("observed", new JSONString(studentArrayStringResult + "\\n"));
            }
        } catch (Exception e) {
            // Restore System.out
            System.setOut(originalOut);
            
            String errorStackTrace = TestTools.getStackTrace(e.getCause());
            result.append("feedback", new JSONString("A runtime error has happened in your code:\\n \\n" + errorStackTrace));
        }

        return result;
    } 
}
