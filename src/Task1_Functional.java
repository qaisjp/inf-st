import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import st.Parser;

public class Task1_Functional {
	private Parser parser;

	@Before
	public void setup() {
		parser = new Parser();
	}

	@Test
	public void shortInt() {
	}

	@Test
	@Ignore
	public void longInt() {

	}

	@Test
	@Ignore
	public void shortBool() {

	}

	@Test
	@Ignore
	public void longBool() {

	}

	@Test
	public void shortStrEq() {
		// Short, equals separated
		parser.add("output", "o", Parser.STRING);
		parser.parse("-o=output.txt");
		assertEquals("output.txt", parser.getString("o"));
	}

	@Test
	public void shortStrSp() {
		// Short, space separated
		parser.add("output", "o", Parser.STRING);
		parser.parse("-o output.txt");
		assertEquals("output.txt", parser.getString("o"));
	}

	@Test
	public void longStrEq() {
		// Long, equals separated
		parser.add("output", "o", Parser.STRING);
		parser.parse("--output=output.txt");
		assertEquals("output.txt", parser.getString("o"));
	}

	@Test
	public void longStrSp() {
		// Long, space separated
		parser.add("output", "o", Parser.STRING);
		parser.parse("--output output.txt");
		assertEquals("output.txt", parser.getString("o"));
	}

	@Test
	public void orderSearch() {
		parser.add("output", "o", Parser.STRING);
		parser.add("o", "f", Parser.STRING);
		parser.parse("--output output.txt --o yada");
		assertEquals("yada", parser.getString("o"));
	}

	@Test
	public void getLong() {
		parser.add("int", "i", Parser.INTEGER);
		parser.add("bool", "b", Parser.BOOLEAN);
		parser.add("str", "s", Parser.STRING);
		parser.add("char", "c", Parser.CHAR);

		parser.parse("-i 100 -b -s Hi -c q");

		assertEquals(100, parser.getInteger("int"));
		assertEquals(true, parser.getBoolean("bool"));
		assertEquals("Hi", parser.getString("str"));
		assertEquals('q', parser.getChar("char"));
	}

	@Test
	public void defaults() {
		parser.add("int", "i", Parser.INTEGER);
		parser.add("bool", "b", Parser.BOOLEAN);
		parser.add("str", "s", Parser.STRING);
		parser.add("char", "c", Parser.CHAR);
		parser.parse("");

		assertEquals(0, parser.getInteger("i"));
		assertEquals(false, parser.getBoolean("b"));
		assertEquals("", parser.getString("s"));
		assertEquals('\0', parser.getChar("c"));
	}
}
