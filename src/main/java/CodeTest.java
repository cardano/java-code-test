import java.util.function.Function;

public class CodeTest {
    public static void main(String[] args) {
        String[] input = {"hello", "wonderful looking", "world"};
        //Reverse Array Test
        String[] reversed = reverseArray(input);
        System.out.println("The Reverse Array should contain : " + reversed.length);
        for (int i = 0; i < reversed.length; i++) {
            System.out.println("[" + i + "] : " + reversed[i]);
        }

        //Upper Case Array Test
        String[] upperCased = uppercaseArray(input);
        for (int i = 0; i < upperCased.length; i++) {
            System.out.println("[" + i + "] : " + upperCased[i]);
        }

        //find word count
        for (String s : input) {
            System.out.println(s + " word count : " + findWordCount(s, "looking"));
        }


    }

    public static String[] reverseArray(String[] input) {
        // add code here
        String[] temp = new String[input.length];
        for (int i = input.length - 1, j = 0; i >= 0; i--, j++) {
            temp[j] = input[i];
        }
        input = temp;
        return input;
    }

    public static String[] uppercaseArray(String[] input) {
        // add code here
        for (int i = 0; i < input.length; i++) {
            input[i] = input[i].toUpperCase();
        }
        return input;
    }

    public static int findWordCount(String text, String wordToFind) {
        // add code here
        String[] temp = text.split(" ");
        int count = 0;
        if(temp.length > 1) {
            for (String s : temp) {
                count = s.equalsIgnoreCase(wordToFind) ? ++count : 0;
            }
        } else {
            count = text.toLowerCase().matches(wordToFind) ? 1 : 0;
        }

        return count;
    }

    public static Function<Integer, Integer> composeU(Function<Integer, Integer> f1, Function<Integer, Integer> f2) {
        // add code here
        return null;
    }

    public static void writeContentsToConsole() {
        // add code here
    }

    public static void handleInvalidArgument() {
        // add code here
    }

    public static void puzzle() {
        // add code here
    }
}
