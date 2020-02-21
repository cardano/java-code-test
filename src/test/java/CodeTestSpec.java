import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/*
 *   Please code the tests in the format of reverseArray_returnsExpectedResult. This is an example of how we write our tests at Cardano.
 *
 *   Test 1-4 tests are easy as the function returns a result that can be asserted. Tests 5-7 are more difficult to assert as they are
 *   void, use your knowledge to write a meaningful test.
 *
 *   Feel free to use the internet to help you with you answers but make sure you understand the answer as we will ask you questions.
 */

public class CodeTestSpec {
    @Test
    public void reverseArray_returnsExpectedResult() {
        assertArrayEquals("expected null returned as null was the input",
                null, CodeTest.reverseArray(null));

        assertArrayEquals("expected empty array to be returned as empty array was input",
                new String[] {}, CodeTest.reverseArray(new String[] {}));

        assertArrayEquals("expected array to be same and all uppercase",
                new String[] {"x", "y", "z"}, CodeTest.reverseArray(new String[] {"z", "y", "x"}));
    }

    @Test
    public void uppercaseArray_returnsExpectedResult() {
        assertArrayEquals("expected null returned as null was the input",
                null, CodeTest.uppercaseArray(null));

        assertArrayEquals("expected empty array to be returned as empty array was input",
                new String[] {}, CodeTest.uppercaseArray(new String[] {}));

        assertArrayEquals("expected array to be same and all uppercase",
                new String[] {"A", "B", "C"}, CodeTest.uppercaseArray(new String[] {"a", "b", "c"}));
    }

    @Test
    public void findWordCount_returnsExpectedResult() {
        String sentence = "The quick brown fox jumps over the lazy dog";

        assertEquals("expected to find the word 'the' in the sentence twice", 2, CodeTest.findWordCount(sentence, "the"));
        assertEquals("expected to find the word 'brown' in the sentence once", 1, CodeTest.findWordCount(sentence, "brown"));
        assertEquals("don't expect to find the word 'cat' in the sentence", 0, CodeTest.findWordCount(sentence, "cat"));
    }

    @Test
    public void composeU_returnsExpectedResult() {
        int expected = 2;
        int actual = CodeTest.composeU(i -> i + 1, i -> i + 1).apply(0);

        assertEquals("expected both functions to be executed", expected, actual);
    }

    @Test
    public void writeContentsToConsole_returnsExpectedResult() throws IOException {
        PrintStream originalOutputStream = System.out;

        ByteArrayOutputStream consoleText = new ByteArrayOutputStream();
        try (PrintStream consoleTextOutputStream = new PrintStream(consoleText)) {
            System.setOut(consoleTextOutputStream);

            System.setProperty("filename", "src/test/resources/example.txt");
            CodeTest.writeContentsToConsole();
        } finally {
            System.setOut(originalOutputStream); // reset output stream
        }

        assertEquals("\nhello\n", consoleText.toString());
    }

    @Test
    public void handleInvalidArgument_returnsExpectedResult() {
        try {
            System.clearProperty("aProperty");
            CodeTest.handleInvalidArgument();
            fail("handleInvalidArgument completed successfully");
        } catch (IllegalArgumentException e) {
            assertEquals("missing property 'aProperty'", e.getMessage());
        }

        try {
            System.setProperty("aProperty", "a");
            CodeTest.handleInvalidArgument();
            fail("handleInvalidArgument completed successfully");
        } catch (IllegalArgumentException e) {
            assertEquals("invalid value for 'aProperty', expected 'aProperty': a", e.getMessage());
        }

        System.setProperty("aProperty", "aProperty");
        CodeTest.handleInvalidArgument();
    }

    @Test
//    @Test(expected = IllegalArgumentException.class) avoided this approach as we can't assert the error message
    public void puzzle_invalidInputThrowsError() {
        try {
            runPuzzle("");
            fail("puzzle completed successfully with invalid sequence");
        } catch (IllegalArgumentException e) {
            assertEquals("missing sequence of numbers", e.getMessage());
        }

        try {
            runPuzzle("a");
            fail("puzzle completed successfully with invalid sequence");
        } catch (IllegalArgumentException e) {
            assertEquals("invalid sequence item: a", e.getMessage());
        }

        try {
            runPuzzle("1,b");
            fail("puzzle completed successfully with invalid sequence");
        } catch (IllegalArgumentException e) {
            assertEquals("invalid sequence item: b", e.getMessage());
        }
    }

    @Test
    public void puzzle_returnsExpectedResult() {
        assertEquals("enter random sequence of numbers, e.g '1,3,9,0,2,2,4,2'...\n1,3,5,5,'Snap'\n", runPuzzle("1,3,5,5"));
        assertEquals("enter random sequence of numbers, e.g '1,3,9,0,2,2,4,2'...\n1,1,'Snap'\n", runPuzzle("1,1"));
        assertEquals("enter random sequence of numbers, e.g '1,3,9,0,2,2,4,2'...\n1,2,1\n", runPuzzle("1,2,1"));
        assertEquals("enter random sequence of numbers, e.g '1,3,9,0,2,2,4,2'...\n1,2,3,3,'Snap',4,4,'Snap',5,2\n", runPuzzle("1,2,3,3,4,4,5,2"));
    }


    private String runPuzzle(String input) {
        InputStream originalInputStream = System.in;
        PrintStream originalOutputStream = System.out;

        ByteArrayOutputStream consoleText = new ByteArrayOutputStream();
        try (PrintStream consoleTextOutputStream = new PrintStream(consoleText)) {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            System.setOut(consoleTextOutputStream);

            CodeTest.puzzle();
        } finally {
            System.setIn(originalInputStream); // reset input stream
            System.setOut(originalOutputStream); // reset output stream
        }
        return consoleText.toString();
    }
}
