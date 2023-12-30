import java.util.*;
public class Music {

    public static void main(String[] args) {
        String randomSong = generateRandomSong(23, "ACG", "ACBDAA");
        System.out.println("Generated Song: " + randomSong);


        String song = "CDEFGAB"; // we can also ask students to do user input here?
        System.out.println("Original Notes: " + song);
        System.out.println("Translated to Solfege: " + translateToSolfege(song));
        System.out.println("Translated to Numbers: " + translateToNumbers(song));
    }

    // Generates a random song string.
    // length - length The length of the song.
    // preferredNotes - A string of preferred notes.
    // pattern - A pattern to be embedded in the song.
    // Returns a randomly generated song string.
    public static String generateRandomSong(int length, String preferredNotes, String pattern) {
        String song = "";
        Random rand = new Random();
        String allNotes = "ABCDEFG";
        int currentNote = 0;

        while (currentNote < length) {
            // Embedding a pattern?? i lowkey hate this pattern
            if (currentNote % pattern.length() == 0 && (currentNote + pattern.length() <= length)) {
                song += pattern;
                currentNote += pattern.length();
            } else {
                // decided if we choose from preferred notes or all notes!
                double randomValue = rand.nextInt();
                if (randomValue == 0) {
                    int randomIndex = rand.nextInt(preferredNotes.length());
                    song += preferredNotes.charAt(randomIndex);
                } else {
                    int randomIndex = rand.nextInt(allNotes.length());
                    song += allNotes.charAt(randomIndex);
                }
                currentNote++;
            }
        }

        return song;
    }

    // Translate notes to Solfege!
    public static String translateToSolfege(String notes) {
        String[] solfege = {"Do", "Re", "Mi", "Fa", "Sol", "La", "Si"};
        return translate(notes, solfege);
    }

    // Translate notes to nums!
    public static String translateToNumbers(String notes) {
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7"};
        return translate(notes, numbers);
    }

    // translation method
    public static String translate(String notes, String[] translationMap) {
        String translated = "";
        for (int i = 0; i < notes.length(); i++) {
            char note = notes.charAt(i);
            int index = note - 'A'; // working under assumption notes are A-G
            translated += translationMap[index] + " ";
        }
        return translated;
    }
}