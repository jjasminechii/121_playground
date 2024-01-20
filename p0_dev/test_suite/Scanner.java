/*
 * This Scanner class shadows Java's actual Scanner class.
 * When student calls new Scanner(...), this Scanner class will
 * be called as long as student uses import java.util.* and NOT
 * import java.util.Scanner.
 *
 * IMPORTANT NOTE: This shadowing only applies when the student
 * use the "Mark" button on Ed. For more info, please refer to
 * the README.md documentation on the top level directory.
 *
 * This Scanner behaves exactly like Java's Scanner but it prints
 * out any user input it takes from STDIN to STDOUT.
 *
 * Previously developed by: Aidan & Sumant
 * Re-documented by: Nic
 */

import java.util.*;
import java.io.*;

public class Scanner {
    // Uses an actual Scanner from Java's stdlib
    private java.util.Scanner scan;
    // Checks if the Scanner needs to print the user input to STDOUT
    private boolean print;

    /**
     * Creates a Scanner object that reads from a file
     *
     * @param source the input file to read
     * @throws FileNotFoundException if file is not found
     */
    public Scanner(File source) throws FileNotFoundException {
        scan = new java.util.Scanner(source);
    }

    /**
     * Creates a Scanner object to read from System.in or any other InputStream
     * source.
     *
     * @param source any InputStream derivatives to read (including System.in)
     */
    public Scanner(InputStream source) {
        scan = new java.util.Scanner(source);

        // If InputStream is System.in, activate print
        print = source.equals(System.in);
    }

    /**
     * Creates a Scanner object that reads from a String
     *
     * @param source string to read over
     */
    public Scanner(String source) {
        scan = new java.util.Scanner(source);
    }

    /**
     * Java's standard hasNext() method
     *
     * @return true if there's more token to take
     */
    public boolean hasNext() {
        return scan.hasNext();
    }

    /**
     * Java's standard next() method
     *
     * @return the next token (String)
     */
    public String next() {
        String result = scan.next();
        if (print) {
            System.out.println(result);
        }
        return result;
    }

    /**
     * Java's standard hasNext() method
     *
     * @return true if there's more line to take
     */
    public boolean hasNextLine() {
        return scan.hasNextLine();
    }

    /**
     * Java's standard next() method
     *
     * @return the next line (String)
     */
    public String nextLine() {
        String result = scan.nextLine();
        if (print) {
            System.out.println(result);
        }
        return result;
    }

    /**
     * Java's standard hasNextInt() method
     *
     * @return true if the next token can be read as an int
     */
    public boolean hasNextInt() {
        return scan.hasNextInt();
    }

    /**
     * Java's standard nextInt() method
     *
     * @return the next token read as an int
     */
    public int nextInt() {
        int result = scan.nextInt();
        if (print) {
            System.out.println(result);
        }
        return result;
    }

    /**
     * Java's standard hasNextDouble() method
     *
     * @return true if the next token can be read as a double
     */
    public boolean hasNextDouble() {
        return scan.hasNextDouble();
    }

    /**
     * Java's standard nextDouble() method
     *
     * @return the next token read as a double
     */
    public double nextDouble() {
        String result = scan.next(); // Have to do this to preserve any trailing 0s
        if (print) {
            System.out.println(result);
        }
        return Double.parseDouble(result);
    }
}