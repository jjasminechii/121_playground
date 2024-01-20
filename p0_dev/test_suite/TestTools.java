// Import statements
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.lang.reflect.Method;

public class TestTools {
    public static final String CLASS_CONSTANT_REGEX = "\\s*public\\s+static\\s+final\\s+<type>\\s+<name>\\s*=\\s*[^;]*;\\s*";
    public static final String FILE_SCANNER_REGEX = "\\s*Scanner\\s+[a-zA-Z]+[a-zA-Z1-9]*\\s*=\\s*new\\s+Scanner\\s*\\(\\s*(?!(System\\.in)\\s*\\)).*;\\s*";
    public static final String CLASS_CONSTANT_DECLARATION = "public static final <type> <name> = <value>;";
    public static final String FILE_SCANNER_DECLARATION = "Scanner <name> = new Scanner(<path>);";
    public static final String CLASS_DECLARATION_REGEX = "\\s*public\\s+class\\s+[a-zA-Z]+[a-zA-Z1-9]*\\s*\\{\\s*";
    public static final String COPY_FILE_NAME = "Temp.java";

    // TODO: document
    public static boolean checkFileExist(String fileName) {
        return new File(fileName).exists();
    }

    public static void cleanup() throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder(new String[] { "rm", "-f", "Temp.java" });

        // Start the process
        Process process = processBuilder.start();

        // Wait for the process to complete
        int exitCode = process.waitFor();
    }

    public static void copyFile(String sourcePath) {
        try {
            Path source = Paths.get(sourcePath);
            Path destination = Paths.get(COPY_FILE_NAME);

            // Standard copy option to replace an existing file
            CopyOption[] options = new CopyOption[] {
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
            };

            // Perform the copy operation
            Files.copy(source, destination, options);
            changeClassHeader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Method checkMethodExist(String className, String methodName, Class<?> returnType,
            Class<?>[] parameterTypes)
            throws Exception {
        // Get the Class object for the given class name
        Class<?> clazz = Class.forName(className);

        // Get all declared methods in the class
        Method[] methods = clazz.getDeclaredMethods();

        // Iterate through each method to check for method existence
        for (Method method : methods) {
            if (method.getName().equals(methodName)
                    && method.getReturnType().equals(returnType)
                    && parameterTypesMatch(method.getParameterTypes(), parameterTypes)) {
                return method; // Method found
            }
        }
        return null; // Method not found
    }

    // Helper method to check if parameter types match
    private static boolean parameterTypesMatch(Class<?>[] types1, Class<?>[] types2) {
        if (types1.length != types2.length) {
            return false;
        }

        for (int i = 0; i < types1.length; i++) {
            if (!types1[i].equals(types2[i])) {
                return false;
            }
        }
        return true;
    }

    public static String runWithoutRedirect(String[] commandAndArgs) throws Exception {
        // Specify the command and arguments
        ProcessBuilder processBuilder = new ProcessBuilder(commandAndArgs);

        // Start the process
        Process process = processBuilder.start();
        processBuilder.redirectErrorStream(true);

        // Read the process output
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n"); // Append process output
        }

        // Wait for the process to complete
        int exitCode = process.waitFor();

        // Check if the process exited normally
        return output.toString(); // Return output as a String
    }

    public static String runWithRedirect(String[] commandAndArgs, String inputFilePath) throws Exception {
        // Specify the command and arguments
        ProcessBuilder processBuilder = new ProcessBuilder(commandAndArgs);

        // Set the input file to read from
        File inputFile = new File(inputFilePath);
        processBuilder.redirectInput(inputFile);
        processBuilder.redirectErrorStream(true);

        // Start the process
        Process process = processBuilder.start();

        // Read the process output
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n"); // Append process output
        }

        // Wait for the process to complete
        int exitCode = process.waitFor();

        // Check if the process exited normally
        return output.toString(); // Return output as a String

    }

    private static void changeClassHeader() throws IOException {
        File inputFile = new File(COPY_FILE_NAME);
        File copyFile = new File("copy");

        BufferedReader readStudentCode = new BufferedReader(new FileReader(COPY_FILE_NAME));
        PrintStream printChangedCode = new PrintStream(copyFile);

        String fileCurLine = readStudentCode.readLine();

        boolean stopMatch = false;
        while (fileCurLine != null) {
            if (!stopMatch && fileCurLine.matches(CLASS_DECLARATION_REGEX)) {
                stopMatch = true;
                // Get the student's scanner name
                Scanner findStudentScanner = new Scanner(fileCurLine);
                // Get the "Scanner" away
                findStudentScanner.next();

                printChangedCode.println("public class Temp {");
            } else {
                printChangedCode.println(fileCurLine);
            }

            fileCurLine = readStudentCode.readLine();
        }

        // Cleanup
        printChangedCode.close();
        readStudentCode.close();

        if (inputFile.delete()) {
            if (!copyFile.renameTo(inputFile)) {
                throw new IOException("Could not rename the temp file to the original file");
            }
        } else {
            throw new IOException("Could not delete the original file");
        }
    }

    // TODO: document
    public static boolean checkConstantExist(Scanner readStudentFile, Map<String, String> allExpectedConstant,
            Map<String, Boolean> constantFound) {
        int classConstantFound = 0;
        // Copy all keys to constantFound map
        for (String curClassConstantName : allExpectedConstant.keySet()) {
            constantFound.put(curClassConstantName, false);
        }

        while (readStudentFile.hasNextLine()) {
            String curLine = readStudentFile.nextLine();

            // To improve efficiency, check if the line contains the keyword "final".
            // If not, don't bother checking with the regex.
            if (curLine.contains("final")) {
                for (String curClassConstantName : allExpectedConstant.keySet()) {
                    String curClassConstantType = allExpectedConstant.get(curClassConstantName);
                    String curConstantRegex = CLASS_CONSTANT_REGEX.replace("<type>", curClassConstantType)
                            .replace("<name>", curClassConstantName);

                    if (!curLine.matches(curConstantRegex)) {
                        classConstantFound++;
                        constantFound.put(curClassConstantName, true);
                    }
                }
            }
        }

        return classConstantFound == allExpectedConstant.size();
    }

    public static String readFile(String fileName) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        StringBuilder result = new StringBuilder();

        // Start reading
        String fileCurLine = fileReader.readLine();

        while (fileCurLine != null) {
            result.append(fileCurLine);
            result.append("\n");

            fileCurLine = fileReader.readLine();
        }

        // Cleanup
        fileReader.close();

        return result.toString();
    }

    // TODO: document
    public static boolean checkFileSimilar(String file1Name, String file2Name) throws IOException {
        BufferedReader file1Reader = new BufferedReader(new FileReader(file1Name));
        BufferedReader file2Reader = new BufferedReader(new FileReader(file2Name));

        // Start reading
        String file1CurLine = file1Reader.readLine();
        String file2CurLine = file2Reader.readLine();

        while (file1CurLine != null && file2CurLine != null) {
            if (!file1CurLine.equals(file2CurLine)) {
                // Cleanup
                file1Reader.close();
                file2Reader.close();
                return false;
            }

            file1CurLine = file1Reader.readLine();
            file2CurLine = file2Reader.readLine();
        }

        // Cleanup
        file1Reader.close();
        file2Reader.close();

        // Make sure that both files end at the same time
        return (file1CurLine == null) && (file2CurLine == null);
    }

    // TODO: document
    public static String getStackTrace(Throwable e) {
        String exceptionMessage = e.toString();
        StackTraceElement[] stackTrace = e.getStackTrace();

        String result = exceptionMessage.toString() + "\\n";

        for (int i = 0; i < stackTrace.length; i++) {
            String curStackTrace = stackTrace[i].toString().toLowerCase();
            if (curStackTrace.contains("reflect") || curStackTrace.contains("test")) {
                break;
            }
            result += "    " + stackTrace[i].toString() + "\\n";
        }

        return result;
    }

    public static boolean changeClassConstant(String type, String name, String newVal)
            throws IOException {
        boolean result = false;
        File inputFile = new File(COPY_FILE_NAME);
        File copyFile = new File("copy");
        BufferedReader readStudentCode = new BufferedReader(new FileReader(COPY_FILE_NAME));
        PrintStream printChangedCode = new PrintStream(copyFile);

        String finalizedConstantRegex = CLASS_CONSTANT_REGEX.replace("<type>", type).replace("<name>", name);
        String fileCurLine = readStudentCode.readLine();

        while (fileCurLine != null) {
            if (!result && fileCurLine.matches(finalizedConstantRegex)) {
                printChangedCode.println(CLASS_CONSTANT_DECLARATION.replace("<type>", type).replace("<name>", name)
                        .replace("<value>", newVal));
                result = true;
            } else {
                printChangedCode.println(fileCurLine);
            }

            fileCurLine = readStudentCode.readLine();
        }

        // Cleanup
        printChangedCode.close();
        readStudentCode.close();

        if (inputFile.delete()) {
            if (!copyFile.renameTo(inputFile)) {
                throw new IOException("Could not rename the temp file to the original file");
            }
        } else {
            throw new IOException("Could not delete the original file");
        }

        return result;
    }

    public static boolean changeVariableValue(String variableType, String variableName, String newValue) throws IOException {
        boolean result = false;
        File inputFile = new File(COPY_FILE_NAME);
        File copyFile = new File("copy");
        BufferedReader readStudentCode = new BufferedReader(new FileReader(COPY_FILE_NAME));
        PrintStream printChangedCode = new PrintStream(copyFile);
    
        String fileCurLine;
        String variableDeclarationRegex = "\\b" + variableType + "\\s+" + variableName + "\\s*\\=.*;";
    
        while ((fileCurLine = readStudentCode.readLine()) != null) {
            if (!result && !fileCurLine.trim().startsWith("//") && fileCurLine.matches(variableDeclarationRegex)) {
                printChangedCode.println(variableType + " " + variableName + " = " + newValue + ";");
                result = true;
            } else {
                printChangedCode.println(fileCurLine);
            }
        }
    
        // Cleanup
        printChangedCode.close();
        readStudentCode.close();
    
        // Rename and replace original file
        if (inputFile.delete()) {
            if (!copyFile.renameTo(inputFile)) {
                throw new IOException("Could not rename the copy file to the original file");
            }
        } else {
            throw new IOException("Could not delete the original file");
        }
    
        return result;
    }
    
    
    
    

    public static boolean checkVariableDeclarations(String filePath, String variableName) throws IOException {
        Path path = Paths.get(filePath);
        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
    
        String fileCurLine;
        // Regex pattern to handle variable declaration with any spacing around '='
        String declarationRegex = "\\bdouble\\s+" + Pattern.quote(variableName) + "\\s*\\=\\s*[^;]*;";
        Pattern declarationPattern = Pattern.compile(declarationRegex);
        boolean declarationFound = false;
    
        while ((fileCurLine = reader.readLine()) != null) {
            if (fileCurLine.trim().startsWith("//")) {
                continue;
            }
    
            Matcher matcher = declarationPattern.matcher(fileCurLine);
            if (matcher.find()) {
                declarationFound = true;
                break;
            }
        }
    
        reader.close();
        return declarationFound;
    }
    
    
    
    
    public static boolean changeFileScanner(String newPath) throws IOException {
        boolean result = false;
        File inputFile = new File(COPY_FILE_NAME);
        File copyFile = new File("copy");
        BufferedReader readStudentCode = new BufferedReader(new FileReader(COPY_FILE_NAME));
        PrintStream printChangedCode = new PrintStream(copyFile);

        String fileCurLine = readStudentCode.readLine();

        while (fileCurLine != null) {
            if (!result && fileCurLine.matches(FILE_SCANNER_REGEX)) {
                // Get the student's scanner name
                Scanner findStudentScanner = new Scanner(fileCurLine);
                // Get the "Scanner" away
                findStudentScanner.next();

                printChangedCode
                        .println(FILE_SCANNER_DECLARATION.replace("<name>", findStudentScanner.next()).replace("<path>",
                                "new File(\"" + newPath + "\")"));

                result = true;
            } else {
                printChangedCode.println(fileCurLine);
            }

            fileCurLine = readStudentCode.readLine();
        }

        // Cleanup
        printChangedCode.close();
        readStudentCode.close();

        if (inputFile.delete()) {
            if (!copyFile.renameTo(inputFile)) {
                throw new IOException("Could not rename the temp file to the original file");
            }
        } else {
            throw new IOException("Could not delete the original file");
        }

        return result;
    }

}
