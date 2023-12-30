package music_box;
import java.util.*;

public class FullMusic {

    public static final String NOTES = "ABCDEFG";

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String notes = "";
        boolean isValidNoteString = true;

        while (isValidNoteString) {
            System.out.print("Enter a string of notes (A-G) or a non-note character to finish: ");
            String input = console.next().toUpperCase();

            if (!isNoteStringValid(input)) {
                isValidNoteString = false;
            } else {
                notes += input;
            }
        }

        // Find the most common note
        String mostCommonNote = mostCommonNote(notes);
        System.out.println("The most common note is: " + mostCommonNote);

        // Translate to Solfege and print
        String solfegeString = translateToSolfege(notes);
        System.out.println("Translated to Solfege: " + solfegeString);

        analyzeNoteDistribution(notes);
        analyzeNoteDistributionSplit(notes, 3);
    }

    // Method to check validity of note string
    public static boolean isNoteStringValid(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) < 'A' || input.charAt(i) > 'G') {
                return false;
            }
        }
        return true;
    }

    // Method to find the most common note
    public static String mostCommonNote(String notes) {
        int[] counts = countNotes(notes);
        return mostFrequent(counts);
    }

    // Counts occurrences of each note
    public static int[] countNotes(String notes) {
        int[] counts = new int[NOTES.length()];
        for (int i = 0; i < notes.length(); i++) {
            char note = notes.charAt(i);
            counts[NOTES.indexOf(note)]++;
        }
        return counts;
    }

    // Finds the most frequent note
    public static String mostFrequent(int[] counts) {
        int maxIndex = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > counts[maxIndex]) {
                maxIndex = i;
            }
        }
        return "" + NOTES.charAt(maxIndex);
    }

    // Translate notes to Solfege syllables
    public static String translateToSolfege(String notes) {
        String[] solfege = {"Do", "Re", "Mi", "Fa", "Sol", "La", "Si"};
        String translated = "";

        for (int i = 0; i < notes.length(); i++) {
            int index = notes.charAt(i) - 'A';
            translated += solfege[index] + " ";
        }
        return translated;
    }

    public static void analyzeNoteDistribution(String notes) {
        int midIndex = notes.length() / 2;
        String firstHalf = notes.substring(0, midIndex);
        String secondHalf = notes.substring(midIndex);

        int[][] noteDistributions = new int[2][NOTES.length()];
        noteDistributions[0] = countNotes(firstHalf);
        noteDistributions[1] = countNotes(secondHalf);

        System.out.println("Note distribution in first half vs second half:");
        for (int i = 0; i < NOTES.length(); i++) {
            char note = NOTES.charAt(i);
            System.out.println(note + ": First Half = " + noteDistributions[0][i] 
                               + ", Second Half = " + noteDistributions[1][i]);
        }
    }

    // cool idea but likely too difficult
    public static void analyzeNoteDistributionSplit(String notes, int numSections) {
        int sectionLength = notes.length() / numSections;
        int[][] noteDistributions = new int[numSections][NOTES.length()];
    
        for (int section = 0; section < numSections; section++) {
            int start = section * sectionLength;
            int end = start + sectionLength;
            if (section == numSections - 1) {
                end = notes.length();
            } 
            String subsection = notes.substring(start, end);
            int[] counts = countNotes(subsection);
            for (int i = 0; i < counts.length; i++) {
                noteDistributions[section][i] = counts[i];
            }
        }
    
        // Print the note distributions for each section
        System.out.println("Note distribution across " + numSections + " sections:");
        for (int noteIndex = 0; noteIndex < NOTES.length(); noteIndex++) {
            System.out.print(NOTES.charAt(noteIndex) + ": ");
            for (int section = 0; section < numSections; section++) {
                System.out.print("Section " + (section + 1) + " = " + noteDistributions[section][noteIndex]);
                if (section < numSections - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
    
}
