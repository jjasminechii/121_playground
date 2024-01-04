package music_box;
import java.util.*;

public class FullMusic {

    public static final String NOTES = "ABCDEFG";
    public static final String[] SCALE = {
        "A", "A♯/B♭", "B", "C", "C♯/D♭", "D", "D♯/E♭", "E", "F", "F♯/G♭", "G", "G♯/A♭"
    };
    public static final int SCALE_LENGTH = SCALE.length;

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

        String[][] song = {{"C", "D", "E", "F"}, {"A", "B", "C", "F"}};
        int shift = 2; // Example shift value
        String[][] shiftedSong = fastForward(song, shift);

        for (String[] melody : shiftedSong) {
            System.out.println(Arrays.toString(melody));
        }
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

    public static void analyzeNoteDistributionSplit(String notes, int numSections) {
        int sectionLength = notes.length() / numSections;
        int[][] noteDistributions = new int[numSections][NOTES.length()];
    
        for (int section = 0; section < numSections; section++) {
            int[] indices = calculateSectionIndices(section, sectionLength, notes.length(), numSections);
            String subsection = notes.substring(indices[0], indices[1]);
            noteDistributions[section] = countNotes(subsection);
        }
    
        printNoteDistributions(noteDistributions, numSections);
    }
    
    // Method to calculate start and end indices of a section
    public static int[] calculateSectionIndices(int section, int sectionLength, int totalLength, int numSections) {
        int start = section * sectionLength;
        int end = start + sectionLength;
        if (section == numSections - 1) {
            end = totalLength;
        }
        return new int[]{start, end};
    }
    
    // Method to print note distributions
    public static void printNoteDistributions(int[][] distributions, int numSections) {
        System.out.println("Note distribution across " + numSections + " sections:");
        for (int noteIndex = 0; noteIndex < NOTES.length(); noteIndex++) {
            System.out.print(NOTES.charAt(noteIndex) + ": ");
            for (int section = 0; section < numSections; section++) {
                System.out.print("Section " + (section + 1) + " = " + distributions[section][noteIndex]);
                if (section < numSections - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    public static String[][] fastForward(String[][] song, int shift) {
        String[][] transposedSong = new String[song.length][song[0].length];
        for (int i = 0; i < song.length; i++) {
            transposedSong[i] = new String[song[i].length];
            for (int j = 0; j < song[i].length; j++) {
                transposedSong[i][j] = shiftNote(song[i][j], shift);
            }
        }
        return transposedSong;
    }

    public static String shiftNote(String note, int shift) {
        int noteIndex = getNoteIndex(note);
        int transposedIndex = (noteIndex + shift + SCALE_LENGTH) % SCALE_LENGTH;
        return SCALE[transposedIndex];
    }    

    public static int getNoteIndex(String note) {
        for (int i = 0; i < SCALE.length; i++) {
             // let's checks if the scale contains the note either as sharp or flat
            if (SCALE[i].contains(note)) {
                return i;
            }
        }
        return -1;
    }
    
    
}
