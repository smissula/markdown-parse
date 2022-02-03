import static org.junit.Assert.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
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
}
