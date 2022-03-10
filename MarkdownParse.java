import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.math.*;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

class LinkCountVisitor extends AbstractVisitor {
    ArrayList<Link> linkArray = new ArrayList<>();

    @Override
    public void visit(Link link) {
        // linkCount += link.getLiteral().split("\\W+").length;

        linkArray.add(link);
        
        // Descend into children (could be omitted in this case because Text nodes don't have children).
        visitChildren(link);

        // System.out.println(wordCount);
    }
}

public class MarkdownParse {
    public static ArrayList<Link> getLinks(String markdown) {
        /**
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        // System.out.print(currentIndex + " ");
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);

            if (nextOpenBracket == -1 || nextCloseBracket == -1 ||
                openParen == -1) {
                break;
            }
            
            // first closing parenthesis 
            int closeParen = markdown.indexOf(")", openParen);
            int endOfLine = markdown.indexOf("\n", openParen);

            // only accepts link in same line
            if (endOfLine!=-1 && closeParen!=-1 && endOfLine <= closeParen) {
                currentIndex = endOfLine + 1;
                continue;
            }
            
            if (closeParen != -1 && nextCloseBracket + 1 != openParen) {
                currentIndex = closeParen + 1;
                continue;
            }
            if (nextOpenBracket != 0 && markdown.substring(
                        nextOpenBracket -1, nextOpenBracket).equals("!")) {
                currentIndex = closeParen + 1;
            }

            // This is where it was
            
            else {
                if (closeParen > -1) {
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                    currentIndex = closeParen + 1;
                }
                else {
                    currentIndex = markdown.indexOf("\n", openParen) + 1;
                }
                if (currentIndex == 0) {
                    break;
                }
            }
            
            // System.out.print(currentIndex + " ");
        }
        // System.out.println();
        return toReturn;
        */

        Parser parser = Parser.builder().build();
        Node linkNode = parser.parse(markdown);
        LinkCountVisitor linkVisitor = new LinkCountVisitor();
        linkNode.accept(linkVisitor);
        return linkVisitor.linkArray;
    }
    
    
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<Link> links = getLinks(contents);
        System.out.println(links);
    }
}