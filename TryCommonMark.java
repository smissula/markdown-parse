import java.util.ArrayList;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

class WordCountVisitor extends AbstractVisitor {
    int wordCount = 0;

    @Override
    public void visit(Text text) {
        // This is called for all Text nodes. Override other visit methods for other node types.

        // Count words (this is just an example, don't actually do it this way for various reasons).
        wordCount += text.getLiteral().split("\\W+").length;

        // Descend into children (could be omitted in this case because Text nodes don't have children).
        visitChildren(text);

        // System.out.println(wordCount);
    }
}

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

class TryCommonMark {
    public static void main(String[] args) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse("This is *Sparta*");
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"

        Node node = parser.parse("Example\n=======\n\nSome more text");
        WordCountVisitor visitor = new WordCountVisitor();
        node.accept(visitor);
        System.out.println(visitor.wordCount);  // 4

        Node linkNode = parser.parse("[Hyperlink1](Heres_a_link) \n Something in between [Hyperlink2](Another_link)");
        LinkCountVisitor linkVisitor = new LinkCountVisitor();
        linkNode.accept(linkVisitor);
        System.out.println(linkVisitor.linkArray);  // 4        
    }
}