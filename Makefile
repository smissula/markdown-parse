test: MarkdownParse.class MarkdownParseTest.class
CLASSPATH = lib/*:.

clean:
	rm *.class

MarkdownParseTest.class: MarkdownParseTest.java MarkdownParse.class
	javac -g -cp $(CLASSPATH) MarkdownParseTest.java
	java -cp $(CLASSPATH) org.junit.runner.JUnitCore MarkdownParseTest

MarkdownParse.class: MarkdownParse.java
	javac -g -cp $(CLASSPATH) MarkdownParse.java

TryCommonMark.class: TryCommonMark.java
	javac -g -cp $(CLASSPATH) TryCommonMark.java