import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CodeTest {
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(reverseArray(new String[] {"a", "b", "c"})));
        System.out.println(Arrays.toString(uppercaseArray(new String[] {"a", "b", "c"})));
        System.out.println(findWordCount("The quick brown fox jumps over the lazy dog", "the"));
        System.out.println(composeU(i -> 1, i -> 2).apply(0));

        System.setProperty("filename", "src/test/resources/example.txt");
        writeContentsToConsole();
        try {
            handleInvalidArgument();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        puzzle();
    }

    /**
     * Create a function that accepts an array of numbers and returns a reversed array (e.g. [1,2,3] would be [3,2,1]
     *
     * @param input - string array to be reversed
     * @return input in reverse order
     */
    public static String[] reverseArray(String[] input) {
        if (input == null) return null;
        return IntStream.rangeClosed(1, input.length) // iterate over all strings
                .mapToObj(i -> input[input.length - i]) // get strings in reverse order
                .toArray(String[]::new); // collect strings into array
    }

    /**
     * Write a function that transforms an array of strings to an upper-case array of strings
     *
     * @param input - string array to be converted to uppercase
     * @return input as upper case
     */
    public static String[] uppercaseArray(String[] input) {
        if (input == null) return null;
        return Arrays.stream(input) // stream over strings
                .map(String::toUpperCase) // convert all strings to upper case
                .toArray(String[]::new); // collect strings into array
    }

    /**
     * Given a sentence create a function that returns the number of unique words (e.g. 'the cat jumped over the mat' would be [{'the': 2}, {'cat': 1}…]
     *
     * @param sentence - the sentence to search
     * @param word - the word to find
     * @return - number of times the word appears (case-insensitive)
     */
    public static int findWordCount(String sentence, String word) {
        if (sentence == null || word == null || word.trim().isEmpty()) return 0;
        String wordToFindLowerCase = word.toLowerCase();
        return Arrays.stream(sentence.toLowerCase().split(" ")) // split sentence using spaces
                .filter(wordToFindLowerCase::equals) // filter out matching words
                // .count(); // returns long instead of int
                .collect(Collectors.toList()) // collect to list
                .size(); // return number of matches
    }

    /**
     * Write a function 'composeu' that takes two unary functions and returns a unary function that calls them both. A unary function has a single argument and a single return value (e.g. f(d) { return d + 1}
     *
     * @param f1 - function 1
     * @param f2 - function 2
     * @return - combined function, will execute f1 and pass the result onto f2 for execution
     */
    public static Function<Integer,Integer> composeU(Function<Integer,Integer> f1, Function<Integer,Integer> f2){
//        return f1.compose(f2);
//        return i -> f2.apply(f1.apply(i));
        return f1.andThen(f2); // using the java 8 method to chain functions
    }

    /**
     * Write a function that reads from a file and prints the contents to the console
     */
    public static void writeContentsToConsole() throws IOException {
        String filename = System.getProperty("filename");
        if (filename == null || filename.trim().isEmpty()) { //
            throw new IllegalArgumentException("missing filename");
        }

        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException("file not found: " + filename);
        }

        Files.lines(file.toPath()).forEach(System.out::println);
    }

    /**
     * Give an example of how a function would handle an invalid argument
     */
    public static void handleInvalidArgument() {
        String aProperty = System.getProperty("aProperty"); // get the property
        if (aProperty == null || aProperty.trim().isEmpty()) { // check property exists
            throw new IllegalArgumentException("missing property 'aProperty'");
        } else if (!aProperty.equals("aProperty")) { // check property is valid
            throw new IllegalArgumentException("invalid value for 'aProperty', expected 'aProperty': " + aProperty);
        }
    }

    /**
     * Write a console application that accepts a random sequence of numbers and loops through looking for 2 equal, consecutive numbers. When found write 'Snap' to the console else print out the number (e.g. 1,3,5,5,'Snap').
     */
    public static void puzzle() {
        System.out.println("enter random sequence of numbers, e.g '1,3,9,0,2,2,4,2'...");
        String sequence;
        try {
            sequence = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            throw new RuntimeException("failed to read sequence from console", e);
        }

        if (sequence == null || sequence.trim().isEmpty()) {
            throw new IllegalArgumentException("missing sequence of numbers");
        }

        StringBuilder output = new StringBuilder();
        String lastItem = null;
        for (String currentItem : sequence.split(",")) {
            try {
                Integer.parseInt(currentItem);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("invalid sequence item: " + currentItem, e);
            }

            if (lastItem != null) {
                output.append(",");
            }

            output.append(currentItem);

            if (currentItem.equals(lastItem)) {
                output.append(",'Snap'");
            }

            lastItem = currentItem;
        }

        System.out.println(output.toString());
    }
}