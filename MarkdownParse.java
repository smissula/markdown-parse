/**
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.math.*;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        System.out.print(currentIndex + " ");
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            // first closing parenthesis 
            int closeParen = markdown.indexOf(")", openParen);
            // index of end of file
            int endOfFile = markdown.length();
            // index of end of line
            int endOfLine = markdown.indexOf("\n", openParen);

            // test breaker for images
            if (nextOpenBracket > 0 &&
                markdown.substring(nextOpenBracket-1, nextOpenBracket).equals("!")) {
                currentIndex = nextOpenBracket + 1;
                continue;
            }

            if (closeParen == -1) {
                if (endOfLine == -1) {
                    toReturn.add(markdown.substring(openParen+1, endOfFile));
                    currentIndex = endOfFile;
                    // System.out.println("1: New current idx: " + currentIndex);
                }
                else {
                    toReturn.add(markdown.substring(openParen+1, 
                                Math.min(endOfLine, endOfFile)));
                    currentIndex = Math.min(endOfLine, endOfFile) + 1;
                    // System.out.println("2: New current idx: " + currentIndex);
                }
            }
            else if (endOfLine == -1) {
                toReturn.add(markdown.substring(openParen+1, 
                            Math.min(closeParen, endOfFile)));
                currentIndex = Math.min(closeParen, endOfFile) + 1;
                // System.out.println("3: New current idx: " + currentIndex);
            }
            else {
                toReturn.add(markdown.substring(openParen+1, 
                        Math.min(endOfLine, Math.min(endOfFile, closeParen))));
                currentIndex = Math.min(endOfLine,
                    Math.min(endOfFile, closeParen)) + 1;
                // System.out.println("4: New current idx: " + currentIndex);
            }
            
            System.out.print(currentIndex + " ");
        }
        System.out.println();
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.math.*;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        System.out.print(currentIndex + " ");
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            // first closing parenthesis 
            int closeParen = markdown.indexOf(")", openParen);
            if (nextOpenBracket != 0 && markdown.substring(
                        nextOpenBracket -1, nextOpenBracket).equals("!")) {
                currentIndex = closeParen + 1;
            }
            
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
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}