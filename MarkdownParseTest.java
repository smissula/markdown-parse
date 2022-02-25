import static org.junit.Assert.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testGetLinks() throws IOException {
        String contents;
        try {
	        contents = Files.readString(Path.of("/home/yoda/Code/CSE 15L/markdown-parse/test-file.md"));
        }
        catch (NoSuchFileException e) {
            contents = Files.readString(Path.of("./test-file.md"));
        }
        // ArrayList<String> links = MarkdownParse.getLinks(contents);
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testImageLink() throws IOException {
        String contents;
        try {
	        contents = Files.readString(Path.of("/home/yoda/Code/CSE 15L/markdown-parse/image-test.md"));
        }
        catch (NoSuchFileException e) {
            contents = Files.readString(Path.of("./image-test.md"));
        }
        List<String> expect = List.of("ThisIsANormalLink", "AlsoNormal!!!");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test(expected = AssertionError.class)
    public void snippet1Test() throws IOException {
        String contents = "`[a link`](url.com)\n\n[another link](`google.com)`"
            + "\n\n[`cod[e`](google.com)\n\n[`code]`](ucsd.edu)";
        List<String> expect = List.of("`google.com", "google.com", "ucsd.edu");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test(expected = AssertionError.class)
    public void snippet2Test() throws IOException {
        String contents = "[a [nested link](a.com)](b.com)\n\n[a nested "
            + "parenthesized url](a.com(()))\n\n[some escaped \\[ brackets "
            + "\\]](example.com)";
        List<String> expect = List.of("a.com", "a.com(())", "example.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test(expected = AssertionError.class)
    public void snippet3Test() throws IOException {
        String contents = "[this title text is really long and takes up more "
            +"than\none line\n\nand has some line breaks](\n\thttps://www."
            +"twitter.com\n)\n\n[this title text is really long and takes up "
            +"more than\none line](\n\thttps://ucsd-cse15l-w22.github.io/\n)"
            +"\n\n\n[this link doesn't have a closing parenthesis](github.com"
            +"\n\nAnd there's still some more text after that.\n\n[this link "
            +"doesn't have a closing parenthesis for a while](https://cse.ucsd"
            +".edu/\n\n\n\n)\n\nAnd then there's more text";
        
        List<String> expect = List.of("https://ucsd-cse15l-w22.github.io/");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test(expected = AssertionError.class)
    public void randomTest() {
        assertEquals(List.of("space-in-url.com"), new ArrayList<String>());
    }
}
