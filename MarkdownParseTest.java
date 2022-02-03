import static org.junit.Assert.*;
import java.nio.file.Files;
import java.nio.file.Path;
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
	    String contents = Files.readString(Path.of("https://github.com/smissula/markdown-parse/blob/main/test-file.md"));
        // ArrayList<String> links = MarkdownParse.getLinks(contents);
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testImageLink() throws IOException {
        String contents = Files.readString(Path.of("https://github.com/smissula/markdown-parse/blob/main/image-file.md"));
        List<String> expect = List.of("ThisIsANormalLink", "AlsoNormal!!!");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
}
